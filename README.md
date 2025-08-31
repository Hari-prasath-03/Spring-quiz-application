# 🧠 Spring Boot Quiz Application

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

## 🧑‍💻 Author

**Hari Prasath**  
Passionate Full-Stack Developer | Java & Spring Boot Enthusiast  
🔗 [LinkedIn](https://www.linkedin.com/in/hari-prasath-k) • 📂 [GitHub](https://github.com/Hari-prasath-03)
