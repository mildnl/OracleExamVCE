package de.bbq.rh.ocase7.card;

import de.bbq.rh.ocase7.Test;
import de.bbq.rh.ocase7.database.IMySQLDatabaseDAO;
import de.bbq.rh.ocase7.database.MySQLConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author $ Lyn Mildner
 */
public class Answer implements IMySQLDatabaseDAO {
    private int id;
    private String text;
    private int question_id;
    private boolean isRight;

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }

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
        this.isRight = false;
        if (isRight == 1) {
            this.isRight = true;
        }
    }
    
    public Answer() {
        this.id = 0;
        this.text = "defaultText";
        this.question_id = 0;
        this.isRight = false; 
    }
    
    public ArrayList<Answer> getAnswersByQuestion(int questionID) {
        ArrayList<Answer> answers = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer WHERE question_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, questionID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                answers.add(new Answer(MySQLConnection.rst.getInt("id"), MySQLConnection.rst.getString("text"), MySQLConnection.rst.getInt("question_id"), MySQLConnection.rst.getInt("isRight")));
            }
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

        return answers;
}

    @Override
    public <E> E getById(E elem, int id) {
        Answer a = (Answer)elem;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer WHERE question_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                a.setId(MySQLConnection.rst.getInt("id")); 
                a.setText(MySQLConnection.rst.getString("text"));
                a.setQuestion_id(MySQLConnection.rst.getInt("question_id"));
                if (MySQLConnection.rst.getInt("isRight") == 1) {
                    a.setIsRight(true);
                } else {
                    a.setIsRight(false);
                }
                
            }         
        } catch (SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
        return (E) a;
    }

    @Override
    public ArrayList<?> getAllList() {
        ArrayList<Answer> c =  new ArrayList();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                c.add(new Answer(MySQLConnection.rst.getInt("id"), MySQLConnection.rst.getString("text"), MySQLConnection.rst.getInt("question_id"), MySQLConnection.rst.getInt("isRight")));
            }
        } catch (SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
        return c; 
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <E> void update(E elem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <E> void insert(E elem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
