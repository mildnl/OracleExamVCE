/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bbq.rh.oracleExams.sceneTwo;

import de.bbq.rh.oracleExams.Main;
import de.bbq.rh.oracleExams.card.Cardbox;
import de.bbq.rh.oracleExams.card.Category;
import de.bbq.rh.oracleExams.sceneThree.QuestionAndAnswerScene;
import de.bbq.rh.oracleExams.session.Session;
import de.bbq.rh.oracleExams.session.User;
import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author $ Lyn Mildner
 */
public class CardboxSelectionScene {

    private User currentUser;
    private int cardBoxSize;
    private ArrayList<Integer> selectedCategoryList;
    private Label maxQuestionLabel;
    private Scene CardboxSelectionScene;
    private HBox categoryCheckBox;
    private VBox sceneTwoContentBox;
    private VBox topMenueBox;
    private VBox categoryBox;
    private HBox learnModeBox;
    private Slider questionSlider;
    private ArrayList<CheckBox> checkBoxList;

    public User getCurrentUser() {
        return this.currentUser;
    }

    public int getCardBoxSize() {
        return this.cardBoxSize;
    }

    public ArrayList<Integer> getSelectedCategoryList() {
        return this.selectedCategoryList;
    }

    public Label getMaxQuestionLabel() {
        return this.maxQuestionLabel;
    }

    public Scene getCardboxSelectionScene() {
        return this.CardboxSelectionScene;
    }

    public HBox getCategoryCheckBox() {
        return this.categoryCheckBox;
    }

    public VBox getSceneTwoContentBox() {
        return this.sceneTwoContentBox;
    }

    public VBox getTopMenueBox() {
        return this.topMenueBox;
    }

    public VBox getCategoryBox() {
        return this.categoryBox;
    }

    public HBox getLearnModeBox() {
        return this.learnModeBox;
    }

    public Slider getQuestionSlider() {
        return this.questionSlider;
    }

