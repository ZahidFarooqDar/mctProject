package com.zahid.foodDelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FoodOrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodHistoryId;
    @ManyToOne
    @JoinColumn(name = "foodItemId")
    private FoodItem foodItem;
    private Date dateOfOrder;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public FoodOrderHistory(FoodItem foodItem, Date dateOfOrder, User user) {
        this.foodItem = foodItem;
        this.dateOfOrder = dateOfOrder;
        this.user = user;
    }
}
