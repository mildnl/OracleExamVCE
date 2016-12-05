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
import static ocase7.Question.pstmt;

/**
 *
 * @author PaulsBook
 */
public class Answer {

    //Verbindungsvariablen 
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;

    private int id;
    private String text;
    private int question_id;
    private boolean isRight;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public boolean isIsRight() {
        return isRight;
    }

    public Answer(int id, String text, int question_id, int isRight) {
        this.id = id;
        this.text = text;
        this.question_id = question_id;
        if (isRight == 1) {
            this.isRight = true;
        }
    }

    public static ArrayList<Answer> getAnswersByQuestion(Question question) {
        ArrayList<Answer> answers = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT id, text, isRight, question_id FROM answer WHERE question_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, question.getId());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                answers.add(new Answer(resultSet.getInt("id"), resultSet.getString("text"), resultSet.getInt("question_id"), resultSet.getInt("isRight")));
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

        return answers;
    }

    @Override
    public String toString() {
        return "Answers{" + "id=" + id + ", text=" + text + ", question_id=" + question_id + ", isRight=" + isRight + '}';
    }

}
