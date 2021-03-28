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















