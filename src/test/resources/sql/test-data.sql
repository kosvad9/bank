INSERT INTO users(phone_number, password, first_name, last_name, patronymic)
VALUES ('+375291111111','qwerty1234','Петр','Иванов','Петрович');

INSERT INTO users(phone_number, password, first_name, last_name, patronymic)
VALUES ('+375292222222','122345678','Ксения','Измайлова','Федоровна');

INSERT INTO users(phone_number, password, first_name, last_name, patronymic)
VALUES ('+375293333333','Hello9955','Илья','Ляхов','Андреевич');

INSERT INTO staff(id_user, role, status)
VALUES (2,'ADMIN','ACTIVE');

INSERT INTO client(id_user, birth_date, passport_number, passport_id, passport_date)
VALUES (1,'2003-01-27','MP1122333','3270103A015PB7','2018-08-10');
