-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 08, 2023 at 01:26 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `filmovie2`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tpost`
--

CREATE TABLE `tpost` (
  `authorId` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `threadId` int(11) NOT NULL,
  `creationTime` datetime(6) DEFAULT NULL,
  `contents` varchar(10000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tpost`
--

INSERT INTO `tpost` (`authorId`, `id`, `threadId`, `creationTime`, `contents`) VALUES
(1, 1, 1, '2023-08-07 15:36:01.000000', '23456456787654');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tthread`
--

CREATE TABLE `tthread` (
  `authorId` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `topicId` int(11) NOT NULL,
  `creationTime` datetime(6) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tthread`
--

INSERT INTO `tthread` (`authorId`, `id`, `topicId`, `creationTime`, `subject`) VALUES
(1, 1, 1, '2023-08-07 15:36:01.000000', '34567876543');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ttopic`
--

CREATE TABLE `ttopic` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ttopic`
--

INSERT INTO `ttopic` (`id`, `description`, `name`) VALUES
(1, '1234565433456', '123456765432');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tuser`
--

CREATE TABLE `tuser` (
  `birthday` date DEFAULT NULL,
  `id` int(11) NOT NULL,
  `joinDate` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `function` enum('ADMIN','USER') DEFAULT NULL,
  `gender` enum('FEMALE','MALE') DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tuser`
--

INSERT INTO `tuser` (`birthday`, `id`, `joinDate`, `email`, `function`, `gender`, `login`, `password`, `place`) VALUES
('1990-05-01', 1, '2023-08-07 15:33:28.000000', 'admin@filmovie.pl', 'ADMIN', 'MALE', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'Rzeszów');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `tpost`
--
ALTER TABLE `tpost`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `tthread`
--
ALTER TABLE `tthread`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `ttopic`
--
ALTER TABLE `ttopic`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `tuser`
--
ALTER TABLE `tuser`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_fxd42ti0c4d7jrttsf8h0lvvn` (`login`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tpost`
--
ALTER TABLE `tpost`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tthread`
--
ALTER TABLE `tthread`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ttopic`
--
ALTER TABLE `ttopic`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tuser`
--
ALTER TABLE `tuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
