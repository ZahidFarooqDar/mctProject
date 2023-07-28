package com.zahid.foodDelivery.service;

import com.zahid.foodDelivery.dto.UserDto;
import com.zahid.foodDelivery.model.User;
import com.zahid.foodDelivery.repository.UserRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class CustomerService {
    @Autowired
    UserRepository userRepo;


    //Sign up customer
    public boolean createCustomer(UserDto userDto){
        User user = userRepo.findByEmail(userDto.getEmail()).orElse(null);

        if(user != null)
        {
            throw new IllegalStateException("User already exists!!!!...sign in instead");
        }

//      encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(userDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userDto.setPassword(encryptedPassword);
        User savedUser =  userRepo.save(new User(userDto.getName(),userDto.getEmail(),userDto.getPassword(), "CUSTOMER"));
        return true;
    }
    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;

    }

}
