package de.bbq.rh.oracleExams;

import de.bbq.rh.oracleExams.card.Cardbox;
import de.bbq.rh.oracleExams.card.Category;
import de.bbq.rh.oracleExams.database.MySQLConnection;
import de.bbq.rh.oracleExams.sceneOne.LoginScene;
import de.bbq.rh.oracleExams.session.User;
import de.bbq.rh.oracleExams.sceneThree.QuestionAndAnswerScene;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Scene currentScene;
    private Stage stage;

    public Scene getCurrentScene() {
        return this.currentScene;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Main(Scene currentScene, Stage stage) {
        this.currentScene = currentScene;
        this.stage = stage;
    }
    
    public Main() {
        this(null, null);
    }

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
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setStage(primaryStage);
        
        primaryStage.setTitle("Oracle Exam VCE Player");
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(660);
        
        LoginScene loginView = new LoginScene();
        setCurrentScene(loginView.createLoginView(this));
        
        primaryStage.setScene(getCurrentScene());

        primaryStage.show();
    }
}
