package com.zahid.foodDelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food_items")
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodItemId;
    @NotEmpty
    private String foodName;
    @NotNull
    private Double price;

    @OneToMany
    private List<FoodOrderHistory> foodOrderHistory;

    public FoodItem(String foodName, Double price) {
        this.foodName = foodName;
        this.price = price;
    }
}
