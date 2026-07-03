-- ============================================
-- Script de criação do banco de dados
-- contausuario
-- ============================================

CREATE DATABASE IF NOT EXISTS contausuario;

USE contausuario;

-- ============================================
-- Tabela de usuários
-- ============================================
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    UNIQUE KEY (email)
);

-- ============================================
-- Tabela de planos
-- ============================================
CREATE TABLE planos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipoPlano VARCHAR(50) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    UNIQUE KEY (tipoPlano)
);

-- ============================================
-- Tabela de assinaturas
-- ============================================
CREATE TABLE assinaturas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuarios_id INT NOT NULL,
    plano_id INT NOT NULL,
    FOREIGN KEY (usuarios_id) REFERENCES usuarios(id),
    CONSTRAINT fk_assinaturas_plano FOREIGN KEY (plano_id) REFERENCES planos(id)
);
