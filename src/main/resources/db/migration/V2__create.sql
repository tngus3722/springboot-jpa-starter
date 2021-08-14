create table if not exists jpatest.user(
    id bigint unsigned auto_increment primary key,
    portal_account varchar(50) unique not null,
    password text not null,
    nickname varchar(50) not null
)default character set utf8 collate utf8_general_ci;

create table if not exists jpatest.major (
    id bigint unsigned auto_increment primary key,
    user_id bigint unsigned not null,
    major varchar(20) not null,
    is_deleted tinyint default 0
)default character set utf8 collate utf8_general_ci;

create table if not exists jpatest.address (
    id bigint unsigned auto_increment primary key,
    user_id bigint unsigned not null,
    address varchar(20) not null
)default character set utf8 collate utf8_general_ci;


alter table jpatest.user  add column created_at TIMESTAMP default current_timestamp ;
alter table jpatest.user   add column updated_at TIMESTAMP default current_timestamp ;

alter table jpatest.major   add column created_at TIMESTAMP default current_timestamp ;
alter table jpatest.major   add column updated_at TIMESTAMP default current_timestamp ;

alter table jpatest.address   add column created_at TIMESTAMP default current_timestamp ;
alter table jpatest.address   add column updated_at TIMESTAMP default current_timestamp ;