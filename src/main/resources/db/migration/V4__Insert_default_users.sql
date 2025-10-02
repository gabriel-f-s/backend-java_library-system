INSERT INTO USERS (username, full_name, email, cpf, password, phone_number, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES
('admin', 'Administrator', 'admin@biblioteca.com', '111.111.111-11', '{pbkdf2}1087699129259d2d62da9adab424e7fb7314ea374b03606c1e0165ddb4abea5b272a1239db789ab6', '34 99999-9999', true, true, true, true),
('gabriel', 'Gabriel Silva', 'gabriel@email.com', '222.222.222-22', '{pbkdf2}1baf6b28b38b38a440e4b5d2071650cb3296fbd3eae44d7a4c032cf8f0d5f7783cea47453d2a90c9', '34 98888-8888', true, true, true, true);

INSERT INTO USER_ROLES (user_id, role_id) VALUES
(1, 1),
(2, 4);