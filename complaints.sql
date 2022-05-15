-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 06:05 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `complaints`
--

CREATE TABLE `complaints` (
  `complaintID` int(11) NOT NULL,
  `accountNumber` int(11) DEFAULT NULL,
  `complaintDate` varchar(20) DEFAULT NULL,
  `nComplaint` varchar(200) DEFAULT NULL,
  `anythingMoreToTell` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complaints`
--

INSERT INTO `complaints` (`complaintID`, `accountNumber`, `complaintDate`, `nComplaint`, `anythingMoreToTell`) VALUES
(1, 100236, '25-01-2022', 'Tree has fallen on to the line', 'Dear sir, Immediately remove the tree and provice electricity.'),
(2, 100235, '18-02-2022', 'Meter Burning ', 'Please take step for this problem.'),
(3, 100265, '21-04-2022', 'Tree has fallen on to the line', 'Dear sir, Immediately remove the tree and provice electricity.'),
(4, 100228, '25-04-2022', 'Tree has fallen on to the line', 'Immediately remove the tree and provice electricity.'),
(20, 100229, '29-01-2022', 'Meter Burning', 'No');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `complaints`
--
ALTER TABLE `complaints`
  ADD PRIMARY KEY (`complaintID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `complaints`
--
ALTER TABLE `complaints`
  MODIFY `complaintID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
