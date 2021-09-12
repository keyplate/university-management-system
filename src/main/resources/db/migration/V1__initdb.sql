create table account_status
(
    id int not null primary key,
    value text not null
);

insert into account_status values
(0, 'active'),
(1, 'suspended');

create table account
(
    id serial primary key,
    email text not null,
    password text not null,
    first_name text not null,
    last_name text not null,
    status int not null references account_status(id)
);

create unique index account_email_uid on account(email);

create table dean
(
    account_id int primary key references account(id) on delete cascade
);

create table refresh_token
(
    value uuid not null primary key,
    account_id int references account(id),
    issued_at timestamp not null,
    expire_ad timestamp not null,
    next uuid,
    constraint refresh_token_account_fk foreign key (account_id)
        references account(id) on delete cascade,
    constraint refresh_token_next foreign key(next)
        references refresh_token(value) on delete cascade
);

create table students_group
(
    id serial primary key,
    name text not null
);

create table lecturer
(
    account_id int primary key references account(id) on delete cascade
);

create table student
(
    account_id int primary key references account(id) on delete cascade ,
    group_id int not null references students_group(id) on delete set null
);

create table subject
(
    id serial primary key,
    title text not null
);

create table course
(
    id bigserial primary key,
    lecturer_id int not null references lecturer(account_id) on delete cascade,
    subject_id smallint not null references subject(id)on delete cascade
);

create table grade
(
    id bigserial primary key,
    course_id int not null references course(id) on delete cascade,
    student_id int not null references student(account_id) on delete cascade,
    value int check ( (value > 0) and (value <= 100) )
);

