package com.zahid.foodDelivery.controller;

import com.zahid.foodDelivery.authentication.UserAuthentication;
import com.zahid.foodDelivery.dto.FoodOrderHistoryDto;
import com.zahid.foodDelivery.model.FoodOrderHistory;
import com.zahid.foodDelivery.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;
    @Autowired
    private UserAuthentication userAuthentication;
    @PostMapping()
    public ResponseEntity<String> createOrder(@RequestParam String userEmail, @RequestParam String token, @RequestParam String foodName){
        if(userAuthentication.validateUser(userEmail,token)){
           if(foodOrderService.orderFood(foodName,userEmail)){
               return new ResponseEntity<>("Your Order is Placed", HttpStatus.CREATED);
           }
           return new ResponseEntity<>("Food Item Not Available",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Invalid User",HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/getAllOrders")
    public ResponseEntity<?> getOrderById(@RequestParam String userEmail, @RequestParam String token){
        if(userAuthentication.validateUser(userEmail,token)){

            return new ResponseEntity<>(foodOrderService.getHistoryOfOrders(userEmail),HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid User",HttpStatus.UNAUTHORIZED);
    }
}
