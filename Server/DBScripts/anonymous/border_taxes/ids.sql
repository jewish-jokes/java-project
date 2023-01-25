create table ids
(
    object_id              int not null
        primary key,
    cost_taxes_id          int null,
    import_export_taxes_id int null,
    type_taxes_id          int null,
    constraint cost_taxes_id
        foreign key (cost_taxes_id) references cost_taxes (cost_taxes_id),
    constraint export_import_taxes_id
        foreign key (import_export_taxes_id) references import_export_taxes (import_export_taxes_id),
    constraint object_id
        foreign key (object_id) references objects (id_object),
    constraint type_taxes_id
        foreign key (type_taxes_id) references type_taxes (type_taxes_id)
);

create index cost_id_idx
    on ids (cost_taxes_id);

create index export_import_taxes_id_idx
    on ids (import_export_taxes_id);

create index type_taxes_id_idx
    on ids (type_taxes_id);

