create table TUSERS
(
  login             VARCHAR2(50) not null,
  name              VARCHAR2(50) not null,
  passwordhash      INTEGER,
  isanadministrator CHAR(1) default 'n'
);
