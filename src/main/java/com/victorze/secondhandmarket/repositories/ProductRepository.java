package com.victorze.secondhandmarket.repositories;

import com.victorze.secondhandmarket.models.Product;
import com.victorze.secondhandmarket.models.Purchase;
import com.victorze.secondhandmarket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByOwner(User owner);

    List<Product> findByPurchase(Purchase purchase);

    List<Product> findByPurchaseIsNull();

    List<Product> findByNameContainsIgnoreCaseAndPurchaseIsNull(String name);

    List<Product> findByNameContainsIgnoreCaseAndOwner(String name, User owner);

}
