create table brands (id bigserial primary key, title varchar(255));
insert into brands (title)
values
('BMW'),
('AUDI');

CREATE TABLE cars (id bigserial primary key, model varchar(255), power int, price int, description varchar(255), brand_id bigint references brands (id));
insert into cars (model, power, price, brand_id)
values ('m3', 500, 10000000, 1),
       ('m2 competition', 1000, 100000000, 1);

-- CREATE TABLE users (id bigserial primary key, login varchar(255), password varchar(255), wallet integer, firstname varchar(255), lastname varchar(255));
-- INSERT INTO users (login, password, wallet, firstname, lastname)
-- values ('login', 'password', 1000000, 'мистер', 'тест'),
--         ('admin', 'password', 10000000, 'мистер', 'суперАдмин');

CREATE TABLE car_options (id bigserial primary keep, car_id bigint references cars (id), option_name varchar (255));
insert into car_options (car_id, option_name)
values (1, 'Черная крыша'),
       (1, 'Алькантара'),
       (1, 'ABS'),
       (1, 'Климат контроль'),
       (1, 'Парковочный ассистент'),
       (2, 'Диски m competition'),
       (2, 'Мотор 800 л.с.'),
       (2, 'Расход масла 100 л на 100 км'),
       (2, 'Парковочный ассистент'),