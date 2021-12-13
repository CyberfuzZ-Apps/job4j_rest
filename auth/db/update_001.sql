CREATE TABLE IF NOT EXISTS person
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(2000) NOT NULL,
    password VARCHAR(2000) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(2000) NOT NULL,
    surname        VARCHAR(2000) NOT NULL,
    tax_number     VARCHAR(2000) NOT NULL,
    date_of_hiring TIMESTAMP     NOT NULL
);

CREATE TABLE IF NOT EXISTS employee_persons
(
    employee_id INTEGER NOT NULL REFERENCES employee (id),
    persons_id  INTEGER NOT NULL UNIQUE REFERENCES person (id)
);


INSERT INTO person (login, password)
VALUES ('parsentev', '123')
ON CONFLICT DO NOTHING;

INSERT INTO person (login, password)
VALUES ('ban', '123')
ON CONFLICT DO NOTHING;

INSERT INTO person (login, password)
VALUES ('ivan', '123')
ON CONFLICT DO NOTHING;


INSERT INTO employee (name, surname, tax_number, date_of_hiring)
VALUES ('John', 'Connor', '111-111-111', '2021-12-14 12:58:49.000000')
ON CONFLICT DO NOTHING;

INSERT INTO employee (name, surname, tax_number, date_of_hiring)
VALUES ('Katie', 'Sierra', '222-222-222', '2021-12-10 12:59:15.000000')
ON CONFLICT DO NOTHING;


INSERT INTO employee_persons (employee_id, persons_id)
VALUES (1, 1)
ON CONFLICT DO NOTHING;

INSERT INTO employee_persons (employee_id, persons_id)
VALUES (1, 2)
ON CONFLICT DO NOTHING;

INSERT INTO employee_persons (employee_id, persons_id)
VALUES (2, 3)
ON CONFLICT DO NOTHING;