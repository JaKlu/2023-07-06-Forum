-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 03, 2023 at 08:28 AM
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
-- Database: `filmovie`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tpost`
--

CREATE TABLE `tpost` (
  `id` int(11) NOT NULL,
  `thread_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `creation_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `contents` varchar(10000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tthread`
--

CREATE TABLE `tthread` (
  `id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `subject` varchar(256) NOT NULL,
  `creation_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ttopic`
--

CREATE TABLE `ttopic` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ttopic`
--

INSERT INTO `ttopic` (`id`, `name`, `description`) VALUES
(1, 'Powitania', 'Tutaj możesz się przywitać i napisać o sobie kilka słów!'),
(2, 'Filmy polskie', 'Rozmowy o filmach nakręconych w Polsce'),
(3, 'Filmy zagraniczne', 'Rozmowy o filmach nakręconych za granicą'),
(4, 'Seriale polskie', 'Rozmowy o serialach nakręconych w Polsce'),
(5, 'Seriale zagraniczne', 'Rozmowy o serialach nakręconych za granicą'),
(6, 'Reżyserzy polscy', 'Rozmowy o reżyserach pochodzących z Polski'),
(7, 'Reżyserzy zagraniczni', 'Rozmowy o reżyserach pochodzących zza granicy'),
(8, 'Kino', 'Rozmowy o premierach i aktualnie wyświetlanych filmach na wielkim ekranie'),
(9, 'Streaming', 'Rozmowy o premierach i aktualnie wyświetlanych filmach na małym ekranie'),
(10, 'Off Topic', 'Rozmowy o wszystkim i o niczym');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tuser`
--

CREATE TABLE `tuser` (
  `id` int(11) NOT NULL,
  `login` varchar(256) NOT NULL,
  `password` varchar(33) NOT NULL,
  `email` varchar(256) NOT NULL,
  `birthday` date NOT NULL,
  `gender` varchar(20) NOT NULL,
  `join_date` datetime NOT NULL,
  `place` varchar(256) NOT NULL,
  `function` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tuser`
--

INSERT INTO `tuser` (`id`, `login`, `password`, `email`, `birthday`, `gender`, `join_date`, `place`, `function`) VALUES
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@filmovie.pl', '1995-07-31', 'MALE', '2023-08-02 18:17:35', 'Rzeszów', 'ADMIN'),
(2, 'kuba', 'fccbce33643556ee698db7d599853a1f', 'kuba@kuba.pl', '1998-05-14', 'MALE', '2023-08-02 18:35:16', 'Wrocław', 'USER');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `tpost`
--
ALTER TABLE `tpost`
  ADD PRIMARY KEY (`id`),
  ADD KEY `contents` (`contents`(1024));

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
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tpost`
--
ALTER TABLE `tpost`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tthread`
--
ALTER TABLE `tthread`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `ttopic`
--
ALTER TABLE `ttopic`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tuser`
--
ALTER TABLE `tuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
