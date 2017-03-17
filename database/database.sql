######### MOVIE TABLE

CREATE TABLE movie
(
  `ID` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `RELEASE_DATE` DATETIME NOT NULL,
 `NAME` VARCHAR(128) NOT NULL, 
 `RATING` TINYINT, 
 `LENGTH` INT,
 `CASTING` VARCHAR(128), 
 `DIRECTOR` VARCHAR(128), 
 `DESCRIPTION` TEXT, 
 `WRITER` VARCHAR(128) NOT NULL
	);

INSERT INTO movie 
(`RELEASE_DATE`,`NAME`,`RATING`,`LENGTH`,`CASTING`,`DIRECTOR`,`WRITER`) 
VALUES 
('1994-10-14','The Shawshank Redemption',5,142,'Morgan Freeman','Frank Darabont','Frank Darabont');

INSERT INTO MOVIE 
(`RELEASE_DATE`,`NAME`,`RATING`,`LENGTH`,`CASTING`,`DIRECTOR`,`WRITER`) 
VALUES
('1972-03-24','The Godfather',5,175,'Marlon Brando','Francis Ford Coppola','Francis Ford Coppola');

INSERT INTO MOVIE 
(`RELEASE_DATE`,`NAME`,`RATING`,`LENGTH`,`CASTING`,`DIRECTOR`,`WRITER`) 
VALUES
('1988-06-03','Titanic',4,194,'Leonardo DiCaprio','James Cameron','James Cameron');

INSERT INTO MOVIE 
(`RELEASE_DATE`,`NAME`,`RATING`,`LENGTH`,`CASTING`,`DIRECTOR`,`WRITER`) 
VALUES
('1999-10-15','Fight Club',5,139,'Brad Pitt','David Fincher','Chuck Palahniuk');

INSERT INTO MOVIE 
(`RELEASE_DATE`,`NAME`,`RATING`,`LENGTH`,`CASTING`,`DIRECTOR`,`WRITER`) 
VALUES
('2014-03-21','The Grand Budapest Hotel',4,99,'Ralph Fiennes','Wes Anderson','Wes Anderson');

######### MOVIE TABLE



######### GENRE TABLE

CREATE TABLE genre (
    `ID` TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `TYPE` VARCHAR(50) NOT NULL
	);
	
INSERT INTO genre (`TYPE`) VALUES
	('Action'),
    ('Drama'),
    ('Science-Fiction'),
    ('Family'),
    ('Music'),
    ('Romance'),
    ('Horror');
    
######### GENRE TABLE