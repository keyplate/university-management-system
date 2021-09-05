create table account_status
(
    id int not null primary key,
    value text not null
);

insert into account_status values
(1, 'active'),
(2, 'suspended');

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

create table admin
(
    account_id int not null references account(id)
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
    id smallint not null,
    name text not null
);

create table lecturer
(
    account_id int not null references account(id)
);

create table student
(
    account_id int not null references account(id),
    group_id smallint not null references students_group(id)
);

create table subject
(
    id smallserial not null,
    title text not null
);

create table lecturer_lecture_subject
(
    lecturer_id int not null references lecturer(account_id),
    subject_id smallint not null references subject(id)
);