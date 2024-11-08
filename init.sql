-- creating a db if it not exists
CREATE DATABASE IF NOT EXISTS line_segments_db;

-- use created db
USE line_segments_db;

-- creating table if it not exists
CREATE TABLE IF NOT EXISTS line_segments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    x1 DOUBLE NOT NULL,
    y1 DOUBLE NOT NULL,
    x2 DOUBLE NOT NULL,
    y2 DOUBLE NOT NULL,
    length DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

