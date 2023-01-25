create table objects
(
    id_object      int         not null
        primary key,
    name           varchar(45) not null,
    cost           double      not null,
    for_export     tinyint     not null,
    for_import     tinyint     not null,
    type           varchar(45) not null,
    object_user_id int         not null,
    constraint object_ibfk_1
        foreign key (object_user_id) references user (id_user)
            on delete cascade
);

create index object_ibfk_1_idx
    on objects (object_user_id);

