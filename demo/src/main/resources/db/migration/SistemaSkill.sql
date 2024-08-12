-- Criação das sequences
CREATE SEQUENCE usuario_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE habilidade_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE habilidade_do_usuario_seq START WITH 1 INCREMENT BY 1;

-- Criação da tabela usuario
CREATE TABLE usuario (
    id_usuario BIGINT DEFAULT NEXTVAL('usuario_seq') PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

-- Criação da tabela habilidade
CREATE TABLE habilidade (
    hab_id BIGINT DEFAULT NEXTVAL('habilidade_seq') PRIMARY KEY,
    nome_hab VARCHAR(255) UNIQUE NOT NULL
);

-- Criação da tabela habilidade_do_usuario
CREATE TABLE habilidade_do_usuario (
    id_hab_usuario BIGINT DEFAULT NEXTVAL('habilidade_do_usuario_seq') PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    hab_id BIGINT NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
    CONSTRAINT fk_habilidade FOREIGN KEY (hab_id) REFERENCES habilidade (hab_id),
    CONSTRAINT unique_usuario_habilidade UNIQUE (id_usuario, hab_id)
);
