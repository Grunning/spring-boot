create database test;
use test;
create table `user`(
`id` int auto_increment primary key,
`name` varchar(255),
`age` int,
`state` int
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4