CREATE DATABASE student_db;

USE student_db;

CREATE TABLE student(
	registration_id VARCHAR(200) PRIMARY KEY,
	roll_no INT NOT NULL,
    `name` VARCHAR(200) NOT NULL,
    class INT NOT NULL,
    division CHAR NOT NULL
);