# Getting Started
 this is a backend sample project ready for SnappPay which use docker and git as thirdParty.  

### Requirements
The fully fledged server uses the following:
- Spring Framework
- SpringBoot
### Dependencies
There are a number of third-party dependencies used in the project. 
Browse the Maven pom.xml file for details of libraries and versions used.
### Build Needs
- java 1.17 or higher
- docker desktop
- Maven 3.1.1 or higher
- Git
### Guides
The following guides illustrate how to use some features project:

* [ Clone the project ]
* [ Run below commands on the root of project in order ]
* [ $ docker build -t my-mysql . ]
* [ $ docker run -d -p 3356:3306 --name my-mysql-container my-mysql  ]
* [ check container to be up with "$docker ps" ]
* [ run this command "$mvn clean package" This will build the application and generate a JAR file in the ]
* [ run this command "$java -jar target/spiliter-0.0.1-SNAPSHOT.jar This will start the application and run on port::8098 which define in application.properties]
* [ or run this command "$mvn spring-boot:run" ]



### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.13/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.13/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.13/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.13/reference/htmlsingle/#data.sql.jpa-and-spring-data)
