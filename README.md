### Car Listing Platform

Problem Statement: Design and implement a platform to upload car listing from different dealers and search the car listing

### Problem faced :-

- Dealer-id must be part of /vehicle_listings/ endpoint as well, so that car with same code from different listing could be 
  identified by dealer

- Dealers must be verified before uploading the listing to check if dealer exist in the platform

- Validations on the listing data is missing/ what are the mandatory attributes , so that incomplete data is not accepted in the system


### Decisions made

- added dealer-id to each point so that listing can be identified by dealer-id

- added validations to the listings and return the discard data to dealer so that dealers can have a look and send complete data


### Ideas that can be implemented

- compare the car with same code with different dealers

- enable customers to compare dealers based on their ratings
 

### Assumptions:-

- 

### Dependencies

 1.'com.h2database'- Embedded database 
 
 2.'spring-boot-starter-data-jpa' - jpa dependency
 
 3.'org.projectlombok' - to remove boilerplate code


### APIs

 1. `/`


*Request* :

 ```

```
*Response*

```

```

#### Building and running the project

 1. `mvn clean` and `mvn package -Dmaven.test.skip=true`
 
 2.  In the target folder executable jar is created, Run as
     `java -jar <jar_name>.jar`
  
 3. In Postman or any other REST client hit the APIs mentioned above :- http://localhost:8080/{request}

### Additional Comments



