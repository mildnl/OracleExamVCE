package de.bbq.rh.ocase7;

import de.bbq.rh.ocase7.card.Cardbox;
import de.bbq.rh.ocase7.card.Category;
import de.bbq.rh.ocase7.database.MySQLConnection;
import de.bbq.rh.ocase7.login.LoginView;
import de.bbq.rh.ocase7.session.User;
import de.bbq.rh.ocase7.view3.QuestionAndAnswerView;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
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

    public void printCategoriesList(Category c) {
        for (Category category : c.getAllList()) {
            System.out.println(category.toString());
        }
    }

    public Cardbox getCardboxByQuestionID(Cardbox c, int id) {
        c.getCardList().set(0, c.getCardList().set(0, c.getCardList().get(0).getById(c.getCardList().get(0), id)));
        return c;
    }

//    public void printQuestionByCardbox(Cardbox c, Scanner scn, Ocase7 o) {
//        System.out.println("Cardbox size is " + c.getCardList().size());
//        System.out.println("Start = true");
//        boolean continueToNextQuestion = scn.nextBoolean();
//
//        for (Card card : c.getCardList()) {
//            while (continueToNextQuestion) {
//                System.out.println("--------------------------------");
//                System.out.println(card.getId());
//                System.out.println("--------------------------------");
//                System.out.println(card.getQuestion());
//                System.out.println("--------------------------------");
//                o.printAnswersByQuestion(card.getAnswer());
//                System.out.println("--------------------------------");
//                System.out.println("Continue = true");
//                continueToNextQuestion = scn.nextBoolean();
//            }
//        }
//
//    }
//    
//    
//
//    public void printAnswersByQuestion(Answer answer) {
//        int counter = 1;
//        for (String a : answer.getTextList()) {
//            System.out.println(counter + ") " + a);
//            System.out.println("");
//            counter++;
//        }
//    }
    public static void main(String[] args) {
//        Ocase7 o = new Ocase7();
//        User u = new User();
//        Session s = new Session(u);
//        System.out.println("");
//        System.out.println("sessionID: " + s.getId());
//        System.out.println("Name: " + s.getUser().getName());
//        System.out.println("");
//
//        Cardbox cardbox = s.getSessionBox();
//        Card card = cardbox.getCardList().get(0);
//        Category category = cardbox.getCardList().get(0).getCat();
//
//        o.printCategoriesList(category);
//
//        Scanner scn = new Scanner(System.in);
//        System.out.println("Which Category ID?");
//        int categoryID = scn.nextInt();
//
//        s.setSessionBox((Cardbox) cardbox.getCardboxByCategoryID(cardbox, categoryID));
//        cardbox = s.getSessionBox();
//        o.printQuestionByCardbox(cardbox, scn, o);
//
//        o.end(scn);
//        --------------------------------------------------------

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("OCP6");
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(660);
        LoginView loginView = new LoginView();
        Scene loginViewScene = loginView.createLoginView();
        QuestionAndAnswerView viewThree = new QuestionAndAnswerView(new User());
        Scene viewThreeScene = viewThree.createViewThree();

        primaryStage.setScene(viewThreeScene);

        primaryStage.show();

//        while (!loginView.isLoginComplete()) {
//            if (loginView.getLoggedUser() != null) {
//                viewThree = new QuestionAndAnswerView(loginView.getLoggedUser());
//                viewThreeScene = viewThree.createViewThree();
//
//            }
//
//            primaryStage.setScene(viewThreeScene);
//
//            primaryStage.show();
//        }
    }
}
