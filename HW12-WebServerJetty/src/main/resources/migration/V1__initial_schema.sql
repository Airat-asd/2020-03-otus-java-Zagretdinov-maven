-- Для @GeneratedValue(strategy = GenerationType.IDENTITY)

create table tUsers
(
    name varchar(50) not null primary key,
    passwordHash integer,
    isAnAdministrator boolean
);