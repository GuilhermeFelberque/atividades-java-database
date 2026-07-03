CREATE DATABASE compraonline

create table if not exists pessoa(
id int auto_increment primary key,
nome varchar(100),
cpf varchar(11) not null unique
);

create table if not exists cliente(
id integer auto_increment primary key,
id_pessoa int not null,
endereco varchar(255) not null,
foreign key (id_pessoa) references pessoa(id)
);

create table if not exists compra(
id integer auto_increment primary key,
id_cliente int not null,
valor decimal(15,2)not null,
frete decimal(15,2) not null,
FormaDePagamento enum(
'CARTAO',
'PIX',
'DINHEIRO',
'BOLETO')NOT NULL,
foreign key (id_cliente) references cliente(id)
);

create table if not exists recibo(
Numero_De_Pedido integer auto_increment primary key,
total decimal(15,2),
FormaDePagamento enum(
'CARTAO',
'PIX',
'DINHEIRO',
'BOLETO')NOT NULL,
Nome_Cliente varchar(100)
);

create table if not exists produtos(
id int auto_increment primary key,
nome varchar(150),
preco_unitario decimal(15,2),
marca varchar(150)
);