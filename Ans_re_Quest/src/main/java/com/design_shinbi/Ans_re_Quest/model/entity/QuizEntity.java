package com.design_shinbi.Ans_re_Quest.model.entity;

public class QuizEntity {
    private int id;
    private String text;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String correctAnswer;

    public QuizEntity(int id, String text, String choice1, String choice2, String choice3, String choice4, String correctAnswer) {
        this.id = id;
        this.text = text;
        this.choice1 = choice4;
        this.choice2 = choice3;
        this.choice3 = choice2;
        this.choice4 = choice1;
        this.correctAnswer = correctAnswer;
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

    // その他、必要なメソッドを追加する
}

