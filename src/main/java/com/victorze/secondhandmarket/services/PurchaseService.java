package com.victorze.secondhandmarket.services;

import com.victorze.secondhandmarket.models.Product;
import com.victorze.secondhandmarket.models.Purchase;
import com.victorze.secondhandmarket.models.User;
import com.victorze.secondhandmarket.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ProductService productService;

    public Purchase save(Purchase purchase, User user) {
        purchase.setOwner(user);
        return purchaseRepository.save(purchase);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public Product addProductPurchase(Product product, Purchase purchase) {
        product.setPurchase(purchase);
        return productService.edit(product);
    }

    public Purchase findById(long id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public List<Purchase> findByOwner(User user) {
        return purchaseRepository.findByOwner(user);
    }

}
