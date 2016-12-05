/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author PaulsBook
 */
public class Category {

    //Verbindungsvariablen 
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;
    
    private int id;
    private String text;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
    
    public Category(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Category() {
    }
    

    public static ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM category";
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"), resultSet.getString("text")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return categories;
    }
    
    public static Category getCategoryById(int id) {
        Category category = new Category();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM category WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
               category = new Category(resultSet.getInt("id"), resultSet.getString("text"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return category;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", text=" + text + '}';
    }
    
}
