CREATE TABLE sprint (
    id IDENTITY NOT NULL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    link VARCHAR_IGNORECASE(255)
);

CREATE TABLE estoria (
    id IDENTITY NOT NULL PRIMARY KEY,
    id_sprint BIGINT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    link VARCHAR_IGNORECASE(255),
    pontuacao INT,

    CONSTRAINT FK_SPRINT FOREIGN KEY (id_sprint) REFERENCES sprint (id)
);

CREATE TABLE desenvolvedor (
    id IDENTITY NOT NULL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR_IGNORECASE(255) NOT NULL
);

CREATE TABLE voto (
    id_estoria BIGINT NOT NULL,
    id_desenvolvedor BIGINT NOT NULL,
    pontos INT NOT NULL,
    data_hora DATETIME NOT NULL,

    CONSTRAINT PK_VOTO PRIMARY KEY (id_estoria, id_desenvolvedor),
    CONSTRAINT FK_ESTORIA FOREIGN KEY (id_estoria) REFERENCES estoria (id),
    CONSTRAINT FK_DESENVOLVEDOR FOREIGN KEY (id_desenvolvedor) REFERENCES desenvolvedor (id)
);



