# task-management-api

- Backend API changes are used to connect to Angular application

- Front end code repository -> https://github.com/sivasai1412/task-management-application


# 🛠️ Task Management Backend API (Spring Boot)

This is the backend RESTful API for the Task Management system, developed using **Spring Boot**. It supports task creation, update, deletion, and retrieval with filtering capabilities based on due date, priority, and completion status.

---

## 📦 Technologies Used

- Java 11+
- Spring Boot 2.6+
- Spring Web, Spring Data JPA
- H2 / PostgreSQL (configurable)
- Lombok
- Maven
- Docker

---

## 🎯 Features

- ✅ Create new tasks
- 🔍 Filter tasks by due date, priority, and completion status
- 🆔 Get a task by ID
- ✏️ Update task details
- ❌ Delete tasks
- 🕒 AWS Lambda-ready endpoint for overdue task notifications

---

## 🧱 Data Model

**Table: `tasks`**

| Field        | Type     | Description                        |
|--------------|----------|------------------------------------|
| `id`         | UUID     | Primary key                        |
| `title`      | String   | Required (1–100 chars)             |
| `description`| String   | Optional                           |
| `dueDate`    | Date     | Must not be in the past            |
| `priority`   | Enum     | One of: LOW, MEDIUM, HIGH          |
| `completed`  | Boolean  | Default: `false`                   |

---

## 🔌 API Endpoints

Base URL: `http://localhost:8080/api/v1/tasks`

| Method | Endpoint               | Description                        |
|--------|------------------------|------------------------------------|
| POST   | `/`                    | Create a new task                  |
| GET    | `/`                    | List tasks with filters            |
| GET    | `/{id}`                | Retrieve task by ID                |
| PUT    | `/{id}`                | Update task by ID                  |
| DELETE | `/{id}`                | Delete task by ID                  |

### 🔍 Filters

Use query params: `dueBefore`, `priority`, `completed`


---

## 🧪 Example Request

```http
POST /api/v1/tasks
Content-Type: application/json

{
  "title": "Submit report",
  "description": "Send Q2 report by email",
  "dueDate": "2025-06-25",
  "priority": "MEDIUM"
}
```


