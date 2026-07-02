# 🚗 Sistema de Controle de Estacionamento (Banco de Dados)

Este repositório contém o script de modelagem e criação de um banco de dados relacional para um **Sistema de Controle de Estacionamento**. O modelo foi projetado de forma simples e eficiente para gerenciar o fluxo de entrada, permanência e saída (com histórico de pagamento) de veículos.

## 🗺️ Arquitetura do Banco de Dados

O sistema é composto por 3 tabelas que organizam o ciclo de vida de um veículo dentro do estacionamento:

1. **`veiculos`**: Cadastro básico que associa a placa do veículo ao seu tipo (ex: Carro, Moto, Caminhonete). Funciona como uma tabela de referência.
2. **`movimentacao`**: Controla o pátio em tempo real. Armazena apenas os veículos que **estão atualmente estacionados**, registrando o momento exato da entrada.
3. **`historico`**: Armazena o registro permanente de todas as operações finalizadas. Quando um veículo sai e o pagamento é efetuado, os dados saem da tabela de `movimentacao` e são consolidados aqui, incluindo o horário de saída e o valor cobrado.

---

## 🚀 Como Executar o Script

Este script foi desenvolvido utilizando a sintaxe do **MySQL / MariaDB**.

### Passo a Passo:
1. Abra o seu gerenciador de banco de dados de preferência (ex: MySQL Workbench, DBeaver, phpMyAdmin).
2. Copie o código contido no bloco abaixo.
3. Execute o script completo para criar o banco de dados `sistemaestacionamento_db`, as tabelas e as relações necessárias.