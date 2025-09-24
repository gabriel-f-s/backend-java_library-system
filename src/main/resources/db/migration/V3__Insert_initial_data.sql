INSERT INTO TB_GENRE (name) VALUES
('Ficção Científica'),
('Fantasia'),
('Romance'),
('Suspense'),
('Mistério'),
('Biografia'),
('História'),
('Autoajuda'),
('Terror'),
('Aventura');

INSERT INTO TB_AUTHOR (name) VALUES
('Isaac Asimov'),
('J.R.R. Tolkien'),
('Agatha Christie'),
('Stephen King'),
('George Orwell'),
('Machado de Assis'),
('Clarice Lispector'),
('Yuval Noah Harari'),
('Brené Brown'),
('Neil Gaiman');

INSERT INTO TB_BOOK (title, isbn, publication_year) VALUES
('Eu, Robô', '978-8576572008', 1950),
('O Senhor dos Anéis: A Sociedade do Anel', '978-8595084759', 1954),
('O Assassinato no Expresso do Oriente', '978-8525411182', 1934),
('It: A Coisa', '978-8560280949', 1986),
('1984', '978-8535914849', 1949),
('Memórias Póstumas de Brás Cubas', '978-8538072186', 1881),
('A Hora da Estrela', '978-8532505699', 1977),
('Sapiens: Uma Breve História da Humanidade', '978-8525432186', 2011),
('A Coragem de Ser Imperfeito', '978-8543100643', 2012),
('Sandman: Prelúdios e Noturnos', '978-8573519835', 1989),
('O Iluminado', '978-8544101676', 1977);

INSERT INTO TB_BOOK_AUTHOR (book_id, author_id) VALUES
(1, 1),  -- Eu, Robô -> Isaac Asimov
(2, 2),  -- O Senhor dos Anéis -> J.R.R. Tolkien
(3, 3),  -- O Assassinato no Expresso do Oriente -> Agatha Christie
(4, 4),  -- It: A Coisa -> Stephen King
(5, 5),  -- 1984 -> George Orwell
(6, 6),  -- Memórias Póstumas de Brás Cubas -> Machado de Assis
(7, 7),  -- A Hora da Estrela -> Clarice Lispector
(8, 8),  -- Sapiens -> Yuval Noah Harari
(9, 9),  -- A Coragem de Ser Imperfeito -> Brené Brown
(10, 10), -- Sandman -> Neil Gaiman
(11, 4); -- O Iluminado -> Stephen King

INSERT INTO TB_BOOK_GENRE (book_id, genre_id) VALUES
(1, 1),  -- Eu, Robô -> Ficção Científica
(2, 2),  -- O Senhor dos Anéis -> Fantasia
(2, 10), -- O Senhor dos Anéis -> Aventura
(3, 5),  -- O Assassinato no Expresso do Oriente -> Mistério
(3, 4),  -- O Assassinato no Expresso do Oriente -> Suspense
(4, 9),  -- It: A Coisa -> Terror
(5, 1),  -- 1984 -> Ficção Científica
(6, 3),  -- Memórias Póstumas de Brás Cubas -> Romance
(7, 3),  -- A Hora da Estrela -> Romance
(8, 7),  -- Sapiens -> História
(8, 6),  -- Sapiens -> Biografia
(9, 8),  -- A Coragem de Ser Imperfeito -> Autoajuda
(10, 2), -- Sandman -> Fantasia
(11, 9), -- O Iluminado -> Terror
(11, 4); -- O Iluminado -> Suspense
