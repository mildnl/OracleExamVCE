package de.bbq.rh.ocase7.view3;

import de.bbq.rh.ocase7.Test;
import de.bbq.rh.ocase7.card.Answer;
import de.bbq.rh.ocase7.card.Card;
import de.bbq.rh.ocase7.card.Cardbox;
import de.bbq.rh.ocase7.database.MySQLConnection;
import de.bbq.rh.ocase7.session.User;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
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
public class QuestionAndAnswerView {

    private User loggedUser;
    private Card currentCard;
    private int currentCardIndex;
    private ScrollPane questionAndAnswerPane;
    private VBox scrollPaneContent;
    private VBox questionContent;
    private VBox answerContent;
    private TextArea questionTextArea;
    private HBox topBar;
    private HBox buttomMenue;

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

    public VBox getQuestionContent() {
        return this.questionContent;
    }

    public VBox getAnswerContent() {
        return this.answerContent;
    }

    private void setQuestionContent(VBox questionContent) {
        this.questionContent = questionContent;
    }

    private void setAnswerContent(VBox answerContent) {
        this.answerContent = answerContent;
    }

    public HBox getTopBar() {
        return this.topBar;
    }

    public User getLoggedUser() {
        return this.loggedUser;
    }

    public HBox getButtomMenue() {
        return this.buttomMenue;
    }

    public ScrollPane getQuestionAndAnswerPane() {
        return this.questionAndAnswerPane;
    }

    public VBox getScrollPaneContent() {
        return this.scrollPaneContent;
    }

    private void setTopBar(HBox topBar) {
        this.topBar = topBar;
    }

    private void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    private void setButtomMenue(HBox buttomMenue) {
        this.buttomMenue = buttomMenue;
    }

    private void setQuestionAndAnswerPane(ScrollPane questionAndAnswerPane) {
        this.questionAndAnswerPane = questionAndAnswerPane;
    }

    private void setScrollPaneContent(VBox scrollPaneContent) {
        this.scrollPaneContent = scrollPaneContent;
    }

    private Cardbox getUserCardbox() {
        return loggedUser.getUserSession().getSessionBox();
    }

    private Card getCurrentCardByIndex(int index) {
        return loggedUser.getUserSession().getSessionBox().getCardList().get(index);
    }

    public Answer getCurrentAnswers(int index) {
        return loggedUser.getUserSession().getSessionBox().getCardList().get(index).getAnswer();
    }

    public QuestionAndAnswerView(User loggedUser) {
        this.loggedUser = loggedUser;
        this.currentCard = getCurrentCardByIndex(0);
        this.currentCardIndex = 0;
    }

    public Scene createViewThree() {

        Group viewThreeRoot = new Group();
        Scene viewThreeScene = new Scene(viewThreeRoot);

        VBox viewContentBox = new VBox();
        viewContentBox.setMinWidth(660);

        setQuestionAndAnswerPane(new ScrollPane());
        getQuestionAndAnswerPane().setMinWidth(660);
        getQuestionAndAnswerPane().setMinHeight(700);
        getQuestionAndAnswerPane().setMaxHeight(700);
        getQuestionAndAnswerPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        setTopBar(addTopBarHBox());
        addStackPane(getTopBar());
        getQuestionAndAnswerPane().setContent(addQnAVBox());
        setButtomMenue(createBottomMenue());
        viewContentBox.getChildren().addAll(getTopBar(), getQuestionAndAnswerPane(), getButtomMenue());

        viewThreeRoot.getChildren().add(viewContentBox);

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

        buttonPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getQuestionContent().getChildren().clear();
                getAnswerContent().getChildren().clear();

                if ((getCurrentCardIndex() - 1) > 0) {
                    setCurrentCard(getUserCardbox().getCardList().get(getCurrentCardIndex() - 1));
                } else {
                    setCurrentCard(getUserCardbox().getCardList().get(getUserCardbox().getCardList().size() - 1));
                }

                setCurrentCard(getCurrentCardByIndex(getCurrentCardIndex()));
                getQuestionTextArea().setText(getCurrentCard().getQuestion());

                getQuestionContent().getChildren().add(getQuestionTextArea());
                getScrollPaneContent().getChildren().add(createAnswerBox());
            }
        });

        buttonNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getQuestionContent().getChildren().clear();
                getAnswerContent().getChildren().clear();

                if (getCurrentCardIndex() < (getUserCardbox().getCardList().size() - 1)) {
                    setCurrentCard(getUserCardbox().getCardList().get(getCurrentCardIndex() + 1));
                } else {
                    setCurrentCard(getUserCardbox().getCardList().get(0));
                }

                getQuestionTextArea().setText(getCurrentCardByIndex(0).getQuestion());

                getQuestionContent().getChildren().add(getQuestionTextArea());
                getScrollPaneContent().getChildren().add(createAnswerBox());
            }
        });

        String cardboxSize = String.valueOf(getUserCardbox().getCardList().size());
        String currentIndex = String.valueOf(getCurrentCardIndex());

        Label currentQuestion = new Label(currentIndex + " / " + cardboxSize);

        hbox.getChildren().addAll(buttonPrevious, buttonNext, currentQuestion);

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

        getQuestionTextArea().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                getQuestionTextArea().setEditable(false);
                getQuestionTextArea().setWrapText(true);
            }
        });

        setAnswerContent(createAnswerBox());

        getScrollPaneContent().getChildren().addAll(getQuestionTextArea(), getAnswerContent());
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

        Button solutionBtn = new Button("Solution");
        solutionBtn.setMinWidth(100);
        solutionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getCurrentCardByIndex(0).setSolutionGiven(true);
                getScrollPaneContent().getChildren().remove(getAnswerContent());
                VBox anserbow = addCorrectAnswersBox();
                anserbow.setDisable(true);
                getScrollPaneContent().getChildren().add(anserbow);

            }
        });

        Button finishBtn = new Button("Finish Session");
        finishBtn.setMinWidth(100);

        finishBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (getUserCardbox().getCardList().size() > 1) {
                    getLoggedUser().insertUserAnswersIDIntoDB(getLoggedUser());
                }
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
                System.exit(0);

            }
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
                cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue == true) {
                            getCurrentCard().getAnswer().getIsSelectedMap().replace(key.getKey(), true);
                        } else if (oldValue == true && newValue == false) {
                            getCurrentCard().getAnswer().getIsSelectedMap().replace(key.getKey(), false);
                        }

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
