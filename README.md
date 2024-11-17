
# Pizza Management System

## Developed by: Dijanira And Alfred

---

### Table of Contents
1. [Introduction](#introduction)  
2. [Project Requirements](#project-requirements)  
3. [General Project Structure](#general-project-structure)  
   - [Technologies Used](#technologies-used)  
   - [Folder Structure](#folder-structure)  
4. [Database](#database)  
   - [Table Structures](#table-structures)  
   - [Table Relationships](#table-relationships)  
5. [Implemented Features](#implemented-features)  
   - [Home Page](#home-page)  
   - [Registration and Login](#registration-and-login)  
   - [User Roles](#user-roles)  
   - [Data Display](#data-display)  
   - [Contact Form](#contact-form)  
   - [Stored Messages](#stored-messages)  
6. [RESTful API](#restful-api)  
   - [Available Endpoints](#available-endpoints)  
   - [Testing with cURL and Postman](#testing-with-curl-and-postman)  
7. [GitHub Usage](#github-usage)  
8. [Screenshots](#screenshots)  
9. [Conclusion](#conclusion)  
10. [Future Improvements](#future-improvements)  

---

## Introduction
This Pizza Management System demonstrates modern web application development techniques, focusing on user authentication, database integration, and RESTful API implementation. It is designed to manage pizza orders effectively, with distinct roles for administrators, users, and visitors.

### Project Objectives
- Develop a functional and interactive order management system.  
- Implement core concepts like authentication, authorization, and data manipulation.  
- Adhere to best practices in version control and documentation.  

---

## Project Requirements
### Necessary Features
1. Attractive home page introducing the company.  
2. User registration and login with role-based access control.  
3. Display data stored in three different database tables.  
4. Functional contact form with server-side validation.  
5. RESTful API implementation with full CRUD operations.  
6. Version control on GitHub with at least five meaningful commits.  
7. Detailed documentation with screenshots explaining each feature.  

---

## General Project Structure

### Technologies Used
- **Frontend:** HTML5, CSS3, Bootstrap  
- **Backend:** Java (Spring Boot), RESTful API  
- **Database:** MySQL  
- **Version Control:** GitHub  



---

## Database

### Table Structures
1. **Users Table**  
   - **Description:** Stores user information.  
   - **Fields:**  
     - `id` (PK, auto_increment): Unique identifier.  
     - `name`: User's name.  
     - `email` (UNIQUE): User's email address.  
     - `password`: Encrypted password.  
     - `role`: User role (Admin, User, Visitor).  
     - `created_at`: Account creation timestamp.  

2. **Order Table**  
   - **Description:** Stores user order details.  
   - **Fields:**  
     - `id` (PK): Unique identifier.  
     - `pizzaname`: Name of the pizza.  
     - `amount`: Ordered quantity.  
     - `taken`: Status (if prepared).  
     - `dispatched`: Dispatch status.  

3. **Pizza Table**  
   - **Description:** List of available pizzas.  
   - **Fields:**  
     - `pname`: Name of the pizza.  
     - `categoryname`: Pizza category.  
     - `vegetarian`: Indicates if the pizza is vegetarian (boolean).  

4. **Category Table**  
   - **Description:** Classification of pizzas.  
   - **Fields:**  
     - `cname` (PK): Category name.  
     - `price`: Average price of the category.  

### Table Relationships
- **Orders → Users:** A user can place multiple orders (1:N).  
- **Pizzas → Categories:** Each pizza belongs to one category (N:1).  

---

## Implemented Features

### Home Page
- Introduces the system's purpose and provides navigation links for key functionalities.

### Registration and Login
- **Registration:** Allows new users to create an account.  
- **Login:** Validates credentials and grants access to the system.  

### User Roles
- **Admin:** Full access, including user management.  
  - **Administrator Credentials:**  
    - **Email:** `muachifi@mail.com`  
    - **Password:** `U2FELP`  
- **User:** Place orders and view statuses.  
- **Visitor:** Limited access to public pages.  

### RESTful API

#### Available Endpoints
- **Users:**  
  - `GET /api/users` - Retrieves all users.  
  - `POST /api/users` - Adds a new user.  
- **Orders:**  
  - `GET /api/orders` - Retrieves all orders.  
  - `POST /api/orders` - Creates a new order.  

---

## Testing with cURL and Postman

#### cURL Examples:
```bash
curl -X GET http://localhost:8080/api/orders
curl -X GET http://localhost:8080/api/pizzas
curl -X GET http://localhost:8080/api/categories
```

#### Postman Examples:
- List Categories  
- List Pizzas  
- List Orders  
- Create New Category  



---

## GitHub Usage
- All features were implemented with meaningful commit messages, ensuring clear version history.

---

## Conclusion
This project showcases advanced web development skills with a focus on authentication, database integration, and RESTful APIs. Future enhancements could include detailed reporting and integration with external APIs to expand functionality.

---

## Future Improvements
- Add detailed reporting features.  
- Integrate with third-party APIs for extended functionality.  

