# SWYNEX Java Database Application

A Java-based Study Session Planner connected to a MySQL relational database using JDBC. This project implements CRUD operations with input validation and exception handling.

## 📌 Project Overview

The Study Session Planner allows users to manage their study sessions through a console-based Java application.

Users can:
- Add study sessions
- View all study sessions
- Search sessions by subject
- Update study sessions
- Delete study sessions
- Mark sessions as completed
- Validate user input
- Handle database and input errors

## 🛠️ Technologies Used

- Java 17+
- MySQL 8.0
- JDBC
- Maven
- Git & GitHub
- VS Code

## 🗂️ Project Structure

```text
StudySessionPlannerDB
├── pom.xml
├── .gitignore
└── src
    └── main
        ├── java
        │   ├── DatabaseConnection.java
        │   ├── Main.java
        │   ├── Priority.java
        │   ├── Status.java
        │   ├── StudySession.java
        │   └── StudySessionDAO.java
        │
        └── resources
            └── db.properties.example
```

## 🗄️ Database Setup

Create the MySQL database:

```sql
CREATE DATABASE study_planner_db;
```

Select the database:

```sql
USE study_planner_db;
```

Create the `study_sessions` table:

```sql
CREATE TABLE study_sessions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    subject VARCHAR(100) NOT NULL,
    topic VARCHAR(150) NOT NULL,
    duration INT NOT NULL,
    priority VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    session_date DATE NOT NULL
);
```

## ⚙️ Configuration

1. Copy:

```text
db.properties.example
```

2. Rename the copy to:

```text
db.properties
```

3. Update the database password in `db.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/study_planner_db
db.username=root
db.password=YOUR_MYSQL_PASSWORD
```

**Note:** `db.properties` is excluded from Git using `.gitignore` so that the database password is not uploaded to GitHub.

## ▶️ How to Run

Make sure MySQL Server is running.

Open the project terminal and run:

```bash
mvn clean compile
```

Then run:

```bash
mvn exec:java
```

## 🔄 CRUD Operations

### Create
Adds a new study session to the MySQL database.

### Read
Displays all stored study sessions.

### Update
Updates an existing study session using its ID.

### Delete
Deletes a study session using its ID.

### Search
Searches study sessions by subject.

### Mark Completed
Changes the status of a study session to `COMPLETED`.

## ✅ Validation & Error Handling

The application validates:

- Empty subject and topic
- Positive duration
- Valid session ID
- Valid priority
- Valid status
- Correct date format
- Invalid numeric input

Database errors are handled using SQL exception handling.

## 🎯 Learning Outcomes

This project demonstrates:

- Java programming
- Object-oriented programming
- JDBC database connectivity
- MySQL integration
- CRUD operations
- Prepared statements
- Input validation
- Exception handling
- Maven project management
