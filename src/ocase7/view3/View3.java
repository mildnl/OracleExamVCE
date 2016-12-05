/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view3;

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

        HBox statusBar = createHboxForTop();
        VBox viewContentBox = new VBox();
        ScrollPane questionAndAnswerPane = new ScrollPane();
        myCard = Card.getCardsByCategory(Category.getCategoryById(4));
        Label questionLbl = new Label(myCard.getQuestion().getText());
        //System.out.println(myCard.getAnswers().get(0).getText());
        VBox answerBox = new VBox();
        for (int i = 0; i < myCard.getAnswers().size(); i++) {
            CheckBox cb = new CheckBox();
            Label answersLbl = new Label(myCard.getAnswers().get(i).getText());
            HBox answerWithCheckbox = new HBox(cb,answersLbl);
            answerBox.getChildren().add(answerWithCheckbox);
            answerBox.setSpacing(20);
        }
        

        //ScrollBar answerScrollBar = new ScrollBar();
        //answerScrollBar.setOrientation(Orientation.VERTICAL);
        questionAndAnswerPane.setMinWidth(600);
        questionAndAnswerPane.setMinHeight(400);
        viewContentBox.getChildren().addAll(statusBar, questionAndAnswerPane);
        view3Root.getChildren().addAll(viewContentBox);
        questionAndAnswerPane.setContent(questionLbl);
        questionAndAnswerPane.setContent(answerBox);

        return view3Scene;
    }

    private HBox createHboxForTop() {
        HBox statusBar = new HBox();
        statusBar.setSpacing(10);
        statusBar.setMinWidth(600);
        statusBar.setAlignment(Pos.BASELINE_CENTER);
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
