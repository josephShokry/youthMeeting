-- Insert fathers
INSERT INTO persons (DTYPE, first_name, church) VALUES ('Father', 'angelos', 'malak');
INSERT INTO persons (DTYPE, first_name, church) VALUES ('Father', 'mina', 'mary');

-- Insert areas
INSERT INTO areas (area_name) VALUES ('Moharm bek');
INSERT INTO areas (area_name) VALUES ('bab geded');

-- Insert streets
INSERT INTO streets (street_name, area_id) VALUES ('Ishaky', 1);
INSERT INTO streets (street_name, area_id) VALUES ('thoryia', 2);

-- Insert families
INSERT INTO families (family_name, family_level, joining_year) VALUES ('Mark', 3, 2021);
INSERT INTO families (family_name, family_level, joining_year) VALUES ('John', 1, 2023);

-- Insert youths
INSERT INTO persons (DTYPE, first_name, last_name, day_of_birth, phone_number, gender, family_id, street_id, father_id)
VALUES ('Youth', 'Joseph', 'Shokry', '2002-04-09', '01284024832', "MALE",
(SELECT id FROM families WHERE family_name = 'Mark'),
(SELECT id FROM streets WHERE street_name = 'Ishaky'),
(SELECT id FROM persons WHERE first_name = 'angelos' and church = 'malak'));

INSERT INTO persons (DTYPE, first_name, last_name, day_of_birth, phone_number, gender, family_id, street_id, father_id)
VALUES ('Youth', 'Isaac', 'Vector', '2003-09-04', '01278497512', "MALE",
(SELECT id FROM families WHERE family_name = 'John'),
(SELECT id FROM streets WHERE street_name = 'thoryia'),
(SELECT id FROM persons WHERE first_name = 'mina' and church = 'mary'));

INSERT INTO persons (DTYPE, first_name, last_name, day_of_birth, phone_number, gender, family_id, street_id, father_id)
VALUES ('Youth', 'Adel', 'Makram', '1998-10-14', '01579486321', "MALE",
(SELECT id FROM families WHERE family_name = 'Mark'),
(SELECT id FROM streets WHERE street_name = 'Ishaky'),
(SELECT id FROM persons WHERE first_name = 'angelos' and church = 'malak'));

INSERT INTO persons (DTYPE, first_name, last_name, day_of_birth, phone_number, gender, family_id, street_id, father_id)
VALUES ('Youth', 'Fady', 'Shokry', '2003-05-04', '01147547894', "MALE",
(SELECT id FROM families WHERE family_name = 'John'),
(SELECT id FROM streets WHERE street_name = 'thoryia'),
(SELECT id FROM persons WHERE first_name = 'mina' and church = 'mary'));

INSERT INTO persons (DTYPE, first_name, last_name, day_of_birth, phone_number, gender, family_id, street_id, father_id)
VALUES ('Youth', 'Kiro', 'Soliman', '2004-11-22', '01075471369', "MALE",
(SELECT id FROM families WHERE family_name = 'Mark'),
(SELECT id FROM streets WHERE street_name = 'Ishaky'),
(SELECT id FROM persons WHERE first_name = 'angelos' and church = 'malak'));

INSERT INTO persons (DTYPE, first_name, last_name, day_of_birth, phone_number, gender, family_id, street_id, father_id)
VALUES ('Youth', 'Josephine', 'Atef', '2001-01-30', '01578945617', "FEMALE",
(SELECT id FROM families WHERE family_name = 'John'),
(SELECT id FROM streets WHERE street_name = 'thoryia'),
(SELECT id FROM persons WHERE first_name = 'mina' and church = 'mary'));