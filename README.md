# HTML Form to PDF - RabbitMQ

completed the frontend, backend, and the application is ready. project description in progress....
<p align="center">
  <img width="90%" height="80%" src="https://drive.google.com/uc?export=view&id=1ofpTtJjAG9DKNpjA2JgAxkasRkA0OvQ5">
</p>

## Technology Stack

### Frontend
* Node.JS
* React
* axios
* Formik

### Backend
* Java
* Spring, Spring Boot, Spring Web, Spring for RabbitMQ
* iText7
* maven
* RabbitMQ

## Initial Setup
* Clone the repository.
* `cd HTML-Forms-to-PDF-RabbitMQ`

### Build and install
Ensure you have: Node.JS, npm, java, maven, RabbitMQ installed Correctly.

#### RabbitMQ
* start rabbitmq `rabbitmq-server`

#### Frontend
* `cd simple-form-frontend`
* `npm i`
* `npm start`

#### Backend
* `cd simple-form-Backend`
* `mvn clean package`
* `cd target`
* `java -jar simpleformstopdf-0.0.1-SNAPSHOT.jar`

### Build using Docker (Recommended)
* `docker-compose up`
