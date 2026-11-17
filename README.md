# Library Management System (Backend)

A backend project for managing libraries, books and users. Admins manage books and libraries. Users can register, log in, and borrow or return books.

**Frontend repo:** https://github.com/anshsharma8/library-management-frontend

## Features

- User registration and login with JWT authentication
- Two roles: Admin and User
- Admin can add, update and delete books and libraries
- A user can access and update only their own data
- Borrow and return books
- Add and remove books from a library
- Search books by author
- Input validation and global exception handling
- API documentation using Swagger

## Tech Stack

- Java 21
- Spring Boot
- Spring Security and JWT
- Spring Data JPA (Hibernate)
- MySQL
- Maven

## Project Structure

The project follows a layered architecture:

- **controller**: handles HTTP requests
- **service**: contains the business logic
- **repository**: talks to the database
- **entity**: database tables
- **dto**: data sent and received through the APIs
- **security**: JWT and security configuration
- **exception**: custom exceptions and global exception handler

## Main API Endpoints

| Module | Endpoint | Description |
|---|---|---|
| User | POST /user/login | Login and get a token |
| User | PUT /user/borrow/{userId}/{bookId} | Borrow a book |
| User | PUT /user/return/{bookId} | Return a book |
| Book | GET /book/fetchAll | Get all books |
| Book | POST /book | Add a book (Admin) |
| Library | GET /library/fetchAll | Get all libraries |
| Library | POST /library/{addressId} | Create a library (Admin) |

Full API documentation is available in Swagger UI after running the project.

## How to Run

1. Clone the repository
2. Create a MySQL database
3. Set these environment variables: `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`
4. Run the project:

```
mvn spring-boot:run
```

5. The server starts at `http://localhost:8081`

## Author

Anshul Sharma
