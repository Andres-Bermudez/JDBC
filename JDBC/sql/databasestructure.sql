CREATE DATABASE jdbc_project_db;

CREATE TABLE employees (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(64) NOT NULL,
  pa_surname VARCHAR(64) NOT NULL,
  ma_surname VARCHAR(64) NOT NULL,
  email VARCHAR(64) NOT NULL UNIQUE,
  salary DECIMAL(10,2) NOT NULL

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary)
VALUES ('John', 'Doe', 'Smith', 'john.doe@example.com', 50000.00);

INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary)
VALUES ('Jane', 'Smith', 'Johnson', 'jane.smith@example.com', 60000.00);

INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary)
VALUES ('Michael', 'Johnson', 'Brown', 'michael.johnson@example.com', 55000.00);

INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary)
VALUES ('Emily', 'Brown', 'Davis', 'emily.brown@example.com', 52000.00);

INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary)
VALUES ('David', 'Davis', 'Wilson', 'david.davis@example.com', 58000.00);

INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary)
VALUES ('Sarah', 'Miller', 'Garcia', 'sarah.miller@example.com', 62000.00);

INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary)
VALUES ('James', 'Taylor', 'Martinez', 'james.taylor@example.com', 63000.00);

INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary)
VALUES ('Olivia', 'Anderson', 'Lopez', 'olivia.anderson@example.com', 59000.00);

INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary)
VALUES ('William', 'Thomas', 'Gonzalez', 'william.thomas@example.com', 65000.00);

INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary)
VALUES ('Sophia', 'Jackson', 'Hernandez', 'sophia.jackson@example.com', 58000.00);