package com.zahid.foodDelivery.service;

import com.zahid.foodDelivery.dto.FoodItemDto;
import com.zahid.foodDelivery.dto.FoodOrderHistoryDto;
import com.zahid.foodDelivery.dto.UserDto;
import com.zahid.foodDelivery.model.FoodItem;
import com.zahid.foodDelivery.model.User;
import com.zahid.foodDelivery.repository.FoodItemRepository;
import com.zahid.foodDelivery.repository.FoodOrderRepository;
import com.zahid.foodDelivery.repository.UserRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class AdminService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    FoodItemRepository foodItemRepository;
    @Autowired
    FoodOrderRepository foodOrderRepository;

    public boolean createAdmin(UserDto userDto){
        User user = userRepo.findByEmail(userDto.getEmail()).orElse(null);

        if(user != null)
        {
            throw new IllegalStateException("Admin already exists!!!!...sign in instead");
        }

//      encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(userDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userDto.setPassword(encryptedPassword);
        User savedUser =  userRepo.save(new User(userDto.getName(),userDto.getEmail(),userDto.getPassword(), "ADMIN"));
        return true;
    }
    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;
    }
    public boolean createFoodItem(FoodItemDto foodItemDto, String email){
        User user = userRepo.findByEmail(email).orElse(null);
        if(user == null || user.getRole().equals("CUSTOMER")){
            return false;
        }
        FoodItem foodItem = foodItemRepository.findByFoodName(foodItemDto.getFoodName()).orElse(null);
        if(foodItem == null) {
            foodItem = new FoodItem();
            foodItem.setFoodName(foodItemDto.getFoodName());
            foodItem.setPrice(foodItemDto.getPrice());
            foodItem.setFoodOrderHistory(new ArrayList<>());
            foodItemRepository.save(foodItem);
            return true;
        }
        return false;
    }

    public boolean updateFoodItem(Long id, String email,FoodItemDto foodItemDto){
        User user = userRepo.findByEmail(email).orElse(null);
        if(user == null || user.getRole().equals("CUSTOMER")) {
            return false;
        }
        FoodItem foodItem = foodItemRepository.findById(id).orElse(null);
        if(foodItem==null){
            return false;
        }
        foodItem.setFoodName(foodItemDto.getFoodName());
        foodItem.setPrice(foodItemDto.getPrice());
        foodItemRepository.save(foodItem);
        return true;
    }
    public boolean deleteFoodItem(Long id, String email){
        User user = userRepo.findByEmail(email).orElse(null);
        if(user == null || user.getRole().equals("CUSTOMER")) {
            return false;
        }
        FoodItem foodItem = foodItemRepository.findById(id).orElse(null);
        if(foodItem==null){
            return false;
        }
        foodItemRepository.delete(foodItem);
        return true;
    }
    public List<FoodItemDto> getAllFoodItems(String email){
        User user = userRepo.findByEmail(email).orElse(null);
        if(user == null || user.getRole().equals("CUSTOMER")) {
            return null;
        }
        return foodItemRepository.findAll().stream().map(i ->{
            return new FoodItemDto(i.getFoodItemId(),i.getFoodName(),i.getPrice());
        }).collect(Collectors.toList());
    }
    public FoodItemDto getFoodItemByID(long id, String email){
        User user = userRepo.findByEmail(email).orElse(null);
        if(user == null || user.getRole().equals("CUSTOMER")) {
            return null;
        }
        FoodItem foodItem = foodItemRepository.findById(id).orElse(null);
        return new FoodItemDto(foodItem.getFoodItemId(), foodItem.getFoodName(), foodItem.getPrice());
    }
    public List<FoodOrderHistoryDto> getHistoryOfUserOrders(Long userId, String email){
        User user = userRepo.findByEmail(email).orElse(null);
        if(user == null || user.getRole().equals("CUSTOMER")) {
            return null;
        }
        User customer = userRepo.findById(userId).orElse(null);
        if(customer == null){
            return null;
        }
        return foodOrderRepository.findByUser(customer).stream().map(i->{
            return new FoodOrderHistoryDto(i.getFoodItem().getFoodItemId(),i.getFoodItem().getFoodName(),
                    i.getUser().getUserId(),i.getUser().getName(),i.getDateOfOrder());
        }).collect(Collectors.toList());
    }
}
