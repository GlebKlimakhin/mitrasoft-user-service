create table users(
    id bigserial not null unique primary key,
    email varchar(50) not null unique,
    password varchar(150)
);

create table roles(
    id serial not null unique primary key,
    name varchar(30) not null unique
);

create table users_roles(
    user_id bigint references users(id) primary key,
    role_id int references roles(id)
);

insert into users(email, password) VALUES
('a@gg.ru', '$2a$10$lj7J5uuTThjy.6WpUNGJuuha0/IuoqkY4Y7HZyGwCbZ10Xi10yjWu'), --! pass: test1
('b@gg.ru', '$2a$12$B7e9i8d.m0NuvMhldNFfXeTByy9xpL5Cy0wpz1bYCk6you7Un4o8e'), --! pass: test2
('c@gg.ru', '$2a$12$12FIrMUgbd1MtA9rBY5/H.sRyXT5tZFGWUwKDZ.huonw.iYxf5eYi'), --! pass: test3
('d@gg.ru', '$2a$12$/Y0hSxxAAI.3y0CHIuSJme340agmzIhRoy1UgKc851QbjcbkiBlse'), --! pass: test4
('e@gg.ru', '$2a$12$uvSI3Y39VJNYeQw4.hQQhu20wFFCDcNtOHrlrGdJ8jextV042Y7rO'); --! pass: test5

insert into roles(name) VALUES
('ROLE_USER'),
('ROLE_ADMIN');

insert into users_roles (user_id, role_id) VALUES
(1, 2),
(2, 1),
(3, 1),
(4, 2),
(5, 1);
