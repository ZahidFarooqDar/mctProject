package com.zahid.foodDelivery.controller;
import com.zahid.foodDelivery.authentication.UserAuthentication;
import com.zahid.foodDelivery.dto.FoodItemDto;
import com.zahid.foodDelivery.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food-items")
public class FoodItemController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserAuthentication userAuthentication;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody FoodItemDto foodItemDto,
                                 @RequestParam String email,
                                 @RequestParam String token
    )  {
        if (!userAuthentication.validateUser(email, token)){
            return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
        }
       if(adminService.createFoodItem(foodItemDto,email)){
           return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);
       }
        return new ResponseEntity<>("Food Item Not Created", HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody FoodItemDto foodItemDto,
                                         @RequestParam String email,
                                         @RequestParam String token,
                                         @RequestParam Long id )
    {
        if (!userAuthentication.validateUser(email, token)){
            return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
        }
        if(adminService.updateFoodItem(id,email,foodItemDto)){
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Food Item Not Updated", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String email,
                                         @RequestParam String token,
                                         @RequestParam Long id )
    {
        if (!userAuthentication.validateUser(email, token)){
            return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
        }
        if(adminService.deleteFoodItem(id,email)){
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Some Error Occurred", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(@RequestParam String email,
                                         @RequestParam String token)
    {
        if (!userAuthentication.validateUser(email, token)){
            return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(adminService.getAllFoodItems(email), HttpStatus.OK);
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getById(@RequestParam String email,
                                    @RequestParam String token,
                                     @PathVariable long id)
    {
        if (!userAuthentication.validateUser(email, token)){
            return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(adminService.getFoodItemByID(id,email), HttpStatus.OK);
    }

   }
