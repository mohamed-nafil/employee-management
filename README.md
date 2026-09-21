# Employee Management & Leave Tracking System

A full-stack Employee Management and Leave Tracking System built using Java, Spring Boot, MySQL, JWT Authentication, and React.

## Features

### Authentication & Security
- JWT-based authentication
- Role-based authorization
- ADMIN and USER roles
- BCrypt password encryption
- Protected REST APIs

### Employee Management
- Add employees
- View employees
- Update employee details
- Delete employees
- Search employees
- Employee validation

### Leave Management
- Apply for leave
- View leave requests
- Update pending leave requests
- Approve leave requests
- Reject leave requests
- Leave status tracking

## User Roles

| Feature | ADMIN | USER |
|---|---|---|
| View Employees | Yes | Yes |
| Add Employee | Yes | No |
| Edit Employee | Yes | No |
| Delete Employee | Yes | No |
| View Leaves | Yes | Yes |
| Apply Leave | Yes | Yes |
| Approve Leave | Yes | No |
| Reject Leave | Yes | No |

## Technology Stack

### Backend
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- Maven

### Database
- MySQL

### Frontend
- React.js
- Vite
- Axios
- React Router

### Tools
- Git
- GitHub
- Postman
- VS Code

## Project Architecture

React Frontend  
↓  
Axios  
↓  
Spring Boot REST API  
↓  
Controller  
↓  
Service  
↓  
Repository / JPA  
↓  
MySQL Database

## REST API Endpoints

### Authentication

```text
POST /api/auth/login