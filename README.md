# Project Management System

Role-based project and task management backend built with Spring Boot, PostgreSQL, and API-driven workflow design.

## Overview

This project demonstrates how I build internal business systems where user roles, task coordination, reporting, and workflow visibility matter. It is designed around real operational use cases such as project ownership, task assignment, dashboard views, and authenticated access.

It is a practical example of the kind of backend I can build for startups, agencies, internal teams, and SaaS MVPs.

## Core Features

- User registration and login
- Authenticated API access
- Project creation and management
- Task creation and assignment
- Task status updates
- User-specific and project-specific task views
- Role-based dashboards for admin, manager, and developer users
- AI-assisted user story generation endpoint

## Tech Stack

- Java
- Spring Boot
- Maven
- PostgreSQL
- REST APIs

## Business Use Cases

This backend is suitable for:

- Internal team management tools
- Agency project tracking systems
- Startup MVP operations dashboards
- Workflow apps with multiple user roles

## API Highlights

### Authentication and Users

- `POST /api/users/register`
- `POST /api/auth/login`
- `GET /api/users/all`
- `GET /api/users/{id}`

### Projects

- `POST /api/projects/create`
- `GET /api/projects`
- `DELETE /api/projects/delete/{id}`
- `GET /api/projects/my-projects`

### Tasks

- `POST /api/tasks/create`
- `GET /api/tasks/{id}`
- `GET /api/tasks/project/{projectId}`
- `GET /api/tasks/user/{userId}`
- `PUT /api/tasks/{id}/status?status=TODO|IN_PROGRESS|DONE`
- `DELETE /api/tasks/{id}`

### Dashboards

- `GET /api/dashboard/admin`
- `GET /api/dashboard/manager`
- `GET /api/dashboard/developer`
- `GET /api/dashboard/project/{id}`

### AI Integration

- `POST /api/ai/generate-user-stories`

## Project Assets

The repository includes:

- Postman collection for API testing
- ER diagram for data modeling reference

These assets make the project easier to review for clients, recruiters, and collaborators.

## Local Setup

### Prerequisites

- Java 21+
- Maven
- PostgreSQL
- Postman

### Database

```sql
CREATE DATABASE project_management;
```

### Environment Variables

```bash
export DB_URL=jdbc:postgresql://localhost:5432/project_management
export DB_USERNAME=your_username
export DB_PASSWORD=your_password
export GROQ_API_KEY=your_groq_api_key
export GROQ_API_BASE=https://api.groq.com/openai/v1
```

### Run

```bash
git clone https://github.com/SubratRai/project-management.git
cd project-management
mvn clean install
mvn spring-boot:run
```

## What This Demonstrates To Clients

- Backend design for team workflows
- Role-based system architecture
- API design for dashboards and reporting
- PostgreSQL-backed business applications
- AI feature integration into an existing product

## Freelance Relevance

If you need a backend for project tracking, task workflows, admin reporting, or internal operations software, this project reflects the type of system I can build and customize.
