drop database projectstoriesdb;
drop user projectstoriesuser;
create user projectstoriesuser with password 'password';
create database projectstoriesdb with template=template0 owner=projectstoriesuser;
\connect projectstoriesdb;
alter default privileges grant all on tables to projectstoriesuser;
alter default privileges grant all on sequences to projectstoriesuser;

create table ps_users(
    user_id integer primary key not null,
    email varchar(30) not null,
    password text not null
);

create table ps_stories(
    story_id integer primary key not null,
    user_id integer not null,
    title varchar(50) not null,
    imageURL varchar,
    description varchar(300) not null,
    text varchar not null,
    views integer DEFAULT 0 not null,
    downloads integer DEFAULT 0 not null
);

alter table ps_stories add constraint cat_users_fk
foreign key (user_id) references ps_users(user_id);

create sequence ps_users_seq increment 1 start 1;
create sequence ps_stories_seq increment 1 start 1;