-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 08, 2023 at 01:25 PM
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

--
-- Dumping data for table `tpost`
--

INSERT INTO `tpost` (`id`, `thread_id`, `author_id`, `creation_time`, `contents`) VALUES
(10, 6, 1, '2023-08-03 13:51:15', 'Witam serdecznie i zapraszam do korzystania z Forum Filmovie!'),
(11, 6, 2, '2023-08-03 15:47:11', 'Hej, super miejsce :)'),
(12, 7, 2, '2023-08-03 15:47:35', 'Hej, pomorze się kłania!'),
(13, 7, 3, '2023-08-03 15:48:04', 'Powitać pana Kubę :]'),
(14, 8, 4, '2023-08-03 15:48:52', 'Siemaneczko pierożki moje kochane :***'),
(15, 8, 3, '2023-08-03 15:49:11', 'Hej!'),
(16, 8, 1, '2023-08-03 15:49:31', 'Cześć i czołem!'),
(17, 8, 5, '2023-08-03 15:50:04', 'Cześć Aniu :*'),
(18, 9, 5, '2023-08-03 15:50:57', 'Siemka, tu Gosia, do napisania :)'),
(19, 9, 1, '2023-08-03 15:51:14', 'Witamy :)'),
(20, 6, 5, '2023-08-03 15:51:58', 'Cześć, coś będziemy filmować :)'),
(21, 6, 3, '2023-08-03 15:52:43', 'Hejka, działamy z tematem!'),
(22, 10, 6, '2023-08-03 15:53:23', 'Nie rozumiem tego filmu...'),
(23, 10, 2, '2023-08-03 15:53:55', 'Żeby chłop nie mógł z gołą babą w windzie :)'),
(24, 10, 5, '2023-08-03 15:54:19', 'No właśnie? O co kaman?'),
(25, 11, 2, '2023-08-03 15:55:11', 'Myślałem, że to film o ufoludkach...'),
(26, 11, 4, '2023-08-03 15:55:41', 'Sam jesteś ufoludek! :P'),
(27, 12, 2, '2023-08-03 15:56:38', 'Co z tym chłopem jest, że ludzie go albo kochają albo nienawidzą?'),
(28, 13, 4, '2023-08-03 15:57:19', 'Kradłam konta tyle lat, a teraz już nie mogę, co robić?'),
(29, 13, 3, '2023-08-03 15:57:50', 'Nie zabijaj - nie cudzołóż - nie kradnij! :P'),
(30, 14, 3, '2023-08-03 15:58:22', 'Czy oni się wreszcie określą co do nazwy usługi?'),
(31, 14, 5, '2023-08-03 15:59:02', 'I tak ich teraz przejmują draby z Discovery, więc już niedługo ta rozkminka potrwa :P'),
(32, 15, 3, '2023-08-03 15:59:36', 'Nigdy tego nie oglądałem. Warto?'),
(33, 15, 1, '2023-08-03 16:00:24', 'Jak masz kilka tygodni wolne, żeby nadrobić tyle sezonów - to warto!'),
(34, 16, 3, '2023-08-03 16:01:04', 'Same głupoty na dużym ekranie... Coś polecacie?'),
(35, 16, 4, '2023-08-03 16:01:42', 'A może po prostu pójdziemy do kina - niekoniecznie na film ;)'),
(36, 16, 1, '2023-08-03 16:02:13', 'To jak się kolega nie zdecyduje, to ja bardzo chętnie :P'),
(37, 16, 6, '2023-08-03 16:02:39', 'To może podwójna randka? ;)'),
(38, 16, 2, '2023-08-03 16:03:19', 'A może we trójkę pójdziemy, co dziewczyny?'),
(39, 16, 5, '2023-08-03 16:04:02', 'Znając życie, to najpewniej pójdziecie we trójkę, ale z chłopakami :D'),
(40, 17, 1, '2023-08-03 16:04:48', 'Ja myślę o Mazurach albo o Madagaskarze.'),
(41, 17, 6, '2023-08-03 16:05:28', 'No to masz dobry rozstrzał. Gdzie Mazury, a gdzie Madagaskar? :D'),
(42, 17, 1, '2023-08-03 16:05:54', 'Bo w tym roku muszę wybyć gdzieś do miejsca na \"M\" :)'),
(43, 17, 4, '2023-08-03 16:06:27', 'To ja zapraszam na wakacje do Mnie :)'),
(44, 18, 4, '2023-08-03 16:07:11', 'Oglądałam ostatnio jakiś odcinek i to jest strasznie żenujący serial...'),
(45, 18, 3, '2023-08-03 16:08:03', 'Kiedyś był nawet śmieszny, ale rzeczywiście, dzisiaj wygląda strasznie słabo.'),
(46, 18, 6, '2023-08-03 16:08:30', 'Kiedyś ludzie mieli inne poczucie humoru :)'),
(47, 19, 5, '2023-08-03 16:10:06', 'Wajda umarł, Kieślowski umarł... Ja też się ostatnio coś źle czuję...'),
(48, 12, 5, '2023-08-03 16:10:46', 'Ja go lubię, chociaż straszny rasista i seksista.'),
(49, 12, 3, '2023-08-03 16:12:49', 'Co ty opowiadasz? W Jackie Brown główną rolę grała czarna kobieta! W co drugim filmie gra Samuel L. Jackson, a w Death Proof jest 6 głównych ról żeńskich!'),
(50, 20, 6, '2023-08-03 16:14:06', 'DiCaprio czy Foxx?'),
(51, 20, 5, '2023-08-03 16:14:32', 'DiCaprio <3'),
(52, 20, 2, '2023-08-03 16:14:54', 'Samuel L. Jackson <3'),
(53, 6, 4, '2023-08-05 07:07:30', 'Cześć wszystkim :]'),
(54, 6, 6, '2023-08-05 07:10:33', 'Buziaczki :)');

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

