create table topicos(

    id BIGINT not null AUTO_INCREMENT,
    titulo VARCHAR(255) not null,
    mensagem TEXT not null,
    dataCriacao VARCHAR(255) not null,
    estadoTopico VARCHAR(50) not null,
    autor VARCHAR(255) not null,
    curso VARCHAR(255) not null,

    primary key(id)

);