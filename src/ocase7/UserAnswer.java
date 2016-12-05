/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

/**
 *
 * @author PaulsBook
 */
public class UserAnswer extends Answer {
    
    private boolean isAnswerClicked;

    public UserAnswer(int id, String text, int question_id, int isRight) {
        super(id, text, question_id, isRight);
    }
    
    public boolean isIsAnswerClicked() {
        return isAnswerClicked;
    }

    public void setIsAnswerClicked(boolean isAnswerClicked) {
        this.isAnswerClicked = isAnswerClicked;
    }
    
}
