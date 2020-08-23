FROM java:8-jdk-alpine

COPY ./target/CarListingPlatform-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch TaskManagementSystem-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","CarListingPlatform-0.0.1-SNAPSHOT.jar"]
