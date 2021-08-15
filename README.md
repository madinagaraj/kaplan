# kaplan
Kaplan Assignemnt

Steps to run the Project:

1) Clone the project from git hub
2) Navigate to root folder(folder where pom.xml is located)
3) Launch command prompt from the folder
4) execute "mvn spring-boot:run"

Alternate way:

Import the project as Maven project and execute.

The Swagger documentation for the Project once sucessfully booted is available at 
http://localhost:8087/swagger-ui/index.html

Breif Architecture:

RestAPI with Spring Boot, HIbernate and a basic H2 db is used.
Assignment is the entity name with all the attributes. 
tags is used as List object. I can explain more over a call in a better way. :)

More Information:

1. How efficient is your code? What are some ways that you could improve performance?
   The code at the moment is not very efficent. LIsting down the things that could be implemented to improve performance and overall reliability
   
   1) I am using basic Error Handling and Reporting services from framework. We can implement a lot of custom messages to create better responses.
   2) Its a very basic tag search mechanism. This will lead to a lot of duplication of tags and data. We can easily implement elsatic search and other mechanism
   3) We can think of creating a Tagging and Tag Search as a seperate service which can be scaled independently
   
2. Suppose we expect this API to be hit 1000s of times a second. How can we handle the load?
   I could think of couple diiferent ways to achieve this
   1) We can use Auto Scaling Cloud services such a AWS Lamda or Azure functions if we dont have budget constarints. Its very less maintaince and easy to implement
   2) We can create a more than one servers and comnnect them through automatic service discovery tool Netflix-Eureka. We can maintain a load balancer to distribute the load. There are lot of moving pieces, so maintaince is liitle more.
   3) Basic horizontal scaling using normal cloud services.
      
3. What if the 3rd party provider is not available? How resilient is our API?
 I could think of implementing a circuit breaker microserice pattern here. 

4. What if we have a database of users and we wanted to make our API smarter by defaulting comparisons to always include the population of the current user's country. How could we accomplish this?
   One idea is to have a Serializable object with Maps to preload the data during the App Booting phase and autowire it. We can deserialize/serialize it based on the needs. But I am still little confused on the question. May be we can discuss if we get a chance.
   
5. What if we wanted to keep a tally of the most frequently requested countries and have this be available to consumers. How could we accomplish this?
   Soory. I think I am confused with the question here as well.
