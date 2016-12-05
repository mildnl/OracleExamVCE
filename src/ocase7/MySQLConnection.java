/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author PaulsBook
 */
public class MySQLConnection {
    //private static final String URL = "jdbc:mysql://192.168.2.15:3306/ocase7";
    private static final String URL = "jdbc:mysql://localhost:3306/ocase7";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static Connection con = null;
    
    public static Connection getConnection() {
        if(con == null) {
            try {
                con = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return con;
    }
 
    public static void closeConnection() {
        try {
            if(con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
