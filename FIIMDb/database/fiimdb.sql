-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 22, 2017 at 05:26 PM
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
-- Table structure for table `actor`
--

CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `actor`
--

INSERT INTO `actor` (`id`, `name`, `nationality`, `age`) VALUES
(1, 'Morgan Freeman', 'english', 84),
(2, 'Marlon Brando', 'italian', 61),
(3, 'Leonardo DiCaprio', 'american', 43),
(4, 'Brad Pitt', 'american', 45),
(5, 'Ralph Fiennes', 'french', 30);

-- --------------------------------------------------------

--
-- Table structure for table `director`
--

CREATE TABLE `director` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `director`
--

INSERT INTO `director` (`id`, `name`, `nationality`, `age`) VALUES
(1, 'Frank Darabont', 'irish', 77),
(2, 'Francis Ford Coppola', 'american', 71),
(3, 'James Cameron', 'english', 83),
(4, 'David Fincher', 'spanish', 65),
(5, 'Wes Anderson', 'english', 30);

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
(5, '2014-03-21 00:00:00', 'The Grand Budapest Hotel', 4, 99, 'Ralph Fiennes', 'Wes Anderson', NULL, 'Wes Anderson');

-- --------------------------------------------------------

--
-- Table structure for table `movie_actor`
--

CREATE TABLE `movie_actor` (
  `id_movie` int(11) NOT NULL,
  `id_actor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie_actor`
--

INSERT INTO `movie_actor` (`id_movie`, `id_actor`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- --------------------------------------------------------

--
-- Table structure for table `movie_director`
--

CREATE TABLE `movie_director` (
  `id_movie` int(11) NOT NULL,
  `id_director` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie_director`
--

INSERT INTO `movie_director` (`id_movie`, `id_director`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

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
(5, 3);

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
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'admin', 'admin@yahoo.com'),
(2, 'iulian', 'B0B841D938EB014400284A704C786C85', 'Gilca', 'Iulian', 'iulian.glk@yahoo.ro'),
(3, 'ghidus', 'fe7f25ceb544f0396947024a2b616040', 'ghidus', 'ghidus', 'ghidus@mycat.com');

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
('iulian', 'user'),
('ghidus', 'user'),
('ala', 'user'),
('md', 'user'),
('jhasbfsdhfbskdfbwo', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `actor`
--
ALTER TABLE `actor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `director`
--
ALTER TABLE `director`
  ADD PRIMARY KEY (`id`);

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
-- Indexes for table `movie_actor`
--
ALTER TABLE `movie_actor`
  ADD KEY `id_movie` (`id_movie`),
  ADD KEY `id_actor` (`id_actor`);

--
-- Indexes for table `movie_director`
--
ALTER TABLE `movie_director`
  ADD KEY `id_movie` (`id_movie`),
  ADD KEY `id_director` (`id_director`);

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
-- AUTO_INCREMENT for table `actor`
--
ALTER TABLE `actor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `director`
--
ALTER TABLE `director`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
  MODIFY `ID` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `movie_actor`
--
ALTER TABLE `movie_actor`
  ADD CONSTRAINT `movie_actor_ibfk_1` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`ID`),
  ADD CONSTRAINT `movie_actor_ibfk_2` FOREIGN KEY (`id_actor`) REFERENCES `actor` (`id`);

--
-- Constraints for table `movie_director`
--
ALTER TABLE `movie_director`
  ADD CONSTRAINT `movie_director_ibfk_1` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`ID`),
  ADD CONSTRAINT `movie_director_ibfk_2` FOREIGN KEY (`id_director`) REFERENCES `director` (`id`);

--
-- Constraints for table `movie_genre`
--
ALTER TABLE `movie_genre`
  ADD CONSTRAINT `movie_genre_ibfk_1` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`ID`),
  ADD CONSTRAINT `movie_genre_ibfk_2` FOREIGN KEY (`id_genre`) REFERENCES `genre` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
