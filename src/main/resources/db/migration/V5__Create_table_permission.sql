CREATE TABLE PERMISSION (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE ROLE_PERMISSIONS (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES ROLE (id),
    FOREIGN KEY (permission_id) REFERENCES PERMISSION (id)
);

INSERT INTO PERMISSION (name) VALUES
('BOOKS_READ'),
('BOOKS_WRITE'),
('AUTHORS_READ'),
('AUTHORS_WRITE'),
('USERS_READ'),
('USERS_WRITE');

INSERT INTO ROLE_PERMISSIONS (role_id, permission_id) VALUES
(1, (SELECT id FROM permission WHERE name = 'BOOKS_READ')),
(1, (SELECT id FROM permission WHERE name = 'BOOKS_WRITE')),
(1, (SELECT id FROM permission WHERE name = 'AUTHORS_READ')),
(1, (SELECT id FROM permission WHERE name = 'AUTHORS_WRITE')),
(1, (SELECT id FROM permission WHERE name = 'USERS_READ')),
(1, (SELECT id FROM permission WHERE name = 'USERS_WRITE'));

INSERT INTO ROLE_PERMISSIONS (role_id, permission_id) VALUES
(2, (SELECT id FROM permission WHERE name = 'BOOKS_READ')),
(2, (SELECT id FROM permission WHERE name = 'AUTHORS_READ'));