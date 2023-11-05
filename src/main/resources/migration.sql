CREATE TABLE IF NOT EXISTS api_key
(
    client varchar(255) primary key,
    secret varchar(255) not null
);

CREATE TABLE IF NOT EXISTS log
(
    id          serial primary key,
    description text         not null,
    ip          varchar(255) not null,
    endpoint    varchar(255) not null,
    client      varchar(255) not null references api_key (client),
    requestedAt timestamp    not null default now()
);

# NOT HASHED KEY F9ikNrL1qNbi1m18YNhBSwFNc502W5AYBSeUyxgzTbpaxxxiiq6D1ZJKmaEB
INSERT IGNORE INTO api_key(client, secret)
VALUES ('listwibuku-monolith', '61087720abaa0ec54c16372c059d1727bd9f788134ae8866d43696436cdf4bd0');

# NOT HASHED KEY 1MHJG1a7gQZWjR1YShD2D5DUU2hQ7HLkUSnLbXULDw08iBHbLApMV4AZAR3V
INSERT IGNORE INTO api_key(client, secret)
VALUES ('listwibuku-rest', '73253e86c253eae1b95bac3ee4ea890f004b5e0700aca9baf92e9163c92c0074');