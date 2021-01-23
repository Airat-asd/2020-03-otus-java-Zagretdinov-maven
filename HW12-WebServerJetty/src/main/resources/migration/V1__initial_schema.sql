-- Для @GeneratedValue(strategy = GenerationType.IDENTITY)

create table tUsers
(
    login varchar(50) not null primary key,
    name varchar(50) not null,
    passwordHash integer,
    isAnAdministrator char(1)
);