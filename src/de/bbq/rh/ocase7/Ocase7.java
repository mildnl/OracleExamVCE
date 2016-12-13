package de.bbq.rh.ocase7;

import de.bbq.rh.ocase7.card.Answer;
import de.bbq.rh.ocase7.card.Card;
import de.bbq.rh.ocase7.card.Cardbox;
import de.bbq.rh.ocase7.card.Category;
import de.bbq.rh.ocase7.database.MySQLConnection;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ocase7 extends Application {
    
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
        System.out.println("Cardbox size is " + c.getCardBox().size());
        System.out.println("Start = true");
        boolean continueToNextQuestion = scn.nextBoolean();
        do {
            for (int i = 0; i < c.getCardBox().size(); i++) {
                System.out.println("--------------------------------");
                System.out.println(c.getCardBox().get(i).getId());
                System.out.println("--------------------------------");
                System.out.println(c.getCardBox().get(i).getQuestion());
                System.out.println("--------------------------------");
                o.printAnswersByQuestion(c.getCardBox().get(i).getAnswerList().get(i), c.getCardBox().get(i));
                System.out.println("--------------------------------");
            }
            System.out.println("Continue = true");
            continueToNextQuestion = scn.nextBoolean();
        } while (continueToNextQuestion);
    }
    
    public void printAnswersByQuestion(Answer a, Card c) {
        ArrayList<String> answerList = new ArrayList<>();
                answerList.addAll(a.getText());
                System.out.println("answerList size is " + answerList.size());
                for (String answer : answerList) {
                    System.out.println(answer);
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

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Welcome");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.show();
        
        
        primaryStage.setScene(scene);
    }
}
