INSERT INTO families (family_name)
VALUES ('mark');

INSERT INTO persons (DTYPE, first_name, family_id)
VALUES ('Servant', 'jos', (SELECT id FROM families WHERE family_name = 'mark'));

INSERT INTO users (username, password, roles, person_id,authenticated,enabled)
VALUES ('joseph servant', 'password', 'ROLE_Servant', (SELECT id FROM persons WHERE first_name = 'jos'),0,0);
------------------------------------------------------------------------------------

INSERT INTO families (family_name)
VALUES ('john');

INSERT INTO persons (DTYPE, first_name, family_id)
VALUES ('Servant', 'fad', (SELECT id FROM families WHERE family_name = 'john'));

INSERT INTO users (username, password, roles, person_id,authenticated,enabled)
VALUES ('fady servant', 'password', 'ROLE_Servant', (SELECT id FROM persons WHERE first_name = 'fad'),0,0);
------------------------------------------------------------------------------------

INSERT INTO users (username, password, roles, person_id,authenticated,enabled)
VALUES ('joseph head', 'password', 'ROLE_SERVANT_HEAD', null,0,0);
