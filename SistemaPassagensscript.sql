CREATE DATABASE IF NOT EXISTS sistemapassagens;

USE sistemapassagens;

CREATE TABLE IF NOT EXISTS passagens (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomePassageiro VARCHAR(100) NOT NULL,
    numeroVoo VARCHAR(20) NOT NULL,
    tipoClasse VARCHAR(20) NOT NULL,
    precoOriginal DECIMAL(10,2) NOT NULL,
    precoFinal DECIMAL(10,2) NOT NULL
);