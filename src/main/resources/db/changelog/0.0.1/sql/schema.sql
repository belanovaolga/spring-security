CREATE TABLE IF NOT EXISTS "user" (
    id        INTEGER      NOT NULL,
    username  VARCHAR(45)  NOT NULL,
    password  TEXT         NOT NULL,
    algorithm VARCHAR(45)  NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT uq_user_username UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS "authority" (
    id   INTEGER      NOT NULL,
    name VARCHAR(45)  NOT NULL,
    "user" INTEGER    NOT NULL,
    CONSTRAINT pk_authority PRIMARY KEY (id),
    CONSTRAINT fk_authority_user FOREIGN KEY ("user") REFERENCES "user" (id)
);

CREATE TABLE IF NOT EXISTS "product" (
    id       INTEGER         NOT NULL,
    name     VARCHAR(45)     NOT NULL,
    price    DOUBLE PRECISION NOT NULL,
    currency VARCHAR(45)     NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);