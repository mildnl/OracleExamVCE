/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bbq.rh.OracleExamVCE.sceneTwo;

import de.bbq.rh.OracleExamVCE.Main;
import de.bbq.rh.OracleExamVCE.cardbox.Cardbox;
import de.bbq.rh.OracleExamVCE.cardbox.Category;
import de.bbq.rh.OracleExamVCE.sceneThree.QuestionAndAnswerScene;
import de.bbq.rh.OracleExamVCE.session.Session;
import de.bbq.rh.OracleExamVCE.session.User;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private Label sliderLabel;
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

    public Label getSliderLabel() {
        return this.sliderLabel;
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

    public void setSliderLabel(Label sliderLabel) {
        this.sliderLabel = sliderLabel;
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
            HBox learnModeBox, Slider questionSlider, Label sliderLabel,
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
        this.sliderLabel = sliderLabel;
        this.checkBoxList = checkBoxList;
    }

    public CardboxSelectionScene(User currentUser) {
        this(currentUser, 0, new ArrayList<Integer>(), new Label(), null, null, new VBox(), null, null,
                null, null, null, new ArrayList<>());
    }

    public Scene createSceneTwo(Main m) {
        Group viewTwoRoot = new Group();

        setTopMenueBox(addTopMenueBox());

        setCategoryBox(addCategoryBox());

        setLearnModeBox(learnMode());

        StackPane sliderStackPane = createSlider();

        VBox lowerHalfBox = new VBox();

//        HBox chartBox = addChartBox();
        ScrollPane chartScrollPane = new ScrollPane();
        chartScrollPane.setMinWidth(660);
        chartScrollPane.setMinHeight(700);
        chartScrollPane.setMaxHeight(700);
        chartScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        HBox resetAndStartButtonBox = createButtonBox(m);
        getSceneTwoContentBox().getChildren().addAll(getTopMenueBox(),
                getCategoryBox(), sliderStackPane, getLearnModeBox(),
                resetAndStartButtonBox);

        if (Platform.isImplicitExit()) {
            System.out.println("Scene 3 - Saved answers into DB");
            getCurrentUser().insertUserAnswersIDIntoDB(getCurrentUser());
        }

//        getSceneTwoContentBox().setMinWidth(700);
//        getSceneTwoContentBox().setMinHeight(700);
//        getSceneTwoContentBox().setMaxHeight(700);
        chartScrollPane.setContent(getSceneTwoContentBox());

        viewTwoRoot.getChildren().addAll(chartScrollPane);
        setCardboxSelectionScene(new Scene(viewTwoRoot));
        return getCardboxSelectionScene();
    }

    private VBox addTopMenueBox() {
        VBox topMenueVBox = new VBox();
        Label topMenueLabel = new Label("Please choose an Option");
//        topMenueLabel.setMinHeight(60);
        topMenueLabel.setPadding(new Insets(5, 0, 5, 0));
//        topMenueLabel.setMinWidth(700);

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
                                    indexOf(checkBox) + 1).getCardList().size());
                    getMaxQuestionLabel().setText(String.valueOf(getCardBoxSize()));
                    getQuestionSlider().setVisible(true);
                    getQuestionSlider().setValue(5);
                    getQuestionSlider().setMax(getCardBoxSize());
                    getSelectedCategoryList().add(getCheckBoxList().
                            indexOf(checkBox) + 1);
                    
                } else {
                    setCardBoxSize(getCardBoxSize()
                            - getUserCardboxByCategoryId(getCheckBoxList().
                                    indexOf(checkBox) + 1).getCardList().size());
                    getMaxQuestionLabel().setText(String.valueOf(getCardBoxSize()));
                    getQuestionSlider().setMax(getCardBoxSize());
                    switch (getSelectedCategoryList().size()) {
                        case 1:
                            getSelectedCategoryList().clear();
                            break;
                        case 0:
                            getQuestionSlider().setVisible(false);
                            break;
                        default:
                            getSelectedCategoryList().remove(getCheckBoxList().indexOf(checkBox));
                            break;
                    }
                }
            });
        });

        getCheckBoxList()
                .get(0).setOnAction((ActionEvent event) -> {
            getMaxQuestionLabel().setText(String.valueOf(getCardBoxSize()));
        }
        );

        categoryVBox.setAlignment(Pos.CENTER);

        return categoryVBox;
    }

    private HBox learnMode() {

        HBox learnModusBox = new HBox();

        learnModusBox.setSpacing(10);
//        learnModusBox.setMinWidth(700);
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
        getQuestionSlider().setVisible(false);
        StackPane sliderRoot = new StackPane(getQuestionSlider());
        sliderRoot.setAlignment(Pos.CENTER);
        sliderRoot.setPadding(new Insets(30, 30, 30, 30));

//        Scene scene = new Scene(sliderRoot);
//        Pane thumb = (Pane) getQuestionSlider().lookup(".thumb");
//        thumb.setEffect(new DropShadow(8, 4, 4, Color.GRAY));
        setSliderLabel(new Label());

        getSliderLabel().textProperty().bind(getQuestionSlider().valueProperty().asString("%.0f"));
//        thumb.getChildren().addAll(getSliderLabel());

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
            if (!getSelectedCategoryList().isEmpty()) {
                getCurrentUser().getUserSession().setSessionBox(getCurrentUser().getUserSession().getSessionBox().getCardboxByMultipleCategoryIDs(getCurrentUser().
                        getUserSession().getSessionBox(),
                        getSelectedCategoryList(), getQuestionSlider().getValue()));
            } else {
                getCurrentUser().getUserSession().getSessionBox().getCardList().clear();
                getCurrentUser().getUserSession().setSessionBox(new Cardbox(getCurrentUser().getUserSession().getSessionBox().getAllList()));
            }

            getCurrentUser().insertUserQuestionsIntoDB(getCurrentUser());

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

    private HBox addChartBox() {
        HBox chartBox = new HBox();

//        ObservableList<PieChart.Data> pieChartData
//                = FXCollections.observableArrayList(
//                        new PieChart.Data("Correct Answers", getCurrentUser().
//                                getUserSession().fetchChartData(getCurrentUser().
//                                getUserSession(), 
//                                        getCurrentUser().getUserSessionsList().
//                                                get(getCurrentUser().
//                                                        getUserSessionsList().
//                                                        size()-2)).get(2)),
//                        new PieChart.Data("Wrong Answers", getCurrentUser().
//                                getUserSession().fetchChartData(getCurrentUser().
//                                getUserSession(), 
//                                        getCurrentUser().getUserSessionsList().
//                                                get(getCurrentUser().
//                                                        getUserSessionsList().
//                                                        size()-2)).get(1)),
//                        new PieChart.Data("To Be Revised", getCurrentUser().
//                                getUserSession().fetchRevisedChartData(getCurrentUser().
//                                getUserSession(), 
//                                        getCurrentUser().getUserSessionsList().
//                                                get(getCurrentUser().
//                                                        getUserSessionsList().
//                                                        size()-2))),
//                        new PieChart.Data("Solution Given", getCurrentUser().
//                                getUserSession().fetchSolutionChartData(getCurrentUser().
//                                getUserSession(), 
//                                        getCurrentUser().getUserSessionsList().
//                                                get(getCurrentUser().
//                                                        getUserSessionsList().
//                                                        size()-2))));
//        final PieChart chart = new PieChart(pieChartData);
//        chart.setTitle("Last Session Results");
//        
//        chart.setLabelLineLength(10);
////        chart.setLegendSide(Side.LEFT);
//        
//        final NumberAxis xAxis = new NumberAxis();
//        final NumberAxis yAxis = new NumberAxis();
//        xAxis.setLabel("Number of Month");
//        //creating the chart
//        final LineChart<Number, Number> lineChart
//                = new LineChart<>(xAxis, yAxis);
//        
//        lineChart.setTitle("Answers by Sessions");
//        //defining a series
//        XYChart.Series series = new XYChart.Series();
//        series.setName("Session Data");
//        //populating the series with data
//        for (Integer sessionId : getCurrentUser().getUserSessionsList()) {
//            series.getData().add(new XYChart.Data(sessionId, getCurrentUser().
//                                getUserSession().fetchChartData(getCurrentUser().
//                                getUserSession(), 
//                                        sessionId)));
//        }
////        series.getData().add(new XYChart.Data(1, 23));
////        series.getData().add(new XYChart.Data(2, 14));
////        series.getData().add(new XYChart.Data(3, 15));
////        series.getData().add(new XYChart.Data(4, 24));
////        series.getData().add(new XYChart.Data(5, 34));
////        series.getData().add(new XYChart.Data(6, 36));
////        series.getData().add(new XYChart.Data(7, 22));
////        series.getData().add(new XYChart.Data(8, 45));
////        series.getData().add(new XYChart.Data(9, 43));
////        series.getData().add(new XYChart.Data(10, 17));
////        series.getData().add(new XYChart.Data(11, 29));
////        series.getData().add(new XYChart.Data(12, 25));
//
////        Scene scene  = new Scene(lineChart,800,600);
//        lineChart.getData().add(series);
//        
//        chartBox.getChildren().addAll(chart, lineChart);
        return chartBox;
    }
}
