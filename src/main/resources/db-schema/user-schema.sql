DROP TABLE LISTINGS IF EXISTS;

CREATE TABLE USERS (
            id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
            name VARCHAR(64) NOT NULL,
            created_at BIGINT NOT NULL,
            updated_at BIGINT NOT NULL
            );
