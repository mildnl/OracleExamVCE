/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bbq.rh.ocase7.login;

import de.bbq.rh.ocase7.database.MySQLConnection;
import de.bbq.rh.ocase7.session.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
public class LoginView {

    private TextField userTextField;
    private PasswordField pwBox;
    private Button btn;
    private Text actiontarget;
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

    public PasswordField getPwBox() {
        return this.pwBox;
    }

    public Button getBtn() {
        return this.btn;
    }

    public Text getActiontarget() {
        return this.actiontarget;
    }

    public User getLoggedUser() {
        return this.loggedUser;
    }

    private void setUserTextField(TextField userTextField) {
        this.userTextField = userTextField;
    }

    private void setPwBox(PasswordField pwBox) {
        this.pwBox = pwBox;
    }

    private void setBtn(Button btn) {
        this.btn = btn;
    }

    private void setActiontarget(Text actiontarget) {
        this.actiontarget = actiontarget;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public LoginView(TextField userTextField, PasswordField pwBox, Button btn, Text actiontarget, User loggedUser) {
        this.userTextField = userTextField;
        this.pwBox = pwBox;
        this.btn = btn;
        this.actiontarget = actiontarget;
        this.loggedUser = loggedUser;
        this.loginComplete = false;
    }

    public LoginView() {
        this(null, null, null, null, null);
    }

    public Scene createLoginView() {
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

        setPwBox(new PasswordField());
        grid.add(getPwBox(), 1, 2);

        setBtn(new Button("Sign in"));
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        setActiontarget(new Text());
        grid.add(getActiontarget(), 1, 6);

        getBtn().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (loginByUserNameAndPasssword(getUserTextField().getText(), getPwBox().getText())) {
                    getActiontarget().setFill(Color.GREEN);
                    getActiontarget().setText("Login succsessfull!");
                    setLoggedUser(new User(getUserTextField().getText()));
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException exc) {
                        exc.printStackTrace();
                    }
                    setLoginComplete(true);
                } else {
                    System.out.println("Username: " + getUserTextField().getText());
                    System.out.println("Password: " + getPwBox().getText());
                    getActiontarget().setFill(Color.FIREBRICK);
                    getActiontarget().setText("Login Password or Username doesnt match!");
                }

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
                System.out.println("DB UserName: " + MySQLConnection.rst.getString("name"));
                System.out.println("UserName: " + userName);
                System.out.println("DB Password: " + MySQLConnection.rst.getString("password"));
                System.out.println("Password: " + userPassword);
                if (MySQLConnection.rst.getString("name").equals(userName) && MySQLConnection.rst.getString("password").equals(userPassword)) {
                    System.out.println("INSIDE");
                    checkPassed = true;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return checkPassed;
    }

}
