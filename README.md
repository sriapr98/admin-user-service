# Admin User Service

A Spring Boot REST API service for managing admin user details with MySQL database, Swagger documentation, and Docker support.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Mac/Linux Instructions](#maclinux-instructions)
- [Windows Instructions](#windows-instructions)
- [API Documentation](#api-documentation)
- [API Endpoints](#api-endpoints)
- [Database Access](#database-access)
- [Local Development](#local-development)
- [Troubleshooting](#troubleshooting)

---

## Prerequisites

### For Both Platforms

| Tool | Version | Required For |
|------|---------|--------------|
| Docker Desktop | Latest | Running containers |
| Java 21 | 21.x | Local development only |

### Install Docker Desktop

- **Mac**: Download from [Docker Desktop for Mac](https://www.docker.com/products/docker-desktop/)
- **Windows**: Download from [Docker Desktop for Windows](https://www.docker.com/products/docker-desktop/)
  - Requires WSL2 or Hyper-V enabled

---

## Quick Start

The fastest way to run the service (works on both Mac and Windows):

```bash
# Clone the repository
git clone <repository-url>
cd admin-user-service

# Start all services
docker-compose up -d

# Wait for services to be ready (about 30 seconds)
# Then access Swagger UI at: http://localhost:8080/swagger-ui.html
```

---

## Mac/Linux Instructions

### Using Make Commands (Recommended)

```bash
# Show all available commands
make help

# Start all services (MySQL + App + Adminer)
make run

# Stop all services
make stop

# View logs from all services
make logs

# View logs from app only
make logs-app

# View logs from MySQL only
make logs-mysql

# Rebuild and restart all services
make rebuild

# Stop services and remove volumes (clean slate)
make clean

# Run tests
make test
```

### Using Docker Compose Directly

```bash
# Start all services in detached mode
docker-compose up -d

# Stop all services
docker-compose down

# View logs
docker-compose logs -f

# View specific service logs
docker-compose logs -f app
docker-compose logs -f mysql

# Rebuild images
docker-compose build --no-cache

# Stop and remove volumes
docker-compose down -v
```

---

## Windows Instructions

### Using PowerShell or Command Prompt

```powershell
# Start all services in detached mode
docker-compose up -d

# Stop all services
docker-compose down

# View logs
docker-compose logs -f

# View specific service logs
docker-compose logs -f app
docker-compose logs -f mysql

# Rebuild images
docker-compose build --no-cache

# Stop and remove volumes
docker-compose down -v
```

### Using Git Bash (Make commands available)

If you have Git for Windows installed, you can use Git Bash which supports Make:

```bash
# Install make via Chocolatey (run as Administrator)
choco install make

# Then use make commands as shown in Mac/Linux section
make run
make stop
make logs
```

### Using WSL2

If you have WSL2 installed, you can run all commands from the WSL terminal:

```bash
# Open WSL terminal
wsl

# Navigate to project directory
cd /mnt/c/path/to/admin-user-service

# Use make commands
make run
```

---

## API Documentation

Once the service is running, access the documentation at:

| Resource | URL |
|----------|-----|
| Swagger UI | http://localhost:8080/swagger-ui.html |
| OpenAPI JSON | http://localhost:8080/api-docs |

---

## API Endpoints

### 1. Create Admin User Details

**POST** `/admin/details`

Creates a new admin user record in the database.

#### Request

```bash
# Mac/Linux
curl -X POST http://localhost:8080/admin/details \
  -H "Content-Type: application/json" \
  -d '{
    "user_id": "USR001",
    "department": "HR",
    "designation": "Manager",
    "product": "Laptop",
    "produce_code": "P01",
    "grade": "A",
    "position": "Senior"
  }'

# Windows (PowerShell)
Invoke-RestMethod -Uri "http://localhost:8080/admin/details" `
  -Method POST `
  -ContentType "application/json" `
  -Body '{"user_id":"USR001","department":"HR","designation":"Manager","product":"Laptop","produce_code":"P01","grade":"A","position":"Senior"}'

# Windows (Command Prompt)
curl -X POST http://localhost:8080/admin/details -H "Content-Type: application/json" -d "{\"user_id\":\"USR001\",\"department\":\"HR\",\"designation\":\"Manager\",\"product\":\"Laptop\",\"produce_code\":\"P01\",\"grade\":\"A\",\"position\":\"Senior\"}"
```

#### Response

- **Status**: `204 No Content`

### 2. Get Admin User Details

**GET** `/admin/details?user_id={userId}`

Retrieves admin user details by user ID.

#### Request

```bash
# Mac/Linux
curl "http://localhost:8080/admin/details?user_id=USR001"

# Windows (PowerShell)
Invoke-RestMethod -Uri "http://localhost:8080/admin/details?user_id=USR001" -Method GET

# Windows (Command Prompt)
curl "http://localhost:8080/admin/details?user_id=USR001"
```

#### Response

- **Status**: `200 OK`

```json
{
  "department": "HR",
  "designation": "Manager",
  "product": "Laptop",
  "produce_code": "P01",
  "grade": "A",
  "position": "Senior"
}
```

### Field Values

| Field | Allowed Values |
|-------|---------------|
| department | HR, Finance |
| designation | Manager, Team lead |
| product | Laptop, Monitor |
| produce_code | P01, P02 |
| grade | A, B, C |
| position | Junior, Senior, Lead |

---

## Database Access

### Adminer (Web-based MySQL UI)

Access Adminer at: **http://localhost:8082**

| Field | Value |
|-------|-------|
| System | MySQL |
| Server | mysql |
| Username | admin |
| Password | adminpassword |
| Database | admin_user_db |

### Direct MySQL Connection

```bash
# Mac/Linux
mysql -h localhost -P 3306 -u admin -padminpassword admin_user_db

# Windows (if MySQL client is installed)
mysql -h localhost -P 3306 -u admin -padminpassword admin_user_db
```

### Connection Details

| Property | Value |
|----------|-------|
| Host | localhost |
| Port | 3306 |
| Database | admin_user_db |
| Username | admin |
| Password | adminpassword |

---

## Local Development

Run the application locally (without Docker for the app, but Docker for MySQL):

### Mac/Linux

```bash
# Start MySQL container only
make run-local
# This starts MySQL and runs the app locally using ./gradlew bootRun
```

Or manually:

```bash
# Start MySQL
docker-compose up -d mysql

# Wait for MySQL to be ready
sleep 10

# Run the application
./gradlew bootRun
```

### Windows

```powershell
# Start MySQL container
docker-compose up -d mysql

# Wait for MySQL to be ready (about 10 seconds)
Start-Sleep -Seconds 10

# Run the application
.\gradlew.bat bootRun
```

### Running Tests

```bash
# Mac/Linux
make test
# or
./gradlew test

# Windows
.\gradlew.bat test
```

---

## Troubleshooting

### Port Already in Use

If you get "port already in use" errors:

```bash
# Check what's using the port (Mac/Linux)
lsof -i :8080
lsof -i :3306
lsof -i :8082

# Check what's using the port (Windows PowerShell)
netstat -ano | findstr :8080
netstat -ano | findstr :3306
netstat -ano | findstr :8082

# Kill the process or stop other services using those ports
```

### Docker Issues

```bash
# Restart Docker Desktop
# Then try again:
docker-compose down
docker-compose up -d
```

### Database Connection Failed

If the app can't connect to MySQL:

```bash
# Check if MySQL container is healthy
docker-compose ps

# View MySQL logs
docker-compose logs mysql

# Restart all services
docker-compose down
docker-compose up -d
```

### Clean Start

For a complete fresh start:

```bash
# Mac/Linux
make clean
make run

# Windows
docker-compose down -v
docker-compose up -d
```

---

## Project Structure

```
admin-user-service/
├── src/main/java/com/example/adminuserservice/
│   ├── AdminUserServiceApplication.java
│   ├── controller/
│   │   └── AdminUserDetailsController.java
│   ├── service/
│   │   └── AdminUserDetailsService.java
│   ├── repository/
│   │   └── AdminUserDetailsRepository.java
│   ├── entity/
│   │   └── AdminUserDetails.java
│   └── dto/
│       ├── AdminUserDetailsRequest.java
│       └── AdminUserDetailsResponse.java
├── src/main/resources/
│   └── application.properties
├── Dockerfile
├── docker-compose.yml
├── Makefile
├── build.gradle
└── README.md
```

---

## License

This project is for demonstration purposes.
