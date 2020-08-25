### Car Listing Platform

Problem Statement: Design and implement a platform to upload car listing from different dealers and search the car listing

### Problem faced :-

- Dealer-id must be part of /vehicle_listings/ endpoint as well, so that car with same code from different listing could be 
  identified by dealer
  
- since same car listing can belong to different dealer so car listing must be mapped to dealer id

- No unique fields to specified to identify if the car listing already exist

- Dealers must be verified before uploading the listing to check if dealer exist in the platform

- Validations on the listing data is missing/ what are the mandatory attributes , so that incomplete data is not accepted in the system

- the json format to send the list (wrapped the list around the object)

- excel file has missing data


### Decisions made regarding architecture

- added dealer-id to each point so that listing can be identified by dealer-id

- mapped car listing with dealer-id

- fetch car listing by code and dealerId  

('better solution that could be implemented' : to make code and dealer Id as unique key and saveOrupdare data this would save database calls and could use batch processing )

- if dealer does not exist then add dealers

- added exceptions if data is incorrect or missing


### Ideas that can be implemented

- compare the car with same code with different dealers

- enable customers to compare dealers based on their ratings and also provide rating after the deal

- enable customer to bid on the price of the car to different dealers

- sanitize the data and make the fields unique and required
 


### Dependencies

 1.'com.h2database'- Embedded database 
 
 2.'spring-boot-starter-data-jpa' - jpa dependency
 
 3.'org.projectlombok' - to remove boilerplate code
 
 4. 'org.apache.commons' - to parse CSV file


### APIs

 1-- `/upload_csv/{dealerId}`


*Request* :

 ```
	CSV file with car listing data

```
*Response*

```
	Status :200

```

 2-- `/vehicleListings/{dealerId}`


*Request* :

 ```
{
    "carListingList": [
        {
            "code": "a",
            "make": "renault",
            "model": "megane",
            "kW": 132,
            "year": 0,
            "color": "red",
            "price": 13990.0,
            "kw": 132
        }
    ]
}

```
*Response*

```
 Status :ok

```

3-- `/search?make=?&model=?&color=?&year=?`
 
 *Response* :

 ```
{
    "carListingList": [
        {
            "code": "a",
            "make": "renault",
            "model": "megane",
            "kW": 132,
            "year": 0,
            "color": "red",
            "price": 13990.0,
            "kw": 132
        }
    ]
}
```


#### Building and running the project

 1. `mvn clean` and `mvn package`
 
 2.  In the target folder executable jar is created, Run as
     `java -jar <jar_name>.jar`
  
 3. In Postman or any other REST client hit the APIs mentioned above :- http://localhost:8083/{request}
 
 4. To run the application in docker :-
 
		```docker build -t boot-CarListingPlatform . ´´´
		```docker run -p 8080:8080 -t boot-CarListingPlatform ´´´
		
 5. Unit test and Integration test cases are present in src/test/java



