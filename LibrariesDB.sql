drop database if exists librariesDB;
create database librariesDB;
-- show databases;
use librariesDB;
drop table if exists admins;
create table admins(
	id int auto_increment,
    admin_name varchar(100) not null,
    username varchar(50) not null unique,
    admin_password varchar(50) not null,
    email varchar(50) not null unique,
    primary key(id)
);

insert into admins(id,admin_name,username,admin_password,email)
values
('1','Administrador1','admin1','123','example1@email.com'),
('2','Administrador2','admin2','123','example2@email.com');
/*
insert into admins(admin_name,username,admin_password,email)
values ('Pancho','pan','123','pancho@email.com');
*/
drop table if exists carriers;
create table carriers(
	id int auto_increment,
    carrier_name varchar(100) not null,
    username varchar(50) not null unique,
    carrier_password varchar(50) not null,
    email varchar(50) not null unique,
    primary key(id)
);

insert into carriers(id,carrier_name,username,carrier_password,email)
values
('1','tranportista1','carrier1','123','example1@email.com');

drop table if exists final_users;
create table final_users(
	id int auto_increment,
    user_name varchar(100) not null,
    username varchar(50) not null unique,
    user_password varchar(50) not null,
    email varchar(50) not null unique,
    balance decimal(10,2) not null,
    ban boolean default false,
    primary key(id)
);

drop table if exists categories;
create table categories(
	id int auto_increment,
    category_name varchar(100) not null,
    category_description varchar(200) not null,
    primary key(id)
);
/*
insert into categories(id,category_name,category_description)
values
('1','Categoria 1','Descripcion cat 1'),
('2','Categoria 2','Descripcion cat 2');
*/
drop table if exists books;
create table books(
	isbn int auto_increment,
    book_name varchar(100) not null unique,
    price double not null,
    category int not null,
    author varchar(100) not null,
    foreign key(category) references categories(id),
    primary key(isbn)
);
/*
insert into books(isbn,book_name,price,category,author)
values
('1','libro1','10.00','1','jhon'),
('2','libro2','10.00','1','jhon'),
('3','libro3','10.00','2','jhon'),
('4','libro4','10.00','1','jhon');
*/
drop table if exists libraries;
create table libraries(
	id int auto_increment,
    library_name varchar(100) not null,
    address varchar(100) not null unique,
    primary key(id)
);
/*
insert into libraries(id,library_name,address)
values
('1','libreria1','xela'),
('2','libreria2','toto'),
('3','libreria3','ciudad');
*/
drop table if exists existing_books;
create table existing_books(
	library int not null,
    book int not null,
    existence int default 0,
    foreign key (library) references libraries(id),
    foreign key (book) references books(isbn),
    primary key (library,book)
);
/*
insert into existing_books(library,book,existence)
values
('1','1','5'),
('1','2','10'),
('1','3','3'),
('2','2','1'),
('2','3','11'),
('3','3','9');
*/
drop table if exists receptionists;
create table receptionists(
	id int auto_increment,
    receptionist_name varchar(100) not null,
    username varchar(50) not null unique,
    receptionist_password varchar(50) not null,
    email varchar(50) not null unique,
    library int not null,
    foreign key(library) references libraries(id),
    primary key(id)
);
/*
insert into receptionists(id,receptionist_name,username,receptionist_password,email,library)
values
('1','recepcionista1','recep1','123','example1@example','1');
*/
drop table if exists loan_applications;
create table loan_applications(
	id int auto_increment,
    library int not null,
    receptionist int not null,
    final_user int not null,
    isbn int not null,
    loan_application_date date not null,
    state varchar(20) not null,
    delivery_type varchar(20) not null,
    carrier int,
    foreign key(library) references libraries(id),
    foreign key(receptionist) references receptionists(id),
    foreign key(final_user) references final_users(id),
    foreign key(isbn) references books(isbn),
    primary key(id)
);

drop table if exists loans;
create table loans(
	id int auto_increment,
    library int not null,
    receptionist int not null,
    final_user int not null,
    isbn int not null,
    loan_date date not null,
    state varchar(20) not null,
    penalty_fee decimal(10,2) not null,
    foreign key(library) references libraries(id),
    foreign key(receptionist) references receptionists(id),
    foreign key(final_user) references final_users(id),
    foreign key(isbn) references books(isbn),
    primary key(id)
);

drop table if exists transport_between_libraries;
create table transport_between_libraries(
	id int auto_increment,
    library int not null,
    receptionist int not null,
    carrier int not null,
    -- transport_date date not null,
    state varchar(20) not null,
    foreign key(library) references libraries(id),
    foreign key(receptionist) references receptionists(id),
    foreign key(carrier) references carriers(id),
    primary key(id)
);
/*
insert into transport_between_libraries(id,library,receptionist,carrier,state)
values
('1','1','1','1','estado malo');
*/
drop table if exists details_transport;
create table details_transport(
	transport_between_library int not null,
    book int not null,
    units int not null,
    foreign key(transport_between_library) references transport_between_libraries(id),
    foreign key(book) references books(isbn),
    primary key(transport_between_library,book)
);
/*insert into details_transport(transport_between_library,book,units)
values
('1','1','3');

select existing_books.library, existing_books.book, existing_books.existence, 
transport_between_libraries.library, details_transport.book, details_transport.units 
from existing_books
join transport_between_libraries
  on existing_books.library = transport_between_libraries.library
join details_transport
  on details_transport.transport_between_library = transport_between_libraries.id;

select existing_books.library, existing_books.book, existing_books.existence, 
transport_between_libraries.library, details_transport.book, details_transport.units 
from details_transport
join transport_between_libraries
  on details_transport.transport_between_library = transport_between_libraries.id
join existing_books
  on existing_books.library = transport_between_libraries.library;
*/
/*constraint check_books
	check (existing_books.existence<units)*/
/*
details
tranport_between_libraries.library (id)
book (isbn)
existing_books
existence>units  
*/

drop table if exists transport_users;
create table transport_users(
	id int auto_increment,
    library int not null,
    final_user int not null,
    carrier int not null,
    transport_date date not null,
    state varchar(20) not null,
    isbn int not null,
    foreign key(library) references libraries(id),
    foreign key(final_user) references final_users(id),
    foreign key(carrier) references carriers(id),
    foreign key(isbn) references books(isbn),
    primary key(id)
);

drop table if exists incidents;
create table incidents(
	id int auto_increment,
    loan int not null,
    incident_type varchar(20) not null,
    amount_paid decimal(10,2) not null,
    foreign key(loan) references loans(id),
    primary key (id)
);

drop table if exists revocation_requests;
create table revocation_requests(
	id int auto_increment,
    final_user int not null,
    state varchar(20) not null,
    detail varchar(200) not null,
    foreign key(final_user) references final_users(id),
    primary key (id)
);