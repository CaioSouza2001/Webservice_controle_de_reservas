create database if not exists reserva_sala;

use reserva_sala;

create table tb_endereco
(
	cep varchar (30),
    logradouro varchar (150) not null,
    bairro varchar (150),
    cidade varchar(150) not null,
    estado varchar (100) not null,
    latitude varchar (200),
    longitude varchar (200),
    ativo tinyint(1) default '1' not null,
constraint pk_tb_endereco primary key (cep)
);

create table tb_empresa
(
	cnpj varchar (200),
    nome varchar (200) not null,
    telefone varchar (25),
    endereco varchar (30),
    id_filial varchar (200),
    tipo varchar (50) not null,
	email varchar (100) not null,
    dominio varchar (50) not null,
    criacao datetime default current_timestamp not null,
    ultima_modificacao datetime default current_timestamp not null,
    horario_abertura datetime,
    horario_encerramento datetime,
	ativo tinyint(1) default '1' not null,
    
constraint pk_tb_empresa primary key (cnpj),
constraint fk_tb_empresa_tb_filial foreign key (id_filial) references tb_empresa (cnpj),
constraint fk_tb_empresa_tb_endereco foreign key (endereco) references tb_endereco (cep)

);

create table tb_sala
(
	id int auto_increment,
    nome varchar (100) not null,
    capacidade_maxima int not null,
    quantidade_assentos int not null,
    andar varchar (50),
    quantidade_ar_condicionado int,
    quantidade_projetor int,
    area varchar(150),
    criacao datetime default current_timestamp not null,
    ultima_modificacao datetime default current_timestamp not null,
    estado varchar (20),
    ativo tinyint(1) default '1' not null,
    id_empresa varchar (200) not null,
    
constraint pk_tb_sala primary key (id),
constraint pk_tb_sala_tb_empregado foreign key (id_empresa) references tb_empresa(cnpj)
);

create table tb_usuario
(
	email varchar (200),
    nome varchar (200) not null,
    senha varchar(500) not null,
    cnpj_empresa varchar (200) not null,
	ativo tinyint(1) default '1' not null,
    criacao datetime default current_timestamp not null ,
    ultima_modificacao datetime default current_timestamp not null ,

constraint pk_tb_usuario primary key (email),
constraint pk_tb_usuario foreign key (cnpj_empresa) references tb_empresa (cnpj)
);

create table tb_reserva
(
	id int auto_increment,
    id_sala int not null,
    id_organizador varchar (200) not null,
    horario_inicio datetime,
    previsao_termino datetime,
    criacao datetime not null default current_timestamp,
	ultima_modificacao datetime not null default current_timestamp,
    ativo tinyint(1) default '1' not null,
    descricao varchar (200) not null,
    titulo varchar (200) not null,

constraint pk_tb_reserva primary key (id),
constraint fk_tb_reserva_tb_sala foreign key (id_sala) references tb_sala(id),
constraint fk_tb_reserva_tb_tb_usuario foreign key (id_organizador) references tb_usuario(email)
);

insert into tb_endereco(cep, logradouro, bairro, cidade, estado, latitude, longitude)
values('81020-520', 'Avenida Marechal Floriano Peixoto', 'Parolin', 'Curitiba', 'Pr', '-25.4521964', '-49.2618287');

insert into tb_empresa(cnpj, nome, telefone, endereco, email, dominio,  tipo, horario_abertura, horario_encerramento)
values('12345', 'Wise Systems', '123456', '81020-520', 'wisessystem@wises.com.br', 'wises.com.br', 'MATRIZ', '1950-01-01 08:00:00', '3000-12-31 19:00:00');

insert into tb_endereco(cep, logradouro, cidade, estado, latitude, longitude)
values('94043', 'Amphitheatre Pkwy', 'Mountain View', 'Ca', '37.422387', '-122.084047');

insert into tb_empresa(cnpj, nome, telefone, endereco, email, dominio, tipo)
values('111', 'Google Inc.', '654321', '94043', 'googleUsa@googlegroup.com', 'googlegroup.com', 'MATRIZ');

insert into tb_endereco(cep, logradouro, cidade, bairro, estado, latitude, longitude)
values('04538-133', 'Avenida Brigadeiro Faria', 'Sao Paulo', 'Itaim Bibi', 'Sp', '-23.586334', '-46.681897');

insert into tb_empresa(cnpj, nome, telefone, endereco, email, dominio,  tipo)
values('222', 'Google Brasil', '40028922', '04538-133', 'googleBR@googlegroup.com.', 'googlegroup.com', 'FILIAL');

update tb_empresa
set id_filial = '222'
where tb_empresa.cnpj like '111';

insert into tb_endereco(cep, logradouro, cidade, bairro, estado, latitude, longitude)
values('83055-9000', 'Avenida Rui Barbosa', 'Sao Jose dos Pinhais', 'Afonso Pena', 'Pr', '-25.501428', '-49.170489');

insert into tb_empresa(cnpj, nome, telefone, endereco, email, dominio,  tipo)
values('4321', 'Boticario', '(41) 3375-7001', '83055-9000', 'boticario@grupoboticario.com.br', 'grupoboticario.com.br', 'MATRIZ');

insert into tb_sala (nome, capacidade_maxima, quantidade_assentos, andar, 
quantidade_ar_condicionado, quantidade_projetor, area, estado, id_empresa)
values('Sala de Vidro', 30, 20, '3', 2, 1, '35.0', 'DISPONIVEL', '12345');

insert into tb_sala (nome, capacidade_maxima, quantidade_assentos, andar, 
quantidade_ar_condicionado, quantidade_projetor, area, estado, id_empresa)
values('Conselho Jedi', 20, 10, '3', 3, 1, '25.0', 'DISPONIVEL', '12345');

insert into tb_sala (nome, capacidade_maxima, quantidade_assentos, andar, 
quantidade_ar_condicionado, quantidade_projetor, area, estado, id_empresa)
values('Games Room', 90, 20, '5', 4, 3, '30.0', 'DISPONIVEL', '111');

insert into tb_sala (nome, capacidade_maxima, quantidade_assentos, andar, 
quantidade_ar_condicionado, quantidade_projetor, area, estado, id_empresa)
values('Sala de Reunioes', 80, 10, '4', 5, 4, '20.0', 'INDISPONIVEL', '222');

insert into tb_sala (nome, capacidade_maxima, quantidade_assentos, andar, 
quantidade_ar_condicionado, quantidade_projetor, area, estado, id_empresa)
values('Perfumes', 70, 5, '2', 2, 2, '10.0', 'DISPONIVEL', '4321');


select * from tb_endereco;
insert into tb_usuario(nome, email, cnpj_empresa)
values('Caio de Souza', 'caio@wises.com.br', '12345');

select * from tb_empresa;
select * from tb_endereco;

select * from tb_usuario;

select @@global.time_zone, @@session.time_zone;

select * from tb_sala;
select * from tb_reserva;

select max(tb_reserva.horario_inicio) from tb_reserva;	

select * from tb_empresa;

select * from tb_Sala;

select tb_reserva from tb_reserva where tb_reserva.id_sala = 1;

select * from tb_reserva where (tb_reserva.horario_inicio) = (select min(horario_inicio) from tb_reserva);