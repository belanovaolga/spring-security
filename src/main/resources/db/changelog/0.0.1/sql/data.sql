INSERT INTO product (id, name, owner)
    OVERRIDING SYSTEM VALUE
VALUES (1, 'beer', 'nikolai') ON CONFLICT (id) DO NOTHING;

INSERT INTO product (id, name, owner)
    OVERRIDING SYSTEM VALUE
VALUES (2, 'candy', 'nikolai') ON CONFLICT (id) DO NOTHING;

INSERT INTO product (id, name, owner)
    OVERRIDING SYSTEM VALUE
VALUES (3, 'chocolate', 'julien') ON CONFLICT (id) DO NOTHING;