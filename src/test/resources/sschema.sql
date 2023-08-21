-- MySQL Workbench Forward Engineering

--SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
--SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
--SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema church
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema church
-- -----------------------------------------------------
--CREATE SCHEMA IF NOT EXISTS `church` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
--USE `church` ;

-- -----------------------------------------------------
-- Table `church`.`areas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `church`.`areas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `area_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `church`.`families`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `church`.`families` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `family_level` INT NULL DEFAULT NULL,
  `family_name` VARCHAR(255) NULL DEFAULT NULL,
  `joining_year` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `church`.`streets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `church`.`streets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street_name` VARCHAR(255) NULL DEFAULT NULL,
  `area_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKld9x6u449arvd73j74w07tfgb` (`area_id` ASC) VISIBLE,
  CONSTRAINT `FKld9x6u449arvd73j74w07tfgb`
    FOREIGN KEY (`area_id`)
    REFERENCES `church`.`areas` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `church`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `church`.`users` (
  `username` VARCHAR(255) NOT NULL,
  `enabled` BIT(1) NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `roles` ENUM('ROLE_Father', 'ROLE_Servant', 'ROLE_Servant_Head') NULL DEFAULT NULL,
  `person_id` INT NULL DEFAULT NULL,
  `authenticated` BIT(1) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `UK_97ih1g5lcdf1s3fg7oo4e18jw` (`person_id` ASC) VISIBLE,
  CONSTRAINT `FKmvbq8q4vpi6csivw9wcnq6ho5`
    FOREIGN KEY (`person_id`)
    REFERENCES `church`.`persons` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `church`.`persons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `church`.`persons` (
  `DTYPE` VARCHAR(31) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `building number` INT NULL DEFAULT NULL,
  `day_of_birth` DATE NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `phone_number` VARCHAR(255) NULL DEFAULT NULL,
  `college` VARCHAR(255) NULL DEFAULT NULL,
  `college_level` INT NULL DEFAULT NULL,
  `grad_level` INT NULL DEFAULT NULL,
  `meeting_level` INT NULL DEFAULT NULL,
  `notes` VARCHAR(255) NULL DEFAULT NULL,
  `university` VARCHAR(255) NULL DEFAULT NULL,
  `area_id` INT NULL DEFAULT NULL,
  `street_id` INT NULL DEFAULT NULL,
  `family_id` INT NULL DEFAULT NULL,
  `floor` VARCHAR(255) NULL DEFAULT NULL,
  `authorities` VARBINARY(255) NULL DEFAULT NULL,
  `hi` BIT(1) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `user_username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_8rjgpr8nb0yne0yrgi7ppo5kj` (`user_username` ASC) VISIBLE,
  INDEX `FKm3lh3qk66cqjw0ub8e7serh52` (`area_id` ASC) VISIBLE,
  INDEX `FKqaftafg588ruvlmrikopgc1vs` (`street_id` ASC) VISIBLE,
  INDEX `FKm22ywnfjlatd43l848198uy8w` (`family_id` ASC) VISIBLE,
  CONSTRAINT `FKm22ywnfjlatd43l848198uy8w`
    FOREIGN KEY (`family_id`)
    REFERENCES `church`.`families` (`id`),
  CONSTRAINT `FKm3lh3qk66cqjw0ub8e7serh52`
    FOREIGN KEY (`area_id`)
    REFERENCES `church`.`areas` (`id`),
  CONSTRAINT `FKqaftafg588ruvlmrikopgc1vs`
    FOREIGN KEY (`street_id`)
    REFERENCES `church`.`streets` (`id`),
  CONSTRAINT `FKtgfeprda33nbwdvrpl55em4xk`
    FOREIGN KEY (`user_username`)
    REFERENCES `church`.`users` (`username`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


--SET SQL_MODE=@OLD_SQL_MODE;
--SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
--SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
