CREATE DATABASE IF NOT EXISTS sistemadelivery;

USE sistemadelivery;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS produtos (
    codigo INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_login VARCHAR(100) NOT NULL,
    numeroPedido INT NOT NULL,
    tipoPagamento VARCHAR(20) NOT NULL,
    totalProdutos DECIMAL(10,2) NOT NULL,
    taxaEntrega DECIMAL(10,2) NOT NULL,
    totalFinal DECIMAL(10,2) NOT NULL,
    dataPedido DATETIME NOT NULL
);
