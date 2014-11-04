
use cldb;

CREATE TABLE clfriendfeed (
    id               INTEGER      PRIMARY KEY,
    friend_id        INTEGER,
    post_image_path  VARCHAR(250) NOT NULL,
    post_date        DATE         NULL,
    caption          VARCHAR(250) NOT NULL,
    tags             VARCHAR(250) NOT NULL,
    likes            LONG         NULL
);
