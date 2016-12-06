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
public class Ocase7 {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        System.out.println(Test.getById(3));
//        Test.delete(3);
//        
//        // Update 
//        // Holt sich ein Objekt anhand der ID aus der DB
//        Test t = Test.getById(1);
//        // bearbeitet den Text des Objektes 
//        t.setText("Super Tach :(");
//        // schreibt das veränderte Objekt wieder in die DB
//        Test.update(t);

//        // liest alle Kategorien aus 
//        ArrayList<Category> c = Category.getAll();
//        CardBox cardBox = new CardBox();
//        for (int i = 0; i < c.size(); i++) {
//            ArrayList<Question> questions = Question.getQuestionsByCategory(c.get(i));
//            for (Question question : questions) {
//                Card card = new Card(question.getId(), question, Answer.getAnswersByQuestion(question));
//                cardBox = cardBox.fillCardBox(card);
//            }
//            System.out.println(cardBox.getCards());
//            System.out.println("##############################");
//            for (int j = 0; j < questions.size(); j++) {
//                ArrayList<Answer> answers = Answer.getAnswersByQuestion(questions.get(j));
////                for (Answer answer : answers) {
////                    // System.out.println(answer);
////                }
//            }
//        }



//########################sani was here##########################################

//        // zeigt CategoryTexte an
//        ArrayList<Category> categories = Category.getAll();
//        for (Category category : categories) {
//            System.out.println(category.getId() + " " + category.getText());
//        }
//        // befüllt CategoryArray mit ausgewählten CategoryIds
//
//        ArrayList<Category> choosenCategories = new ArrayList<>();
//        choosenCategories.add(categories.get(0));
//        choosenCategories.add(categories.get(1));
//
//        // erstellt neue CardBox anhand der eben erstellten CategoryArray
//        
//        CardBox cardbox = new CardBox(choosenCategories);
//
//        // Gibt jede Karte(Frage+Antworten) aus der CardBoy aus
//        
//        for (Card card : cardbox.getCards()) {
//            System.out.println(card.getQuestion().getText() + "      " + card.getId());
//            for (Answer answer : card.getAnswers()) {
//                System.out.println(answer.getText());
//            }
//
//            MySQLConnection.closeConnection();
//        }

    //}
//#################sani was here#########################################
}
