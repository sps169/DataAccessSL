SET @@session.foreign_key_checks = 0;

DROP DATABASE IF EXISTS `data_access_sl`;
CREATE DATABASE `data_access_sl`;

USE `data_access_sl`;

CREATE TABLE `boss_history` (
                                `id_programmer` int(10) unsigned NOT NULL,
                                `id_department` int(10) unsigned NOT NULL,
                                `entry_date` datetime NOT NULL,
                                `leave_date` datetime DEFAULT NULL,
                                PRIMARY KEY (`id_programmer`,`id_department`,`entry_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `commit` (
                          `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                          `title` varchar(100) NOT NULL,
                          `text` text NOT NULL,
                          `date` datetime NOT NULL,
                          `id_repository` int(10) unsigned NOT NULL,
                          `id_programmer` int(10) unsigned NOT NULL,
                          `id_issue` int(10) unsigned NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `department` (
                              `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                              `name` varchar(50) NOT NULL,
                              `boss` int(10) unsigned NOT NULL,
                              `budget` float NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `issue` (
                         `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                         `title` varchar(100) NOT NULL,
                         `text` text NOT NULL,
                         `date` datetime NOT NULL,
                         `state` varchar(20) NOT NULL,
                         `id_repository` int(10) unsigned NOT NULL,
                         `id_boss` int(10) unsigned NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `issue_assignment` (
                                    `id_programmer` int(10) unsigned NOT NULL,
                                    `id_issue` int(10) unsigned NOT NULL,
                                    `start_date` datetime NOT NULL,
                                    PRIMARY KEY (`id_programmer`,`id_issue`,`start_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `programmer` (
                              `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                              `name` varchar(50) NOT NULL,
                              `entry_date` datetime NOT NULL,
                              `password` varchar(100) NOT NULL,
                              `technologies` varchar(200) DEFAULT NULL,
                              `salary` float NOT NULL,
                              `id_department` int(10) unsigned NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `project` (
                           `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                           `name` varchar(100) NOT NULL,
                           `start_date` datetime NOT NULL,
                           `end_date` datetime DEFAULT NULL,
                           `technologies` varchar(512) NOT NULL,
                           `annual_budget` float NOT NULL,
                           `state` varchar(20) NOT NULL,
                           `id_boss` int(10) unsigned NOT NULL,
                           `id_department` int(10) unsigned NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE project_assignment (
                                    `id_programmer` int(10) unsigned NOT NULL,
                                    `id_project` int(10) unsigned NOT NULL,
                                    `start_date` datetime NOT NULL,
                                    `end_date` datetime DEFAULT NULL,
                                    PRIMARY KEY (`id_programmer`,`id_project`,`start_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `repository` (
                              `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                              `name` varchar(100) NOT NULL,
                              `creation_date` datetime NOT NULL,
                              `id_project` int(10) unsigned NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE boss_history ADD CONSTRAINT `boss_history_department_FK` FOREIGN KEY (`id_department`) REFERENCES `department` (`id`);
ALTER TABLE boss_history ADD CONSTRAINT `boss_history_programmer_FK` FOREIGN KEY (`id_programmer`) REFERENCES `programmer` (`id`);

ALTER TABLE data_access_sl.commit ADD CONSTRAINT `commit_issue_FK` FOREIGN KEY (`id_issue`) REFERENCES `issue` (`id`);
ALTER TABLE data_access_sl.commit ADD CONSTRAINT `commit_programmer_FK` FOREIGN KEY (`id_programmer`) REFERENCES `programmer` (`id`);
ALTER TABLE data_access_sl.commit ADD CONSTRAINT `commit_repository_FK` FOREIGN KEY (`id_repository`) REFERENCES `repository` (`id`);

ALTER TABLE data_access_sl.department ADD CONSTRAINT `department_FK` FOREIGN KEY (`boss`) REFERENCES `programmer` (`id`);

ALTER TABLE data_access_sl.issue ADD CONSTRAINT `issue_boss_FK` FOREIGN KEY (`id_boss`) REFERENCES `programmer` (`id`);
ALTER TABLE data_access_sl.issue ADD CONSTRAINT `issue_repository_FK` FOREIGN KEY (`id_repository`) REFERENCES `repository` (`id`);

ALTER TABLE data_access_sl.issue_assignment ADD CONSTRAINT `issue_assignment_issue_FK` FOREIGN KEY (`id_issue`) REFERENCES `issue` (`id`);
ALTER TABLE data_access_sl.issue_assignment ADD CONSTRAINT `issue_assignment_programmer_FK` FOREIGN KEY (`id_programmer`) REFERENCES `programmer` (`id`);

ALTER TABLE data_access_sl.programmer ADD CONSTRAINT `programmer_FK` FOREIGN KEY (`id_department`) REFERENCES `department` (`id`);

ALTER TABLE data_access_sl.project ADD CONSTRAINT `projects_boss_FK` FOREIGN KEY (`id_boss`) REFERENCES `programmer` (`id`);
ALTER TABLE data_access_sl.project ADD CONSTRAINT `projects_department_FK` FOREIGN KEY (`id_department`) REFERENCES `department` (`id`);

ALTER TABLE data_access_sl.project_assignment ADD CONSTRAINT `project_assignment_programmer_FK` FOREIGN KEY (`id_programmer`) REFERENCES `programmer` (`id`);
ALTER TABLE data_access_sl.project_assignment ADD CONSTRAINT `project_assignment_project_FK` FOREIGN KEY (`id_project`) REFERENCES `project` (`id`);

ALTER TABLE data_access_sl.repository ADD FOREIGN KEY (`id_project`) REFERENCES `project` (`id`);

/*
 * Department insertion
 */
INSERT INTO data_access_sl.department
(id, name, boss, budget)
VALUES(1, 'Inteligencia Artificial', 1, 200000);

INSERT INTO data_access_sl.department
(id, name, boss, budget)
VALUES(2, 'Interfaces', 2, 100000);

INSERT INTO data_access_sl.department
(id, name, boss, budget)
VALUES(3, 'Software de pruebas', 3, 120000);

INSERT INTO data_access_sl.department
(id, name, boss, budget)
VALUES(4, 'Desarrollo de servicios nativos', 4, 140000);

/*
 * Programmers insertion
 */
INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(1, 'Sergio Pérez', '2020-12-21 00:00:00.000', SHA2('aluminio', 256),'Java;C', 3000.0, 1);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(2, 'Federico Toledo', '2019-08-18 00:00:00.000', SHA2('cobre44', 256), 'Java;C#;C++;Javascript', 2000.0, 2);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(3, 'Juanito Unitario', '2018-08-10 00:00:00.000', SHA2('mitrilo90', 256), 'Java', 1500.0, 3);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(4, 'Iria Manzanara', '2021-01-20 00:00:00.000', SHA2('oro1504', 256), 'C;C++;Javascript', 2000.0, 4);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(5, 'Alonso Mateo', '2020-12-29 00:00:00.000', SHA2('estaño98', 256), 'Java;Python', 1500.0, 1);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(6, 'Vicente Vago', '2020-02-27 00:00:00.000', SHA2('plata9797', 256), 'Java', 1500.0, 2);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(7, 'Mavi López', '2021-03-01 00:00:00.000', SHA2('iridio19', 256), 'Java;C;C++', 1250.0, 3);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(8, 'Katarina Loto', '2020-12-11 00:00:00.000', SHA2('niquel34', 256), 'C++', 1000.0, 4);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(9, 'Ruben Pereda', '2020-02-10 00:00:00.000', SHA2('helio87', 256), 'Java;C;Python', 1500.0, 1);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(10, 'Milagros García', '2020-04-15 00:00:00.000', SHA2('bronce4', 256), 'Java', 1500.0, 2);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(11, 'Ulises Grande', '2018-05-12 00:00:00.000', SHA2('potasio9', 256), 'C;Python', 1250.0, 3);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(12, 'Clementina Pérez', '2021-06-11 00:00:00.000', SHA2('wolframio17', 256), 'Java;C++', 1000.0, 4);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(13, 'Lucia Manzano', '2021-03-12 00:00:00.000', SHA2('Platino1', 256), 'Python', 1500.0, 1);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(14, 'Carlos Carlanga', '2020-08-05 00:00:00.000', SHA2('carbono14', 256), 'Java', 1000.0, 2);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(15, 'Eliseo Santo', '2021-10-20 00:00:00.000', SHA2('hidrogeno5', 256), 'C;Python', 1200.0, 3);

INSERT INTO data_access_sl.programmer
(id, name, entry_date, password, technologies, salary, id_department)
VALUES(16, 'Jesus Matamoros', '2020-03-12 00:00:00.000', SHA2('vanadio65', 256), 'Java;C++', 1200.0, 4);

/*
 * Project insertion
 */
INSERT INTO data_access_sl.project
(id, name, start_date, end_date, technologies, annual_budget, state, id_boss, id_department)
VALUES(1, 'GnomeAI', '2020-12-11 00:00:00.000', NULL, 'Python', 150000, 'active', 5, 1);

INSERT INTO data_access_sl.project
(id, name, start_date, end_date, technologies, annual_budget, state, id_boss, id_department)
VALUES(2, 'GnomeUI', '2019-12-11 00:00:00.000', '2020-12-11 00:00:00.000', 'Java', 90000, 'ended', 6, 2);

INSERT INTO data_access_sl.project
(id, name, start_date, end_date, technologies, annual_budget, state, id_boss, id_department)
VALUES(3, 'GnomeTester', '2021-02-09 00:00:00.000', NULL, 'Java;C++', 100000, 'active', 7, 3);

INSERT INTO data_access_sl.project
(id, name, start_date, end_date, technologies, annual_budget, state, id_boss, id_department)
VALUES(4, 'GnomeMaker', '2021-02-09 00:00:00.000', NULL, 'C++', 100000, 'active', 8, 4);

/*
 * Project Assignment insertion
 */
INSERT INTO data_access_sl.project_assignment
(id_programmer, id_project, start_date, end_date)
VALUES(9, 1, '2020-12-11 00:00:00.000', NULL);

INSERT INTO data_access_sl.project_assignment
(id_programmer, id_project, start_date, end_date)
VALUES(10, 2, '2019-12-11 00:00:00.000', '2020-12-11 00:00:00.000');

INSERT INTO data_access_sl.project_assignment
(id_programmer, id_project, start_date, end_date)
VALUES(11, 3, '2021-02-09 00:00:00.000', NULL);

INSERT INTO data_access_sl.project_assignment
(id_programmer, id_project, start_date, end_date)
VALUES(12, 4, '2021-02-09 00:00:00.000', NULL);

INSERT INTO data_access_sl.project_assignment
(id_programmer, id_project, start_date, end_date)
VALUES(13, 1, '2021-02-01 00:00:00.000', NULL);

INSERT INTO data_access_sl.project_assignment
(id_programmer, id_project, start_date, end_date)
VALUES(14, 2, '2020-12-01 00:00:00.000', '2020-12-11 00:00:00.000');

INSERT INTO data_access_sl.project_assignment
(id_programmer, id_project, start_date, end_date)
VALUES(15, 3, '2021-02-12 00:00:00.000', NULL);

INSERT INTO data_access_sl.project_assignment
(id_programmer, id_project, start_date, end_date)
VALUES(16, 4, '2021-02-11 00:00:00.000', NULL);

/*
 * Boss History insertion
 */
INSERT INTO data_access_sl.boss_history
(id_programmer, id_department , entry_date , leave_date)
VALUES(1, 2, '2020-12-21 00:00:00.000', '2021-02-21 00:00:00.000');

INSERT INTO data_access_sl.boss_history
(id_programmer, id_department , entry_date , leave_date)
VALUES(2, 3, '2019-08-18 00:00:00.000', '2020-01-10 00:00:00.000');

INSERT INTO data_access_sl.boss_history
(id_programmer, id_department , entry_date , leave_date)
VALUES(3, 4, '2018-08-10 00:00:00.000', '2019-04-11 00:00:00.000');

INSERT INTO data_access_sl.boss_history
(id_programmer, id_department , entry_date , leave_date)
VALUES(4, 1, '2021-01-20 00:00:00.000', '2021-03-25 00:00:00.000');

INSERT INTO data_access_sl.boss_history
(id_programmer, id_department , entry_date , leave_date)
VALUES(1, 1, '2021-02-21 00:00:00.000', NULL);

INSERT INTO data_access_sl.boss_history
(id_programmer, id_department , entry_date , leave_date)
VALUES(2, 2, '2020-01-10 00:00:00.000', NULL);

INSERT INTO data_access_sl.boss_history
(id_programmer, id_department , entry_date , leave_date)
VALUES(3, 3, '2019-04-11 00:00:00.000', NULL);

INSERT INTO data_access_sl.boss_history
(id_programmer, id_department , entry_date , leave_date)
VALUES(4, 4, '2021-03-25 00:00:00.000', NULL);

/*
 * Repository inserts
 */
INSERT INTO data_access_sl.repository
(id, name , creation_date , id_project)
VALUES(1, 'GnomeAIRepository', '2020-12-11 00:00:00.000', 1);

INSERT INTO data_access_sl.repository
(id, name , creation_date , id_project)
VALUES(2, 'GnomeUIRepository', '2019-12-11 00:00:00.000', 2);

INSERT INTO data_access_sl.repository
(id, name , creation_date , id_project)
VALUES(3, 'GnomeTesterRepository', '2021-02-09 00:00:00.000', 3);

INSERT INTO data_access_sl.repository
(id, name , creation_date , id_project)
VALUES(4, 'GnomeMakerRepository', '2021-02-09 00:00:00.000', 4);

/*
 * Issue inserts
 */
INSERT INTO data_access_sl.issue
(id, title , `text` , `date`, state, id_repository, id_boss)
VALUES(1, 'GnomeAIRepositoryIssue1', 'No sabiamos que hacer, el almacen estaba en llamas',
       '2021-02-09 10:42:00.000', 'solved', 1, 1);

INSERT INTO data_access_sl.issue
(id, title , `text` , `date`, state, id_repository, id_boss)
VALUES(2, 'GnomeAIRepositoryIssue2', 'Necesitamos un generador de gnomos',
       '2021-03-09 14:41:00.000', 'open', 1, 1);

INSERT INTO data_access_sl.issue
(id, title , `text` , `date`, state, id_repository, id_boss)
VALUES(3, 'GnomeUIRepositoryIssue1', 'Necesitamos pinceles digitales',
       '2019-12-11 10:03:00.000', 'solved', 2, 2);

INSERT INTO data_access_sl.issue
(id, title , `text` , `date`, state, id_repository, id_boss)
VALUES(4, 'GnomeUIRepositoryIssue2', 'No sabemos pintar',
       '2020-01-07 08:01:00.000', 'open', 2, 2);

INSERT INTO data_access_sl.issue
(id, title , `text` , `date`, state, id_repository, id_boss)
VALUES(5, 'GnomeTesterRepositoryIssue1', 'Null pointer exception está causando caos',
       '2021-02-09 12:00:00.000', 'solved', 3, 3);

INSERT INTO data_access_sl.issue
(id, title , `text` , `date`, state, id_repository, id_boss)
VALUES(6, 'GnomeTesterRepositoryIssue2', 'Necesitamos un framework de modelos 5D',
       '2021-05-19 09:05:00.000', 'open', 3, 3);

INSERT INTO data_access_sl.issue
(id, title , `text` , `date`, state, id_repository, id_boss)
VALUES(7, 'GnomeMakerRepositoryIssue1', 'Necesitamos crackear una ps5',
       '2021-02-09 20:00:00.000', 'solved', 4, 4);

INSERT INTO data_access_sl.issue
(id, title , `text` , `date`, state, id_repository, id_boss)
VALUES(8, 'GnomeMakerRepositoryIssue2', 'Se nos ha jodido la ps5 por crackearla',
       '2021-02-11 8:00:00.000', 'open', 4, 4);
/*
 * Issue Assignment inserts
 */
INSERT INTO data_access_sl.issue_assignment
(id_programmer, id_issue, start_date) VALUES(9,1,'2021-02-09 10:42:00.000'),(13,1,'2021-02-09 10:42:00.000');

INSERT INTO data_access_sl.issue_assignment
(id_programmer, id_issue, start_date) VALUES(9,2,'2021-03-09 14:41:00.000'),(13,2,'2021-03-09 14:41:00.000');

INSERT INTO data_access_sl.issue_assignment
(id_programmer, id_issue, start_date) VALUES(10,3,'2019-12-11 10:03:00.000'),(14,3,'2019-12-11 10:03:00.000');

INSERT INTO data_access_sl.issue_assignment
(id_programmer, id_issue, start_date) VALUES(10,4,'2020-01-07 08:01:00.000'),(14,4,'2020-01-07 08:01:00.000');

INSERT INTO data_access_sl.issue_assignment
(id_programmer, id_issue, start_date) VALUES(11,5,'2021-02-09 12:00:00.000'),(15,5,'2021-02-09 12:00:00.000');

INSERT INTO data_access_sl.issue_assignment
(id_programmer, id_issue, start_date) VALUES(11,6,'2021-05-19 09:05:00.000'),(15,6,'2021-05-19 09:05:00.000');

INSERT INTO data_access_sl.issue_assignment
(id_programmer, id_issue, start_date) VALUES(12,7,'2021-02-09 12:00:00.000'),(16,7,'2021-02-09 12:00:00.000');

INSERT INTO data_access_sl.issue_assignment
(id_programmer, id_issue, start_date) VALUES(12,8,'2021-02-11 8:00:00.000'),(16,8,'2021-02-11 8:00:00.000');
/*
 * commit inserts
 */
INSERT INTO data_access_sl.commit
(id, title, text, date, id_repository, id_programmer, id_issue)VALUES(1,'Los buenos matafuegos',
'Problema solucionado con los buenos matafuegos, mitad de las cosas quemadas','2021-02-09 15:42:00.000',1,9,1);

INSERT INTO data_access_sl.commit
(id, title, text, date, id_repository, id_programmer, id_issue)VALUES(2,'El jefe nos ha traido los pinceles',
'Aunque le parecia un desperdicio, el jefe de mala gana nos dio unos cuantos','2019-12-11 16:03:00.000',2,10,3);

INSERT INTO data_access_sl.commit
(id, title, text, date, id_repository, id_programmer, id_issue)VALUES(3,'hemos puesto random a todo, solucinado',
'entre nada y el azar decidimos rellenarlo todo con aleatorios','2021-02-09 21:00:00.000',3,11,5);

INSERT INTO data_access_sl.commit
(id, title, text, date, id_repository, id_programmer, id_issue) VALUES(4,'la ps5 hizo crack asi que ahora tiene tiritas',
'despues de unos cuantos martillazos le logramos crackear al viejo estilo','2021-02-09 23:00:00.000',4,12,7);
