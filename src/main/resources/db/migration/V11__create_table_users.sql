CREATE TABLE users(
    username VARCHAR(255),
    password VARCHAR(255),
    enabled TINYINT(1),
    authenticated TINYINT(1),
    person_id bigint,
    role ENUM('ROLE_SERVANT', 'ROLE_SERVANT_HEAD', 'ROLE_FATHER'),
    PRIMARY key (username)) engine=InnoDB;
