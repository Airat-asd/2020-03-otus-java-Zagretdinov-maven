create table tUsers
(
    login varchar(50) not null unique primary key,
    name varchar(50),
    passwordHash integer,
    isAnAdministrator char(1)
);
insert into tUsers values ('login1', 'name1', 12345, 'n');