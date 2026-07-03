# 🚀 Minhas Atividades de Java com Banco de Dados

Este repositório foi criado para centralizar e organizar todas as atividades práticas desenvolvidas durante as aulas de Banco de Dados e integração com Java (JDBC). Aqui você encontrará os scripts SQL e os códigos-fonte de cada projeto.

---

## 📌 Índice de Projetos

Abaixo estão listados todos os sistemas desenvolvidos, com links diretos para suas respectivas pastas e uma breve descrição do que foi aprendido.

### 🚗 1. Sistema de Controle de Estacionamento
* **O que faz:** Gerencia o fluxo de entrada, permanência e saída de veículos, calculando o valor a ser pago e guardando um histórico.
* **Conceitos aplicados:** `PRIMARY KEY`, `AUTO_INCREMENT`, chaves estrangeiras e manipulação de datas (`DATETIME`).
* **📂 [Acessar arquivos desta atividade](./Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados)**
* 📂 [Ver Script SQL desta atividade](./SistemaEstacionamentoscript.sql)

### 🩺 2. Sistema Médico
* **O que faz:** Controla o cadastro de pacientes (com trava de CPF único), médicos e o agendamento de consultas por especialidade.
* **Conceitos aplicados:** Restrição de unicidade (`UNIQUE KEY`), chaves estrangeiras (`FOREIGN KEY`) e relacionamentos entre tabelas.
* **📂 [Acessar arquivos desta atividade](./Sistema_De_Agendamento_Medico_refatorado_Enum_Interacao_Banco_De_Dados)**
* 📂 [Ver Script SQL desta atividade](./SistemaMedicoscript.sql)

### 💳 3. Sistema de Conta de Usuário e Assinaturas
* **O que faz:** Faz a gestão do acesso de utilizadores a diferentes planos de subscrição (ex: Bronze, Prata, Ouro) e controla as restrições de e-mail único.
* **Conceitos aplicados:** Uso de chaves exclusivas (`UNIQUE KEY`), tabelas associativas para relações muitos-para-muitos e mapeamento preciso de valores monetários com `DECIMAL`.
* **📂 [Acessar arquivos desta atividade](./Sistema_De_Contas_De_UsuarioStreamig_Interacao_Banco_De_Dados)**
* **📄 [Ver Script SQL desta atividade](./SistemaStreamingscript.sql)**

### 📚 4. Sistema de Biblioteca Escolar
* **O que faz:** Controla o empréstimo de livros por alunos, acompanhando o status de disponibilidade do livro, o total de obras com o estudante e se ele possui multas pendentes.
* **Conceitos aplicados:** Uso de chaves primárias definidas manualmente (`PRIMARY KEY`), valores padrão para novos registros (`DEFAULT 0` e `DEFAULT 1`)`.
* **📂 [Acessar arquivos desta atividade](./Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados)**
* **📄 [Ver Script SQL desta atividade](./SistemaBibliotecascript.sql)**

### 🍔 5. Sistema de Delivery
* **O que faz:** Controla o fluxo de pedidos de um aplicativo de entregas, registrando as credenciais de acesso dos usuários, o cardápio de produtos e os detalhes de fechamento de cada compra (taxas, totais e formas de pagamento).
* **Conceitos aplicados:** Uso de chaves primárias mistas (manuais e `AUTO_INCREMENT`), tipo `DATETIME` para registro de carimbo de data/hora, e o tipo `DECIMAL(10,2)` para cálculos financeiros precisos de taxas e totais.
* **📂 [Acessar arquivos desta atividade](./Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados)**
* **📄 [Ver Script SQL desta atividade](./SistemaDeliveryscript.sql)**

### ✈️ 6. Sistema de Passagens Aéreas
* **O que faz:** Controla o registro de emissão de bilhetes aéreos, armazenando os dados dos passageiros, identificação do voo, classe de assento selecionada e o cálculo financeiro entre o preço original da passagem e o preço final praticado com descontos ou taxas.
* **Conceitos aplicados:** Cláusula de segurança `IF NOT EXISTS` na criação de tabelas, controle de chaves numéricas sequenciais (`AUTO_INCREMENT`) e uso do tipo numérico de alta precisão `DECIMAL` para transações de valores monetários.
* **📂 [Acessar arquivos desta atividade](./Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados)**
* **📄 [Ver Script SQL desta atividade](./SistemaPassagensscript.sql)**

---

## 🛠️ Tecnologias Utilizadas Geral
* **Linguagem de Programação:** Java
* **Banco de Dados:** MySQL / MariaDB
* **Conexão:** JDBC (Java Database Connectivity)
