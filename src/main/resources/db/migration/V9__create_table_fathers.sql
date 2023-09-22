CREATE TABLE fathers (
    id bigint NOT NULL auto_increment,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(255),
    church VARCHAR(255),
    PRIMARY key (id)) engine=InnoDB;