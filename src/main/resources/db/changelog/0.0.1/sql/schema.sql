CREATE TABLE IF NOT EXISTS users (
    id        INTEGER      NOT NULL,
    username  VARCHAR(45)  NOT NULL,
    password  TEXT         NOT NULL,
    algorithm VARCHAR(45)  NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uq_users_username UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS authority (
    id   INTEGER      NOT NULL,
    name VARCHAR(45)  NOT NULL,
    users INTEGER    NOT NULL,
    CONSTRAINT pk_authority PRIMARY KEY (id),
    CONSTRAINT fk_authority_users FOREIGN KEY (users) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS product (
    id       INTEGER         NOT NULL,
    name     VARCHAR(45)     NOT NULL,
    price    DOUBLE PRECISION NOT NULL,
    currency VARCHAR(45)     NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);