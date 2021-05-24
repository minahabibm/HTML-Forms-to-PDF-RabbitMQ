# HTML Form to PDF - RabbitMQ

This is a HTML Form to PDF web app; a tutorial using Spring Boot and RabbitMQ to demonstrate handling long timing processes without Interrupting the user experience or violating HTTP/HTTPS protocols. Used to fill a HTML form and Obtain a PDF for download.

The APP will start by filling a HTML form using React, the form also, accepts images using drag & drop in the upload box or you can navigate the folder structure to select the image. Submitting the form, will start post request to the server side. The server side accepts multiple endpoints, create PDF, download PDF, upload images and saves it in the server, available PDFs, delete PDF, and checking on progress. Once the post endpoint hit with a create a PDF request, a new task will sent through RabbitMQ. That will start the task of creating a PDF. During the process of creating the pdf, the progress will be updated, and the client side will continuously check on progress until its ready for download. Allowing to start new tasks independently.

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
