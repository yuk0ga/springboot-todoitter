CREATE DATABASE IF NOT EXISTS todoitter;
USE todoitter;

create table todo(
    id int auto_increment primary key,
    content varchar(255) null,
    due_date tinyblob null,
    done bit null
);

