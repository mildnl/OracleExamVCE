package de.bbq.rh.ocase7.card;

import de.bbq.rh.ocase7.Test;
import de.bbq.rh.ocase7.database.IMySQLDatabaseDAO;
import de.bbq.rh.ocase7.database.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private ArrayList<String> textList;
    private int question_id;
    private boolean isRight;
    private boolean isSelected;

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    private void setId(int id) {
        this.id = id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    private void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }

    public void setTextList(ArrayList<String> textList) {
        this.textList = textList;
    }

    public int getId() {
        return this.id;
    }

    public int getQuestion_id() {
        return this.question_id;
    }

    public boolean isIsRight() {
        return this.isRight;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public ArrayList<String> getTextList() {
        return this.textList;
    }

    public Answer(int id, ArrayList<String> text, int question_id, int isRight) {
        this.id = id;
        this.textList = text;
        this.question_id = question_id;
        this.isSelected = false;    //Temporary value
        if (isRight == 1) {
            this.setIsRight(true);
        } else {
            this.setIsRight(false);
        }
    }

    public Answer(int questionID) {
        this.textList = new ArrayList<>(getAnswersByQuestionID(questionID));
        this.question_id = questionID;
        this.id = getAnswerIDByQuestionID(questionID);
        this.isRight = getIsRightByQuestionID(questionID);
        this.isSelected = false;    //Temporary value
    }

    private boolean getIsRightByQuestionID(int questionID) {
        boolean answerIsRight = false;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT isRight FROM answer WHERE question_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, questionID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                if (MySQLConnection.rst.getInt("isRight") == 1) {
                    answerIsRight = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answerIsRight;
    }

    private ArrayList<String> getAnswersByQuestionID(int questionID) {
        ArrayList<String> answerList = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer WHERE question_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, questionID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                answerList.add(MySQLConnection.rst.getString("text"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answerList;
    }

    private int getAnswerIDByQuestionID(int questionID) {
        int answerID = 0;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT id FROM answer WHERE question_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, questionID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                answerID = MySQLConnection.rst.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answerID;
    }

    @Override
    public <E> E getById(E elem, int id) {
        Answer a = (Answer) elem;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer WHERE question_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                a.setId(MySQLConnection.rst.getInt("id"));
                a.getTextList().add(MySQLConnection.rst.getString("text"));
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
        ArrayList<Answer> c = new ArrayList();
        ArrayList<String> answersList = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                answersList.add(MySQLConnection.rst.getString("text"));
            }

            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                c.add(new Answer(MySQLConnection.rst.getInt("id"), answersList, MySQLConnection.rst.getInt("question_id"), MySQLConnection.rst.getInt("isRight")));
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
//        Answer a = (Answer) elem;
//        try {
//            Connection con = MySQLConnection.getConnection();
//            String sql = "INSERT INTO session2Answer VALUES(null, ?)";
//                //1 Object is saved through MySQL
//                //created PK should be returned
//                MySQLConnection.pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//                MySQLConnection.pst.setInt(1, a.getId());
//                MySQLConnection.pst.executeUpdate();
//                MySQLConnection.rst = MySQLConnection.pst.getGeneratedKeys();
//                while (MySQLConnection.rst.next()) {
//                    s.id = MySQLConnection.rst.getInt(1);
//                }
//            
//        } catch (SQLException e) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
//            System.out.println(e.getMessage());
//        }
    }

}
