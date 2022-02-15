DELETE FROM book;
DELETE FROM genre;

INSERT INTO genre (id, genre)
VALUES (1, 'Parody');
INSERT INTO genre (id, genre)
VALUES (2, 'Computer Networks');
INSERT INTO genre (id, genre)
VALUES (3, 'Medical Applied Psychology');

INSERT INTO book (id, title, year_issued, number_of_pages, genre_id)
    VALUES (1, '12 Rules for Life', 2018, 409, 3);
INSERT INTO book (id, title, year_issued, number_of_pages, genre_id)
    VALUES (2, 'Computer networking', 2021, 800, 2);
INSERT INTO book (id, title, year_issued, number_of_pages, genre_id)
    VALUES (777, 'The Truth About Chuck Norris: 400 Facts About the World''s Greatest Human', 2007, 176, 1);