    public ArrayList<CheckBox> getCheckBoxList() {
        return this.checkBoxList;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setCardBoxSize(int cardBoxSize) {
        this.cardBoxSize = cardBoxSize;
    }

    public void setSelectedCategoryList(ArrayList<Integer> selectedCategoryList) {
        this.selectedCategoryList = selectedCategoryList;
    }

    public void setMaxQuestionLabel(Label maxQuestionLabel) {
        this.maxQuestionLabel = maxQuestionLabel;
    }

    public void setCardboxSelectionScene(Scene CardboxSelectionScene) {
        this.CardboxSelectionScene = CardboxSelectionScene;
    }

    public void setCategoryCheckBox(HBox categoryCheckBox) {
        this.categoryCheckBox = categoryCheckBox;
    }

    public void setSceneTwoContentBox(VBox sceneTwoContentBox) {
        this.sceneTwoContentBox = sceneTwoContentBox;
    }

    public void setTopMenueBox(VBox topMenueBox) {
        this.topMenueBox = topMenueBox;
    }

    public void setCategoryBox(VBox categoryBox) {
        this.categoryBox = categoryBox;
    }

    public void setLearnModeBox(HBox learnModeBox) {
        this.learnModeBox = learnModeBox;
    }

    public void setQuestionSlider(Slider questionSlider) {
        this.questionSlider = questionSlider;
    }

    public void setCheckBoxList(ArrayList<CheckBox> checkBoxList) {
        this.checkBoxList = checkBoxList;
    }

    public Session getUserSession() {
        return this.currentUser.getUserSession();
    }

    public Cardbox getUserCardbox() {
        return this.currentUser.getUserSession().getSessionBox();
    }

    public Cardbox getUserCardboxByCategoryId(int categoryId) {
        return this.currentUser.getUserSession().getSessionBox().getCardboxByCategoryID(getUserCardbox(), categoryId);
    }

    public ArrayList<Category> getAllCategoryList() {
        return this.currentUser.getUserSession().getSessionBox().getCardList().get(0).getCat().getAllList();
    }

    private CardboxSelectionScene(User currentUser, int cardBoxSize,
            ArrayList<Integer> selectedCategoryList, Label maxQuestionLabel,
            Scene CardboxSelectionScene, HBox categoryCheckBox,
            VBox sceneTwoContentBox, VBox topMenueBox, VBox categoryBox,
            HBox learnModeBox, Slider questionSlider,
            ArrayList<CheckBox> checkBoxList) {

        this.currentUser = currentUser;
        this.cardBoxSize = cardBoxSize;
        this.selectedCategoryList = selectedCategoryList;
        this.maxQuestionLabel = maxQuestionLabel;
        this.CardboxSelectionScene = CardboxSelectionScene;
        this.categoryCheckBox = categoryCheckBox;
        this.sceneTwoContentBox = sceneTwoContentBox;
        this.topMenueBox = topMenueBox;
        this.categoryBox = categoryBox;
        this.learnModeBox = learnModeBox;
        this.questionSlider = questionSlider;
        this.checkBoxList = checkBoxList;
    }

    public CardboxSelectionScene(User currentUser) {
        this(currentUser, 0, new ArrayList<Integer>(), new Label(), null, null, new VBox(), null, null,
                null, null, new ArrayList<>());
    }

    public Scene createSceneTwo(Main m) {
        Group viewTwoRoot = new Group();

        setTopMenueBox(addTopMenueBox());

        setCategoryBox(addCategoryBox());

        setLearnModeBox(learnMode());

        StackPane sliderStackPane = createSlider();

        HBox resetAndStartButtonBox = createButtonBox(m);
        getSceneTwoContentBox().setMaxWidth(700);
        getSceneTwoContentBox().getChildren().addAll(getTopMenueBox(),
                getCategoryBox(), sliderStackPane, getLearnModeBox(),
                resetAndStartButtonBox);

        viewTwoRoot.getChildren().addAll(getSceneTwoContentBox());
        setCardboxSelectionScene(new Scene(viewTwoRoot));
        return getCardboxSelectionScene();
    }

    private VBox addTopMenueBox() {
        VBox topMenueVBox = new VBox();
        Label topMenueLabel = new Label("Please choose an Option");
        topMenueLabel.setMinHeight(60);
        topMenueLabel.setPadding(new Insets(5, 0, 5, 0));
        topMenueLabel.setMinWidth(710);

        topMenueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        topMenueLabel.setAlignment(Pos.CENTER);
        topMenueVBox.getChildren().add(topMenueLabel);

        return topMenueVBox;
    }

    private VBox addCategoryBox() {
        VBox categoryVBox = new VBox();

        Label categoryBoxLabel = new Label("Categories:");
        categoryBoxLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        categoryBoxLabel.setPadding(new Insets(30, 490, 10, 0));
        categoryVBox.setPadding(new Insets(8, 0, 40, 130));
        categoryVBox.getChildren().addAll(categoryBoxLabel);

        CheckBox cb;
        Label categoryLabel;

        ArrayList<Category> allCategoriesList = new ArrayList<>(getAllCategoryList());

        for (Category cat : allCategoriesList) {
            cb = new CheckBox();
            getCheckBoxList().add(cb);

            categoryLabel = new Label(cat.getName());
            setCategoryCheckBox(new HBox(cb, categoryLabel));
            categoryVBox.getChildren().add(getCategoryCheckBox());
            categoryVBox.setSpacing(10);

        }
        getCheckBoxList().forEach((checkBox) -> {
            checkBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue,
                    Boolean newValue) -> {

                if (newValue) {
                    checkBox.setSelected(true);
                    setCardBoxSize(getCardBoxSize()
                            + getUserCardboxByCategoryId(getCheckBoxList().
                                    indexOf(checkBox)).getCardList().size() + 1);
                    getMaxQuestionLabel().setText(String.valueOf(getCardBoxSize()));
                    getQuestionSlider().setMax(getCardBoxSize());
                    getSelectedCategoryList().add(getCheckBoxList().
                            indexOf(checkBox) + 1);
                    System.out.println("CategoryIds: " + getSelectedCategoryList());
                } else {
                    setCardBoxSize(getCardBoxSize()
                            - getUserCardboxByCategoryId(getCheckBoxList().
                                    indexOf(checkBox)).getCardList().size() + 1);
                    getMaxQuestionLabel().setText(String.valueOf(getCardBoxSize()));
                    getQuestionSlider().setMax(getCardBoxSize());
                    if (getSelectedCategoryList().size() == 1) {
                        getSelectedCategoryList().clear();
                    } else {
                        getSelectedCategoryList().remove(getCheckBoxList().indexOf(checkBox) + 1);
                    }

                    System.out.println("CategoryIds: " + getSelectedCategoryList());
                }
            });
        });

