-- Here we create the necessary tables for our SQL database
-- This file is executed automatically by Spring Boot
-- Code from https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/jdbc.html#servlet-authentication-jdbc-schema-user

create table users
(
    username varchar(50)  not null primary key,
    password varchar(500) not null,
    enabled  boolean      not null
);

create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);