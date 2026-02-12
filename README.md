# project-management

# postman collection
[New Collection.postman_collection.json](https://github.com/user-attachments/files/20747063/New.Collection.postman_collection.json)

# ER-Diagram
![final er drawio](https://github.com/user-attachments/assets/bf7bde79-12cc-4d7d-9600-c401338e853f)

# how to run this project
__🛠️ Prerequisites__
1. Java 21+
2. Maven
3. PostgreSQL (database created)
4. IntelliJ IDEA or any IDE
5. Postman

__Clone Repository__
```bash
git clone https://github.com/SubratRai/project-management.git
cd project-management
```

__Configure PostgreSQL DB__
```sql
CREATE DATABASE project_management;
```

__Setup environment variables (recommended)__
```bash
export DB_URL=jdbc:postgresql://localhost:5432/project_management
export DB_USERNAME=your_username
export DB_PASSWORD=your_password
export GROQ_API_KEY=your_groq_api_key
export GROQ_API_BASE=https://api.groq.com/openai/v1
```

__Use Maven to build the project__
```bash
mvn clean install
mvn spring-boot:run
```

__Test APIs using Postman__
Base URL: `http://localhost:8080`

Endpoints:
1. `POST /api/users/register` - Registration
2. `POST /api/auth/login` - Login
3. `GET /api/users/all` - All users
4. `GET /api/users/{id}` - User by id
5. `POST /api/projects/create` - Create project
6. `GET /api/projects` - Get all projects
7. `DELETE /api/projects/delete/{id}` - Delete project
8. `GET /api/projects/my-projects` - Current user projects (ADMIN/MANAGER)
9. `POST /api/tasks/create` - Create task
10. `GET /api/tasks/{id}` - Get task by id
11. `GET /api/tasks/project/{projectId}` - Get tasks by project
12. `GET /api/tasks/user/{userId}` - Get tasks by user
13. `PUT /api/tasks/{id}/status?status=TODO|IN_PROGRESS|DONE` - Update task status
14. `DELETE /api/tasks/{id}` - Delete task
15. `GET /api/dashboard/admin` - Admin dashboard
16. `GET /api/dashboard/manager` - Manager dashboard
17. `GET /api/dashboard/developer` - Developer dashboard
18. `GET /api/dashboard/project/{id}` - Project dashboard
19. `POST /api/ai/generate-user-stories` - AI user story generation
