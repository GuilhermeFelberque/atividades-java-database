-- ============================================
-- Script de criação do banco de dados
-- sistemamedico
-- ============================================

CREATE DATABASE IF NOT EXISTS sistemamedico;

USE sistemamedico;

-- ============================================
-- Tabela de pacientes
-- ============================================
CREATE TABLE pacientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    nome VARCHAR(150) NOT NULL,
    UNIQUE KEY (cpf)
);

-- ============================================
-- Tabela de médicos
-- ============================================
CREATE TABLE medicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    especialidade VARCHAR(50) NOT NULL
);

-- ============================================
-- Tabela de consultas
-- ============================================
CREATE TABLE consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    horario VARCHAR(50),
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);
