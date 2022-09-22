-- liquibase formatted sql

-- changeset User:1663839500976-7

INSERT INTO lector
VALUES
    (1,'Kozlov','Eugene',1, 8000),
    (2,'Lavryniuk','Illia', 2,1200),
    (3,'Dzundza', 'Andrew', 3, 1800),
    (4,'Moskovchuk','Aleksandr', 2, 2300);
-- changeset User:1663839500976-8
INSERT INTO department
VALUES
    (1,'Math', 2),
    (2,'Tourism',4),
    (3,'Gym',1);
-- changeset User:1663839500976-9
INSERT INTO `department_lectors_of_department`
VALUES
    (2,3),
    (1,3);