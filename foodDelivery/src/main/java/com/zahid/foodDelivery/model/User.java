package com.zahid.foodDelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;


    @NotEmpty(message = "UserName is Empty!!!")
    @Size(min=3,max=20, message = "User name cannot be less than 3 letters or greater than 20 letters")
    private String name;

    @Email
    private String email;

    @NotNull
    private String password;

    //@NotNull
    private String role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<AuthenticationToken> authenticationTokens;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<FoodOrderHistory> foodItem;
    public User(String name, String email, @NotNull String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
