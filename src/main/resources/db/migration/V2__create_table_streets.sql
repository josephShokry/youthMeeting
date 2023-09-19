CREATE TABLE streets (
    id bigint NOT NULL auto_increment,
    name VARCHAR(255),
    area_id bigint,
    PRIMARY KEY (id)) engine=InnoDB;