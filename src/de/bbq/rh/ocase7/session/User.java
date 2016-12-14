/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bbq.rh.ocase7.session;

import de.bbq.rh.ocase7.database.MySQLConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author $ Lyn Mildner
 */
public class User {

    private final int userID;
    private final String name;
    private final String password;

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return this.name;
    }

    public User(int user_id, String name, String password) {
        this.userID = user_id;
        this.name = name;
        this.password = password;
    }
    
    public User(int userID) {
        this.userID = userID;
        this.name = fetchUserNameByUserID(userID);
        this.password = fetchUserPasswordByUserID(userID);
    }
    
    public User() {
        this(1);
    }

    private int fetchUserIDByUserName(String userName) {
        int userId = 0;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT id FROM user WHERE name = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setString(1, userName);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                userId = MySQLConnection.rst.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userId;
    }
    
    private String fetchUserNameByUserID(int userID) {
        String userName = null;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT name FROM user WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, userID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                userName = MySQLConnection.rst.getString("name");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userName;
    }
    
    private String fetchUserPasswordByUserID(int userID)  {
        String userPassword = null;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT password FROM user WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, userID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                userPassword = MySQLConnection.rst.getString("password");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userPassword;
    }
}
