package com.zahid.foodDelivery.service;

import com.zahid.foodDelivery.dto.FoodOrderHistoryDto;
import com.zahid.foodDelivery.model.FoodItem;
import com.zahid.foodDelivery.model.FoodOrderHistory;
import com.zahid.foodDelivery.model.User;
import com.zahid.foodDelivery.repository.FoodItemRepository;
import com.zahid.foodDelivery.repository.FoodOrderRepository;
import com.zahid.foodDelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodOrderService {
    @Autowired
    FoodOrderRepository foodOrderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodItemRepository foodItemRepository;

    public boolean orderFood(String foodName, String email){
        User user = userRepository.findByEmail(email).orElse(null);
        if(user == null || user.getRole().equals("ADMIN")) {
            return false;
        }
        FoodItem foodItem = foodItemRepository.findByFoodName(foodName).orElse(null);
        if(foodItem == null){
            return false;
        }
        FoodOrderHistory foodOrderHistory = new FoodOrderHistory(foodItem,new Date(),user);
        foodOrderRepository.save(foodOrderHistory);
        return true;
    }
    public List<FoodOrderHistoryDto> getHistoryOfOrders(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || user.getRole().equals("ADMIN")) {
            return null;
        }
        return foodOrderRepository.findByUser(user).stream().map(i->{
            return new FoodOrderHistoryDto(i.getFoodItem().getFoodItemId(),i.getFoodItem().getFoodName(),
                    i.getUser().getUserId(),i.getUser().getName(),i.getDateOfOrder());
        }).collect(Collectors.toList());
    }
}
