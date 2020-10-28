package com.victorze.secondhandmarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorze.secondhandmarket.models.Product;
import com.victorze.secondhandmarket.models.Purchase;
import com.victorze.secondhandmarket.models.User;
import com.victorze.secondhandmarket.repositories.ProductRepository;
import com.victorze.secondhandmarket.upload.StorageService;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    StorageService storageService;

    public Product save(Product p) {
        return productRepository.save(p);
    }

    public void deleteById(long id) {
    	Product product = findById(id);
    	if (product != null) {
    		storageService.delete(product.getImage());
    	}
        productRepository.deleteById(id);
    }

    public void delete(Product product) {
    	if (!product.getImage().isEmpty()) {
    		storageService.delete(product.getImage());
    	}
        productRepository.delete(product);
    }

    public Product edit(Product p) {
        return productRepository.save(p);
    }

    public Product findById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findOfOwner(User u) {
        return productRepository.findByOwner(u);
    }

    public List<Product> findByPurchase(Purchase c) {
        return productRepository.findByPurchase(c);
    }

    public List<Product> searchUnsoldProducts() {
        return productRepository.findByPurchaseIsNull();
    }

    public List<Product> search(String query) {
        return productRepository.findByNameContainsIgnoreCaseAndPurchaseIsNull(query);
    }

    public List<Product> searchMyProducts(String query, User user) {
        return productRepository.findByNameContainsIgnoreCaseAndOwner(query, user);
    }

    public List<Product> findAllById(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

}
