Task Manager Application

Description:
Task Manager is a Java console application for managing tasks. Users can add, update, delete, archive, and search tasks.

Features:
- Add tasks with title, description, status, and due date.
- Update task details and status.
- Delete tasks from any table.
- Archive completed tasks.
- View all tasks or only archived tasks.
- Search tasks by title, due date, or ID.

Requirements:
- Java 8 or higher
- MySQL Database
- IntelliJ IDEA or any Java IDE

Database Setup:
1. Create a MySQL database:
   CREATE DATABASE TaskManagerDB;
2. Create tables:
   CREATE TABLE in_progress_tasks (
       id INT PRIMARY KEY AUTO_INCREMENT,
       title VARCHAR(255) NOT NULL,
       description TEXT,
       due_date DATE,
       status ENUM('In Progress', 'Completed') NOT NULL
   );

   CREATE TABLE completed_tasks (
       id INT PRIMARY KEY AUTO_INCREMENT,
       title VARCHAR(255) NOT NULL,
       description TEXT,
       due_date DATE,
       status ENUM('Completed') NOT NULL
   );

   CREATE TABLE archived_tasks (
       id INT PRIMARY KEY AUTO_INCREMENT,
       title VARCHAR(255) NOT NULL,
       description TEXT,
       due_date DATE,
       status ENUM('Completed') NOT NULL
   );
