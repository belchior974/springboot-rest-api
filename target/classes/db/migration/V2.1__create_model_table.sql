create table model (
    id serial,
    description varchar(50) not null,
    engine_power varchar(50) not null,
    doors integer not null,
    color varchar(50) not null,
    brand_id integer not null,
    constraint pk_model primary key(id),
    constraint description unique(description)
);