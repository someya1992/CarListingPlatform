drop table car_listing;

CREATE TABLE IF NOT EXISTS car_listing (
    id INT AUTO_INCREMENT ,
    code VARCHAR(255) NOT NULL,
    make VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    kW INT NOT NULL,
    year INT,
    color VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    dealer_Id INT
)  ;


drop table dealer_listing;

CREATE TABLE IF NOT EXISTS dealer_listing (
    id INT AUTO_INCREMENT,
    dealer_Id INT
)  ;
