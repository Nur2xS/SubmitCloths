# SubmitCloths - Cloth Submission App
Textilla web app assignment for showcasing and validation of my programming skills, for upcoming job interview

This application provides a user-friendly interface for submitting clothing items into an inventory system. Users can enter details about clothing items, such as name, size, and color, and see a real-time updated list of all items that have been entered.

## Features

- Add a new clothing item with its name, size, and color.
- View a list of all submitted clothing items.
- Responsive design for various screen sizes.

## Tech Stack

- **Frontend**: Angular with PrimeNG and PrimeFlex for UI components and layout.
- **Backend**: Java with Spring Boot, utilizing Spring Data JPA for data persistence.
- **Database**: H2 in-memory database for quick setup and testing.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What you need to install the software and how to install them:

- Node.js and npm
- Angular CLI
- Java Development Kit (JDK)
- Maven (for Java backend)

### Installing

A step-by-step series of examples that tell you how to get a development environment running.

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/cloth-submission-app.git
    ```

2. Navigate to the project directory:
    ```sh
    cd cloth-submission-app
    ```

3. Install NPM packages:
    ```sh
    npm install
    ```

4. Run the Angular development server:
    ```sh
    ng serve
    ```

5. Navigate to `http://localhost:4200/` in your browser.

For setting up and running the backend, follow these steps:

1. Navigate to the backend directory:
    ```sh
    cd backend
    ```

2. Build and run the Spring Boot application:
    ```sh
    mvn spring-boot:run
    ```

