create table user
(
    id_user  int auto_increment
        primary key,
    login    varchar(45) not null,
    password varchar(45) not null,
    role     varchar(45) not null
);

