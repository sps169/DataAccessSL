DROP DATABASE IF EXISTS `data_access_sl`;
CREATE DATABASE `data_access_sl`;

USE `data_access_sl`;

CREATE TABLE `boss_history` (
                                `id_programmer` int(10) unsigned NOT NULL,
                                `id_department` int(10) unsigned NOT NULL,
                                `entry_date` datetime NOT NULL,
                                `leave_date` datetime DEFAULT NULL,
                                KEY `boss_history_department_FK` (`id_department`),
                                KEY `boss_history_programmer_FK` (`id_programmer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `commit` (
                          `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                          `title` varchar(100) NOT NULL,
                          `text` text NOT NULL,
                          `date` datetime NOT NULL,
                          `id_repository` int(10) unsigned NOT NULL,
                          `id_programmer` int(10) unsigned NOT NULL,
                          `id_issue` int(10) unsigned NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `commit_programmer_FK` (`id_programmer`),
                          KEY `commit_issue_FK` (`id_issue`),
                          KEY `commit_repository_FK` (`id_repository`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `department` (
                              `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                              `name` varchar(50) NOT NULL,
                              `boss` int(10) unsigned NOT NULL,
                              `budget` float NOT NULL,
                              PRIMARY KEY (`id`),
                              KEY `department_FK` (`boss`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `issue` (
                         `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                         `title` varchar(100) NOT NULL,
                         `text` text NOT NULL,
                         `date` datetime NOT NULL,
                         `state` varchar(20) NOT NULL,
                         `id_repository` int(10) unsigned NOT NULL,
                         `id_boss` int(10) unsigned NOT NULL,
                         PRIMARY KEY (`id`),
                         KEY `issue_boss_FK` (`id_boss`),
                         KEY `issue_repository_FK` (`id_repository`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `issue_assignment` (
                                    `id_programmer` int(10) unsigned NOT NULL,
                                    `id_issue` int(10) unsigned NOT NULL,
                                    `start_date` datetime NOT NULL,
                                    PRIMARY KEY (`id_programmer`,`id_issue`),
                                    KEY `issue_assignment_issue_FK` (`id_issue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `programmer` (
                              `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                              `nombre` varchar(50) NOT NULL,
                              `entry_date` datetime NOT NULL,
                              `tecnologias` varchar(200) DEFAULT NULL,
                              `salary` float NOT NULL,
                              `id_department` int(10) unsigned NOT NULL,
                              PRIMARY KEY (`id`),
                              KEY `id_department` (`id_department`)
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
                           PRIMARY KEY (`id`),
                           KEY `projects_department_FK` (`id_department`),
                           KEY `projects_boss_FK` (`id_boss`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE project_assignment (
                                    `id_programmer` int(10) unsigned NOT NULL,
                                    `id_project` int(10) unsigned NOT NULL,
                                    `start_date` datetime NOT NULL,
                                    `end_date` datetime DEFAULT NULL,
                                    PRIMARY KEY (`id_programmer`,`id_project`,`start_date`),
                                    KEY `project_assignment_project_FK` (`id_project`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `repository` (
                              `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                              `name` varchar(100) NOT NULL,
                              `creation_date` datetime NOT NULL,
                              `id_project` int(10) unsigned NOT NULL,
                              PRIMARY KEY (`id`),
                              KEY `repository_project_FK` (`id_project`)
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

ALTER TABLE data_access_sl.repository ADD CONSTRAINT `repository_project_FK` FOREIGN KEY (`id_project`) REFERENCES `project` (`id`);