.PHONY: help build run run-local stop clean logs test

# Default target
help:
	@echo "Admin User Service - Available Commands"
	@echo "========================================"
	@echo "make build       - Build the Docker image"
	@echo "make run         - Start all services (MySQL + App) via Docker"
	@echo "make run-local   - Start MySQL via Docker, run app locally"
	@echo "make stop        - Stop all Docker services"
	@echo "make clean       - Stop services and remove volumes"
	@echo "make logs        - View logs from all services"
	@echo "make logs-app    - View logs from app service only"
	@echo "make logs-mysql  - View logs from MySQL service only"
	@echo "make test        - Run tests"
	@echo "make rebuild     - Rebuild and restart all services"

# Build Docker image
build:
	docker-compose build

# Start all services via Docker
run:
	docker-compose up -d
	@echo "Services starting..."
	@echo "Swagger UI: http://localhost:8080/swagger-ui.html"
	@echo "API Docs: http://localhost:8080/api-docs"
	@echo "Adminer (MySQL UI): http://localhost:8082 (Server: mysql, User: admin, Password: adminpassword, Database: admin_user_db)"

# Start MySQL via Docker, run app locally
run-local:
	docker-compose up -d mysql
	@echo "Waiting for MySQL to be ready..."
	@sleep 10
	./gradlew bootRun

# Stop all services
stop:
	docker-compose down

# Stop services and remove volumes
clean:
	docker-compose down -v
	./gradlew clean

# View logs from all services
logs:
	docker-compose logs -f

# View logs from app service only
logs-app:
	docker-compose logs -f app

# View logs from MySQL service only
logs-mysql:
	docker-compose logs -f mysql

# Run tests
test:
	./gradlew test

# Rebuild and restart all services
rebuild:
	docker-compose down
	docker-compose build --no-cache
	docker-compose up -d
	@echo "Services rebuilt and started"
	@echo "Swagger UI: http://localhost:8080/swagger-ui.html"
	@echo "Adminer (MySQL UI): http://localhost:8082"