        getCheckBoxList().get(1).setOnAction((ActionEvent event) -> {
            getMaxQuestionLabel().setText(String.valueOf(getCardBoxSize()));
        });

        categoryVBox.setAlignment(Pos.CENTER);

        return categoryVBox;
    }

    private HBox learnMode() {

        HBox learnModusBox = new HBox();

        learnModusBox.setSpacing(10);
        learnModusBox.setMinWidth(700);
        learnModusBox.setAlignment(Pos.CENTER);
        learnModusBox.setPadding(new Insets(30, 0, 30, 0));
        ToggleButton learnModusButton = new ToggleButton("Learn Mode");

        Label learnModusLabel = new Label("Sort-By Mode");
        learnModusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        learnModusLabel.setPadding(new Insets(2, 0, 0, 0));
        learnModusButton.setOnAction((ActionEvent event) -> {
            if (learnModusLabel.getText().equals("Sort-By Mode")) {
                learnModusLabel.setText("Random Mode");
            } else {
                learnModusLabel.setText("Sort-By Mode");
            }
        });
        learnModusBox.getChildren().addAll(learnModusButton, learnModusLabel);

        return learnModusBox;
    }

    public StackPane createSlider() {

        setQuestionSlider(new Slider());
        getQuestionSlider().setShowTickLabels(true);
        getQuestionSlider().setShowTickMarks(true);
        getQuestionSlider().setMajorTickUnit(10);
        getQuestionSlider().setMinorTickCount(5);
        StackPane sliderRoot = new StackPane(getQuestionSlider());
        sliderRoot.setAlignment(Pos.CENTER);
        sliderRoot.setPadding(new Insets(30, 30, 30, 30));
        sliderRoot.setMaxWidth(450);

//        getQuestionSlider().layout();
//        Pane sliderPane = (Pane) getQuestionSlider().lookup(".thumb");
//        Label sliderLabel = new Label();
//        sliderLabel.textProperty().bind(getQuestionSlider().valueProperty().asString("%.0f"));
//
//        sliderPane.getChildren().addAll(sliderLabel);
        return sliderRoot;

    }

    private HBox createButtonBox(Main m) {
        HBox ButtonBox = new HBox();
        ButtonBox.setPadding(new Insets(100, 0, 80, 230));
        VBox resetButtonBox = new VBox();
        resetButtonBox.setPadding(new Insets(0, 30, 0, 0));
        VBox startButtonBox = new VBox();

        Button resetBtn = new Button("Reset");
        resetBtn.setOnAction((ActionEvent event) -> {
            getSelectedCategoryList().clear();
            getCheckBoxList().forEach((checkbox) -> {
                checkbox.setSelected(false);
            });
        });
        Button startBtn = new Button("Start Session");
        startBtn.setOnAction((ActionEvent event) -> {
            getCurrentUser().getUserSession().getSessionBox().getCardboxByMultipleCategoryIDs(getCurrentUser().
                    getUserSession().getSessionBox(),
                    getSelectedCategoryList());

            QuestionAndAnswerScene viewTwo = new QuestionAndAnswerScene(getCurrentUser());
            Scene sceneThree = viewTwo.createSceneThree(m);
            m.setCurrentScene(sceneThree);
            m.getStage().setScene(m.getCurrentScene());
        });

        resetButtonBox.getChildren().add(resetBtn);
        startButtonBox.getChildren().add(startBtn);
        ButtonBox.getChildren().addAll(resetButtonBox, startButtonBox);

        return ButtonBox;
    }
}
