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


-- Add sample 10 data
INSERT INTO members (member_id, name, email, phone, plan_id) VALUES
(201, 'Chamara Rajapaksha', 'chamara.r@gmail.com', '0771234567', 1),
(202, 'Sanduni Ekanayake', 'sanduni.e@gmail.com', '0712345678', 2),
(203, 'Buddhika Wijesinghe', 'buddhika.w@yahoo.com', '0776543210', 3),
(204, 'Hasini Karunaratne', 'hasini.k@gmail.com', '0754321098', 1),
(205, 'Lasith Amarasinghe', 'lasith.a@outlook.com', '0703216549', 2),
(206, 'Piumi Senanayake', 'piumi.s@gmail.com', '0778889990', 3),
(207, 'Ravindu Dissanayake', 'ravindu.d@yahoo.com', '0761112233', 1),
(208, 'Dilini Herath', 'dilini.h@gmail.com', '0724445566', 2),
(209, 'Ashan Gunawardena', 'ashan.g@gmail.com', '0767778889', 3),
(210, 'Nimasha Peiris', 'nimasha.p@outlook.com', '0713332221', 1);




