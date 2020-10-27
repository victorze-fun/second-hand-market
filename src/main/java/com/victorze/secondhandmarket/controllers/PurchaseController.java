package com.victorze.secondhandmarket.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.victorze.secondhandmarket.models.Product;
import com.victorze.secondhandmarket.models.Purchase;
import com.victorze.secondhandmarket.models.User;
import com.victorze.secondhandmarket.services.ProductService;
import com.victorze.secondhandmarket.services.PurchaseService;
import com.victorze.secondhandmarket.services.UserService;

@Controller
@RequestMapping("/app")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	private User user;
	
	@ModelAttribute("cart")
	public List<Product> productsCart() {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		return (content == null) ? null : productService.findAllById(content);
	}
	
	@ModelAttribute("total_cart")
	public BigDecimal totalCart() {
		List<Product> productsCart = productsCart();
		if (productsCart != null) {
			return productsCart.stream()
					.map(p -> p.getPrice())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		}
		return new BigDecimal(0);
	}
	
	@ModelAttribute("my_purchases")
	public List<Purchase> myPurchases() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findByEmail(email);
		return purchaseService.findByOwner(user);
	}
	
	@GetMapping("/cart")
	public String showCart(Model model) {
		return "app/purchase/cart";
	}
	
	@GetMapping("/cart/add/{id}")
	public String addCart(Model model, @PathVariable Long id) {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		if (content == null) {
			content = new ArrayList<>();
		}
		if (!content.contains(id)) {
			content.add(id);
		}
		session.setAttribute("cart", content);
		return "redirect:/app/cart";
	}
	
	@GetMapping("/cart/remove/{id}")
	public String removeOfCart(Model model, @PathVariable Long id) {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		if (content == null) {
			return "redirect:/public";
		}
		content.remove(id);
		if (content.isEmpty()) {
			session.removeAttribute("cart");
		} else {
			session.setAttribute("cart", content);
		}
		return "redirect:/app/cart";
	}
	
	@GetMapping("/cart/finalize")
	public String checkout() {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		if (content == null) {
			return "redirect:/public";
		}
		List<Product> products = productsCart();
		Purchase purchase = purchaseService.save(new Purchase(), user);
		
		products.forEach(product -> purchaseService.addProductPurchase(product, purchase));
		session.removeAttribute("cart");
		
		return "redirect:/app/purchase/invoice/" + purchase.getId();
	}
	
	@GetMapping("/purchase/invoice/{id}")
	public String invoice(Model model, @PathVariable Long id) {
		Purchase purchase =  purchaseService.findById(id);
		List<Product> products = productService.findByPurchase(purchase);
		model.addAttribute("products", products);
		model.addAttribute("purchase", purchase);
		BigDecimal totalPurchase =  products.stream()
										.map(p -> p.getPrice())
										.reduce(BigDecimal.ZERO, BigDecimal::add);
		model.addAttribute("total_purchase", totalPurchase);
		return "/app/purchase/invoice";
	}
	
	@GetMapping("/mypurchases")
	public String showMyPurchases(Model model) {
		return "/app/purchase/list";
	}

}
