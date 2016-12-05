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
import static ocase7.Category.stmt;
import static ocase7.Test.pstmt;

/**
 *
 * @author PaulsBook
 */
public class Question {

    //Verbindungsvariablen 
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;

    private int id;
    private String text;
    private int category_id;
    private boolean inactive;
    
    public static Statement getStmt() {
        return stmt;
    }

    public static PreparedStatement getPstmt() {
        return pstmt;
    }

    public static ResultSet getResultSet() {
        return resultSet;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getCategory_id() {
        return category_id;
    }

    public boolean isInactive() {
        return inactive;
    }
    

    public Question(int id, String text, int category_id, int inactive) {
        this.id = id;
        this.text = text;
        this.category_id = category_id;

        if (inactive == 1) {
            this.inactive = true;
        }
    }
    
    public Question(int id, String text){
        this.id = id;
        this.text = text;
    }

    public static ArrayList<Question> getAll() {
        ArrayList<Question> questions = new ArrayList<>();
        try {
             Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM question";
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                questions.add(new Question(resultSet.getInt("id"), resultSet.getString("text"), resultSet.getInt("category_id"), resultSet.getInt("inactive")));
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
        
        return questions;
    }
    
    public static ArrayList<Question> getQuestionsByCategory(Category category) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT id, text, inactive, category_id FROM question, category2question WHERE category_id = ? AND question.id = question_id";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, category.getId());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                questions.add(new Question(resultSet.getInt("id"), resultSet.getString("text"), resultSet.getInt("category_id"), resultSet.getInt("inactive")));
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

        return questions;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", text=" + text + ", category_id=" + category_id + ", inactive=" + inactive + '}';
    }
//######################sani was here##########################################
    
    // macht das Selbe wie deine Methode
    // nur dass ich nicht so einen tollen SQL-STMT benutzt habe
    // hier muss zuerst das questions_idsArray befüllt werden anhand der CategoryID
    // erst dann werden die Fragentexte abgerufen und als questionsArray zurückgegeben
    
    public static ArrayList<Question> getAllQuestionsByCategoryId(int category_id) {
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Integer> questions_ids = Question.getAllQuestion_IdsByCategoryId(category_id);

        try {
            Connection con = MySQLConnection.getConnection();
            for (Integer questions_id : questions_ids) {
                String sql = "SELECT * FROM question WHERE id = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, questions_id);
                resultSet = pstmt.executeQuery();

                while (resultSet.next()) {
                    questions.add(new Question(resultSet.getInt("id"), resultSet.getString("text")));
                }
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
//                if(con != null){
//                    con.close();
//                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return questions;
    }

    public static ArrayList<Integer> getAllQuestion_IdsByCategoryId(int category_id) {
        ArrayList<Integer> question_ids = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM category2question WHERE category_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, category_id);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                question_ids.add(resultSet.getInt("question_id"));
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
//                if(con != null){
//                    con.close();
//                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return question_ids;
    }

    
    // um Frage nur anhand von Ihrer ID auslesen lassen zu können
    
    public static Question getQuestionById(int question_id) {
        Question question = null;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM question WHERE id =?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, question_id);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                question = new Question(resultSet.getInt("id"), resultSet.getString("text"));
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
//                if(con != null){
//                    con.close();
//                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return question;
    }
    //#################sani was here##########################################
}
