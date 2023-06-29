CREATE TABLE Players (
    player_id INT PRIMARY KEY,
    name VARCHAR(100),
    level INT,
    experience INT,
    hp INT,
    mp INT
);

CREATE TABLE Questions (
    question_id INT PRIMARY KEY,
    question_text TEXT,
    correct_answer VARCHAR(200),
    choice1 VARCHAR(200),
    choice2 VARCHAR(200),
    choice3 VARCHAR(200),
    choice4 VARCHAR(200)
);

CREATE TABLE Answers (
    answer_id INT PRIMARY KEY,
    player_id INT,
    question_id INT,
    chosen_answer VARCHAR(200),
    FOREIGN KEY (player_id) REFERENCES Players(player_id),
    FOREIGN KEY (question_id) REFERENCES Questions(question_id)
);

CREATE TABLE Monsters (
    monster_id INT PRIMARY KEY,
    name VARCHAR(100),
    hp INT,
    mp INT,
    attack INT,
    defense INT,
    experience_reward INT
);

CREATE TABLE Battles (
    battle_id INT PRIMARY KEY,
    player_id INT,
    monster_id INT,
    result VARCHAR(10),
    experience_gained INT,
    FOREIGN KEY (player_id) REFERENCES Players(player_id),
    FOREIGN KEY (monster_id) REFERENCES Monsters(monster_id)
);

CREATE TABLE Items (
    item_id INT PRIMARY KEY,
    name VARCHAR(100),
    effect TEXT
);

CREATE TABLE Player_Items (
    player_id INT,
    item_id INT,
    quantity INT,
    FOREIGN KEY (player_id) REFERENCES Players(player_id),
    FOREIGN KEY (item_id) REFERENCES Items(item_id),
    PRIMARY KEY (player_id, item_id)
);
