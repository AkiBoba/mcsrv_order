create table IF NOT EXISTS orders(
    id serial primary key not null,
    status int not null default 1,
    customer bigint
);