# Getting Started
 This is a backend sample project ready for SnappPay, which uses Docker and Git as thirdParty.  

### Requirements
The fully fledged server uses the following:
- Spring Framework
- SpringBoot
### Dependencies
There are numbers of third-party dependencies used in the project. 
Browse the Maven pom.xml file for details of libraries and versions used.
- java 1.17 or higher
- docker desktop
- Maven 3.1.1 or higher
- Git
### Guides
The following guides illustrate how to use some features project:

* [ Clone the project ]
* [ Run below commands on the root of project in order ]
* [ $ for first time docker build -t my-mysql . ]
* [ $ for first time docker run -d -p 3356:3306 --name my-mysql-container my-mysql  ]
* [ check container to be up with "$docker ps" or "docker ps -a" ]
* [ for second time and more just run this command "$docker start <containerId> " you can find container id from "$docker ps -a" ]
* [ run this command "$mvn clean package" This will build the application and generate a JAR file in the ]
* [ to examine dataBase "$docker exec -it <container_id> bash" this make bash after that "$mysql -u <username> -p<password> -h localhost <database_name>" ]
* [ run this command "$java -jar target/spiliter-0.0.1-SNAPSHOT.jar This will start the application and run on port::8098 which define in application.properties]
* [ or run this command "$mvn spring-boot:run" ]
* [ you can find Swagger doc in http://localhost:8098/swagger-ui/index.html ]
* [ or run this command "$mvn spring-boot:run" ]

### How to work this app
Firstly call /register method and add user 
then /login with this user and get jwtToken put this token in header of all noAuth endpoint
-------------
to work with this app follow this step :
1.add users
2.add Group (gang)
3.add bill 
4.report bill or pay



### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.13/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.13/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.13/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.13/reference/htmlsingle/#data.sql.jpa-and-spring-data)
