CREATE TABLE families (
    id bigint NOT NULL auto_increment,
    family_level INTEGER,
    name VARCHAR(255),
    joining_year INTEGER,
    PRIMARY key (id)) engine=InnoDB;