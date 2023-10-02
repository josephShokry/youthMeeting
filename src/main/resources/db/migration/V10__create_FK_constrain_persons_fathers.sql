ALTER TABLE persons ADD COLUMN father_id bigint;
ALTER TABLE persons ADD CONSTRAINT FKld9x6u449arvd73j74w07tffv FOREIGN KEY (father_id) REFERENCES fathers (id);