package de.bbq.rh.ocase7;

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
        return c.getCardBox().get(0).getCat().getAllList();
    }
    
    public Card getCardByID(Cardbox c, int id) {
        return c.getCardBox().set(0,c.getCardBox().get(0).getById(c.getCardBox().get(0),id));
    }
    
    public void printQuestionByCardbox(Cardbox c) {
        for (Card i : c.getCardBox()) {
                System.out.println("--------------------------------");
                System.out.println(i.getId());
                System.out.println("--------------------------------");
                System.out.println(i.getQuestion());
                System.out.println("--------------------------------");
                i.getAnswerList().forEach((a) -> {  
                    a.getAnswersByQuestion(i.getId()).forEach((b) -> {                  
                        System.out.println(b.getText());
                });
            });
        }
    }
        

    public static void main(String[] args) {
        Ocase7 o = new Ocase7();
        MySQLConnection mySQLDB = new MySQLConnection();
        Cardbox cardBox = new Cardbox();
        Scanner scn = new Scanner(System.in);
        
        int index = 1;
        
        
        while (index < cardBox.getCardBox().size()) {
            
        }  
        o.end(scn);
    } 
}
