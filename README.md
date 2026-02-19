# Library Management System (LMS)

Spring Boot + Thymeleaf Library Management System with dark theme inspired by Scholastic’s design language (bold accent red on dark surfaces).

## Features
- Borrower
  - Search books by title, author, or subject
  - Place hold when a book is unavailable
  - View borrower profile and current loans
- Checkout Clerk
  - Issue, return, and renew items
  - Record fines, add/update borrowers
- Librarian
  - Add, edit, delete items (books)
- Administrator
  - Add staff (Clerk/Librarian/Admin) by setting roles
  - View issued books and all books

## Tech Stack
- Java, Spring Boot 2.7.x, Spring Data JPA, H2
- Thymeleaf, Bootstrap 5 with a dark theme
- Maven Wrapper included

## Quick Start
```
.\mvnw.cmd spring-boot:run
```
App: http://localhost:8080  
H2: http://localhost:8080/h2-console  
JDBC URL: `jdbc:h2:mem:librarydb` — user `sa` / password `password`

## Screens
- Books: list, search, add/edit/delete, place hold
- Borrowings: issue, return, renew, record fine
- Users: add/edit/delete, borrower profile

## CI
GitHub Actions workflow builds with Java 17 and Maven Wrapper.

## License
MIT
