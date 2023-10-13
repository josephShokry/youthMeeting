CREATE TABLE instructors(
    id bigint NOT NULL auto_increment,
    servant_id bigint,
    father_id bigint,
    PRIMARY key (id)
);
ALTER TABLE instructors ADD CONSTRAINT fdfdhgyuy FOREIGN KEY (servant_id) REFERENCES persons (id);
ALTER TABLE instructors ADD CONSTRAINT lafjsl FOREIGN KEY (father_id) REFERENCES fathers (id);
ALTER TABLE instructors ADD CONSTRAINT
chk_either_fk1_or_fk2 CHECK ((servant_id IS NULL AND father_id IS NOT NULL) OR (servant_id IS NOT NULL AND father_id IS NULL));
