-- liquibase formatted sql

-- changeset User:1663844044673-1
CREATE TABLE department (id BIGINT AUTO_INCREMENT NOT NULL, department_name VARCHAR(255) NULL, department_head_id BIGINT NULL, CONSTRAINT pk_department PRIMARY KEY (id));

-- changeset User:1663844044673-2
CREATE TABLE department_lectors_of_department (department_id BIGINT NOT NULL, lectors_of_department_id BIGINT NOT NULL, CONSTRAINT pk_department_lectorsofdepartment PRIMARY KEY (department_id, lectors_of_department_id));

-- changeset User:1663844044673-3
CREATE TABLE lector (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(255) NULL, last_name VARCHAR(255) NULL, degree INT NULL, salary DECIMAL NULL, CONSTRAINT pk_lector PRIMARY KEY (id));

-- changeset User:1663844044673-4
ALTER TABLE department ADD CONSTRAINT FK_DEPARTMENT_ON_DEPARTMENTHEAD FOREIGN KEY (department_head_id) REFERENCES lector (id);

-- changeset User:1663844044673-5
ALTER TABLE department_lectors_of_department ADD CONSTRAINT fk_deplecofdep_on_department FOREIGN KEY (department_id) REFERENCES department (id);

-- changeset User:1663844044673-6
ALTER TABLE department_lectors_of_department ADD CONSTRAINT fk_deplecofdep_on_lector FOREIGN KEY (lectors_of_department_id) REFERENCES lector (id);

