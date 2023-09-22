CREATE TABLE users(
    username VARCHAR(255),
    password VARCHAR(255),
    enabled TINYINT(1),
    authenticated TINYINT(1),
    person_id bigint,
    roles ENUM('ROLE_Servant', 'ROLE_Servant_Head', 'ROLE_Father'),
    PRIMARY key (username)) engine=InnoDB;
