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
import static ocase7.Test.stmt;

/**
 *
 * @author PaulsBook
 */
public class Card {

    //Verbindungsvariablen 
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;

    private int id;
    private Question question;
    private ArrayList<Answer> answers;

    public Card(int id, Question question, ArrayList<Answer> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

//    public static ArrayList<Card> getAll() {
//        ArrayList<Card> cards = new ArrayList<>();
//        try {
//            Connection con = MySQLConnection.getConnection();
//            String sql = "SELECT * FROM question";
//            stmt = con.createStatement();
//            //pstmt = con.prepareStatement(sql);
//            resultSet = stmt.executeQuery(sql);
//            Question q;
//            while (resultSet.next()) {
//                q = new Question(resultSet.getInt("id"), resultSet.getString("text"), resultSet.getInt("category_id"), resultSet.getInt("inactive"));
//                cards.add(new Card(resultSet.getInt("id"), q, Answer.getAnswersByQuestion(q)));
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        return cards;
//    }
    public static Card getCardsByCategory(Category c) {
        //ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Question> questions = Question.getQuestionsByCategory(c);
        Question q = questions.get(0);
        Card card = new Card(q.getId(), q, Answer.getAnswersByQuestion(q));
        
        return card;
    }

    @Override
    public String toString() {
        return "Card{" + "id=" + id + ", question=" + question + ", answers=" + answers + '}';
    }

}
