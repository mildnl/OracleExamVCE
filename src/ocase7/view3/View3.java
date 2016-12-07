package ocase7.view3;

import com.sun.javafx.scene.control.skin.DatePickerContent;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import ocase7.Question;

/**
 *
 * @author PaulsBook
 */
public class View3 {

    ocase7.Card myCard;
    CardBox cardBox;
    ArrayList<Category> categories = new ArrayList<>();
    VBox questionBox;
    VBox scrollPaneContent;
    VBox answersBox;
    Label questionLabel;
    Label answerLabel;
    HBox checkboxWithAnswerBox;
    Label lblQuestionNumber;
    
    private void fillCategories() {
        categories.add(Category.getCategoryById(1));
        cardBox = new CardBox(categories);
        System.out.println(cardBox.getCards() + "########" + cardBox.getNumberOfCards());

    }

    public Scene createView3() {
        fillCategories();
        Group view3Root = new Group();
        Scene view3Scene = new Scene(view3Root, Color.DEEPSKYBLUE);


        myCard = cardBox.getCards().get(0);
        
        //Erstelle Boxen für Layout        
        VBox view3ContentBox = new VBox();
        //view3ContentBox.
        HBox statusBar = createHboxForTop();
        HBox buttonBar = createHBoxForDown();
        ScrollPane answerAndQuestionScrollPane = new ScrollPane();
        answerAndQuestionScrollPane.setMinHeight(700);
        scrollPaneContent = new VBox();
        scrollPaneContent.setMinHeight(600);

        // setze Mindestbreite und den Border für die Fragenbox
        setMinWidthAndStyleOnQuestionBox();
        questionLabel = new Label(myCard.getQuestion().getText());

        // erstelle Checkboxen mit der Anzahl an Antwortmöglichkeiten
        answersBox = new VBox();
        checkboxWithAnswerBox = new HBox();
        checkboxWithAnswerBox.setAlignment(Pos.CENTER);
        for (int i = 0; i < myCard.getAnswers().size(); i++) {
            CheckBox cb = new CheckBox();
            answerLabel = new Label(myCard.getAnswers().get(i).getText());
            checkboxWithAnswerBox = new HBox(cb, answerLabel);
            answersBox.getChildren().add(checkboxWithAnswerBox);
            answersBox.setSpacing(20);
        }
        //createCheckboxWithAnswerBox();

        //fülle Boxen mit ihren Elementen
        questionBox.getChildren().add(questionLabel);
        scrollPaneContent.getChildren().addAll(questionBox, answersBox);
        answerAndQuestionScrollPane.setContent(scrollPaneContent);
        view3ContentBox.getChildren().addAll(statusBar, answerAndQuestionScrollPane, buttonBar);

        //übergebe den gesamten Inhalt an Group
        view3Root.getChildren().add(view3ContentBox);

        return view3Scene;
    }

    private void setMinWidthAndStyleOnQuestionBox() {
        questionBox = new VBox();
        questionBox.setMinWidth(600);
        questionBox.setStyle("-fx-border-style: solid;" + "-fx-border-width: 1;");
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
        String questionNumber = "" + (cardBox.getCards().indexOf(myCard)+1);
        lblQuestionNumber = new Label(questionNumber);
        lblQuestionNumber.setFont(Font.font("Arial", 18));

        Label seperateSign = new Label(" / ");
        seperateSign.setFont(Font.font("Arial", 18));

        String totalNumOfQuestions = "" + cardBox.getCards().size();
        Label totalNumberOfQuestions = new Label(totalNumOfQuestions);
        totalNumberOfQuestions.setFont(Font.font("Arial", 18));

        Button nextQuestionBtn = new Button("Vor");
        nextQuestionBtn.setMinWidth(60);
        nextQuestionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                //lösche Elemente aus Boxen -> Clear oder remove sind die Zauberwörter !!!
                questionBox.getChildren().remove(questionLabel);
                answersBox.getChildren().clear();
                myCard = cardBox.nextCard(cardBox.getCards().indexOf(myCard));
                lblQuestionNumber.setText("" + cardBox.getCards().indexOf(myCard));
                
                // setze den neuen Text in das Label 
                questionLabel.setText(myCard.getQuestion().getText());
                // füge Neues Label wieder zur questionBox hinzu
                questionBox.getChildren().add(questionLabel);
                
                // erstelle neue Antwortbox
                answersBox = new VBox();
                checkboxWithAnswerBox = new HBox();
                checkboxWithAnswerBox.setAlignment(Pos.CENTER);
               
                for (int i = 0; i < myCard.getAnswers().size(); i++) {
                    CheckBox cb = new CheckBox();
                    answerLabel = new Label(myCard.getAnswers().get(i).getText());
                    checkboxWithAnswerBox = new HBox(cb, answerLabel);
                    answersBox.getChildren().add(checkboxWithAnswerBox);
                    answersBox.setSpacing(20);
                }
                scrollPaneContent.getChildren().add(answersBox);
            }
        });
        
        Button prevQuestionBtn = new Button("Zurück");
        prevQuestionBtn.setMinWidth(60);

        statusBar.getChildren().addAll(prevQuestionBtn, lblQuestionNumber, seperateSign, totalNumberOfQuestions, nextQuestionBtn);
        return statusBar;
    }

    private HBox createHBoxForDown() {

        HBox buttonBar = new HBox();

        buttonBar.setSpacing(100);
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