--
-- Dumping data for table `tthread`
--

INSERT INTO `tthread` (`id`, `author_id`, `topic_id`, `subject`, `creation_time`) VALUES
(6, 1, 1, 'Witam wszystkich na forum', '2023-08-03 13:51:15'),
(7, 2, 1, 'Kuba pozdrawia :)', '2023-08-03 15:47:35'),
(8, 4, 1, 'Gdańszczana ELUWINA!!!', '2023-08-03 15:48:52'),
(9, 5, 1, 'Poznańskie koziołki witają', '2023-08-03 15:50:57'),
(10, 6, 2, 'Seksmisja - o co tu chodzi?', '2023-08-03 15:53:23'),
(11, 2, 3, 'Shrek - historia prawdziwa', '2023-08-03 15:55:11'),
(12, 2, 7, 'Quentin Tarantino - legenda czy bufon?', '2023-08-03 15:56:38'),
(13, 4, 9, 'Zablokowali mi NETFIXa!!!', '2023-08-03 15:57:19'),
(14, 3, 9, 'HBO Max czy HBO GO', '2023-08-03 15:58:22'),
(15, 3, 5, 'Gra o Tron', '2023-08-03 15:59:36'),
(16, 3, 8, 'Na co teraz warto iść do kina?', '2023-08-03 16:01:04'),
(17, 1, 10, 'Gdzie na wakacje uderzacie?', '2023-08-03 16:04:48'),
(18, 4, 4, '13 Posterunek', '2023-08-03 16:07:11'),
(19, 5, 6, 'Wajda, Kieślowski...', '2023-08-03 16:10:06'),
(20, 6, 3, 'Django', '2023-08-03 16:14:06');

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
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@filmovie.pl', '1995-07-31', 'MALE', '2023-07-02 18:17:35', 'Rzeszów', 'ADMIN'),
(2, 'kuba', 'fccbce33643556ee698db7d599853a1f', 'kuba@kuba.pl', '1998-05-14', 'MALE', '2023-07-12 18:35:16', 'Sopot', 'USER'),
(3, 'wojtek', '0d333f240498cfd51eb8bd1d74ee0f6e', 'wojtek@wojtek.pl', '1985-05-12', 'MALE', '2023-07-18 15:38:07', 'Wrocław', 'ADMIN'),
(4, 'ania', '5f59ac736640f43e61c6070284bf1c06', 'ania@ania.pl', '2000-01-13', 'FEMALE', '2023-07-28 15:44:39', 'Gdańsk', 'USER'),
(5, 'gosia', '065887557599a38b8580aedbe14566dd', 'gosia@gosia.pl', '2002-09-16', 'FEMALE', '2023-08-01 15:46:25', 'Poznań', 'USER'),
(6, 'zuza', '11dae1584a3dc4df2ea741268a7b86d3', 'zuza@zuza.pl', '1998-04-20', 'FEMALE', '2023-08-03 17:41:02', 'Warszawa', 'USER');

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
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tpost`
--
ALTER TABLE `tpost`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `tthread`
--
ALTER TABLE `tthread`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `ttopic`
--
ALTER TABLE `ttopic`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tuser`
--
ALTER TABLE `tuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
