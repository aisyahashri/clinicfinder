-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 01, 2025 at 11:28 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clinicfinder`
--

-- --------------------------------------------------------

--
-- Table structure for table `clinics`
--

CREATE TABLE `clinics` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `longitude` varchar(255) NOT NULL,
  `latitude` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clinics`
--

INSERT INTO `clinics` (`id`, `name`, `longitude`, `latitude`) VALUES
(1, 'Arau Clinic', '100.27518472208341', '6.446870079843356'),
(2, 'Klinik Haji Adnan', '100.23781005326747', '6.447159967948237'),
(3, 'Poliklinik Dr.Azhar dan Rakan-rakan Jejawi', '100.23600265757386', '6.444927262915474'),
(4, 'Poliklinik Dr.Azhar dan Rakan-rakan Pauh', '100.30461652200484', '6.437510562131281'),
(5, 'Unit Kesihatan Klinik UiTM', '100.28006094017391', '6.44708468548699'),
(6, 'Kampung Gial Health Clinic', '100.27403387437762', '6.465416385595192'),
(7, 'Klinik Kamil Arif', '100.27350664348492', '6.428171540269451'),
(8, 'Arau Health Clinic', '100.26951854655158', '6.434067712236922'),
(9, 'Naurah Clinic', '100.297435225085', '6.435324264633094'),
(10, 'KLINIK REMEDIC PAUH', '100.30369620760084', '6.437276139032896');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `ip_address` varchar(64) NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp(),
  `location` varchar(255) NOT NULL,
  `user_agent` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `ip_address`, `date`, `location`, `user_agent`) VALUES
(12, 'Alif Najmuddin', 'din@gmail.com', '127.0.0.1', '2025-02-01 21:57:41', 'User logged in from Lat: 6.4297067, Long: 100.2698467', 'Dalvik/2.1.0 (Linux; U; Android 15; sdk_gphone64_x86_64 Build/AE3A.240806.005)'),
(13, 'Haydar', 'haydar@gmail.com', '127.0.0.1', '2025-02-01 22:03:21', 'User logged in from Lat: 6.4297067, Long: 100.2698467', 'Dalvik/2.1.0 (Linux; U; Android 15; sdk_gphone64_x86_64 Build/AE3A.240806.005)'),
(14, 'Irfan Naqiuddin', 'irfan@gmail.com', '127.0.0.1', '2025-02-01 22:04:09', 'User logged in from Lat: 6.4297067, Long: 100.2698467', 'Dalvik/2.1.0 (Linux; U; Android 15; sdk_gphone64_x86_64 Build/AE3A.240806.005)'),
(15, 'Syafiq Hazwan', 'syafiq@gmail.com', '127.0.0.1', '2025-02-01 22:06:59', 'User logged in from Lat: 6.4297067, Long: 100.2698467', 'Dalvik/2.1.0 (Linux; U; Android 15; sdk_gphone64_x86_64 Build/AE3A.240806.005)'),
(16, 'Irdina Jelani', 'dina@gmail.com', '127.0.0.1', '2025-02-01 22:07:54', 'User logged in from Lat: 6.4297067, Long: 100.2698467', 'Dalvik/2.1.0 (Linux; U; Android 15; sdk_gphone64_x86_64 Build/AE3A.240806.005)'),
(17, 'Nurul Ain Natasya', 'tasya@gmail.com', '127.0.0.1', '2025-02-01 22:09:09', 'User logged in from Lat: 6.4297067, Long: 100.2698467', 'Dalvik/2.1.0 (Linux; U; Android 15; sdk_gphone64_x86_64 Build/AE3A.240806.005)'),
(18, 'Siti Aisyah', 'caa@gmail.com', '127.0.0.1', '2025-02-01 22:10:53', 'User logged in from Lat: 6.4297067, Long: 100.2698467', 'Dalvik/2.1.0 (Linux; U; Android 15; sdk_gphone64_x86_64 Build/AE3A.240806.005)'),
(19, 'Irfan Naqiuddin', 'irfan@gmail.com', '127.0.0.1', '2025-02-01 23:13:45', 'User logged in from Lat: 6.4297067, Long: 100.2698467', 'Dalvik/2.1.0 (Linux; U; Android 15; sdk_gphone64_x86_64 Build/AE3A.240806.005)');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clinics`
--
ALTER TABLE `clinics`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clinics`
--
ALTER TABLE `clinics`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
