# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table admin (
  username                  VARCHAR(30) NOT NULL not null,
  user_username             VARCHAR(30) NOT NULL,
  constraint pk_admin primary key (username))
;

create table attempt (
  attempt_id                bigint auto_increment not null,
  submitted_code            varchar(255),
  last_timing               integer,
  cumulative_timing         integer,
  attempt_count             integer,
  last_attempted_date       datetime,
  user_username             VARCHAR(30) NOT NULL,
  question_id               bigint,
  constraint pk_attempt primary key (attempt_id))
;

create table instructor (
  username                  VARCHAR(30) NOT NULL not null,
  user_username             VARCHAR(30) NOT NULL,
  constraint pk_instructor primary key (username))
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
  author_username           VARCHAR(30) NOT NULL,
  constraint pk_questions primary key (id))
;

create table student (
  username                  VARCHAR(30) NOT NULL not null,
  user_username             VARCHAR(30) NOT NULL,
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

alter table admin add constraint fk_admin_user_1 foreign key (user_username) references users (username) on delete restrict on update restrict;
create index ix_admin_user_1 on admin (user_username);
alter table attempt add constraint fk_attempt_user_2 foreign key (user_username) references users (username) on delete restrict on update restrict;
create index ix_attempt_user_2 on attempt (user_username);
alter table attempt add constraint fk_attempt_question_3 foreign key (question_id) references questions (id) on delete restrict on update restrict;
create index ix_attempt_question_3 on attempt (question_id);
alter table instructor add constraint fk_instructor_user_4 foreign key (user_username) references users (username) on delete restrict on update restrict;
create index ix_instructor_user_4 on instructor (user_username);
alter table questions add constraint fk_questions_author_5 foreign key (author_username) references users (username) on delete restrict on update restrict;
create index ix_questions_author_5 on questions (author_username);
alter table student add constraint fk_student_user_6 foreign key (user_username) references users (username) on delete restrict on update restrict;
create index ix_student_user_6 on student (user_username);
alter table test_cases add constraint fk_test_cases_question_7 foreign key (question_id) references questions (id) on delete restrict on update restrict;
create index ix_test_cases_question_7 on test_cases (question_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table admin;

drop table attempt;

drop table instructor;

drop table questions;

drop table student;

drop table test_cases;

drop table users;

SET FOREIGN_KEY_CHECKS=1;

