create sequence address_SEQ start with 1 increment by 1;
create sequence phone_seq start with 1 increment by 1;

create table address (
    client_id bigint not null unique,
    id bigint not null,
    street varchar(255),
    primary key (id)
);

alter table if exists address add constraint FK7156ty2o5atyuy9f6kuup9dna foreign key (client_id) references client;

create table phone (
    client_id bigint not null,
    id bigint not null,
    number varchar(255),
    primary key (id)
);

alter table if exists phone add constraint FK3o48ec26lujl3kf01hwqplhn2 foreign key (client_id) references client;