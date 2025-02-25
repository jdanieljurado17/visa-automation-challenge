# Visa Automation Challenge

Developed by: **Jan Jurado** - jandaniel.jurado@gmail.com

This repository contains the solution for the proposed test automation challenge, testing the Swagger Pet Store API project.

## Repository Structure
This project consists of two main implementations:
1. **API Test Automation** – Automated API testing of the Swagger Pet Store API.
2. **Performance Testing** – Load and stress testing for critical API endpoints.

## How to run the project 
These are the steps to set up the project on your local machine: 
1. Set up the Swagger Pet Store API project - for further instructions follow this link -> https://github.com/swagger-api/swagger-petstore
2. Initialize the Swagger Pet Store API project on your local.  
3. Clone and open this project in your local.
4. Execute the TestRunner.java file or on the terminal execute: mvn clean test -Dtest=TestRunner
5. To open the allure reports execute on the terminal: mvn allure:serve
---

## API Automation Implementation
### **Tech Stack**
The following technologies were selected to ensure compatibility with SerenityBDD and Java, prioritizing a BDD framework design:
- **Java**
- **Cucumber**
- **SerenityBDD**
- **Gherkin**
- **TestNG**
- **Rest Assured**
- **Maven**

### **Test Suites**
This implementation follows a structured approach with two suites: **Smoke** and **Regression**.

#### **Smoke Suite (Completed ✅)**
The Smoke suite performs fast and effective tests on critical endpoints to quickly validate API health.
- **Create a pet** (POST `/pet`) ✅
- **Get a pet by ID** (GET `/pet/{petId}`) ✅
- **Update a pet** (PUT `/pet`) ✅
- **Delete a pet** (DELETE `/pet/{petId}`) ✅
- **Place an order** (POST `/store/order`) ✅
- **Get order by ID** (GET `/store/order/{orderId}`) ✅
- **Create a user** (POST `/user`) ✅
- **Login a user** (GET `/user/login`) ✅
- **Update a user** (PUT `/user/{username}`) ✅

#### **Regression Suite (Work in Progress 🚧)**
The Regression suite extends beyond the Smoke suite, covering all API endpoints with additional negative test cases.

- **All Smoke Tests** ✅
- **Find pets by status** (GET `/pet/findByStatus`)
- **Find pets by tags** (GET `/pet/findByTags`)
- **Update a pet using form data** (POST `/pet/{petId}`)
- **Upload pet image** (POST `/pet/{petId}/uploadImage`)
- **Get inventory** (GET `/store/inventory`)
- **Find an order by ID** (GET `/store/order/{orderId}`)
- **Delete an order** (DELETE `/store/order/{orderId}`)
- **Create multiple users** (POST `/user/createWithList`)
- **Get a user by username** (GET `/user/{username}`)
- **Delete a user** (DELETE `/user/{username}`)
- **Logout a user** (GET `/user/logout`)

##### **Negative Test Cases**
- Create pet with missing required fields.
- Retrieve pet with invalid ID.
- Place an order with incorrect data.
- Login with invalid credentials.
- Delete a non-existent user.

---

## Performance Testing Implementation
### **Tech Stack**
The following lightweight and efficient stack was used for performance testing:
- **Python**
- **Locust**

### **Performance Test Strategy (Completed ✅)**
Performance tests were designed to evaluate **Load and Stress** conditions, focusing on the primary **GET** and **POST** requests for key endpoints:

#### **Endpoints Tested**
##### **Pet Endpoint**✅
- `GET /pet/findByStatus`
- `GET /pet/{petID}`
- `POST /pet`

##### **Store Endpoint**✅
- `GET /store/inventory`
- `GET /store/order/{orderID}`
- `POST /store/order`

##### **User Endpoint**✅
- `GET /user/login`
- `GET /user/{username}`
- `POST /user`

These selected endpoints ensure **comprehensive evaluation** of API health, stability, and performance under realistic traffic conditions.

---

### **Conclusion**
This implementation provides a solid test automation solution for functional and performance testing of the Swagger Pet Store API. The structured approach ensures maintainability, scalability, and reliability, effectively simulating real-world scenarios to validate API robustness.

For further details, refer to the documentation and test execution reports included in this repository.

