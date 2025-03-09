# Order-Management-Application

The main objective of this project is to design and implement an application for managing a warehouse and customer orders using a MySQL database for efficiency and easy access. This is complemented by an intuitive graphical user interface (GUI) that allows users to perform various operations (CRUD) on products, registered customers, and their orders.

## Problem Analysis

This application addresses the inefficient and error-prone manual management of orders in a warehouse, which uses handwritten logs that are susceptible to errors, lack data integrity, and are time-consuming. The application proposes the use of a MySQL database to create easy-to-follow links between products and customers, ensuring data integrity. A graphical interface is also added to provide easy and intuitive access to the database.

## Functionalities:

- Users can input customer and product information via keyboard or modify existing information (including deleting records with cascade).
- The application communicates with a MySQL database to display the content of selected tables.
- Data entered from the keyboard are validated to maintain integrity and prevent errors.
- Users can place orders within available stock limits, selecting an existing customer and product, generating an order without the need to remember IDs, reducing the risk of errors.
- Display a table of invoices generated from placed orders, maintaining information even after modifications in other tables.


## Object-Oriented Design (OOD)

The application follows a layered architecture and is organized into the following main packages:
-  **DataAccess**: Manages the connection to the database.
-  **BusinessLogic**: Ensures proper method calls from the DataAccess module.
-  **View**: Manages the graphical interface for monitoring BusinessLogic activities.
-  **Model**: Defines the objects used in the application.

## Graphical Interface:
- **Client and Product Pages**: These pages display a table of data, where users can select an item. The item’s details are previewed in textboxes on the left side.
- **Operations**: Selected clients or products can be added, modified, or deleted using buttons located at the bottom of the page. Inserting a new client clears the textboxes using the "clear" button.
- **Main Menu**: A menu that allows users to select different application functions, with each function opening a new window for the relevant table operations.
- **Order Page**: Displays data from both the customer and product tables. The order quantity is entered on this page, but modifications are only allowed on their respective pages.
  
 ![image](https://github.com/user-attachments/assets/3176cf14-3c86-4f8f-999c-337ef9e21422)

## Technologies Used

- **Java**: For the application logic and graphical interface.
- **MySQL**: For managing the database and storing customer, product, and order information.
- **JDBC**: For database connection and operations.
- **Java Swing**: For the graphical user interface (GUI).
- **Javadoc**: For generating documentation for the application code.
- **Reflection**: Used for dynamically accessing class fields.
- **Layered Architecture**: Organizing the application into logical layers for better maintainability and scalability.


