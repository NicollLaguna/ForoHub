CREATE TABLE topics
(
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    title   VARCHAR(100) NOT NULL UNIQUE,
    message VARCHAR(200) NOT NULL UNIQUE,
    date    DATETIME     NOT NULL,
    status  TINYINT      NOT NULL,
    author  VARCHAR(100) NOT NULL,
    course  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);