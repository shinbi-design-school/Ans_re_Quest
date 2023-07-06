-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2023-07-06 07:12:53
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
  `experience_reward` int(11) DEFAULT NULL,
  `genre` varchar(11) NOT NULL DEFAULT 'normal',
  `difficulty` varchar(11) NOT NULL DEFAULT 'normal'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `enemies`
--

INSERT INTO `enemies` (`enemy_id`, `name`, `hp`, `mp`, `attack`, `defense`, `experience_reward`, `genre`, `difficulty`) VALUES
(1, 'いちたろう', 11, 11, NULL, NULL, NULL, 'normal', 'normal'),
(2, 'にたろう', 12, 12, NULL, NULL, NULL, 'normal', 'normal'),
(3, 'さんたろう', 13, 13, NULL, NULL, NULL, 'normal', 'normal'),
(4, 'よんたろう', 14, 14, NULL, NULL, NULL, 'normal', 'normal'),
(5, 'ごたろう', 15, 15, NULL, NULL, NULL, 'normal', 'normal'),
(6, 'ろくたろう', 16, 16, NULL, NULL, NULL, 'normal', 'normal');

-- --------------------------------------------------------

--
-- テーブルの構造 `items`
--

CREATE TABLE `items` (
  `item_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `effect` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `items`
--

INSERT INTO `items` (`item_id`, `name`, `effect`) VALUES
(1, '5050', '選択肢を半分に絞る'),
(2, 'SKIP', 'この問題を飛ばす');

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
  `mp` int(11) DEFAULT NULL,
  `achieve` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `players`
--

INSERT INTO `players` (`player_id`, `name`, `level`, `experience`, `hp`, `mp`, `achieve`) VALUES
(1, 'aaaa', 1, 1, 30, 30, 0);

-- --------------------------------------------------------

--
-- テーブルの構造 `player_items`
--

CREATE TABLE `player_items` (
  `player_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `player_items`
--

INSERT INTO `player_items` (`player_id`, `item_id`, `quantity`) VALUES
(1, 1, 10),
(1, 2, 10);

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
  `ai_answer` text DEFAULT NULL,
  `limit_time` int(11) NOT NULL DEFAULT 15,
  `genre` varchar(11) NOT NULL DEFAULT 'normal',
  `difficulty` varchar(11) NOT NULL DEFAULT 'normal'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `questions`
--

INSERT INTO `questions` (`id`, `text`, `correctAnswer`, `choice1`, `choice2`, `choice3`, `choice4`, `ai_answer`, `limit_time`, `genre`, `difficulty`) VALUES
(1, 'moziretu　id=1', 'moziretu', '1', '2', '3', 'moziretu', 'こんにちは！ご質問がありますか？もしご質問があれば、お気軽にお聞きください。', 15, 'normal', 'normal'),
(2, '文字列', '文字列', '1', '2', '3', '文字列', 'こんにちは！ご質問がありますか？もしご質問があれば、お気軽にお聞きください。', 15, 'normal', 'normal'),
(3, '3', '3', '1', '2', '3', '4', 'こんにちは！ご質問がありますか？もしご質問があれば、お気軽にお聞きください。', 15, 'normal', 'normal'),
(4, '3', '3', '1', '2', '3', '4', 'こんにちは！ご質問がありますか？もしご質問があれば、お気軽にお聞きください。', 15, 'normal', 'normal'),
(5, '3', '3', '1', '2', '3', '4', 'こんにちは！ご質問がありますか？もしご質問があれば、お気軽にお聞きください。', 15, 'normal', 'normal'),
(6, '3', '3', '1', '2', '3', '4', 'こんにちは！ご質問がありますか？もしご質問があれば、お気軽にお聞きください。', 15, 'normal', 'normal'),
(7, '3', '3', '1', '2', '3', '4', 'こんにちは！ご質問がありますか？もしご質問があれば、お気軽にお聞きください。', 15, 'normal', 'normal'),
(8, '3', '3', '1', '2', '3', '4', 'こんにちは！ご質問がありますか？もしご質問があれば、お気軽にお聞きください。', 15, 'normal', 'normal'),
(9, '3', '3', '1', '2', '3', '4', 'こんにちは！ご質問がありますか？もしご質問があれば、お気軽にお聞きください。', 15, 'normal', 'normal'),
(10, '3', '3', '1', '2', '3', '4', 'こんにちは！ご質問がありますか？もしご質問があれば、お気軽にお聞きください。', 15, 'normal', 'normal');

-- --------------------------------------------------------

--
-- テーブルの構造 `towers`
--

CREATE TABLE `towers` (
  `tower_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `flores` int(11) NOT NULL,
  `event_flore` int(11) NOT NULL DEFAULT 3,
  `genre` varchar(11) NOT NULL,
  `difficulty` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `towers`
--

INSERT INTO `towers` (`tower_id`, `name`, `flores`, `event_flore`, `genre`, `difficulty`) VALUES
(1, '衒学の塔', 5, 3, 'normal', 'normal');

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
-- テーブルのインデックス `towers`
--
ALTER TABLE `towers`
  ADD PRIMARY KEY (`tower_id`);

--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- テーブルの AUTO_INCREMENT `towers`
--
ALTER TABLE `towers`
  MODIFY `tower_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
