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
-- Table `motedb`.`college`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`college` ;

CREATE TABLE IF NOT EXISTS `motedb`.`college` (
  `college_id` INT NOT NULL AUTO_INCREMENT,
  `college_img_path` VARCHAR(250) NOT NULL,
  `college_name` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`college_id`))
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
-- Table `motedb`.`locale`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`locale` ;

CREATE TABLE IF NOT EXISTS `motedb`.`locale` (
  `locale_country_code` VARCHAR(5) NOT NULL,
  `locale_language_code` VARCHAR(5) NOT NULL,
  `locale_id` INT NOT NULL,
  PRIMARY KEY (`locale_id`),
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


-- -----------------------------------------------------
-- Table `motedb`.`profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`profile` ;

CREATE TABLE IF NOT EXISTS `motedb`.`profile` (
  `profile_id` INT NOT NULL AUTO_INCREMENT,
  `profile_email` VARCHAR(250) NOT NULL,
  `profile_first_name` VARCHAR(100) NOT NULL,
  `profile_last_name` VARCHAR(100) NOT NULL,
  `profile_user_name` VARCHAR(100) NOT NULL,
  `profile_password` VARCHAR(100) NOT NULL,
  `profile_picture_url` VARCHAR(250) NOT NULL,
  `profile_college_id` INT NOT NULL,
  `locale_locale_id` INT NOT NULL,
  PRIMARY KEY (`profile_id`),
  CONSTRAINT `fk_profile_college1`
    FOREIGN KEY (`profile_college_id`)
    REFERENCES `motedb`.`college` (`college_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_profile_locale1`
    FOREIGN KEY (`locale_locale_id`)
    REFERENCES `motedb`.`locale` (`locale_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `profile_user_name_UNIQUE` ON `motedb`.`profile` (`profile_user_name` ASC);

CREATE INDEX `fk_profile_college1_idx` ON `motedb`.`profile` (`profile_college_id` ASC);

CREATE INDEX `fk_profile_locale1_idx` ON `motedb`.`profile` (`locale_locale_id` ASC);


-- -----------------------------------------------------
-- Table `motedb`.`type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`type` ;

CREATE TABLE IF NOT EXISTS `motedb`.`type` (
  `type_code` VARCHAR(20) NOT NULL COMMENT 'username, name [public], or anonymous',
  `type_description` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`type_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `motedb`.`post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`post` ;

CREATE TABLE IF NOT EXISTS `motedb`.`post` (
  `post_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Mote Profile Post Feeds [Friend, School and National]\nV - the number of views on a post.\nL - the number of likes on a post.\nNs - the number of posts in School Feed.\n\nCr - the number of people registered from that school.\nCl - the number of likes per post from that school.\nCpn - the number of post from that school.\n\nCrIdealAvg - the average number of people registered per school.\nClIdealAvg - the average number of likes per post from all school.\nCpnAvg - the average number of post from that school.',
  `post_type_code` VARCHAR(20) NOT NULL,
  `post_object_path` VARCHAR(250) NOT NULL,
  `post_date` DATETIME NOT NULL DEFAULT now(),
  `post_caption` VARCHAR(45) NULL,
  `post_likes` INT NULL DEFAULT 0,
  `post_views` INT NULL DEFAULT 0,
  `post_school_promote` TINYINT(1) NULL DEFAULT 0,
  `post_national_promote` TINYINT(1) NULL DEFAULT 0,
  `post_profile_id` INT NOT NULL,
  PRIMARY KEY (`post_id`),
  CONSTRAINT `fk_post_type1`
    FOREIGN KEY (`post_type_code`)
    REFERENCES `motedb`.`type` (`type_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_profile1`
    FOREIGN KEY (`post_profile_id`)
    REFERENCES `motedb`.`profile` (`profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_post_type1_idx` ON `motedb`.`post` (`post_type_code` ASC);

CREATE INDEX `fk_post_profile1_idx` ON `motedb`.`post` (`post_profile_id` ASC);


-- -----------------------------------------------------
-- Table `motedb`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`tag` ;

CREATE TABLE IF NOT EXISTS `motedb`.`tag` (
  `tag_id` INT NOT NULL AUTO_INCREMENT,
  `tag_description` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`tag_id`))
ENGINE = InnoDB;


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
-- Table `motedb`.`post_has_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `motedb`.`post_has_tag` ;

CREATE TABLE IF NOT EXISTS `motedb`.`post_has_tag` (
  `post_post_id` INT NOT NULL,
  `tag_tag_id` INT NOT NULL,
  PRIMARY KEY (`post_post_id`, `tag_tag_id`),
  CONSTRAINT `fk_post_has_tag_post1`
    FOREIGN KEY (`post_post_id`)
    REFERENCES `motedb`.`post` (`post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_has_tag_tag1`
    FOREIGN KEY (`tag_tag_id`)
    REFERENCES `motedb`.`tag` (`tag_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_post_has_tag_tag1_idx` ON `motedb`.`post_has_tag` (`tag_tag_id` ASC);

CREATE INDEX `fk_post_has_tag_post1_idx` ON `motedb`.`post_has_tag` (`post_post_id` ASC);

-- -----------------------------------------------------
-- Table `motedb`.`post_user_like`
-- Used for avoiding mulitple likes by a user and maintain 
-- like at level of Friends, School and National
-- -----------------------------------------------------

DROP TABLE IF EXISTS `motedb`.`post_user_like` ;

CREATE TABLE IF NOT EXISTS `motedb`.`post_user_like` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	 `post_id` INT NOT NULL,
	 `profile_id` INT NOT NULL,
	 `level` char(1) DEFAULT NULL,
	 PRIMARY KEY (`id`)
	 
)
ENGINE = InnoDB;

CREATE INDEX `fk_post_user_like_profile_id_idx` ON `motedb`.`post_user_like` (`profile_id` ASC);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- -----------------------------------------------------
-- Table `motedb`.`ssa_coefficient_parameters`
-- Used for configuring the Social Stairway Algorithm Coefficients 
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `motedb`.`ssa_coefficient_parameters` (
  `Kf` DECIMAL(3,2) NOT NULL DEFAULT 0.20,
  `Ks` DECIMAL(3,2) NOT NULL DEFAULT 0.20,
  `Cf` DECIMAL(3,2) NOT NULL DEFAULT 0.50,
  `NsIdeal` INT NOT NULL DEFAULT 800,
  `NnIdeal` INT NOT NULL DEFAULT 800,
  `T1` DECIMAL(3,2) NOT NULL DEFAULT 0.50,
  `T2` DECIMAL(3,2) NOT NULL DEFAULT 0.30,
  `T3` DECIMAL(3,2) NOT NULL DEFAULT 0.30,
  `T4` DECIMAL(3,2) NOT NULL DEFAULT 0.30)
ENGINE = InnoDB
