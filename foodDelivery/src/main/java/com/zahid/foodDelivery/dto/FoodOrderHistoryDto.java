package com.zahid.foodDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodOrderHistoryDto {
    private Long foodItemID;
    private String foodItemName;
    private Integer userID;
    private String userName;
    private Date date;
}
