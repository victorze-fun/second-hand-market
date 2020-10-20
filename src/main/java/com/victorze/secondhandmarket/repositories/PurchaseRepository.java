package com.victorze.secondhandmarket.repositories;

import com.victorze.secondhandmarket.models.Purchase;
import com.victorze.secondhandmarket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByOwner(User owner);

}
