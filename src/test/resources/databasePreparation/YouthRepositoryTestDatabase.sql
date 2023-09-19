-- Insert areas
INSERT INTO areas (name) VALUES ('Moharm bek');
INSERT INTO areas (name) VALUES ('bab geded');

-- Insert streets
INSERT INTO streets (name, area_id) VALUES ('Ishaky', 1);
INSERT INTO streets (name, area_id) VALUES ('thoryia', 2);

-- Insert families
INSERT INTO families (name, family_level, joining_year) VALUES ('Mark', 3, 2021);
INSERT INTO families (name, family_level, joining_year) VALUES ('John', 1, 2023);

-- Insert youths
INSERT INTO persons (DTYPE, first_name, last_name, day_of_birth, phone_number, family_id, street_id)
VALUES ('Youth', 'Joseph', 'Shokry', '2002-04-09', '01284024832',
(SELECT id FROM families WHERE name = 'Mark'),
(SELECT id FROM streets WHERE name = 'Ishaky'));

INSERT INTO persons (DTYPE, first_name, last_name, day_of_birth, phone_number, family_id, street_id)
VALUES ('Youth', 'Isaac', 'Vector', '2003-09-04', '01278497512',
(SELECT id FROM families WHERE name = 'John'),
(SELECT id FROM streets WHERE name = 'thoryia'));

INSERT INTO persons (DTYPE, first_name, last_name, day_of_birth, phone_number, family_id, street_id)
VALUES ('Youth', 'Adel', 'Makram', '1998-10-14', '01579486321',
(SELECT id FROM families WHERE name = 'Mark'),
(SELECT id FROM streets WHERE name = 'Ishaky'));

INSERT INTO persons (DTYPE, first_name, last_name, day_of_birth, phone_number, family_id, street_id)
VALUES ('Youth', 'Fady', 'Shokry', '2003-05-04', '01147547894',
(SELECT id FROM families WHERE name = 'John'),
(SELECT id FROM streets WHERE name = 'thoryia'));

INSERT INTO persons (DTYPE, first_name, last_name, day_of_birth, phone_number, family_id, street_id)
VALUES ('Youth', 'Kiro', 'Soliman', '2004-11-22', '01075471369',
(SELECT id FROM families WHERE name = 'Mark'),
(SELECT id FROM streets WHERE name = 'Ishaky'));

INSERT INTO persons (DTYPE, first_name, last_name, day_of_birth, phone_number, family_id, street_id)
VALUES ('Youth', 'Josephine', 'Atef', '2001-01-30', '01578945617',
(SELECT id FROM families WHERE name = 'John'),
(SELECT id FROM streets WHERE name = 'thoryia'));