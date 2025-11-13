DROP DATABASE IF EXISTS dwese_ticket_logger_webapp;
CREATE DATABASE dwese_ticket_logger_webapp CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE dwese_ticket_logger_webapp;

CREATE TABLE IF NOT EXISTS region (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    image VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS province (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    region_id INT NOT NULL,
    CONSTRAINT fk_province_region
        FOREIGN KEY (region_id) REFERENCES region(id)
        ON DELETE CASCADE
);