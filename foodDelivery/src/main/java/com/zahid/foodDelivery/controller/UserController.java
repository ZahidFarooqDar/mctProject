package com.zahid.foodDelivery.controller;

import com.zahid.foodDelivery.dto.SignInOutput;
import com.zahid.foodDelivery.dto.UserDto;
import com.zahid.foodDelivery.service.AdminService;
import com.zahid.foodDelivery.service.CustomerService;
import com.zahid.foodDelivery.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    CustomerService customerService;
    @PostMapping("/signin")

    public SignInOutput signIn(@Valid @RequestBody UserDto userDto, HttpServletResponse httpServletResponse){
        return userService.signIn(userDto,httpServletResponse);
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<String> signUpAdmin(@Valid @RequestBody UserDto userDto){
        if(adminService.createAdmin(userDto)){
            return new ResponseEntity<>("Admin Sign Up Successful", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Admin Sign Up Unsuccessful", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signUpCustomer(@Valid @RequestBody UserDto userDto){
        if(customerService.createCustomer(userDto)){
            return new ResponseEntity<>("Account Sign Up Successful", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Account Sign Up Unsuccessful", HttpStatus.BAD_REQUEST);
    }


}
