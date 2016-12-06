package ocase7.view3;

import com.sun.javafx.scene.control.skin.DatePickerContent;
import java.util.ArrayList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import ocase7.Card;
import ocase7.CardBox;
import ocase7.Category;

/**
 *
 * @author PaulsBook
 */
public class View3 {

    
    ocase7.Card myCard;
    CardBox cardBox;
    ArrayList<Category> categories = new ArrayList<>();
    

    private void fillCategories() {
        categories.add(Category.getCategoryById(1));
        cardBox = new CardBox(categories);
        System.out.println(cardBox.getCards());

    }
    
    public Scene createView3() {
        fillCategories();
        Group view3Root = new Group();
        Scene view3Scene = new Scene(view3Root, Color.DEEPSKYBLUE);
        
        //Erstelle Boxen für Layout        
        myCard = Card.getCardsByCategory(Category.getCategoryById(1));
        VBox view3ContentBox = new VBox();
        HBox statusBar = createHboxForTop();
        HBox buttonBar = createHBoxForDown();
        ScrollPane answerAndQuestionScrollPane = new ScrollPane();
        VBox scrollPaneContent = new VBox();
        VBox questionBox = new VBox();
        questionBox.setMinWidth(600);
        questionBox.setStyle("-fx-border-style: solid;" + "-fx-border-width: 1;");
        Label questionLabel = new Label(myCard.getQuestion().getText());
        VBox answersBox = new VBox();
        HBox checkboxWithAnswerBox = new HBox();
        for (int i = 0; i < myCard.getAnswers().size(); i++) {
            CheckBox cb = new CheckBox();
            Label answerLabel = new Label(myCard.getAnswers().get(i).getText());
            checkboxWithAnswerBox = new HBox(cb, answerLabel);
            answersBox.getChildren().add(checkboxWithAnswerBox);
            answersBox.setSpacing(20);
        }

        //fülle Boxen mit ihren Elementen
        questionBox.getChildren().add(questionLabel);
        scrollPaneContent.getChildren().addAll(questionBox, answersBox);
        answerAndQuestionScrollPane.setContent(scrollPaneContent);
        view3ContentBox.getChildren().addAll(statusBar, answerAndQuestionScrollPane, buttonBar);

        //übergebe den gesamten Inhalt an Group
        view3Root.getChildren().add(view3ContentBox);

        return view3Scene;
    }

    private HBox createHboxForTop() {
        HBox statusBar = new HBox();
        statusBar.setSpacing(10);
        statusBar.setMinWidth(600);
        statusBar.setMinHeight(40);
        statusBar.setAlignment(Pos.CENTER);
        statusBar.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: grey;");

        Label lblQuestionNumber = new Label("Question");
        lblQuestionNumber.setFont(Font.font("Arial", 18));

        Label seperateSign = new Label(" / ");
        seperateSign.setFont(Font.font("Arial", 18));

        Label totalNumberOfQuestions = new Label("TotalNumber");
        totalNumberOfQuestions.setFont(Font.font("Arial", 18));

        Button nextQuestionBtn = new Button("Vor");
        nextQuestionBtn.setMinWidth(60);
        Button prevQuestionBtn = new Button("Zurück");
        prevQuestionBtn.setMinWidth(60);

        statusBar.getChildren().addAll(prevQuestionBtn, lblQuestionNumber, seperateSign, totalNumberOfQuestions, nextQuestionBtn);
        return statusBar;
    }

    private HBox createHBoxForDown() {

        HBox buttonBar = new HBox();

        buttonBar.setSpacing(150);
        buttonBar.setMinWidth(600);
        buttonBar.setMinHeight(40);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: grey;");

        Button followUp = new Button("Wiedervorlage");
        followUp.setMinWidth(100);

        Button cheater = new Button("Cheater-Knopf");
        cheater.setMinWidth(100);

        Button save = new Button("Session fertig");
        save.setMinWidth(100);

        buttonBar.getChildren().addAll(followUp, cheater, save);

        return buttonBar;
    }
}
