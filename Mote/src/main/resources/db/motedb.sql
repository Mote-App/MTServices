-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema motedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema motedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `motedb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `motedb` ;

-- -----------------------------------------------------
-- Table `motedb`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`country` ;

CREATE TABLE IF NOT EXISTS `motedb`.`country` (
  `country_code` VARCHAR(5) NOT NULL,
  `country_name` VARCHAR(100) NULL,
  PRIMARY KEY (`country_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `motedb`.`language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`language` ;

CREATE TABLE IF NOT EXISTS `motedb`.`language` (
  `language_code` VARCHAR(5) NOT NULL,
  `language_description` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`language_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `motedb`.`college`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`college` ;

CREATE TABLE IF NOT EXISTS `motedb`.`college` (
  `college_id` INT NOT NULL,
  `college_img_path` VARCHAR(250) NOT NULL,
  `college_name` VARCHAR(250) NOT NULL,
  `college_language_code` VARCHAR(5) NOT NULL,
  `college_country_code` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`college_id`),
  CONSTRAINT `fk_college_language1`
    FOREIGN KEY (`college_language_code`)
    REFERENCES `motedb`.`language` (`language_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_college_country1`
    FOREIGN KEY (`college_country_code`)
    REFERENCES `motedb`.`country` (`country_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_college_language1_idx` ON `motedb`.`college` (`college_language_code` ASC);

CREATE INDEX `fk_college_country1_idx` ON `motedb`.`college` (`college_country_code` ASC);


-- -----------------------------------------------------
-- Table `motedb`.`profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`profile` ;

CREATE TABLE IF NOT EXISTS `motedb`.`profile` (
  `profile_id` INT NOT NULL,
  `profile_email` VARCHAR(250) NOT NULL,
  `profile_first_name` VARCHAR(100) NOT NULL,
  `profile_last_name` VARCHAR(100) NOT NULL,
  `profile_user_name` VARCHAR(100) NOT NULL,
  `profile_password` VARCHAR(100) NOT NULL,
  `profile_picture_url` VARCHAR(250) NOT NULL,
  `profile_college_id` INT NOT NULL,
  PRIMARY KEY (`profile_id`),
  CONSTRAINT `fk_profile_college1`
    FOREIGN KEY (`profile_college_id`)
    REFERENCES `motedb`.`college` (`college_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `profile_user_name_UNIQUE` ON `motedb`.`profile` (`profile_user_name` ASC);

CREATE INDEX `fk_profile_college1_idx` ON `motedb`.`profile` (`profile_college_id` ASC);


-- -----------------------------------------------------
-- Table `motedb`.`type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`type` ;

CREATE TABLE IF NOT EXISTS `motedb`.`type` (
  `type_code` VARCHAR(5) NOT NULL COMMENT 'username, name [public], or anonymous',
  `type_description` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`type_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `motedb`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`tag` ;

CREATE TABLE IF NOT EXISTS `motedb`.`tag` (
  `tag_id` INT NOT NULL,
  `tag_description` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`tag_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `motedb`.`post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`post` ;

CREATE TABLE IF NOT EXISTS `motedb`.`post` (
  `post_id` INT NOT NULL,
  `post_type_code` VARCHAR(5) NOT NULL,
  `post_tag_id` INT NOT NULL,
  `post_object_path` VARCHAR(250) NOT NULL,
  `post_date` DATETIME NOT NULL DEFAULT now(),
  `post_caption` VARCHAR(45) NULL,
  PRIMARY KEY (`post_id`),
  CONSTRAINT `fk_post_type1`
    FOREIGN KEY (`post_type_code`)
    REFERENCES `motedb`.`type` (`type_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_tag1`
    FOREIGN KEY (`post_tag_id`)
    REFERENCES `motedb`.`tag` (`tag_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_post_type1_idx` ON `motedb`.`post` (`post_type_code` ASC);

CREATE INDEX `fk_post_tag1_idx` ON `motedb`.`post` (`post_tag_id` ASC);


-- -----------------------------------------------------
-- Table `motedb`.`profile_has_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`profile_has_post` ;

CREATE TABLE IF NOT EXISTS `motedb`.`profile_has_post` (
  `likes` INT NULL DEFAULT 0 COMMENT 'Mote Profile Post Feeds [Friend, School and National]\nV - the number of \'views\' on a post.\nL - the number of \'likes\' on a post.\nNs - the number of \'posts\' in School Feed.\n\nCr - the number of \'people\' registered from that school.\nCl - the number of \'likes\' per post from that school.\nCpn - the number of \'post\' from that school.\n\nCrIdealAvg - the average number of \'people\' registered per school.\nClIdealAvg - the average number of \'likes\' per post from all school.\nCpnAvg - the average number of \'post\' from that school.',
  `views` INT NULL DEFAULT 0,
  `school_promote` TINYINT(1) NULL DEFAULT 0,
  `national_promote` TINYINT(1) NULL DEFAULT 0,
  `profile_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`profile_id`, `post_id`),
  CONSTRAINT `fk_profile_has_post_profile1`
    FOREIGN KEY (`profile_id`)
    REFERENCES `motedb`.`profile` (`profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_profile_has_post_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `motedb`.`post` (`post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_profile_has_post_post1_idx` ON `motedb`.`profile_has_post` (`post_id` ASC);


-- -----------------------------------------------------
-- Table `motedb`.`profile_has_friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`profile_has_friend` ;

CREATE TABLE IF NOT EXISTS `motedb`.`profile_has_friend` (
  `profile_id` INT NOT NULL,
  `profile_friend_id` INT NOT NULL,
  PRIMARY KEY (`profile_id`, `profile_friend_id`),
  CONSTRAINT `fk_profile_has_profile_profile1`
    FOREIGN KEY (`profile_id`)
    REFERENCES `motedb`.`profile` (`profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_profile_has_profile_profile2`
    FOREIGN KEY (`profile_friend_id`)
    REFERENCES `motedb`.`profile` (`profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_profile_has_profile_profile2_idx` ON `motedb`.`profile_has_friend` (`profile_friend_id` ASC);

CREATE INDEX `fk_profile_has_profile_profile1_idx` ON `motedb`.`profile_has_friend` (`profile_id` ASC);


-- -----------------------------------------------------
-- Table `motedb`.`locale`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`locale` ;

CREATE TABLE IF NOT EXISTS `motedb`.`locale` (
  `locale_country_code` VARCHAR(5) NOT NULL,
  `locale_language_code` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`locale_country_code`, `locale_language_code`),
  CONSTRAINT `fk_locale_country1`
    FOREIGN KEY (`locale_country_code`)
    REFERENCES `motedb`.`country` (`country_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_locale_language1`
    FOREIGN KEY (`locale_language_code`)
    REFERENCES `motedb`.`language` (`language_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_locale_country1_idx` ON `motedb`.`locale` (`locale_country_code` ASC);

CREATE INDEX `fk_locale_language1_idx` ON `motedb`.`locale` (`locale_language_code` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;