
-- Script SQL corregido para CRUD_Terminal_MySQL

CREATE DATABASE IF NOT EXISTS mydb CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE mydb;

DROP TABLE IF EXISTS personas;
CREATE TABLE personas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    edad INT NOT NULL CHECK (edad > 0)
);

-- Insertando datos de ejemplo sin definir el ID manualmente
INSERT INTO personas (nombre, edad) VALUES
    ('Miguel', 45),
    ('Juan', 45);
