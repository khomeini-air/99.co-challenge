# Services

For simplicity, I created required microservices in one single project.
The microservices are:
* listing-service
* user-service
* web-service for public API

All those microservices are hosted in local Spring Cloud (Eureka) server.

For testing purposes, I used H2 Relational Database so that you can try to call the APIs e.g using Curl or Postman.

## Out Of Scope

Below are **assumed** to be excluded from the scope of this challenge:
* API authentication/authorization
* Unit Testing

## How To Run

At this moment, the application can only be run **manually** by running the main method of each microservices in the following order:
1. `DiscoveryApplication.main()`, this is to start the Eureka cloud server.
1. `ListingApplication.main()`, this is to start the Listing microservices.
1. `UserApplication.main()`, this is to start the User microservices.
1. `WebApplication.main()`, this is to start the Web microservices for public API.

Once done, you can see all the microservices via http://localhost:1111
All microservices API can be called using respective host/port as follows:
* Listing services: http://localhost:2222
* User services: http://localhost:4444
* Web Services: http://localhost:3333

