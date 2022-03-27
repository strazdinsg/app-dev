-- Here we create the necessary tables for our SQL database
-- This file is executed automatically by Spring Boot

CREATE TABLE users
(
    username   varchar(250) not null primary key,
    password   varchar(250) not null,
    is_enabled boolean      not null
);

create table roles
(
    username varchar(250) not null,
    role     varchar(250) not null,
    primary key (username, role),
    constraint fk_roles_users foreign key (username) references users (username)
);
