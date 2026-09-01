CREATE TABLE IF NOT EXISTS Content (
    id SERIAL PRIMARY KEY ,
    title varchar(255) NOT NULL,
    artist varchar(255) NOT NULL,
    content_type VARCHAR(50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    url VARCHAR(255)
    );
CREATE TABLE IF NOT EXISTS Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL
    );