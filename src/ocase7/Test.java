/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author PaulsBook
 */
public class Test {

    //Verbindungsvariablen 
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;

    private int id;
    private String text;

    public Test(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Test(String text) {
        this.text = text;
    }
    
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    

    @Override
    public String toString() {
        return "Test{" + "id=" + id + ", text=" + text + '}';
    }

    public static Test getById(int id) {
        Test t = null;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "Select * from test where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            // executeQuery() wenn etwas aus der DB ausgelesen werden soll 
            resultSet = pstmt.executeQuery(); 
            // Abfrage allgemein, für meherer Datensätze
            while (resultSet.next()) {
                t = new Test(resultSet.getInt("id"), resultSet.getString("text"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        return t;
    }
    
     public static ArrayList<Test> getAll() {
         ArrayList<Test> testList = new ArrayList<>();
         Test t = null;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM test";
            stmt = con.createStatement();
            //pstmt = con.prepareStatement(sql);
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                t = new Test(resultSet.getInt("id"), resultSet.getString("text"));
                testList.add(t);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(stmt != null) {
                    stmt.close();
                }
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return testList;
    }

    public static void delete(int id) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "DELETE FROM test WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            // executeUpdate() wenn etwas in der DB geändert werden soll
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static void update(Test t) {
         try {
            Connection con = MySQLConnection.getConnection();
            String sql = "UPDATE test set text = ? WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, t.getText());
            pstmt.setInt(2, t.getId());
            // executeUpdate() wenn etwas in der DB geändert werden soll
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static void insert(Test t) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO test VALUES (null, ?)";
            if (t.getId() == 0) {
                
            // 1 Objekt wird gespeichert, der durch MySQL
            // erstellte PK soll zurückgegeben werden
            pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, t.getText());
            pstmt.executeUpdate();
            resultSet = pstmt.getGeneratedKeys();
                while (resultSet.next()) {   
                    t.id = resultSet.getInt(1); // 1 = erste Spalte der DB
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
   
    
}
