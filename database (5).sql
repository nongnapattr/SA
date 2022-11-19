-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 19, 2022 at 05:51 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `database`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `C_name` varchar(50) COLLATE utf8_thai_520_w2 NOT NULL,
  `C_id` int(1) NOT NULL,
  `C_tel` varchar(10) COLLATE utf8_thai_520_w2 NOT NULL,
  `C_username` varchar(50) COLLATE utf8_thai_520_w2 NOT NULL,
  `C_password` varchar(12) COLLATE utf8_thai_520_w2 NOT NULL,
  `C_status` varchar(2) COLLATE utf8_thai_520_w2 NOT NULL DEFAULT '0',
  `role` varchar(10) COLLATE utf8_thai_520_w2 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_thai_520_w2;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`C_name`, `C_id`, `C_tel`, `C_username`, `C_password`, `C_status`, `role`) VALUES
('dew', 1, '23', 'dew', '111', '1', 'Admin'),
('pp krit', 2, '0897648374', 'pp', '1234', '1', 'Customer'),
('bbillkin', 3, '0945677894', 'billkin', '12123', '1', 'Customer'),
('fish', 4, '0877789000', 'fish', '111111', '0', 'Customer'),
('film', 5, '0955748955', 'film', '345', '1', 'Customer'),
('nnuey', 6, '0857489954', 'nuey', '2222', '1', 'Customer'),
('rrose', 7, '0946378990', 'rose', '4444', '1', 'Customer'),
('jayb', 8, '0983748999', 'jayb', '22222', '1', 'Customer');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `O_id` int(5) NOT NULL,
  `Amount` varchar(10) COLLATE utf8_thai_520_w2 NOT NULL,
  `I_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_thai_520_w2;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`O_id`, `Amount`, `I_id`) VALUES
(1, '400', 1),
(2, '300', 2),
(3, '500', 3),
(4, '400', 4),
(5, '200', 5),
(6, '400', 6),
(7, '200', 7),
(8, '200', 8),
(9, '400', 9),
(10, '200', 10),
(11, '300', 11),
(12, '500', 12);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `O_id` int(5) NOT NULL,
  `C_id` int(5) DEFAULT NULL,
  `O_date` varchar(15) COLLATE utf8_thai_520_w2 DEFAULT NULL,
  `O_time` varchar(12) COLLATE utf8_thai_520_w2 DEFAULT NULL,
  `O_detail` varchar(50) COLLATE utf8_thai_520_w2 DEFAULT NULL,
  `O_status` varchar(2) COLLATE utf8_thai_520_w2 NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_thai_520_w2;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`O_id`, `C_id`, `O_date`, `O_time`, `O_detail`, `O_status`) VALUES
(1, 2, '2022-11-25', '12:00-13:00', 'นวดน้ำมัน (OIL MASSAGE)', '1'),
(2, 2, '2022-11-25', '11:00-12:00', 'นวดไทย (THAI MASSAGE AND OIL)', '1'),
(3, 3, '2022-11-25', '14:00-15:00', 'นวดฮอทออย (HOT OIL)', '1'),
(4, 5, '2022-11-25', '11:00-12:00', 'นวดน้ำมัน (OIL MASSAGE)', '1'),
(5, 6, '2022-11-25', '11:00-12:00', 'นวดฝ่าเท้า (FOOT MASSAGE)', '1'),
(6, 7, '2022-11-25', '12:00-13:00', 'ขัดตัวสมุนไพร (HERBAL BODY SCRUB)', '1'),
(7, 8, '2022-11-23', '14:00-15:00', 'นวดฝ่าเท้า (FOOT MASSAGE)', '0'),
(8, 8, '2022-11-23', '13:00-14:00', 'นวดฝ่าเท้า (FOOT MASSAGE)', '1'),
(9, 2, '2022-11-25', '12:00-13:00', 'นวดน้ำมัน (OIL MASSAGE)', '1'),
(10, 2, '2024-10-31', '15:00-16:00', 'นวดฝ่าเท้า (FOOT MASSAGE)', '1'),
(11, 2, '2022-11-25', '13:00-14:00', 'นวดไทย + ประคบ (THAI HERBAL HOT MASSAGE)', '0'),
(12, 2, '2022-12-03', '11:00-12:00', 'นวดฮอทออย (HOT OIL)', '1'),
(13, 2, '2022-11-30', '10:00-11:00', 'ขัดตัวสมุนไพร (HERBAL BODY SCRUB)', '0'),
(14, 2, '2022-12-01', '10:00-11:00', 'นวดไทย (THAI MASSAGE AND OIL)', '0'),
(15, 2, '2022-11-29', '13:00-14:00', 'นวดไทย + ประคบ (THAI HERBAL HOT MASSAGE)', '0'),
(16, 2, '2022-11-29', '14:00-15:00', 'นวดไทย + ประคบ (THAI HERBAL HOT MASSAGE)', '0'),
(17, 2, '2022-12-04', '12:00-13:00', 'นวดไทย + ประคบ (THAI HERBAL HOT MASSAGE)', '0'),
(18, 2, '2022-11-30', '11:00-12:00', 'นวดเปิดท่อน้ำนม หลังคลอด', '0'),
(19, 2, '2022-11-17', '14:00-15:00', 'นวดไทย + ประคบ (THAI HERBAL HOT MASSAGE)', '0'),
(20, 2, '2022-11-21', '12:00-13:00', 'นวดผ่อนคลาย (RELAXING MASSAGE)', '0'),
(21, 2, '2022-11-15', '12:00-13:00', 'นวดเปิดท่อน้ำนม หลังคลอด', '0'),
(22, 2, '2022-11-29', '13:00-14:00', 'นวดผ่อนคลาย (RELAXING MASSAGE)', '0'),
(23, 2, '2022-11-28', '13:00-14:00', 'นวดหลัง + ศรีษะ + ไหล่ (BACK + HEAD + SHOULDER)', '0'),
(24, 2, '2022-11-29', '12:00-13:00', 'นวดออยรีดเส้นตึง (OIL MASSAGE FOR TIGHT LINES)', '0'),
(25, 2, '2022-11-29', '11:00-12:00', 'นวดหลัง + ศรีษะ + ไหล่ (BACK + HEAD + SHOULDER)', '0'),
(26, 2, '2022-11-27', '13:00-14:00', 'นวดไทย + ประคบ (THAI HERBAL HOT MASSAGE)', '0');

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `R_id` int(5) NOT NULL,
  `C_id` int(5) NOT NULL,
  `I_id` int(5) NOT NULL,
  `R_date` varchar(12) CHARACTER SET utf8 COLLATE utf8_thai_520_w2 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`C_id`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`I_id`),
  ADD KEY `O_id` (`O_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`O_id`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`R_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `C_id` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `I_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `O_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `receipt`
--
ALTER TABLE `receipt`
  MODIFY `R_id` int(5) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`O_id`) REFERENCES `orders` (`O_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
