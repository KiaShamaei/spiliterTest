CREATE TABLE `gang` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `admin_user_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `email` varchar(256) NOT NULL,
  `contact` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `bill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `amount` double NOT NULL,
  `status` varchar(64) NOT NULL,
  `gang_id` bigint,
  PRIMARY KEY (`id`)
)

CREATE TABLE user_gang (
  id bigint NOT NULL AUTO_INCREMENT,
  gang_id bigint NOT NULL,
  user_id bigint NOT NULL,
  PRIMARY KEY (`id`)
)

alter table user_gang add constraint fk1 foreign key (gang_id) references gang(id);
alter table user_gang add constraint fk2 foreign key (user_id) references `user_info` (`id`)
alter table bill add constraint fk3 foreign key (gang_id) references gang(id);

CREATE TABLE `bill_user_gang` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk5` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `fk6` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`)
)

-- alter table bill add column status tinyint(3) not null default 1;
alter table bill add column created_at datetime not null default current_timestamp;
alter table bill add column updated_at datetime not null default current_timestamp on update current_timestamp;

alter table gang add column created_at datetime not null default current_timestamp;
alter table gang add column updated_at datetime not null default current_timestamp on update current_timestamp;

alter table user_info add column created_at datetime not null default current_timestamp;
alter table user_info add column updated_at datetime not null default current_timestamp on update current_timestamp;

alter table user_gang add column created_at datetime not null default current_timestamp;
alter table user_gang add column updated_at datetime not null default current_timestamp on update current_timestamp;

alter table bill_user_gang add column created_at datetime not null default current_timestamp;
alter table bill_user_gang add column updated_at datetime not null default current_timestamp on update current_timestamp;