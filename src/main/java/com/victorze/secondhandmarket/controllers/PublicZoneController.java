package com.victorze.secondhandmarket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.victorze.secondhandmarket.models.Product;
import com.victorze.secondhandmarket.services.ProductService;

@Controller
@RequestMapping("/public")
public class PublicZoneController {
	
	@Autowired
	ProductService productService;
	
	@ModelAttribute("products")
	public List<Product> unsoldProducts() {
		return productService.searchUnsoldProducts();
	}
	
	@GetMapping({"/", "/index"})
	public String index(Model model, @RequestParam(name="q", required=false) String query) {
		if (query != null) {
			model.addAttribute("products", productService.search(query));
		}
		return "index";
	}
	
	@GetMapping("/product/{id}")
	public String showProduct(Model model, @PathVariable Long id) {
		Product product = productService.findById(id);
		
		if (product != null) {
			model.addAttribute("product", product);
		}
		return "redirect:/public/";
	}

}
