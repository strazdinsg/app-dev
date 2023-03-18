-- Here we populate our database with the default users. In production you would not do this, of course
-- This file is executed automatically by Spring Boot, after executing the schema.sql

insert into users (username, password, enabled)
values ('dave', '{bcrypt}$2a$10$nwbEjYKgcomq2rjUPge2JegqI.y4zEcNqRMPdqwFnd1ytorNCQM/y', true),
       ('chuck', '{bcrypt}$2a$12$/NoknpFFPDlzL3kBryJfsur0yeYC2JFqAs7Fd79ypMP6PN/mtSYmC', true);

insert into authorities (username, authority)
values ('chuck', 'ROLE_USER'), ('chuck', 'ROLE_ADMIN'), ('dave', 'ROLE_USER');