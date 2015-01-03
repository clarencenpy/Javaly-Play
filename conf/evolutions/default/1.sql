# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table admin (
  username                  VARCHAR(30) NOT NULL not null,
  constraint pk_admin primary key (username))
;

create table instructor (
  username                  VARCHAR(30) NOT NULL not null,
  constraint pk_instructor primary key (username))
;

create table questions (
  id                        bigint auto_increment not null,
  author_username           VARCHAR(30) NOT NULL,
  title                     VARCHAR(30) NOT NULL,
  class_name                VARCHAR(30) NOT NULL,
  method_name               VARCHAR(30) NOT NULL,
  description               TEXT NOT NULL,
  main_method_code          TEXT,
  helper_method_code        TEXT,
  created_date              DATETIME NOT NULL,
  last_update               TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint pk_questions primary key (id))
;

create table student (
  username                  VARCHAR(30) NOT NULL not null,
  attempts                  bigint,
  constraint pk_student primary key (username))
;

create table test_cases (
  id                        bigint auto_increment not null,
  question_id               bigint,
  input                     VARCHAR(50) NOT NULL,
  output                    VARCHAR(50) NOT NULL,
  constraint pk_test_cases primary key (id))
;

create table users (
  username                  VARCHAR(30) NOT NULL not null,
  name                      VARCHAR(30) NOT NULL,
  password                  VARCHAR(100) NOT NULL,
  salt                      VARCHAR(32) NOT NULL,
  created_date              DATETIME NOT NULL,
  last_update               TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  user_type                 VARCHAR(1) NOT NULL,
  constraint pk_users primary key (username))
;

alter table questions add constraint fk_questions_author_1 foreign key (author_username) references users (username) on delete restrict on update restrict;
create index ix_questions_author_1 on questions (author_username);
alter table test_cases add constraint fk_test_cases_question_2 foreign key (question_id) references questions (id) on delete restrict on update restrict;
create index ix_test_cases_question_2 on test_cases (question_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table admin;

drop table instructor;

drop table questions;

drop table student;

drop table test_cases;

drop table users;

SET FOREIGN_KEY_CHECKS=1;

