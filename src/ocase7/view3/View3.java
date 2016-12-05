package ocase7.view3;

import com.sun.javafx.scene.control.skin.DatePickerContent;
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
import ocase7.Category;

/**
 *
 * @author PaulsBook
 */
public class View3 {

    ocase7.Card myCard;

    public Scene createView3() {
        Group view3Root = new Group();
        Scene view3Scene = new Scene(view3Root, Color.DEEPSKYBLUE);
        
        //Erstelle Boxen für Layout
        myCard = Card.getCardsByCategory(Category.getCategoryById(1));
        VBox view3ContentBox = new VBox();
        HBox statusBar = createHboxForTop();
        ScrollPane answerAndQuestionScrollPane = new ScrollPane();
        VBox scrollPaneContent = new VBox();
        VBox questionBox = new VBox();
        Label questionLabel = new Label(myCard.getQuestion().getText());
        VBox answersBox = new VBox();
        HBox checkboxWithAnswerBox = new HBox();
        for (int i = 0; i < myCard.getAnswers().size(); i++) {
            CheckBox cb = new CheckBox();
            Label answerLabel = new Label(myCard.getAnswers().get(i).getText());
            checkboxWithAnswerBox = new HBox(cb,answerLabel);
            answersBox.getChildren().add(checkboxWithAnswerBox);
            answersBox.setSpacing(20);
        }
        
        //fülle Boxen mit ihren Elementen
        questionBox.getChildren().add(questionLabel);
        scrollPaneContent.getChildren().addAll(questionBox, answersBox);
        answerAndQuestionScrollPane.setContent(scrollPaneContent);
        view3ContentBox.getChildren().addAll(statusBar, answerAndQuestionScrollPane);
        
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

        Label totalNumberOfQuestions = new Label("Totalnumber");
        totalNumberOfQuestions.setFont(Font.font("Arial", 18));

        Button nextQuestionBtn = new Button("Vor");
        nextQuestionBtn.setMinWidth(60);
        Button prevQuestionBtn = new Button("Zurück");
        prevQuestionBtn.setMinWidth(60);

        statusBar.getChildren().addAll(prevQuestionBtn, lblQuestionNumber, seperateSign, totalNumberOfQuestions, nextQuestionBtn);
        return statusBar;
    }
}
