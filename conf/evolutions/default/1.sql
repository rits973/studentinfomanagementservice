# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table app_user (
  id                            bigserial not null,
  first_name                    varchar(255),
  last_name                     varchar(255),
  full_name                     varchar(255),
  phone_number                  varchar(255),
  email                         varchar(255),
  password                      TEXT,
  role                          varchar(7),
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint ck_app_user_role check (role in ('STUDENT','FACULTY')),
  constraint uq_app_user_email unique (email),
  constraint pk_app_user primary key (id)
);


# --- !Downs

drop table if exists app_user cascade;

