SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema DBProject
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema DBProject
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DBProject` DEFAULT CHARACTER SET utf8 ;
USE `DBProject` ;

-- -----------------------------------------------------
-- Table `DBProject`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBProject`.`Category` (
  `cId` CHAR(6) NOT NULL,
  `cName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cId`),
  UNIQUE INDEX `cName_UNIQUE` (`cName` ASC) VISIBLE)
ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;


-- -----------------------------------------------------
-- Table `DBProject`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBProject`.`Product` (
  `pId` INT NOT NULL,
  `cId` CHAR(6) NOT NULL,
  `pName` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `stock` INT NOT NULL,
  PRIMARY KEY (`pId`),
  UNIQUE INDEX `pName_UNIQUE` (`pName` ASC) VISIBLE,
  CONSTRAINT `fk_Product_Category`
    FOREIGN KEY (`cId`)
    REFERENCES `DBProject`.`Category` (`cId`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;


-- -----------------------------------------------------
-- Table `DBProject`.`Customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBProject`.`Customers` (
  `cusName` VARCHAR(20) NOT NULL,
  `cusId` CHAR(6) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  UNIQUE INDEX `cusName_UNIQUE` (`cusName` ASC) VISIBLE,
  PRIMARY KEY (`cusId`),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE)
ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;


-- -----------------------------------------------------
-- Table `DBProject`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBProject`.`Order` (
  `oId` CHAR(6) NOT NULL,
  `cusId` CHAR(6) NULL,
  `date` DATE NOT NULL,
  `total` INT NOT NULL,
  PRIMARY KEY (`oId`),
  CONSTRAINT `fk_Orders_Customers`
    FOREIGN KEY (`cusId`)
    REFERENCES `DBProject`.`Customers` (`cusId`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;


-- -----------------------------------------------------
-- Table `DBProject`.`OrderDetail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBProject`.`OrderDetail` (
  `oId` CHAR(6) NOT NULL,
  `pId` INT NOT NULL,
  `num` INT NOT NULL,
  `unitPrice` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`oId`,`pId`),
  CONSTRAINT `fk_OrderDetail_Orders`
    FOREIGN KEY (`oId`)
    REFERENCES `DBProject`.`Order` (`oId`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderDetail_Product`
    FOREIGN KEY (`pId`)
    REFERENCES `DBProject`.`Product` (`pId`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO Category (cId, cName)
VALUES
('cat001', '狗狗飼料'),
('cat002', '狗狗零食'),
('cat003', '貓咪玩具'),
('cat004', '貓咪用品'),
('cat005', '寵物清潔');


INSERT INTO Customers (cusId, cusName, phone)
VALUES
('cus001', '市民1', '0912345678'),
('cus002', 'Andy王', '0922333444'),
('cus003', '張家寧', '0933777888');

INSERT INTO Product (pId, cId, pName, price, stock)
VALUES
(1, 'cat002', '潔牙骨', 150, 100),
(2, 'cat003', '逗貓棒', 120, 80),
(3, 'cat001', '牛肉飼料10kg', 800, 50),
(4, 'cat004', '貓抓板', 250, 40),
(5, 'cat005', '洗毛精', 300, 30);

INSERT INTO `Order` (oId, cusId, `date`, total)
VALUES
('ord001', 'cus001', '2025-06-01', 300),
('ord002', 'cus002', '2025-06-02', 800),
('ord003', 'cus002', '2025-06-02', 360),
('ord004', 'cus003', '2025-06-03', 300);

INSERT INTO OrderDetail (oId, pId, num, unitPrice)
VALUES
('ord001', 1, 2, 150), 
('ord002', 3, 1, 800),   
('ord003', 2, 3, 120),   
('ord004', 5, 1, 300);