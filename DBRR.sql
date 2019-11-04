

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Db` DEFAULT CHARACTER SET utf8 ;
USE `Db` ;

-- -----------------------------------------------------
-- Table `Db`.`Route`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Db`.`Route` ;

CREATE TABLE IF NOT EXISTS `Db`.`Route` (
  `RouteID` VARCHAR(45) NOT NULL,
  `Date` DATE NOT NULL,
  `Time` TIME NOT NULL,
  `Departure` VARCHAR(45) NOT NULL,
  `Arrival` VARCHAR(45) NOT NULL,
  `Weather` VARCHAR(45) NULL,
  PRIMARY KEY (`RouteID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Db`.`Train`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Db`.`Train` ;

CREATE TABLE IF NOT EXISTS `Db`.`Train` (
  `Type` VARCHAR(45) NOT NULL DEFAULT 'Default',
  `ID` INT NOT NULL,
  `MaxSeat` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`, `Type`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Db`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Db`.`User` ;

CREATE TABLE IF NOT EXISTS `Db`.`User` (
  `UserID` INT NOT NULL auto_increment,
  `Lname` VARCHAR(45) NOT NULL,
  `Fname` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Username` VARCHAR(45) NULL DEFAULT (CONCAT(Fname,'.',Lname)),
  `Status` VARCHAR(45) NULL DEFAULT 'Passenger',
  `Password` VARCHAR(45) NULL DEFAULT '12345',
  PRIMARY KEY (`UserID`),
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Db`.`Ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Db`.`Ticket` ;

CREATE TABLE IF NOT EXISTS `Db`.`Ticket` (
  `Seat_Number` INT NOT NULL,
  `RouteID` VARCHAR(45) NOT NULL,
  `Passenger` INT NULL,
  `TrainID` INT NOT NULL,
  `Status` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`Seat_Number`, `RouteID`, `TrainID`),
  INDEX `fk_Ticket_Route_idx` (`RouteID` ASC) VISIBLE,
  INDEX `fk_Ticket_Train1_idx` (`TrainID` ASC) VISIBLE,
  UNIQUE INDEX `Passenger_UNIQUE` (`Passenger` ASC) VISIBLE,
  CONSTRAINT `fk_Ticket_Route`
    FOREIGN KEY (`RouteID`)
    REFERENCES `Db`.`Route` (`RouteID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_User1`
    FOREIGN KEY (`Passenger`)
    REFERENCES `Db`.`User` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_Train1`
    FOREIGN KEY (`TrainID`)
    REFERENCES `Db`.`Train` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Db`.`Trip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Db`.`Trip` ;

CREATE TABLE IF NOT EXISTS `Db`.`Trip` (
  `Passenger` INT NOT NULL,
  `Status` TINYINT NULL DEFAULT 1,
  `Ticket_Seat_Number` INT NOT NULL,
  `Ticket_RouteID` VARCHAR(45) NOT NULL,
  `Ticket_TrainID` INT NOT NULL,
  PRIMARY KEY (`Passenger`, `Ticket_TrainID`, `Ticket_RouteID`, `Ticket_Seat_Number`),
  INDEX `fk_Trip_Ticket1_idx` (`Ticket_Seat_Number` ASC, `Ticket_RouteID` ASC, `Ticket_TrainID` ASC) VISIBLE,
  CONSTRAINT `fk_Trip_User1`
    FOREIGN KEY (`Passenger`)
    REFERENCES `Db`.`User` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Trip_Ticket1`
    FOREIGN KEY (`Ticket_Seat_Number` , `Ticket_RouteID` , `Ticket_TrainID`)
    REFERENCES `Db`.`Ticket` (`Seat_Number` , `RouteID` , `TrainID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Db`.`Station`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Db`.`Station` ;

CREATE TABLE IF NOT EXISTS `Db`.`Station` (
  `StationID` INT NOT NULL,
  `Location` VARCHAR(45) NOT NULL,
  `ManagerID` INT NOT NULL,
  PRIMARY KEY (`StationID`, `ManagerID`),
  INDEX `fk_Station_User1_idx` (`ManagerID` ASC) VISIBLE,
  CONSTRAINT `fk_Station_User1`
    FOREIGN KEY (`ManagerID`)
    REFERENCES `Db`.`User` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Db`.`EmployeeID`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Db`.`EmployeeID` ;

CREATE TABLE IF NOT EXISTS `Db`.`EmployeeID` (
  `idStation_Employee` INT NOT NULL,
  `FName` VARCHAR(45) NULL,
  `LName` VARCHAR(45) NULL,
  `Position` VARCHAR(45) NULL,
  `Station_StationID` INT NOT NULL,
  PRIMARY KEY (`idStation_Employee`),
  INDEX `fk_Station_Employee_Station1_idx` (`Station_StationID` ASC) VISIBLE,
  CONSTRAINT `fk_Station_Employee_Station1`
    FOREIGN KEY (`Station_StationID`)
    REFERENCES `Db`.`Station` (`StationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Db`.`Work_E`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Db`.`Work_E` ;

CREATE TABLE IF NOT EXISTS `Db`.`Work_E` (
  `ID` INT NOT NULL,
  `Date` DATE NULL,
  `Start_time` TIME NULL,
  `End_time` TIME NULL,
  `Hours` INT NULL DEFAULT(0),
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_Work_E_Station_Employee1`
    FOREIGN KEY (`ID`)
    REFERENCES `Db`.`EmployeeID` (`idStation_Employee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Db`.`Fare`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Db`.`Fare` ;

CREATE TABLE IF NOT EXISTS `Db`.`Fare` (
  `FareCode` VARCHAR(45) NOT NULL,
  `Price` VARCHAR(45) NOT NULL,
  `RouteID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`FareCode`, `RouteID`),
  INDEX `fk_Fare_Route1_idx` (`RouteID` ASC) VISIBLE,
  CONSTRAINT `fk_Fare_Route1`
    FOREIGN KEY (`RouteID`)
    REFERENCES `Db`.`Route` (`RouteID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Db`.`CityOrder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Db`.`CityOrder` ;

CREATE TABLE IF NOT EXISTS `Db`.`CityOrder` (
  `IDCityOrder` INT NOT NULL,
  `Route_RouteID` VARCHAR(45) NOT NULL,
  `Station_ID` INT NOT NULL,
  PRIMARY KEY (`IDCityOrder`, `Route_RouteID`),
  INDEX `fk_CityOrder_Route1_idx` (`Route_RouteID` ASC) VISIBLE,
  INDEX `fk_CityOrder_Station1_idx` (`Station_ID` ASC) VISIBLE,
  CONSTRAINT `fk_CityOrder_Route1`
    FOREIGN KEY (`Route_RouteID`)
    REFERENCES `Db`.`Route` (`RouteID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CityOrder_Station1`
    FOREIGN KEY (`Station_ID`)
    REFERENCES `Db`.`Station` (`StationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
