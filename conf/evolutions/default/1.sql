# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table admin (
  username                  VARCHAR(30) NOT NULL not null,
  constraint pk_admin primary key (username))
;

create table attempt (
  attempt_id                bigint,
  question_id               bigint,
  submitted_code            varchar(255),
  last_timing               integer,
  cumulative_timing         integer,
  attempt_count             integer,
  last_attempted_date       datetime)
;

create table instructor (
  name                      VARCHAR(30) NOT NULL,
  password                  VARCHAR(100) NOT NULL,
  salt                      VARCHAR(32) NOT NULL,
  created_date              DATETIME NOT NULL,
  last_update               TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  user_type                 VARCHAR(1) NOT NULL,
  constraint pk_instructor primary key (name, password, salt, created_date, last_update, user_type))
;

create table questions (
  id                        bigint auto_increment not null,
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

alter table attempt add constraint fk_attempt_question_1 foreign key (question_id) references questions (id) on delete restrict on update restrict;
create index ix_attempt_question_1 on attempt (question_id);
alter table test_cases add constraint fk_test_cases_question_2 foreign key (question_id) references questions (id) on delete restrict on update restrict;
create index ix_test_cases_question_2 on test_cases (question_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table admin;

drop table attempt;

drop table instructor;

drop table questions;

drop table student;

drop table test_cases;

SET FOREIGN_KEY_CHECKS=1;

