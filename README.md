# Account Management Service with Docker Integration

This project is an Account Management Service built using Java Spring Boot. It demonstrates a microservice architecture that manages customer accounts, providing features such as adding, withdrawing, and deleting money from accounts. The service is containerized using Docker and uses MySQL as the database backend, managed through Docker Compose.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them.

- Docker
- Docker Compose
- Maven
- Java 17

### Installing

A step-by-step series of examples that tell you how to get a development environment running.

#### 1. Create a project folder and clone the Account Management service
```bash
mkdir docker-assignment && cd docker-assignment
git clone https://github.com/itshivam242/account-management-service-dockercompose.git ./
```
#### 2. Create project Jar file
```bash
mvn clean install
```
#### 3. Start MySQL Container
To run a new MySQL container with the following settings, use the command:
```bash
docker run -p 3307:3306 --name mysql-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=devops -d mysql

```
#### 4. Create a Docker Network
This command creates a new Docker network named mysql-network. Docker networks allow containers to communicate with each other by name. This network will be used to link the MySQL container and the application container.
```bash
docker network create mysql-network
```
#### 5. Connect MySQL Container to the Network
This command connects the mysql-container to the previously created mysql-network. This step ensures that other containers on the same network can resolve the MySQL container by name.
```bash
docker network connect mysql-network mysql-container

```
#### 6. Build the Application Docker Image
This command builds a Docker image for the application from the current directory (.) using the Dockerfile located there. The image is tagged as account-management-service-image. This image will be used to run the application container.
```bash
docker build -t account-management-service-image .
```
#### 7. Run the Application Container
This command runs a new container from the account-management-service-image image with the following settings:
```bash
docker run -p 8091:8080 --name account-management-service-container --net mysql-network -e MYSQL_HOST=mysql-container -e MYSQL_PORT=3306 -e MYSQL_DB_NAME=devops -e MYSQL_USER=root -e MYSQL_PASSWORD=root account-management-service-image
```
#### 8. Verify the Application

Once Docker Compose has finished setting up, you can verify the application by accessing the Account Management Service at:
```bash
http://localhost:8091/accounts
````
Check API endpoints below in this file to check application on postman.
#### 9. Stop and Remove the both Container
```bash
docker stop account-management-service-container
docker stop mysql-container
docker rm account-management-service-container
docker rm mysql-container
```
#### 10.Remove the Docker Network
```bash
docker network rm mysql-network
```
## Usage

The following API endpoints are available for managing accounts:

1. **Create a new account**
    - **HTTP Method:** POST
    - **Endpoint:** `/accounts`

2. **Add money to an account**
    - **HTTP Method:** PUT
    - **Endpoint:** `/accounts/add-money/{accountId}`

3. **Withdraw money from an account**
    - **HTTP Method:** PUT
    - **Endpoint:** `/accounts/withdraw-money/{accountId}`

4. **Get account details by account ID**
    - **HTTP Method:** GET
    - **Endpoint:** `/accounts/{accountId}`

5. **Delete an account**
    - **HTTP Method:** DELETE
    - **Endpoint:** `/accounts/{accountId}`

6. **Delete account by customer ID**
    - **HTTP Method:** DELETE
    - **Endpoint:** `/accounts/customers/{customerId}`



