CREATE DATABASE library;

\connect library;

CREATE TABLE book_places
(
    book_place_id SERIAL PRIMARY KEY,
    floor         INT NOT NULL,
    bookcase      INT NOT NULL,
    shelf         INT NOT NULL,
    CONSTRAINT unique_book_place UNIQUE (floor, bookcase, shelf)
);

CREATE TABLE books
(
    book_id          SERIAL PRIMARY KEY,
    book_place_id    INT REFERENCES book_places (book_place_id),
    author           VARCHAR(100) NOT NULL,
    title            VARCHAR(100) NOT NULL,
    publisher        VARCHAR(100) NOT NULL,
    publication_year INT          NOT NULL,
    num_pages        INT          NOT NULL,
    writing_year     INT          NOT NULL,
    weight_grams     INT          NOT NULL
);

INSERT INTO book_places (floor, bookcase, shelf)
VALUES (1, 1, 1),
       (1, 1, 2),
       (1, 1, 3),
       (1, 2, 1),
       (1, 2, 2),
       (1, 2, 3),
       (1, 3, 1),
       (1, 3, 2),
       (1, 3, 3),
       (2, 1, 1),
       (2, 1, 2),
       (2, 1, 3),
       (2, 2, 1),
       (2, 2, 2),
       (2, 2, 3),
       (2, 3, 1),
       (2, 3, 2),
       (2, 3, 3),
       (3, 1, 1),
       (3, 1, 2),
       (3, 1, 3),
       (3, 2, 1),
       (3, 2, 2),
       (3, 2, 3),
       (3, 3, 1),
       (3, 3, 2),
       (3, 3, 3);

INSERT INTO books (book_place_id, author, title, publisher, publication_year, num_pages, writing_year, weight_grams)
VALUES (1, 'Лев Толстой', 'Война и мир', 'Издательство Альфа', 1869, 1400, 1863, 1200),
       (1, 'Александр Пушкин', 'Евгений Онегин', 'Издательство Бета', 1833, 300, 1823, 500),
       (1, 'Фёдор Достоевский', 'Преступление и наказание', 'Издательство Гамма', 1866, 500, 1865, 800),
       (2, 'Михаил Булгаков', 'Мастер и Маргарита', 'Издательство Дельта', 1967, 400, 1940, 700),
       (2, 'Иван Гончаров', 'Обломов', 'Издательство Эпсилон', 1859, 500, 1857, 600),
       (3, 'Антон Чехов', 'Вишнёвый сад', 'Издательство Зета', 1904, 150, 1903, 300),
       (4, 'Александр Пушкин', 'Евгений Онегин', 'Издательство Бета', 1833, 300, 1823, 500),
       (4, 'Фёдор Достоевский', 'Преступление и наказание', 'Издательство Гамма', 1866, 500, 1865, 800),
       (4, 'Лев Толстой', 'Война и мир', 'Издательство Альфа', 1869, 1400, 1863, 1200),
       (4, 'Михаил Булгаков', 'Мастер и Маргарита', 'Издательство Дельта', 1967, 400, 1940, 700),
       (4, 'Иван Гончаров', 'Обломов', 'Издательство Эпсилон', 1859, 500, 1857, 600),
       (4, 'Антон Чехов', 'Вишнёвый сад', 'Издательство Зета', 1904, 150, 1903, 300),
       (4, 'Николай Гоголь', 'Мёртвые души', 'Издательство Иота', 1842, 400, 1841, 900),
       (4, 'Александр Солженицын', 'Архипелаг ГУЛАГ', 'Издательство Каппа', 1973, 2000, 1968, 1500),
       (4, 'Анна Ахматова', 'Реквием', 'Издательство Лямбда', 1963, 50, 1935, 200),
       (4, 'Иван Тургенев', 'Отцы и дети', 'Издательство Мю', 1862, 400, 1861, 600),
       (5, 'Лев Толстой', 'Анна Каренина', 'Издательство Альфа', 1877, 900, 1873, 1100),
       (5, 'Фёдор Достоевский', 'Братья Карамазовы', 'Издательство Гамма', 1880, 800, 1879, 1000),
       (5, 'Александр Пушкин', 'Руслан и Людмила', 'Издательство Бета', 1820, 200, 1817, 400),
       (5, 'Иван Гончаров', 'Обрыв', 'Издательство Эпсилон', 1849, 350, 1847, 550),
       (5, 'Михаил Булгаков', 'Собачье сердце', 'Издательство Дельта', 1925, 250, 1925, 500),
       (6, 'Антон Чехов', 'Три сестры', 'Издательство Зета', 1901, 200, 1900, 300),
       (6, 'Николай Гоголь', 'Ревизор', 'Издательство Иота', 1836, 150, 1836, 250),
       (6, 'Александр Солженицын', 'Раковый корпус', 'Издательство Каппа', 1969, 800, 1966, 1200),
       (6, 'Анна Ахматова', 'Поэма без героя', 'Издательство Лямбда', 1960, 100, 1959, 150),
       (6, 'Иван Тургенев', 'Накануне', 'Издательство Мю', 1860, 350, 1859, 550);