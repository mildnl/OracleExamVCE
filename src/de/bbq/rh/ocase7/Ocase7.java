package de.bbq.rh.ocase7;

import de.bbq.rh.ocase7.card.Answer;
import de.bbq.rh.ocase7.card.Card;
import de.bbq.rh.ocase7.card.Cardbox;
import de.bbq.rh.ocase7.card.Category;
import de.bbq.rh.ocase7.database.MySQLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ocase7 {
    
    public void end(Scanner scn) {
        try {
                if (MySQLConnection.pst != null) {
                    MySQLConnection.pst.close();
                }
                if (MySQLConnection.rst != null) {
                    MySQLConnection.rst.close();
                }
                scn.close();
                MySQLConnection.closeConnection();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Category> getCompleteCategoriesList(Cardbox c) {
        for (Category category : c.getCardBox().get(0).getCat().getAllList()) {
            System.out.println(category.toString());
        }
        return c.getCardBox().get(0).getCat().getAllList();
    }
    
    public Cardbox getCardboxByQuestionID(Cardbox c, int id) {
        c.getCardBox().set(0, c.getCardBox().set(0,c.getCardBox().get(0).getById(c.getCardBox().get(0),id)));
        return c;
    }
    
    public void printQuestionByCardbox(Cardbox c, Scanner scn, Ocase7 o) {
        Answer a = new Answer();
        System.out.println("Cardbox size is " + c.getCardBox().size());
        System.out.println("Start = true");
        boolean continueToNextQuestion = scn.nextBoolean();
        do {
            for (Card i : c.getCardBox()) {
                System.out.println("--------------------------------");
                System.out.println(i.getId());
                System.out.println("--------------------------------");
                System.out.println(i.getQuestion());
                System.out.println("--------------------------------");
                o.printAnswersByQuestion(a, i);
                System.out.println("--------------------------------");
            }
            System.out.println("Continue = true");
            continueToNextQuestion = scn.nextBoolean();
        } while (continueToNextQuestion);
    }
    
    public void printAnswersByQuestion(Answer a, Card i) {
        ArrayList<Answer> answerList = new ArrayList<>();
                answerList.addAll(a.getAnswersByQuestion(i.getId()));
                System.out.println("answerList size is " + answerList.size());
                for (Answer answer : answerList) {
                    System.out.println(answer.getText());
                }
    }
    
    public Cardbox getQuestionsByCategoryID(Cardbox c, int categoryID , Category k) {
        ArrayList<Card> categoryQuestionCards = new ArrayList<>();
        
        ArrayList<Integer> questionList = new ArrayList<>();
        questionList.addAll(k.getQuestionIDListByCategoryID(categoryID));
        for (Integer i : questionList) {
            Card currentCard = c.getCardBox().get(0).getById(c.getCardBox().get(0), i);
            categoryQuestionCards.add(currentCard); 
        }
        c.getCardBox().clear();
        c.getCardBox().addAll(categoryQuestionCards);
        return c;
    }
    
    public void getAnswersByQuestionID() {
        
    }
        

    public static void main(String[] args) {
        Ocase7 o = new Ocase7();
        MySQLConnection mySQLDB = new MySQLConnection();
        Cardbox cardBox = new Cardbox();
        Scanner scn = new Scanner(System.in);
        ArrayList<Category> categoryList = o.getCompleteCategoriesList(cardBox); 
        System.out.println("Which Category ID?");
        int categoryID = scn.nextInt();
        cardBox = o.getQuestionsByCategoryID(cardBox, categoryID, categoryList.get(0));
        o.printQuestionByCardbox(cardBox, scn, o);
        
//        o.getCardboxByQuestionID(cardBox, index);
        
        
//        while (index < cardBox.getCardBox().size()) {
//            
//        }  
        o.end(scn);
    } 
}
