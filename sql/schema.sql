CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       role_name VARCHAR(50) UNIQUE NOT NULL CHECK (role_name IN ('ADMIN', 'USER'))
);

SELECT e.*, p.name AS position_name, ts.id AS time_slot_id, ts.start_time, ts.end_time, ts.is_booked
FROM employees e
         JOIN positions p ON e.position_id = p.id
         JOIN time_slots ts ON e.id = ts.employee_id
where not ts.is_booked = FALSE;


create table "galleryImage"
(
    id serial primary key,
    url varchar(256)
);




SELECT e.*, p.name AS position_name
FROM employees e
         JOIN positions p ON e.position_id = p.id
WHERE position_id = 2;

alter table employees
    drop column address;

alter table "portfolioImage"
    alter column url type varchar(256);

alter table categories
    alter column img_url type varchar(256);


alter table categories
    add column img_url varchar(128);

select * from "portfolioImage";

alter table employees
    add column experience int;



CREATE TABLE "users_image" (
                               user_id INT,
                               storage_img_name VARCHAR(128),
                               original_img_name VARCHAR(128),
                               size INT,
                               PRIMARY KEY (user_id),
                               FOREIGN KEY (user_id) REFERENCES users(id)
);

select *, r.role_name as role_name from users u
                                            join roles r on u.role_id = r.id;


create table portfilioImage (
                                id serial primary key ,
                                url varchar(128)
);

ALTER TABLE appointments
    ALTER COLUMN status SET DEFAULT 'Не выполнен';

create table images (
                        id serial primary key,
                        url varchar(1024),
                        discription varchar(256)
);


SELECT
    u.name || ' ' || u.surname AS user_name,
    c.name AS category_name,
    e.name || ' ' || e.surname AS employee_name,
    t.start_time AS time_slot_start_time
FROM
    appointments a
        JOIN
    categories c ON c.id = a.category_id
        JOIN
    users u ON u.id = a.user_id
        JOIN
    employees e ON e.id = a.employee_id
        JOIN
    time_slots t ON t.id = a.time_slot_id
where u.id = ?;

alter table appointments
    add column status varchar(255);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       surname VARCHAR(255) NOT NULL,
                       login VARCHAR(255) UNIQUE NOT NULL,
                       phone_number VARCHAR(15) UNIQUE,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       birthday DATE NOT NULL,
                       gender VARCHAR(10) CHECK (gender IN ('Male', 'Female', 'Other')),
                       role_id INT NOT NULL,
                       FOREIGN KEY (role_id) REFERENCES roles(id)
);

alter table  users
    alter column role_id set default 2;


alter table users
    add constraint gender_check check ( gender in ('Мужской', 'Женский'));

delete
from users
where id = 2;

CREATE TABLE positions (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE employees (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           surname VARCHAR(255) NOT NULL,
                           phone_number VARCHAR(20),
                           position_id INTEGER NOT NULL,
                           email VARCHAR(255),
                           address VARCHAR(255),
                           birthday DATE,
                           gender VARCHAR(10),
                           url_image VARCHAR(255),
                           FOREIGN KEY (position_id) REFERENCES positions(id)
);

create table categories (
                            id serial primary key,
                            name varchar(128) not null unique
);

CREATE TABLE time_slots (
                            id SERIAL PRIMARY KEY,
                            employee_id INTEGER NOT NULL,
                            start_time TIMESTAMP NOT NULL,
                            end_time TIMESTAMP NOT NULL,
                            is_booked BOOLEAN default FALSE,
                            FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);



CREATE TABLE haircuts (
                          id SERIAL PRIMARY KEY,
                          name varchar(128) NOT NULL UNIQUE,
                          description varchar(256),
                          category_id int not null,
                          foreign key (category_id) references categories(id) on delete cascade

);

INSERT INTO positions (name) VALUES ('MANAGER');
INSERT INTO positions (name) VALUES ('BARBER');

INSERT INTO employees (name, surname, phone_number, position_id, email, address, birthday, gender, url_image)
VALUES ('John', 'Doe', '123-456-7890', 1, 'john.doe@example.com', '123 Main St', '1980-01-01', 'Male', 'http://example.com/john.jpg');

INSERT INTO employees (name, surname, phone_number, position_id, email, address, birthday, gender, url_image)
VALUES ('Jane', 'Smith', '987-654-3210', 2, 'jane.smith@example.com', '456 Elm St', '1990-02-02', 'Female', 'http://example.com/jane.jpg');

INSERT INTO employees (name, surname, phone_number, position_id, email, address, birthday, gender, url_image)
VALUES ('Alice', 'Johnson', '555-123-4567', 1, 'alice.johnson@example.com', '789 Oak St', '1985-03-03', 'Female', 'http://example.com/alice.jpg');

INSERT INTO employees (name, surname, phone_number, position_id, email, address, birthday, gender, url_image)
VALUES ('Bob', 'Brown', '333-444-5555', 2, 'bob.brown@example.com', '101 Pine St', '1995-04-04', 'Male', 'http://example.com/bob.jpg');




SELECT e.*, p.name as positions_name from employees e
                                              join positions p on e.position_id = p.id;

SELECT e.*, p.name as positions_name
from employees e
         join positions p on e.position_id = p.id
where e.id = 3;

alter table positions
    add column salary float4 default 0;

alter table employees
    alter column url_image TYPE varchar(1000);

create table appointments(
                             id serial primary key,
                             user_id integer not null,
                             category_id integer not null,
                             employee_id integer not null,
                             time_slot_id integer not null,
                             foreign key (user_id) references users(id) on delete cascade,
                             foreign key (category_id) references categories(id) on DELETE cascade,
                             foreign key (employee_id) references employees(id) on delete cascade,
                             foreign key (time_slot_id) references time_slots(id) on delete cascade
);

SELECT * FROM categories;

alter table categories add column price int not null check ( price >=0 );

alter table appointments alter column user_id drop not null ;

select u.*, r.role_name as role_name from users u
                                              join roles r on u.role_id = r.id
where u.email = 'yroboros2020@mail.ru';

SELECT e.*, p.name AS position_name, ts.id AS time_slot_id, ts.start_time, ts.end_time, ts.is_booked
FROM employees e
         JOIN positions p ON e.position_id = p.id
         JOIN time_slots ts ON e.id = ts.employee_id
WHERE e.id = 2;

SELECT e.*, p.name as positions_name from employees e
                                              join positions p on e.position_id = p.id

