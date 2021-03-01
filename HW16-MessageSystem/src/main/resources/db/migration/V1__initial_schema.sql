create table tUsers
(
    login varchar(50) not null unique primary key,
    name varchar(50),
    passwordHash integer,
    isAnAdministrator char(1)
);

insert into tUsers values ('login', 'n', 'name', 12345);
