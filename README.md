# README #

Boat app eval project

## Boat backend ##

Springboot app that expose a CRUD controller to manage boat

### Requirement ###

* Docker up and running
* Java 11+

### How do I get set up? ###

1. go to ./boat-app-back
2. Build the project `mvn install`
3. Start the db with run `docker-compose up -d`
4. Start the project
    1. With maven `mvn spring-boot:run`
    2. With java `java -jar target/boat-app-back-0.0.1-SNAPSHOT.jar`

The app will start with the default spring profile is `local` on port 8080

## Boat front ##

Angular app to manage boat

### Requirement ###

* NodeJs
* Angular CLI
* Boat-app-back up and running

### How do I get set up? ###

1. go to ./boat-front
2. Build the project `npm install`
3. Run the app `npm start`

The app will start 4200

Default authentication :

* username : user
* password : password
