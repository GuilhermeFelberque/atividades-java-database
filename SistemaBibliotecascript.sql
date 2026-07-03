CREATE DATABASE IF NOT EXISTS sistemabibliotecaescolar;

USE sistemabibliotecaescolar;

CREATE TABLE IF NOT EXISTs aluno (
    matricula INT PRIMARY KEY,
    nomeAluno VARCHAR(100),
    possuiMulta INT DEFAULT 0,
    livrosEmprestados INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS livro (
    codigoLivro INT PRIMARY KEY,
    tituloLivro VARCHAR(150),
    livroDisponivel INT DEFAULT 1
);
