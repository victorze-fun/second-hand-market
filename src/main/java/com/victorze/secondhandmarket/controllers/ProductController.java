package com.victorze.secondhandmarket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.victorze.secondhandmarket.models.Product;
import com.victorze.secondhandmarket.models.User;
import com.victorze.secondhandmarket.services.ProductService;
import com.victorze.secondhandmarket.services.UserService;

@Controller
@RequestMapping("/app")
public class ProductController {
	
	 @Autowired
	 ProductService productService;
	 
	 @Autowired
	 UserService userService;
	 
	 private User user;
	 
	 @ModelAttribute("myproducts")
	 public List<Product> myProducts() {
		 String email = SecurityContextHolder.getContext().getAuthentication().getName();
		 user = userService.findByEmail(email);
		 return productService.findOfOwner(user);
	 }
	 
	@GetMapping("/myproducts")
	public String index(Model model, @RequestParam(name="q", required=false) String query) {
		if (query != null) {
			model.addAttribute("myproducts", productService.searchMyProducts(query, user));
		}
		return "app/product/list";
	} 

	@GetMapping("/myproducts/{id}/delete")
	public String delete(@PathVariable Long id) {
		Product product = productService.findById(id);
		if (product.getPurchase() == null) {
			productService.delete(product);
		}
		return "redirect:/app/myproducts";
	}

	@GetMapping("/product/new")
	public String newProductForm(Model model) {
		Product product = new Product();
		System.out.println(product);
		model.addAttribute("product", product);
		return "app/product/form";
	}
	
	@PostMapping("/product/new/submit")
	public String newProductSubmit(@ModelAttribute Product product){
		product.setOwner(user);
		productService.save(product);
		return "redirect:/app/myproducts";
	}
	
}
