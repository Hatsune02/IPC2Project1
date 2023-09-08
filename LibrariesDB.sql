drop database if exists librariesDB;
create database librariesDB;
-- show databases;
use librariesDB;

create table admins(
	id int auto_increment,
    admin_name varchar(100) not null,
    username varchar(50) not null unique,
    admin_password varchar(50) not null,
    email varchar(50) not null unique,
    primary key(id)
);

create table carriers(
	id int auto_increment,
    carrier_name varchar(100) not null,
    username varchar(50) not null unique,
    carrier_password varchar(50) not null,
    email varchar(50) not null unique,
    primary key(id)
);


create table final_users(
	id int auto_increment,
    user_name varchar(100) not null,
    username varchar(50) not null unique,
    user_password varchar(50) not null,
    email varchar(50) not null unique,
    balance decimal(10,2) not null default 0,
    ban boolean not null default 0,
    premium boolean not null default 0,
    primary key(id)
);


create table categories(
	id int auto_increment,
    category_name varchar(100) not null,
    category_description varchar(200) not null,
    primary key(id)
);



create table books(
	isbn int auto_increment,
    book_name varchar(100) not null unique,
    price double not null,
    category int not null,
    author varchar(100) not null,
    foreign key(category) references categories(id),
    primary key(isbn)
);



create table libraries(
	id int auto_increment,
    library_name varchar(100) not null,
    address varchar(100) not null unique,
    primary key(id)
);



create table existing_books(
	library int not null,
    book int not null,
    existence int default 0,
    foreign key (library) references libraries(id),
    foreign key (book) references books(isbn),
    primary key (library,book)
);


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



create table loan_applications(
	id int auto_increment,
    library int not null,
    receptionist int not null,
    final_user int not null,
    book int not null,	
    loan_application_date date not null,
    state varchar(50) not null,
    delivery_type varchar(20) not null,
    carrier int,
    foreign key(library) references libraries(id),
    foreign key(receptionist) references receptionists(id),
    foreign key(final_user) references final_users(id),
    foreign key(book) references books(isbn),
    foreign key(carrier) references carriers(id) ,
    primary key(id)
);


create table loans(
	id int auto_increment,
    library int not null,
    receptionist int not null,
    final_user int not null,
    book int not null,
    loan_date date not null,
    state varchar(50) not null,
    penalty_fee decimal(10,2) not null,
    foreign key(library) references libraries(id),
    foreign key(receptionist) references receptionists(id),
    foreign key(final_user) references final_users(id),
    foreign key(book) references books(isbn),
    primary key(id)
);


create table transport_between_libraries(
	id int auto_increment,
    libraryO int not null,
    libraryD int not null,
    receptionist int not null,
    carrier int not null,
    transport_date date not null,
    state varchar(50) not null,
    foreign key(libraryO) references libraries(id),
    foreign key(libraryD) references libraries(id),
    foreign key(receptionist) references receptionists(id),
    foreign key(carrier) references carriers(id),
    primary key(id)
);


create table details_transport(

	transport_between_library int not null,
    book int not null,
    units int not null,
    foreign key(transport_between_library) references transport_between_libraries(id),
    foreign key(book) references books(isbn),
    primary key(transport_between_library,book)
);


create table transport_users(
	id int auto_increment,
    library int not null,
    final_user int not null,
    carrier int not null,
    transport_date date not null,
    state varchar(50) not null,
    book int not null,
    foreign key(library) references libraries(id),
    foreign key(final_user) references final_users(id),
    foreign key(carrier) references carriers(id),
    foreign key(book) references books(isbn),
    primary key(id)
);


create table incidents(
	id int auto_increment,
    loan int not null,
    incident_type varchar(50) not null,
    amount_paid decimal(10,2) not null,
    foreign key(loan) references loans(id),
    primary key (id)
);

create table revocation_requests(
	id int auto_increment,
    final_user int not null,
    state varchar(50) not null,
    detail varchar(300) not null,
    foreign key(final_user) references final_users(id),
    primary key (id)
);


create table parameters(
	id int auto_increment,
	parameter_name varchar(100),
    parameter_value decimal(10,2) not null,
    primary key(id)
);
insert into parameters(parameter_name,parameter_value)
value('Costo de suscripción','100'),
('Costo de envio a domicilio','30'),	
('Descuento premium','0.40'),
('Días que se puede prestar un libro','8'),
('Máximo de libros que puede tener prestado un usuario','5'),
('Multa por retraso','50'),
('Días de suspensión de usuarios','15')
;

create table emails(
	email_id int auto_increment,
    sender_id int not null,
    receiver_id int not null,
    body varchar(200) not null,
    primary key (email_id)
);

create table transactions(
	id int auto_increment,
	money_spent decimal(10,2)not null,
    money_recharge decimal(10,2)not null,
    detail varchar(200) not null,
    primary key (id)
);

