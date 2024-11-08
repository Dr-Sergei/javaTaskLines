# Line Segment Visualizer

A Spring Boot application that reads a coordinates and calculates line segments from a file, saves them to a PostgreSQL
database, and visualizes them on a web page. Each upload replaces old data, with the new segments displayed in different
colors.

## Features

- Upload a list of coordinates and calculates line segments; older data is automatically deleted.
- Segments are saved in a PostgreSQL database and visualized in the UI.
- Data is persistent; the database container is automatically created and destroyed as needed.

## Prerequisites

- Docker and Docker Compose
- Java 11+
- Maven

## How to Start

- ### Start Docker Engine:
  Ensure Docker is running on your system.
- ### Run the Application:
    - Clone the repository and navigate to the project directory.
    - Start the PostgreSQL container and Spring Boot application:

```` 
bash

 docker-compose up -d
 mvn spring-boot:run
 ````
- This will set up the PostgreSQL container and start the Spring Boot app at http://localhost:8080.
- ### Access the Application:
    - Open http://localhost:8080 in your browser.

## Configuration
  The PostgreSQL database is configured to persist data across container restarts.


