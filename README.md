# 🧠 Spring Boot Quiz Application 
🔗[View Microservices⬇️](#microservices)

A full-featured backend application for managing quizzes, users, and exam results with robust JWT-based authentication and role-based access control. Built using **Spring Boot**, **MongoDB**, and layered architecture principles.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- JWT-based authentication system
- Role-based access control: `ADMIN`, `STAFF`, `STUDENT`
- Custom handlers for:
  - Unauthorized access (`AuthenticationEntryPoint`)
  - Access denial (`AccessDeniedHandler`)

### 📚 Quiz Management
- Gemini-powered AI question generation
- CRUD operations on quizzes
- Store structured quiz data with questions and answers
- Clean DTO abstraction for request/response objects

### 🎓 Exam System
- Submit and evaluate exam responses
- Auto-score based on correct answers
- Feedback with explanation support

### 👤 User Management
- Register and login
- Role assignment on registration
- Fetch quiz attempt history

### 📊 Results
- Store and retrieve quiz results
- Fetch detailed score breakdowns
- Result history per quiz

### ⚙️ Security
- JWT filter for secured endpoints
- Role-based endpoint access control
- Centralized exception handling

### 🧱 Architecture
- Clean layered design:
  - `controller`, `service`, `repository`
  - `dto`, `entity`, `filter`, `config`
  - `customexception`, `advice`, `utils`
- Modular and scalable structure
- Easy to test and migrate to microservices in the future

---

## 📁 Project Structure

```
quizApplication/
├── controller/
├── service/
├── repository/
├── dto/
├── entity/
├── config/
├── filter/
├── customexception/
├── advice/
├── utils/
└── QuizApplication.java
```

> ✅ Clean Code Practices  
> ✅ Ready for scaling & microservice transition

---

## 🛠️ Tech Stack

- ☕ **Java 24**
- 🧪 **Spring Boot**
- 🛡️ **Spring Security + JWT**
- 🗃️ **MongoDB** with Spring Data Mongo
- ✨ **Lombok** for boilerplate reduction
- 🧰 **Maven** for project management

---

## 🧪 API Overview

| Feature           | Endpoint                         | Method | Role Required |
| ----------------- | -------------------------------- | ------ | ------------- |
| Register          | `/user/register`                 | POST   | Public        |
| Login             | `/auth/login`                    | POST   | Public        |
| Generate Quiz       | `/quiz/create`                   | POST   | `STAFF`       |
| Save Quiz       | `/quiz/save`                   | POST   | `STAFF`       |
| Update Quiz       | `/quiz/update`                   | PUT    | `STAFF`       |
| Delete Quiz       | `/quiz/delete/{id}`              | DELETE | `STAFF`       |
| Fetch All Quizzes | `/quiz/get-all`                  | GET    | `STAFF`       |
| Submit Exam       | `/exam/take-test`                   | GET | `STUDENT`     |
| View Results      | `/exam/evaluate-test`                  | POST| `STUDENT`     |


> 🔐 Use the JWT token as a Bearer token in the `Authorization` header after login.

---


## 🧱 Monolith to Microservices <a id="microservices"></a>

To ensure better **scalability** and **modularity** the monolithic architecture is being **evolved into microservices**. 

### 🔀 Transition Steps

#### ✅ Step 1: Separate Auth Service & User Service -> 🔗[repo link](https://github.com/Hari-prasath-03/Quiz-auth-microservice)

* Manages user registration, profile data, and user-specific queries.
* Handles login, registration, role management, and **JWT token generation**.
* Acts as the authority for issuing and validating credentials.


#### ✅ Step 2: Separate Quiz Service -> 🔗[repo link](https://github.com/Hari-prasath-03/Quiz-quiz-microservice)

* Handles quiz creation, updates, deletions, and retrievals.
* Isolated responsibility for quiz schema and question management.

#### ✅ Step 3: Separate Exam Service -> 🔗[repo link](https://github.com/Hari-prasath-03/Quiz-exam-microservice)

* Manages test-taking logic and result evaluation.
* Interacts with the Quiz Service to fetch questions and validate answers.

#### ✅ Step 4: Eureka Discovery Service -> 🔗[repo link](https://github.com/Hari-prasath-03/Quiz-service-registry-microservice)

* Services register themselves to **Eureka Server**.
* Enables dynamic service lookup (no hardcoded URLs).

#### ✅ Step 5: Inter-Service Communication

* Use **Spring OpenFeign** or **RestTemplate** for HTTP-based service calls.
* Gateway forwards user requests to appropriate services.

#### ✅ Step 6: Implement API Gateway -> 🔗[repo link](https://github.com/Hari-prasath-03/Quiz-api-gateway-microservice)

* All requests go through a central **Spring Cloud Gateway**.
* Validates JWT via security filter before routing.
* Injects user info into headers for downstream services.

#### ✅ Step 7: Secure Internal Communication

* Internal endpoints protected via roles and internal access policies.
* External clients only talk to the **Gateway**, not directly to services.

> 🧩 Result: A fully decomposed, scalable microservice-based system with centralized authentication and clean service boundaries.

---
## 🧑‍💻 Author

**Hari Prasath**  
Passionate Full-Stack Developer | Java & Spring Boot Enthusiast  
🔗 [LinkedIn](https://www.linkedin.com/in/hari-prasath-k) • 📂 [GitHub](https://github.com/Hari-prasath-03)
