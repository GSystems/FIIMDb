-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 20, 2017 at 05:11 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fiimdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `ID` tinyint(4) NOT NULL,
  `TYPE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`ID`, `TYPE`) VALUES
(1, 'Action'),
(2, 'Drama'),
(3, 'Science-Fiction'),
(4, 'Family'),
(5, 'Music'),
(6, 'Romance'),
(7, 'Horror');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `ID` int(11) NOT NULL,
  `RELEASE_DATE` datetime NOT NULL,
  `NAME` varchar(128) NOT NULL,
  `RATING` tinyint(4) DEFAULT NULL,
  `LENGTH` int(11) DEFAULT NULL,
  `CASTING` varchar(128) DEFAULT NULL,
  `DIRECTOR` varchar(128) DEFAULT NULL,
  `DESCRIPTION` text,
  `WRITER` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`ID`, `RELEASE_DATE`, `NAME`, `RATING`, `LENGTH`, `CASTING`, `DIRECTOR`, `DESCRIPTION`, `WRITER`) VALUES
(1, '1994-10-14 00:00:00', 'The Shawshank Redemption', 5, 142, 'Morgan Freeman', 'Frank Darabont', NULL, 'Frank Darabont'),
(2, '1972-03-24 00:00:00', 'The Godfather', 5, 175, 'Marlon Brando', 'Francis Ford Coppola', NULL, 'Francis Ford Coppola'),
(3, '1988-06-03 00:00:00', 'Titanic', 4, 194, 'Leonardo DiCaprio', 'James Cameron', NULL, 'James Cameron'),
(4, '1999-10-15 00:00:00', 'Fight Club', 5, 139, 'Brad Pitt', 'David Fincher', NULL, 'Chuck Palahniuk'),
(5, '2014-03-21 00:00:00', 'The Grand Budapest Hotel', 4, 99, 'Ralph Fiennes', 'Wes Anderson', NULL, 'Wes Anderson'),
(6, '2113-09-21 00:00:00', 'my movie', 4, 2, 'sad', 'dasdasd', 'asdasd', 'asd'),
(9, '2012-01-28 00:00:00', 'another test', 0, 0, 's', 'sd', 'as', 'a');

-- --------------------------------------------------------

--
-- Table structure for table `movie_genre`
--

CREATE TABLE `movie_genre` (
  `id_movie` int(11) NOT NULL,
  `id_genre` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie_genre`
--

INSERT INTO `movie_genre` (`id_movie`, `id_genre`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3),
(3, 2),
(4, 5),
(5, 3),
(6, 1),
(9, 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `FIRSTNAME` varchar(50) NOT NULL,
  `LASTNAME` varchar(50) NOT NULL,
  `EMAIL` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `USERNAME`, `PASSWORD`, `FIRSTNAME`, `LASTNAME`, `EMAIL`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', 'admin@yahoo.com'),
(2, 'iulian', 'iulian', 'Gilca', 'Iulian', 'iulian.glk@yahoo.ro');

--
-- Triggers `users`
--
DELIMITER $$
CREATE TRIGGER `users_roles` AFTER INSERT ON `users` FOR EACH ROW BEGIN 
INSERT INTO users_roles
VALUES(new.username, 'user');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `username` varchar(50) DEFAULT NULL,
  `rolename` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`username`, `rolename`) VALUES
('admin', 'admin'),
('iulian', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `movie_genre`
--
ALTER TABLE `movie_genre`
  ADD KEY `id_movie` (`id_movie`),
  ADD KEY `id_genre` (`id_genre`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`),
  ADD UNIQUE KEY `USERNAME` (`USERNAME`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
  MODIFY `ID` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `movie_genre`
--
ALTER TABLE `movie_genre`
  ADD CONSTRAINT `movie_genre_ibfk_1` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`ID`),
  ADD CONSTRAINT `movie_genre_ibfk_2` FOREIGN KEY (`id_genre`) REFERENCES `genre` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
