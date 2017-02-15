-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2016 at 08:13 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `librarysystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `description` varchar(200) NOT NULL,
  `status` int(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `name`, `author`, `description`, `status`) VALUES
(1, 'Love', 'charith', 'udara was best', 1),
(2, 'harry potter', 'j k rowling', 'Magic', 0);

-- --------------------------------------------------------

--
-- Table structure for table `borrower_record`
--

CREATE TABLE IF NOT EXISTS `borrower_record` (
  `id` int(5) NOT NULL,
  `borrower_no` int(5) NOT NULL,
  `bname` varchar(30) NOT NULL,
  `bookname` varchar(30) NOT NULL,
  `borrowdate` date NOT NULL DEFAULT '0000-00-00',
  `handoverdate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borrower_record`
--

INSERT INTO `borrower_record` (`id`, `borrower_no`, `bname`, `bookname`, `borrowdate`, `handoverdate`) VALUES
(1, 5, 'Love', 'Isira', '2016-05-09', '2016-05-23'),
(2, 5, 'harry potter', 'Isira', '2016-05-11', '2016-05-25');

-- --------------------------------------------------------

--
-- Table structure for table `confirm_code`
--

CREATE TABLE IF NOT EXISTS `confirm_code` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `confirm_code` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `confirm_code` (`confirm_code`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `confirm_code`
--

INSERT INTO `confirm_code` (`id`, `confirm_code`) VALUES
(1, '123456');

-- --------------------------------------------------------

--
-- Table structure for table `login_member`
--

CREATE TABLE IF NOT EXISTS `login_member` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `mem_no` int(5) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `login_member`
--

INSERT INTO `login_member` (`id`, `mem_no`, `username`, `password`) VALUES
(1, 1, 'sarasa', '92481426'),
(2, 2, 'nethmi', '92481426'),
(5, 5, 'isira', '123456'),
(6, 6, 'hasitha', '123456'),
(7, 7, 'shehan', '789456'),
(8, 8, 'dulan', '456789'),
(9, 9, 'hansi', 'abc123'),
(10, 10, 'aw', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) DEFAULT NULL,
  `sname` varchar(100) DEFAULT NULL,
  `dob` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `type` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `fname`, `sname`, `dob`, `email`, `telephone`, `type`) VALUES
(1, 'sarasa', 'gunawardana', '9-11-1992', 'sarasagunawardana@gmail.com', '0717859851', 'A'),
(2, 'nethmi', 'weerathunge', '24-5-1993', 'nethmi54@gmail.com', '0719745857', 'A'),
(5, 'Isira', 'Wellapuly', '4-05-1994', 'isirawelle@yahoo.com', '0774526892', 'U'),
(6, 'hasitha', 'gamage', '5-04-1995', 'hasithagamage@hotmail.com', '0784568521', 'U'),
(7, 'shehan', 'hettiarachchi', '29-07-1993', 'shehanhasi@yahoo.com', '0719215469', 'U'),
(8, 'dulan', 'kumarasinghe', '13-11-1991', 'dulankuma@hotmail.com', '0751452689', 'U'),
(9, 'hansi', 'abayarathna', '3-10-1995', 'hansiabayarathna@gmail.com', '0717294104', 'U'),
(10, 'wdf', 'rrff', '5-05-2000', 'efbf@gmail.com', '0786543217', 'U');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(5) NOT NULL,
  `r_person_no` int(5) NOT NULL,
  `r_bookname` varchar(30) NOT NULL,
  `rdate` date NOT NULL,
  `renddate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `r_person_no`, `r_bookname`, `rdate`, `renddate`) VALUES
(2, 5, 'harry potter', '2016-05-11', '2016-05-25');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
