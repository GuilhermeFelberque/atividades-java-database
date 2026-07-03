CREATE DATABASE IF NOT EXISTS sistemamedico;

USE sistemamedico;

CREATE TABLE IF NOT EXISTS pacientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    nome VARCHAR(150) NOT NULL,
    UNIQUE KEY (cpf)
);

CREATE TABLE IF NOT EXISTS medicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    especialidade VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    horario VARCHAR(50),
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);
