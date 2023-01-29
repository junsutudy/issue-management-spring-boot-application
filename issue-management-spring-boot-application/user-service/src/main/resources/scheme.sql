DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER`
(
    PRIMARY KEY (id),
    id          BIGINT NOT NULL AUTO_INCREMENT,
    email       VARCHAR(100),
    username    VARCHAR(50),
    password    VARCHAR(500),
    profile_url VARCHAR(500),
    created_at  TIMESTAMP DEFAULT NOW(),
    updated_at  TIMESTAMP DEFAULT NOW(),
)
