create table accounts
(
    id int auto_increment,
    username varchar(30) not null,
    password varchar(30) not null,
    constraint accounts_pk
        primary key (id)
);