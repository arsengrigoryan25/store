ALTER TABLE rate
ADD CONSTRAINT check_discount_amount CHECK (rate BETWEEN 0 AND 5);

INSERT INTO category(name) VALUES ('էլեկտրոնիկա');
INSERT INTO category(name) VALUES ('Մթերք');
INSERT INTO category(name) VALUES ('Կենցաղային տեխնիկա');
INSERT INTO category(name, parent_id) VALUES ('Բջջային հեռախոսներ', 1);
INSERT INTO category(name, parent_id) VALUES ('Պլանշետներ', 1);
INSERT INTO category(name, parent_id) VALUES ('Միրգ և բանջարեղեն', 2);
INSERT INTO category(name, parent_id) VALUES ('Կաթնամթերք', 2);
INSERT INTO category(name, parent_id) VALUES ('Սառնարաններ', 3);
INSERT INTO category(name, parent_id) VALUES ('Գազօջախներ', 3);


INSERT INTO product(title, price, description, category_id) VALUES ('Xiaomi Note 8', 100000, 'RAM 4GB, camera 48Mp', 4);
INSERT INTO product(title, price, description, category_id) VALUES ('iPhone 11', 200000, 'RAM 4GB, camera 25Mp', 4);
INSERT INTO product(title, price, description, category_id) VALUES ('Samsung A51', 150000, 'RAM 3GB, camera 40Mp', 4);
INSERT INTO product(title, price, description, category_id) VALUES ('Sharp', 100000, 'Description Sharp', 8);
INSERT INTO product(title, price, description, category_id) VALUES ('Hitachi', 200000, 'Description Hitachi', 8);
INSERT INTO product(title, price, description, category_id) VALUES ('Toshiba', 150000, 'Description Toshiba', 8);













