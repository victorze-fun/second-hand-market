package com.victorze.secondhandmarket.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@Entity
public class Product {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private float price;

    private String image;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Purchase purchase;

    public Product(String name, float price, String image, User owner) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.owner = owner;
    }
}
