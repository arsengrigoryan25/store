create table users(
	id serial not null
		constraint user_pk
			primary key,
	username varchar not null,
	password varchar,
	is_admin bool not null,
	is_blocked bool not null
);

create unique index user_username_uindex
	on users (username);

create table category (
    id serial not null
            constraint category_pk
			    primary key,
    parent_id int
		constraint category_category_id_fk
			references category (id),
    name varchar(64) null
);

create table product (
    id serial not null
        constraint product_pk
			    primary key,
    title varchar(255) not null,
    price decimal not null,
    rate_avg decimal null,
    description text null,
    category_id int constraint product_category_id_fk
			references category (id)
);

create table rate(
    id serial not null constraint rate_pk
			    primary key,
    product_id int not null constraint rate_product_id_fk
			references product (id),
    user_id int not null constraint rate_user_id_fk
			references users (id),
    rate int not null,
    comment text null,
    created date not null
);







