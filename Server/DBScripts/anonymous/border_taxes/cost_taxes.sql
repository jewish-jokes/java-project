create table cost_taxes
(
    under_1000         double not null,
    from_1000_to_10000 double not null,
    over_10000         double not null,
    cost_taxes_id      int    not null
        primary key
);

