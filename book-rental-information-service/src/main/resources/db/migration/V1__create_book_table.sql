CREATE SEQUENCE book_seq;

CREATE TABLE IF NOT EXISTS book (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('book_seq'),
    title VARCHAR(255),
    author VARCHAR(255),
    isbn VARCHAR(255) UNIQUE NOT NULL,
    category VARCHAR(255),
    borrower VARCHAR(255)
);