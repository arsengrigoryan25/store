-- alter table f_product_category
--     add constraint f_product_category_category_id_fk
--         foreign key (categoryId) references category (id);
--
-- alter table f_product_category
--     add constraint f_product_category_product_id_fk
--         foreign key (productId) references product (id);

alter table rate
    add constraint f_rate_user_id_fk
        foreign key (user_id) references user (id);

alter table rate
    add constraint f_rate_product_id_fk
        foreign key (product_id) references product (id);

alter table category
    add constraint f_category_parent_id_fk
        foreign key (parent_id) references category (id);

create index product_title_uindex on product (title);
create index product_price_description_index on product (price);


