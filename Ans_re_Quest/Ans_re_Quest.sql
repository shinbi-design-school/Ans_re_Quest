-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2023-07-02 11:21:41
-- サーバのバージョン： 10.4.28-MariaDB
-- PHP のバージョン: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- データベース: `ans_re_quest`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `battles`
--

CREATE TABLE `battles` (
  `battle_id` int(11) NOT NULL,
  `player_id` int(11) DEFAULT NULL,
  `monster_id` int(11) DEFAULT NULL,
  `result` varchar(10) DEFAULT NULL,
  `experience_gained` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- テーブルの構造 `enemies`
--

CREATE TABLE `enemies` (
  `enemy_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `hp` int(11) DEFAULT NULL,
  `mp` int(11) DEFAULT NULL,
  `attack` int(11) DEFAULT NULL,
  `defense` int(11) DEFAULT NULL,
  `experience_reward` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `enemies`
--

INSERT INTO `enemies` (`enemy_id`, `name`, `hp`, `mp`, `attack`, `defense`, `experience_reward`) VALUES
(1, 'いちたろう', 11, 11, NULL, NULL, NULL),
(2, 'にたろう', 12, 12, NULL, NULL, NULL),
(3, 'さんたろう', 13, 13, NULL, NULL, NULL),
(4, 'よんたろう', 14, 14, NULL, NULL, NULL),
(5, 'ごたろう', 15, 15, NULL, NULL, NULL),
(6, 'ろくたろう', 16, 16, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- テーブルの構造 `items`
--

CREATE TABLE `items` (
  `item_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `effect` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- テーブルの構造 `players`
--

CREATE TABLE `players` (
  `player_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `experience` int(11) DEFAULT NULL,
  `hp` int(11) DEFAULT NULL,
  `mp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `players`
--

INSERT INTO `players` (`player_id`, `name`, `level`, `experience`, `hp`, `mp`) VALUES
(1, 'aaaa', 1, 1, 30, 30);

-- --------------------------------------------------------

--
-- テーブルの構造 `player_items`
--

CREATE TABLE `player_items` (
  `player_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- テーブルの構造 `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `text` text DEFAULT NULL,
  `correctAnswer` varchar(200) DEFAULT NULL,
  `choice1` varchar(200) DEFAULT NULL,
  `choice2` varchar(200) DEFAULT NULL,
  `choice3` varchar(200) DEFAULT NULL,
  `choice4` varchar(200) DEFAULT NULL,
  `ai_answer` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `questions`
--

INSERT INTO `questions` (`id`, `text`, `correctAnswer`, `choice1`, `choice2`, `choice3`, `choice4`, `ai_answer`) VALUES
(1, 'moziretu', 'moziretu', '1', '2', '3', 'moziretu', ''),
(2, '文字列', '文字列', '1', '2', '3', '文字列', ''),
(3, '3', '3', '1', '2', '3', '4', ''),
(4, '3', '3', '1', '2', '3', '4', ''),
(5, '3', '3', '1', '2', '3', '4', ''),
(6, '3', '3', '1', '2', '3', '4', ''),
(7, '3', '3', '1', '2', '3', '4', ''),
(8, '3', '3', '1', '2', '3', '4', ''),
(9, '3', '3', '1', '2', '3', '4', ''),
(10, '3', '3', '1', '2', '3', '4', '');

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `battles`
--
ALTER TABLE `battles`
  ADD PRIMARY KEY (`battle_id`),
  ADD KEY `player_id` (`player_id`),
  ADD KEY `monster_id` (`monster_id`);

--
-- テーブルのインデックス `enemies`
--
ALTER TABLE `enemies`
  ADD PRIMARY KEY (`enemy_id`);

--
-- テーブルのインデックス `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`item_id`);

--
-- テーブルのインデックス `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`player_id`);

--
-- テーブルのインデックス `player_items`
--
ALTER TABLE `player_items`
  ADD PRIMARY KEY (`player_id`,`item_id`),
  ADD KEY `item_id` (`item_id`);

--
-- テーブルのインデックス `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);

--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- ダンプしたテーブルの制約
--

--
-- テーブルの制約 `battles`
--
ALTER TABLE `battles`
  ADD CONSTRAINT `battles_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`),
  ADD CONSTRAINT `battles_ibfk_2` FOREIGN KEY (`monster_id`) REFERENCES `enemies` (`enemy_id`);

--
-- テーブルの制約 `player_items`
--
ALTER TABLE `player_items`
  ADD CONSTRAINT `player_items_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`),
  ADD CONSTRAINT `player_items_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;