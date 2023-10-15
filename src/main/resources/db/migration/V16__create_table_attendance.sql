CREATE TABLE attendances(
    id bigint NOT NULL auto_increment,
    time time,
    youth_id bigint,
    meeting_id bigint,
    PRIMARY key (id)
);
ALTER TABLE attendances ADD CONSTRAINT ljafl FOREIGN KEY (youth_id) REFERENCES persons (id);
ALTER TABLE attendances ADD CONSTRAINT fajlsf FOREIGN KEY (meeting_id) REFERENCES meetings (id);