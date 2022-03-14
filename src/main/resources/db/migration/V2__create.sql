create table if not exists user (
    id bigint unsigned auto_increment primary key,
    portal_account varchar(50) unique not null,
    password text not null,
    nickname varchar(50) not null
);

create table if not exists major (
    id bigint unsigned auto_increment primary key,
    user_id bigint unsigned not null,
    major varchar(20) not null,
    is_deleted tinyint default 0
);

create table if not exists address (
    id bigint unsigned auto_increment primary key,
    user_id bigint unsigned not null,
    address varchar(20) not null
);


alter table user add column created_at TIMESTAMP default current_timestamp;
alter table user add column updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

alter table major add column created_at TIMESTAMP default current_timestamp;
alter table major add column updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

alter table address add column created_at TIMESTAMP default current_timestamp;
alter table address add column updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;