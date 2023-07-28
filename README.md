# FOOD DELIVERY API
![Version](https://img.shields.io/static/v1?label=java-version&message=%3E=11&color=blue) ![documentation](https://img.shields.io/static/v1?label=documentation&message=yes&color=green) ![maintanined?](https://img.shields.io/static/v1?label=maintained?&message=yes&color=green) ![License](https://img.shields.io/static/v1?label=license&message=ZAHID&color=orange)
* This repository contains the code for a food delivery platform API that allows users to order food from restaurants. The API is developed using the Spring Boot framework and utilizes MySQL (provided by the EC2 instance) as the database management system.
## 🏠  [Homepage](https://github.com/ZahidFarooqDar/mctProject.git)
## Prerequisities
* java >=11.0
* Spring Boot
* MySQL database
* Swagger
* AWS EC2 instance  

## Author

* 🙍‍♂️ Zahid Farooq Dar
  * LinkedIn: [@Zahid Farooq](https://www.linkedin.com/in/zahid-farooq-dar/)
  * Github: [@ZahidFarooqDar](https://github.com/ZahidFarooqDar)

## DATA FLOW
* The application is built using the SpringBoot framework and consists of four layers: DTO, model, service, and repository.
---
### MODEL
* This layer consists basically of class-level things, the various classes required for the project, and these classes consist of the attributes to be stored.
---
### CONTROLLER
* The controller layer handles the HTTP requests, translates the JSON parameter to object, authenticates the request and transfer it to the 
business (service) layer.
* The controller actually contains the different APIs that are used in the project.


### SERVICE
* The service layer here used is beautifully managed as it contains the respective business logic for the services used.
* Here we have AdminService, CustomerService, FoodOrderService, and UserService, which are managed to have well coded business logic.
  
---
### REPOSITORY 
* It is the repository layer where we extend JPA repository.
* Here we are having different interfaces of respective models and also inside, there is well coded methods as well.

### DATA BASE DESIGN
* The API uses a MySQL database to store its data. The database design includes the following tables
* Here we have used MySql instance created using aws ec2 instance.
---

## PROJECT SUMMARY
* The "Food Delivery Platform API" is a revolutionary solution that enables users to easily order food from various restaurants, providing a seamless and convenient food ordering experience. The project addresses the challenges faced by food enthusiasts when ordering from multiple restaurants by simplifying the process.

* Key features of the API include user authentication to ensure secure access, order management allowing users to order one food item at a time and view their order history, and admin capabilities that give administrators complete control over the food menu. Admins can add, update, or delete food items and track orders, gaining valuable insights into each order and the food items ordered.

* The project was meticulously executed, with robust controller services and repositories developed to provide a user-friendly interface. Annotation-based validation ensures data accuracy and integrity. As a result, the API has led to increased user satisfaction and efficient order management.
---
## Links of the project
### [@SWAGGER](http://3.110.217.189:8080/swagger-ui/index.html#/)
## 📝 License

 Copyright @ 2019 [@ZahidFarooqDar](https://github.com/ZahidFarooqDar)


# 

This README was generated with ❤️ by [@ZahidFarooqDar](https://github.com/ZahidFarooqDar)

