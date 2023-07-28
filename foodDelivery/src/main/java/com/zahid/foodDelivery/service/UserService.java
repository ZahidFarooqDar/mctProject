package com.zahid.foodDelivery.service;

import com.zahid.foodDelivery.dto.SignInOutput;
import com.zahid.foodDelivery.dto.UserDto;
import com.zahid.foodDelivery.model.AuthenticationToken;
import com.zahid.foodDelivery.model.User;
import com.zahid.foodDelivery.repository.TokenRepository;
import com.zahid.foodDelivery.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.Cookie;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    TokenRepository tokenRepository;

    public SignInOutput signIn(UserDto userDto, HttpServletResponse httpServletResponse) {
        User user = userRepo.findByEmail(userDto.getEmail()).orElse(null);
        if(user == null)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }
        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(userDto.getPassword());
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Encryption Invalid!!!");
        }
        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }
        List<AuthenticationToken> users = tokenRepository.findByUser(user);
        users.stream().forEach(item->{
            item.setExpiredToken(true);
        });
        AuthenticationToken token = new AuthenticationToken(user);
        users.add(token);
        tokenRepository.save(token);
        return new SignInOutput("Authentication Successfull !!!", token.getToken());
    }
    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;

    }
}
