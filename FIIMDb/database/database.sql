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


######### MOVIE_GENRE TABLE

CREATE TABLE movie_genre (
    `id_movie` INT NOT NULL,
    `id_genre` TINYINT NOT NULL,
    FOREIGN KEY (id_movie) REFERENCES movie(ID),
    FOREIGN KEY (id_genre) REFERENCES genre(ID)
);

Insert into movie_genre (id_movie, id_genre) VALUES
	(1,1),
    (1,2),
    (2,1),
    (3,2);
	(4,5),
    (5,3);

######### MOVIE_GENRE TABLE

######### USERS TABLE

CREATE TABLE users (
    `ID` INTEGER, 
	`USERNAME` VARCHAR(50), 
	`PASSWORD` VARCHAR(50)
   );
   
######### USERS TABLE


######### USERS_ROLES TABLE

  CREATE TABLE users_roles (
      `USERNAME` VARCHAR(50), 
      `ROLENAME` VARCHAR(50)
	);
   
   Insert into USERS (ID,USERNAME,PASSWORD) values (65,'admin','admin');
   Insert into USERS_ROLES (USERNAME,ROLENAME) values ('admin','admin');

   
######### USERS_ROLES TABLE


#########