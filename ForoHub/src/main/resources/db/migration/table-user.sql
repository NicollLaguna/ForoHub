CREATE TABLE users
(
    id    BIGINT       NOT NULL AUTO_INCREMENT,
    user  VARCHAR(100) NOT NULL,
    pass  VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
);