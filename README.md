Todo Management Application

A full-stack Todo Management application built with Spring Boot, PostgreSQL, HTML, CSS, and JavaScript. The application provides user authentication using JWT and allows authenticated users to create, view, update, complete, and delete their todos.

🚀 Features
User registration
User login
JWT-based authentication
Create new todos
View all todos
Update todo status
Mark todos as completed using a checkbox
Delete todos
PostgreSQL database integration
RESTful APIs
Responsive and simple frontend interface


🛠️ Technologies Used
Backend
Java
Spring Boot
Spring Web
Spring Data JPA
Spring Security
JWT
Maven
PostgreSQL
Lombok
Frontend
HTML5
CSS3
JavaScript
Fetch API
Local Storage


📁 Project Structure
todo-app/
│
├── TodoFrontend/
│   ├── login.html
│   ├── register.html
│   ├── todos.html
│   ├── script.js
│   └── style.css
│
└── helloworld/
    ├── pom.xml
    └── src/
        └── main/
            ├── java/
            │   └── Javapractice/
            │       └── helloworld/
            │           ├── controller/
            │           ├── model/
            │           ├── repo/
            │           ├── service/
            │           ├── JwtFilter.java
            │           ├── securityConfig.java
            │           └── utils/
            │
            └── resources/
                └── application.properties

                
🔐 Authentication Flow

The application uses JWT authentication.

User Login
    ↓
Spring Boot validates credentials
    ↓
JWT Token generated
    ↓
Frontend stores token in Local Storage
    ↓
Token sent with protected requests
    ↓
JWT Filter validates token
    ↓
Request is processed

Protected Todo requests send the token using:

Authorization: Bearer <token>
🔄 Todo Flow
Create Todo
Frontend
   ↓
POST /create
   ↓
TodoController
   ↓
TodoService
   ↓
TodoRepo
   ↓
PostgreSQL
Update Todo

When the checkbox is clicked:

Checkbox
   ↓
isTrue changed
   ↓
PUT request
   ↓
TodoController
   ↓
TodoService
   ↓
TodoRepo
   ↓
PostgreSQL
Delete Todo
Delete button
   ↓
DELETE /delete?id=<id>
   ↓
TodoController
   ↓
TodoService
   ↓
TodoRepo
   ↓
PostgreSQL
🗄️ Database Configuration

This project uses PostgreSQL.

Update your application.properties with your local database configuration:

spring.datasource.url=jdbc:postgresql://localhost:5432/toddh
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Set the DB_PASSWORD environment variable to your PostgreSQL password.

Do not commit your actual database password or JWT secret to GitHub.

▶️ How to Run
Backend

Navigate to the backend:

cd helloworld

Run the Spring Boot application using Maven:

./mvnw spring-boot:run

On Windows:

.\mvnw.cmd spring-boot:run

The backend runs on:

http://localhost:8080
Frontend

Open the files from:

TodoFrontend/

Start with:

login.html

Register a user, log in, and then manage your todos.

📌 API Endpoints
Method	Endpoint	Description
POST	/Auth/register	Register a new user
POST	/Auth/login	Login and receive JWT
POST	/create	Create a Todo
GET	/find	Find Todo by ID
GET	/findAll	Get all Todos
GET	/findAllpage	Get paginated Todos
PUT	/	Update a Todo
DELETE	/delete?id={id}	Delete a Todo
🎯 Future Improvements
Add Todo categories
Add due dates and reminders
Add search and filtering
Add pagination to the frontend
Improve UI/UX
Deploy the backend and database
Deploy the frontend
Add automated testing
👨‍💻 Author

Mohamed Askar
