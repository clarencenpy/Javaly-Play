# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table question (
  id                        bigint auto_increment not null,
  author_id                 bigint,
  title                     VARCHAR(30) NOT NULL,
  class_name                VARCHAR(30) NOT NULL,
  method_name               VARCHAR(30) NOT NULL,
  description               TEXT NOT NULL,
  main_method_code          TEXT NOT NULL,
  helper_method_code        TEXT,
  created_date              DATETIME NOT NULL,
  last_update               TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint pk_question primary key (id))
;

create table test_case (
  id                        bigint auto_increment not null,
  question_id               bigint,
  input                     VARCHAR(50) NOT NULL,
  output                    VARCHAR(50) NOT NULL,
  constraint pk_test_case primary key (id))
;

create table users (
  id                        bigint auto_increment not null,
  name                      VARCHAR(30) NOT NULL,
  username                  VARCHAR(30) NOT NULL,
  password                  VARCHAR(30) NOT NULL,
  created_date              DATETIME NOT NULL,
  last_update               TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint pk_users primary key (id))
;

alter table question add constraint fk_question_author_1 foreign key (author_id) references users (id) on delete restrict on update restrict;
create index ix_question_author_1 on question (author_id);
alter table test_case add constraint fk_test_case_question_2 foreign key (question_id) references question (id) on delete restrict on update restrict;
create index ix_test_case_question_2 on test_case (question_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table question;

drop table test_case;

drop table users;

SET FOREIGN_KEY_CHECKS=1;

