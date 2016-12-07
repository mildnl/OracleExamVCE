/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import java.util.ArrayList;

/**
 *
 * @author PaulsBook
 */
public class CardBox {

    private ArrayList<Card> cards;
    private int NumberOfCards;

    public int getNumberOfCards() {
        return NumberOfCards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCard(ArrayList<Card> cards) {
        this.cards = cards;
    }

//    public CardBox(ArrayList<Card> cards) {
//        this.cards = cards;
//        this.NumberOfCards = cards.size();
//
//    }

    
    //###############sani was here###############################
    
    // neue CardBox wird direkt mit Karten erstellt
    // setzt Gesamtanzahl der Karten
    
    
    public CardBox(ArrayList<Category> categories) {
        this.cards = fillCardBox(categories);
        this.NumberOfCards=cards.size();
    }
       
    
    // füllt CardBox:
    // durchläuft KategorienArray und zieht sich dabei die dazugehörigen Fragen,    
    // überprüft ob die Frage im questionsArray schon erhalten ist,
    // wenn nicht, wird es dem questionsArray hinzugefügt
    //befüllt Card über jede QuestionID die sich nun im questionsArray befindet
    //gibt CardsArray zurück

    public static ArrayList<Card> fillCardBox(ArrayList<Category> categories) {
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Card> cardsi = new ArrayList<>();
        for (Category category : categories) {
            for (Question question : Question.getAllQuestionsByCategoryId(category.getId())) {
                if (!questions.contains(question)) {
                    questions.add(question);
                }
            }
        }
        for (Question question : questions) {
            cardsi.add(new Card(question.getId()));
        }

        return cardsi;

    }
//######################sani was here#####################################################
    
    public Card nextCard(int cardBoxIndex) {
        return this.cards.get(cardBoxIndex+1);
    }
    
    
    @Override
    public String toString() {
        return "CardBox{" + "cards=" + cards + '}';
    }

}
