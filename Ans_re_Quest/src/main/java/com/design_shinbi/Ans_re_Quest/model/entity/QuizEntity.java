package com.design_shinbi.Ans_re_Quest.model.entity;

import java.util.ArrayList;
import java.util.List;

public class QuizEntity {
    private int id;
    private String text;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String correctAnswer;
    private String aiAnswer;
    private int limitTime;
    private String genre;
    private String difficulty;
    

    public QuizEntity(int id, String text, String choice1, String choice2, String choice3, String choice4, String correctAnswer,String aiAnswer, int limitTime, String genre,String difficulty) {
        this.id = id;
        this.text = text;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.correctAnswer = correctAnswer;
        this.aiAnswer = aiAnswer;
        this.limitTime = limitTime;
        this.genre = genre;
        this.difficulty = difficulty;
    }



	public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getChoice1() {
        return choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getAi_answer() {
        return aiAnswer;
    }
    
    public int getLimitTime() {
		return limitTime;
	}
    public String getGenre() {
        return genre;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public List<String> getOtherChoices() {
        List<String> otherChoices = new ArrayList<>();
        otherChoices.add(choice1);
        otherChoices.add(choice2);
        otherChoices.add(choice3);
        otherChoices.add(choice4);

        // 正解を他の選択肢から削除する
        otherChoices.remove(correctAnswer);

        return otherChoices;
    }

    // その他、必要なメソッドを追加する
}

