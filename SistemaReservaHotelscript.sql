CREATE DATABASE IF NOT EXISTS sistemareservahotel;

USE sistemareservahotel;

CREATE TABLE IF NOT EXISTS Quartos (
    idQuarto INT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    ValorDiaria DECIMAL(10,2) NOT NULL,
    tipoQuartos VARCHAR(20) NOT NULL,
    diasEstadia INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Chekin (
    idChekin INT AUTO_INCREMENT PRIMARY KEY,
    nomeCliente VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    dataCheckin DATETIME NOT NULL,
    dataCheckout DATETIME NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    ValorTotal DECIMAL(10,2) NOT NULL,
    pagamento VARCHAR(20) NOT NULL,
    idQuarto INT NOT NULL,
    FOREIGN KEY (idQuarto) REFERENCES Quartos(idQuarto)
);