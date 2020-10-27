package com.victorze.secondhandmarket.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class Product {

    @Id @GeneratedValue
    private Long id;

    @NotBlank(message="El nombre es obligatorio")
    private String name;

    @Digits(integer=8, fraction=2)
    private BigDecimal price;

    @NotBlank(message="La imagen es obligatoria")
    private String image;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Purchase purchase;

    public Product(String name, BigDecimal price, String image, User owner) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.owner = owner;
    }
}
