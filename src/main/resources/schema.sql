create table if not exists users (
    id serial primary key,
    mail varchar(255) not null unique,
    name varchar(255) not null,
    hashed_pw varchar(255) not null
);

create table if not exists diary (
    id serial primary key,
    user_id integer not null,
    content text not null,
    created_at timestamptz default current_timestamp not null,
    updated_at timestamptz default null,
    diary_date date not null,
    foreign key (user_id) references users(id),
    unique (user_id, diary_date)
);

create table if not exists memo (
    id serial primary key,
    user_id integer not null,
    content text not null,
    created_at timestamptz not null,
    foreign key (user_id) references users(id)
);

create table if not exists password_recovery (
    id serial primary key,
    user_id integer not null,
    token_hash varchar(64) not null unique,
    expires_at timestamptz not null,
    used_at timestamptz default null,
    foreign key (user_id) references users(id)
);