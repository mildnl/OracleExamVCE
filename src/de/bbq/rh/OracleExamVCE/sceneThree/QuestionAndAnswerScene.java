package de.bbq.rh.OracleExamVCE.sceneThree;

import de.bbq.rh.OracleExamVCE.Main;
import de.bbq.rh.OracleExamVCE.cardbox.Answer;
import de.bbq.rh.OracleExamVCE.cardbox.Card;
import de.bbq.rh.OracleExamVCE.cardbox.Cardbox;
import de.bbq.rh.OracleExamVCE.database.MySQLConnection;
import de.bbq.rh.OracleExamVCE.session.User;
import java.sql.SQLException;
import java.util.Map;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author $ Lyn Mildner
 */
public class QuestionAndAnswerScene {

    private User currentUser;
    private Card currentCard;
    private int currentCardIndex;
    private ScrollPane questionAndAnswerPane;
    private VBox scrollPaneContent;
    private VBox questionContentBox;
    private VBox answerContentBox;
    private TextArea questionTextArea;
    private HBox topBarBox;
    private HBox buttomMenueBox;

    public Card getCurrentCard() {
        return this.currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public int getCurrentCardIndex() {
        return this.currentCardIndex;
    }

    public void setCurrentCardIndex(int currentCardIndex) {
        this.currentCardIndex = currentCardIndex;
    }

    public void setQuestionTextArea(TextArea questionTextArea) {
        this.questionTextArea = questionTextArea;
    }

    public TextArea getQuestionTextArea() {
        return this.questionTextArea;
    }

    public VBox getQuestionContentBox() {
        return this.questionContentBox;
    }

    public VBox getAnswerContentBox() {
        return this.answerContentBox;
    }

    private void setQuestionContentBox(VBox questionContentBox) {
        this.questionContentBox = questionContentBox;
    }

    private void setAnswerContentBox(VBox answerContentBox) {
        this.answerContentBox = answerContentBox;
    }

    public HBox getTopBarBox() {
        return this.topBarBox;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public HBox getButtomMenueBox() {
        return this.buttomMenueBox;
    }

    public ScrollPane getQuestionAndAnswerPane() {
        return this.questionAndAnswerPane;
    }

    public VBox getScrollPaneContent() {
        return this.scrollPaneContent;
    }

    private void setTopBarBox(HBox topBarBox) {
        this.topBarBox = topBarBox;
    }

    private void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private void setButtomMenueBox(HBox buttomMenueBox) {
        this.buttomMenueBox = buttomMenueBox;
    }

    private void setQuestionAndAnswerPane(ScrollPane questionAndAnswerPane) {
        this.questionAndAnswerPane = questionAndAnswerPane;
    }

    private void setScrollPaneContent(VBox scrollPaneContent) {
        this.scrollPaneContent = scrollPaneContent;
    }

    private Cardbox getUserCardbox() {
        return currentUser.getUserSession().getSessionBox();
    }

    private Card getCurrentCardByIndex(int index) {
        return currentUser.getUserSession().getSessionBox().getCardList().get(index);
    }

    public Answer getCurrentAnswers(int index) {
        return currentUser.getUserSession().getSessionBox().getCardList().get(index).getAnswer();
    }

    public QuestionAndAnswerScene(User loggedUser) {
        this.currentUser = loggedUser;
        this.currentCard = getCurrentCardByIndex(0);
        this.currentCardIndex = 1;
    }

    public Scene createSceneThree(Main m) {

        Group viewThreeRoot = new Group();
        Scene viewThreeScene = new Scene(viewThreeRoot);

        VBox viewContentBox = new VBox();
        viewContentBox.setMinWidth(660);

        setQuestionAndAnswerPane(new ScrollPane());
        getQuestionAndAnswerPane().setMinWidth(660);
        getQuestionAndAnswerPane().setMinHeight(700);
        getQuestionAndAnswerPane().setMaxHeight(700);
        getQuestionAndAnswerPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        setTopBarBox(addTopBarHBox());
        addStackPane(getTopBarBox());
        getQuestionAndAnswerPane().setContent(addQnAVBox());
        setButtomMenueBox(createBottomMenue());
        viewContentBox.getChildren().addAll(getTopBarBox(), getQuestionAndAnswerPane(), getButtomMenueBox());

        viewThreeRoot.getChildren().add(viewContentBox);
        
        if (Platform.isImplicitExit()) {
            System.out.println("Scene 3 - Saved answers into DB");
            getCurrentUser().insertUserAnswersIDIntoDB(getCurrentUser());
        }

        return viewThreeScene;
    }

    public HBox addTopBarHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        Button buttonPrevious = new Button("Previous");
        buttonPrevious.setPrefSize(100, 20);

        Button buttonNext = new Button("Next");
        buttonNext.setPrefSize(100, 20);

        Label currentQuestion = new Label("1");
        currentQuestion.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        Label cardboxSize = new Label(" / " + String.valueOf(getUserCardbox().getCardList().size()));
        cardboxSize.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        buttonPrevious.setOnAction((ActionEvent event) -> {

            if (getCurrentCardIndex() > 1) {
                setCurrentCardIndex(getCurrentCardIndex() - 1);
                setCurrentCard(getUserCardbox().getCardList().get(getCurrentCardIndex() - 1));
                currentQuestion.setText(String.valueOf(getCurrentCardIndex()));
            } else {
                setCurrentCard(getUserCardbox().getCardList().get(getUserCardbox().getCardList().size() - 1));
                setCurrentCardIndex(getUserCardbox().getCardList().size());
                currentQuestion.setText(String.valueOf(getCurrentCardIndex()));
            }

            getQuestionAndAnswerPane().setContent(addQnAVBox());
        });

        buttonNext.setOnAction((ActionEvent event) -> {

            if (getCurrentCardIndex() < getUserCardbox().getCardList().size()) {
                setCurrentCard(getUserCardbox().getCardList().get(getCurrentCardIndex()));
                setCurrentCardIndex(getCurrentCardIndex() + 1);
                currentQuestion.setText(String.valueOf(getCurrentCardIndex()));
            } else {
                setCurrentCard(getUserCardbox().getCardList().get(0));
                setCurrentCardIndex(1);
                currentQuestion.setText(String.valueOf(getCurrentCardIndex()));
            }

            getQuestionAndAnswerPane().setContent(addQnAVBox());

        });

        currentQuestion.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(buttonPrevious, buttonNext, currentQuestion, cardboxSize);

        return hbox;
    }

    public void addStackPane(HBox hb) {
        StackPane stack = new StackPane();
        Rectangle helpIcon = new Rectangle(30.0, 25.0);
        helpIcon.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop[]{
                    new Stop(0, Color.web("#4977A3")),
                    new Stop(0.5, Color.web("#B0C6DA")),
                    new Stop(1, Color.web("#9CB6CF")),}));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);

        Text helpText = new Text("?");
        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0"));

        stack.getChildren().addAll(helpIcon, helpText);
        stack.setAlignment(Pos.CENTER_RIGHT);     // Right-justify nodes in stack
        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0)); // Center "?"

        hb.getChildren().add(stack);            // Add to HBox from Example 1-2
        HBox.setHgrow(stack, Priority.ALWAYS);    // Give stack any extra space
    }

    public VBox addQnAVBox() {

        VBox questionNAnswerVBox = new VBox();
        questionNAnswerVBox.setPadding(new Insets(5, 0, 5, 0));
        questionNAnswerVBox.setSpacing(10);

        setScrollPaneContent(new VBox());
        getScrollPaneContent().setMinHeight(600);
        getScrollPaneContent().setMinWidth(658);

        setQuestionTextArea(new TextArea(getCurrentCard().getQuestion()));
        getQuestionTextArea().setMinHeight(500);

        getQuestionTextArea().focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            getQuestionTextArea().setEditable(false);
            getQuestionTextArea().setWrapText(true);
        });

        setAnswerContentBox(createAnswerBox());

        getScrollPaneContent().getChildren().addAll(getQuestionTextArea(), getAnswerContentBox());
        getScrollPaneContent().setFillWidth(true);
        questionNAnswerVBox.getChildren().add(getScrollPaneContent());

        return questionNAnswerVBox;
    }

    private HBox createBottomMenue() {
        HBox btnMenue = new HBox();

        btnMenue.setSpacing(100);
        btnMenue.setMinWidth(600);
        btnMenue.setMinHeight(40);
        btnMenue.setAlignment(Pos.CENTER);

        Button reviseBtn = new Button("To be Revised");
        reviseBtn.setMinWidth(100);
        reviseBtn.setOnAction((ActionEvent event) -> {
            getCurrentCard().setToBeRevised(true);
        });

        Button solutionBtn = new Button("Solution");
        solutionBtn.setMinWidth(100);
        solutionBtn.setOnAction((ActionEvent event) -> {
            getCurrentCard().setSolutionGiven(true);
            getScrollPaneContent().getChildren().remove(getAnswerContentBox());
            VBox anserbow = addCorrectAnswersBox();
            anserbow.setDisable(true);
            getScrollPaneContent().getChildren().add(anserbow);
        });

        Button finishBtn = new Button("Finish Session");
        finishBtn.setMinWidth(100);

        finishBtn.setOnAction((ActionEvent event) -> {

            getCurrentUser().insertUserAnswersIDIntoDB(getCurrentUser());

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
            }
            System.exit(0);
        });

        btnMenue.getChildren().addAll(reviseBtn, solutionBtn, finishBtn);

        return btnMenue;
    }

    private VBox createAnswerBox() {

        VBox answerBox = new VBox();
        HBox checkAnswerBox = new HBox();
        CheckBox cb = new CheckBox();
        Label answerLabel = new Label();

        if (!getCurrentCard().isSolutionGiven()) {
            for (Map.Entry<Integer, String> key : getCurrentCard().getAnswer().getTextMap().entrySet()) {
                cb = new CheckBox();
                answerLabel = new Label(getCurrentCard().getAnswer().getTextMap().get(key.getKey()));
                if (getCurrentCard().isSolutionGiven()) {
                    cb.setSelected(true);
                }
                cb.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    if (newValue == true) {
                        getCurrentCard().getAnswer().getIsSelectedMap().replace(key.getKey(), true);
                    } else if (oldValue == true && newValue == false) {
                        getCurrentCard().getAnswer().getIsSelectedMap().replace(key.getKey(), false);
                    }
                });
                checkAnswerBox = new HBox(cb, answerLabel);
                checkAnswerBox.setSpacing(10);
                checkAnswerBox.setAlignment(Pos.CENTER_LEFT);
                answerBox.getChildren().add(checkAnswerBox);
                answerBox.setSpacing(20);
            }
        } else {
            answerBox.getChildren().add(addCorrectAnswersBox());
            answerBox.setDisable(true);
        }

        return answerBox;
    }

    private VBox addCorrectAnswersBox() {
        VBox answerBox = new VBox();
        HBox checkboxWithAnswerBox = new HBox();
        CheckBox cb = new CheckBox();
        Label answerLabel = new Label();

        for (Map.Entry<Integer, Boolean> key : getCurrentCard().getAnswer().getIsRightMap().entrySet()) {
            if (key.getValue()) {
                cb = new CheckBox();
                cb.setSelected(true);
                answerLabel = new Label(getCurrentCard().getAnswer().getTextMap().get(key.getKey()));
                answerLabel.setTextFill(Color.GREEN);
                checkboxWithAnswerBox = new HBox(cb, answerLabel);
                checkboxWithAnswerBox.setAlignment(Pos.CENTER_LEFT);
                checkboxWithAnswerBox.setSpacing(10);
                answerBox.getChildren().add(checkboxWithAnswerBox);
                answerBox.setSpacing(20);
            }

        }
        return answerBox;
    }
}
