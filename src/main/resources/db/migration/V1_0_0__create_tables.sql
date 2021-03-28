create table user (
    id int auto_increment,
    username varchar(64) not null unique,
    password varchar(256) not null,
    is_admin bool not null,
    is_blocked bool not null,
    constraint user_pk
        primary key (id)
);

create table category (
    id int auto_increment,
    parent_id int null,
    name varchar(64) null,
    constraint category_pk
        primary key (id),
    constraint category_category_id_fk
        foreign key (parent_id) references category (id)
);

create table product (
    id int auto_increment,
    title varchar(255) not null,
    price decimal not null,
    rate_avg decimal null,
    description text null,
    category_Id int not null,
    constraint product_pk
        primary key (id)
);

create table rate(
    id int auto_increment,
    product_id int not null,
    user_id int not null,
    rate int not null,
    comment text null,
    created date not null,
    constraint rate_pk
        primary key (id)
);







