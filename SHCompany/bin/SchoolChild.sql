create table employee (
    joindate date not null,
    code number(5),
    name varchar2(20) not null,
    rrnum varchar2(13) not null unique,
    addr varchar2(50) not null,
    addr2 varchar2(50) not null,
    email varchar2(50) not null,
    resigndate date,
    primary key(code)
);

create table salary (
    no number(4),
    year number(4) not null,
    month number(2)not null,
    code number(5) not null unique,
    name varchar2(20) not null,
    basepay number(8) not null,
    bonus number(8),
    overpay number(8),
    sumpay number(8) not null,
    pension number(6) not null,
    health number(6) not null,
    empment number(6) not null,
    withtax number(6) not null,
    localtax number(6) not null,
    sumtax number(6) not null,
    actualpay number(8) not null,
    primary key(no),
    foreign key(code) references employee (code)
);

create sequence salary_seq
start with 1
increment by 1;