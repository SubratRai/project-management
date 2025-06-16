# project-management

# postman collection
[New Collection.postman_collection.json](https://github.com/user-attachments/files/20747063/New.Collection.postman_collection.json)

# ER-Diagram
![final er drawio](https://github.com/user-attachments/assets/bf7bde79-12cc-4d7d-9600-c401338e853f)




# how to run this project
__🛠️ Prerequisites__
1. java 21+(for compatible version)
2. Maven
3. postgreSQL(with database created)
4. intellij idea or any ide
5. postman

__Clone Repository__
git clone https://github.com/SubratRai/project-management.git
cd project-management
Or download the repository and open it in any IDE

__Configure PostgreSQL DB__

CREATE DATABASE project-management;

__Setup application.properties__
use you db credentials:
spring.datasource.url=jdbc:postgresql://localhost:5432/project_management
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect


__Use Maven to build the project__
In terminal: mvn clean install

Then run using mvn spring-boot: run

__Test APIs using Postman__
Base URL: http://localhost:8080
EndPoints:
1. POST    /api/users/register           // for Registration    
2. POST    /api/auth/login               // for login    
3. GET     /api/users/all                // all users details   
4. GET     /api/users/{id}               // user details by id   
5. POST    /api/projects/create          // to create a project   
6. GET     /api/projects                 // to get all projects   
7. DELETE  /api/projects/delete/{id}     // to delete project   
8. GET     /api/projects/my-projects     // to access all projects for the current user   
9. DELETE  /api/task/delete/{id}         // to delete task   
10. POST    /api/tasks                    // to create a task   
11. GET     /api/dashboard/admin          // admin dashboard   
12. GET     /api/dashboard/manager        // manager dashboard   
13. GET     /api/dashboard/developer      // developer dashboard   
14. GET     /api/dashboard/project/{id}   // project dashboard   


__Postman Collections__
[Uploading New Collection.postman_collection.json…]()

