package com.zahid.foodDelivery.repository;

import com.zahid.foodDelivery.model.FoodOrderHistory;
import com.zahid.foodDelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodOrderRepository extends JpaRepository<FoodOrderHistory,Long> {

    List<FoodOrderHistory> findByUser(User user);
}
