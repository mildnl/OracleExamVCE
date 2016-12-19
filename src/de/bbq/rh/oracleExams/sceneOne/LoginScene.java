/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bbq.rh.oracleExams.sceneOne;

import de.bbq.rh.oracleExams.Main;
import de.bbq.rh.oracleExams.database.MySQLConnection;
import de.bbq.rh.oracleExams.sceneTwo.CardboxSelectionScene;
import de.bbq.rh.oracleExams.session.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author $ Lyn Mildner
 */
public class LoginScene {

    private TextField userTextField;
    private PasswordField passwordBox;
    private Button btn;
    private Text actionTarget;
    private User loggedUser;
    private boolean loginComplete;

    public void setLoginComplete(boolean loginComplete) {
        this.loginComplete = loginComplete;
    }

    public boolean isLoginComplete() {
        return this.loginComplete;
    }

    public TextField getUserTextField() {
        return this.userTextField;
    }

    public PasswordField getPasswordBox() {
        return this.passwordBox;
    }

    public Button getBtn() {
        return this.btn;
    }

    public Text getActionTarget() {
        return this.actionTarget;
    }

    public User getLoggedUser() {
        return this.loggedUser;
    }

    private void setUserTextField(TextField userTextField) {
        this.userTextField = userTextField;
    }

    private void setPasswordBox(PasswordField passwordBox) {
        this.passwordBox = passwordBox;
    }

    private void setBtn(Button btn) {
        this.btn = btn;
    }

    private void setActionTarget(Text actionTarget) {
        this.actionTarget = actionTarget;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public LoginScene(TextField userTextField, PasswordField pwBox, Button btn, Text actiontarget, User loggedUser) {
        this.userTextField = userTextField;
        this.passwordBox = pwBox;
        this.btn = btn;
        this.actionTarget = actiontarget;
        this.loggedUser = loggedUser;
        this.loginComplete = false;
    }

    public LoginScene() {
        this(null, null, null, null, null);
    }

    public Scene createLoginView(Main m) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Login");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        setUserTextField(new TextField());
        grid.add(getUserTextField(), 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        setPasswordBox(new PasswordField());
        grid.add(getPasswordBox(), 1, 2);

        setBtn(new Button("Sign in"));
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        setActionTarget(new Text());
        grid.add(getActionTarget(), 1, 6);

        getBtn().setOnAction((ActionEvent e) -> {
            if (loginByUserNameAndPasssword(getUserTextField().getText(), getPasswordBox().getText())) {
                setLoggedUser(new User(getUserTextField().getText()));
                CardboxSelectionScene viewTwo = new CardboxSelectionScene(getLoggedUser());
                Scene viewTwoScene = viewTwo.createSceneTwo(m);
                m.setCurrentScene(viewTwoScene);
                m.getStage().setScene(m.getCurrentScene());
            } else {
                getActionTarget().setFill(Color.FIREBRICK);
                getActionTarget().setText("Login Password or Username doesnt match!");
            }
        });

        Scene loginViewScene = new Scene(grid, 800, 660);
        return loginViewScene;

    }

    private boolean loginByUserNameAndPasssword(String userName, String userPassword) {
        boolean checkPassed = false;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM user WHERE name = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setString(1, userName);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                if (MySQLConnection.rst.getString("name").equals(userName) && MySQLConnection.rst.getString("password").equals(userPassword)) {
                    checkPassed = true;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return checkPassed;
    }

}
