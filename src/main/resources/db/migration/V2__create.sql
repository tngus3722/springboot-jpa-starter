create table if not exists user (
    id BIGINT auto_increment PRIMARY KEY,
    portal_account VARCHAR(50) UNIQUE NOT NULL,
    password text NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table if not exists major (
    id BIGINT auto_increment PRIMARY KEY,
    user_id BIGINT NOT NULL,
    major VARCHAR(20) NOT NULL,
    is_deleted tinyint DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table if not exists address (
    id BIGINT auto_increment PRIMARY KEY,
    user_id BIGINT unsigned NOT NULL,
    address VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
