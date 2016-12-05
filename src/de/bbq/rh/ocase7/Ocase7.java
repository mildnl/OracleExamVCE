package de.bbq.rh.ocase7;

import de.bbq.rh.ocase7.card.Answer;
import de.bbq.rh.ocase7.card.Card;
import de.bbq.rh.ocase7.card.Cardbox;
import de.bbq.rh.ocase7.database.MySQLConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ocase7 {
    
    public void end() {
        try {
                if (MySQLConnection.pst != null) {
                    MySQLConnection.pst.close();
                }
                if (MySQLConnection.rst != null) {
                    MySQLConnection.rst.close();
                }
                MySQLConnection.closeConnection();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Ocase7 o = new Ocase7();
        MySQLConnection mySQLDB = new MySQLConnection();
        Cardbox cardBox = new Cardbox();
        cardBox.getCardBox().set(0,cardBox.getCardBox().get(0).getById(cardBox.getCardBox().get(0),1));
        for (Card i : cardBox.getCardBox()) {
            System.out.println("--------------------------------");
            System.out.println(i.getId());
            System.out.println("--------------------------------");
            System.out.println(i.getQuestion());
            System.out.println("--------------------------------");
            i.getAnswerList().forEach((a) -> {
                a.getAnswersByQuestion(i.getId());
            });
            
        }
//        Test t = new Test(2, "Test 1 3");
//        t.update(t);
//        t.getById(2);
//        cardBox.getAllList().forEach((i) -> {
//            System.out.println("--------------------------------");
//            System.out.println("Question:");
//            System.out.println(i.getQuestion());
//            System.out.println("--------------------------------");
//            System.out.println("Answers");
//            System.out.println(i.getAnswerList().get(i.getId()));
//            System.out.println("--------------------------------");
//        });
        
        o.end();
    }
}
