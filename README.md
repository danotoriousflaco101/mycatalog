MyCatalog: Media Tracker 🎬

MyCatalog is a full-stack application created to track movies and TV shows. Born as a project to showcase practical skills with the Java stack, the application was developed, deployed, and successfully integrated as a live component on the personal website www.theflacosite.com.

The application allows users to add and view the media content they are watching, have completed, or plan to watch, directly from the website.



✨ Features

RESTful Backend: A solid API built with Spring Boot to handle all CRUD (Create, Read, Update, Delete) operations on media items.

Flexible Data Model: Uses JPA/Hibernate inheritance to distinguish between Movie and TV Show, allowing for tracking specific details like the last watched season and episode for series.

Complete Frontend Integration: A clean and responsive UI, built with Vanilla JavaScript, which allows users to:

View the entire media catalog.

Add new movies or TV shows through a convenient modal window.

See the list update in real-time after each addition.

Interactive API Documentation: Thanks to SpringDoc (Swagger), the API is fully documented and testable at the /swagger-ui.html endpoint.


🛠️ Tech Stack & Tools =

Backend
Java 21

Spring Boot 3

Spring Web (for REST APIs)

Spring Data JPA (for database interaction)

Hibernate (as JPA implementation)

Spring Boot Validation (for input data validation)

Maven (as build tool)

Lombok (to reduce boilerplate code)

Frontend
HTML5

CSS3

Vanilla JavaScript (ES6+) (with Fetch API for API calls)

Database
H2 Database (for local development and testing)

PostgreSQL (for the production environment)

Development & Deployment Tools
IDE: IntelliJ IDEA and Visual Studio Code

Version Control: Git & GitHub

API Testing: Insomnia

Deployment Platform: Render

The backend is deployed as a Dockerized service.

The PostgreSQL database is hosted (for free) as a managed service on Render.

🚀 Deployment & Integration
The deployment architecture was designed to be modern and robust:

The Spring Boot backend is packaged into a Docker image and deployed on Render. This ensures a consistent and isolated execution environment.

The PostgreSQL database is a separate service on Render, to which the application connects securely via environment variables.

The frontend is integrated directly into www.theflacosite.com and communicates with the deployed backend on Render via API calls, with CORS management to ensure communication between the two different domains.

🔧 How to Run Locally
To run the application on your own computer for testing or development purposes:

Clone the repository:

git clone https://github.com/danotoriousflaco101/mycatalog.git

Open the project with an IDE like IntelliJ IDEA.

Run the main MycatalogApplication.java class.

The application will start on http://localhost:8080 and will automatically use the H2 in-memory database, without needing any external configuration.