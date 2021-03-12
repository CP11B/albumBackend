DROP TABLE IF EXISTS `album` CASCADE;
CREATE TABLE album (
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(255),
    artist VARCHAR(255),
    img_src VARCHAR(255),
    play_src VARCHAR(255),
    release_year INTEGER NOT NULL,
    PRIMARY KEY (id)
);