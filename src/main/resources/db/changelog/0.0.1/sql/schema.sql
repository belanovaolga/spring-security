CREATE TABLE IF NOT EXISTS token
(
    id SERIAL NOT NULL,
    identifier VARCHAR(45) NULL,
    token TEXT NULL,
    PRIMARY KEY(id)
);