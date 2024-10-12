drop database sua_base;

CREATE DATABASE sua_base;

USE sua_base;

CREATE TABLE pessoas
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    nome  VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE professores
(
    pessoa_id    INT PRIMARY KEY,
    departamento VARCHAR(100) NOT NULL,
    FOREIGN KEY (pessoa_id) REFERENCES pessoas (id)
        ON DELETE CASCADE
);

CREATE TABLE estudantes
(
    pessoa_id INT PRIMARY KEY,
    matricula VARCHAR(50) NOT NULL UNIQUE,
    FOREIGN KEY (pessoa_id) REFERENCES pessoas (id)
        ON DELETE CASCADE
);
