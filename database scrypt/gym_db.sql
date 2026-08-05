-- Run this ONCE in MySQL Workbench before running the Java program.

CREATE DATABASE gym_db;
USE gym_db;

CREATE TABLE plans (
    plan_id         INT PRIMARY KEY,
    plan_name       VARCHAR(50),
    duration_months INT,
    fee             DOUBLE
);

CREATE TABLE members (
    member_id  INT PRIMARY KEY,
    name       VARCHAR(100),
    email      VARCHAR(100) UNIQUE,
    phone      VARCHAR(20),
    plan_id    INT,
    FOREIGN KEY (plan_id) REFERENCES plans(plan_id)
);

INSERT INTO plans (plan_id, plan_name, duration_months, fee) VALUES
    (1, 'Basic', 1, 3000),
    (2, 'Premium', 3, 8000),
    (3, 'VIP', 12, 25000);

-- check tables
SELECT * FROM plans;
SELECT * FROM members;

