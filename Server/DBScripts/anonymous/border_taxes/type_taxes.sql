create table type_taxes
(
    alcohol_taxes    double not null,
    cigarettes_taxes double not null,
    fur_taxes        double not null,
    jewelry_taxes    double not null,
    other_taxes      double not null,
    type_taxes_id    int    not null
        primary key
);

