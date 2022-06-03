# payMyBuddy
Application de transfert d’argent

## Requirements

For building and running the application you need:

- [JDK 11](https://openjdk.java.net/projects/jdk/11/)
- [Maven 3](https://maven.apache.org)
- [Postgresql 13](https://www.postgresql.org/download/)

## How to Run 

* Clone this repository 
* Make sure you are using JDK 11 and Maven 3.x
* Run postgresql server
* Create 2 datebases with Postgresql:
    - Application database
    - Tests database
* Add the following variables to your environnement variables:
    - PAYMYBUDDY_DATABASE_URL: the url to application database
    - {PAYMYBUDDY_DATABASE_TEST_URL: the url to the test database
    - {PAYMYBUDDY_DATABASE_USER: database username
    - {PAYMYBUDDY_DATABASE_PASSWORD: database password

* To create the database tables automatically with hibernate change the spring.jpa.hibernate.ddl-auto property to "create-drop", once the application started change it back to "update"

* To create the datebase tables manually execute the script `src\main\resources\data_base.sql` in your database 

* There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `src\main\java\com\salimahafirassou\paymybuddy\PayMyBuddyApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
* Check the stdout to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2022-06-04 00:25:15.707  INFO 15148 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-06-04 00:25:15.725  INFO 15148 --- [  restartedMain] c.s.paymybuddy.PayMyBuddyApplication     : Started PayMyBuddyApplication in 6.266 seconds (JVM running for 7.199)
```

The application is running on **http://localhost:8080**

if you want to change the port of the application, modify the server.port in application.properties file
