package de.bbq.rh.oracleExams.card;

import de.bbq.rh.oracleExams.Test;
import de.bbq.rh.oracleExams.database.IMySQLDatabaseDAO;
import de.bbq.rh.oracleExams.database.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author $ Lyn Mildner
 */
public class Answer implements IMySQLDatabaseDAO {

    private ArrayList<Integer> id;
    private HashMap<Integer, String> textMap;
    private int question_id;
    private HashMap<Integer, Boolean> isRightMap;
    private HashMap<Integer, Boolean> isSelectedMap;

    public void setIsSelectedMap(HashMap<Integer, Boolean> isSelectedMap) {
        this.isSelectedMap = isSelectedMap;
    }

    public HashMap<Integer, Boolean> getIsSelectedMap() {
        return this.isSelectedMap;
    }

    public ArrayList<Integer> getId() {
        return id;
    }

    private void setId(ArrayList<Integer> id) {
        this.id = id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public void setIsRightMap(HashMap<Integer, Boolean> isRightMap) {
        this.isRightMap = isRightMap;
    }

    public HashMap<Integer, String> getTextMap() {
        return textMap;
    }

    public int getQuestion_id() {
        return this.question_id;
    }

    public HashMap<Integer, Boolean> getIsRightMap() {
        return isRightMap;
    }

    public void setTextMap(HashMap<Integer, String> textMap) {
        this.textMap = textMap;
    }

    public Answer(ArrayList<Integer> id, HashMap<Integer, String> text, int question_id, HashMap<Integer, Boolean> isRightMap, HashMap<Integer, Boolean> isSelectedMap) {
        this.id = id;
        this.textMap = text;
        this.question_id = question_id;
        this.isRightMap = isRightMap;
        this.isSelectedMap = isSelectedMap;
    }

    public Answer(int questionID) {
        this.textMap = getTextMapByQuestionID(questionID);
        this.question_id = questionID;
        this.id = getAnswerIDListByQuestionID(questionID);
        this.isRightMap = getIsRightMapByQuestionID(questionID);
        this.isSelectedMap = getIsSelectedMapByQuestionID(questionID);
    }

    public Answer() {
        this(1);
    }

    private HashMap<Integer, Boolean> getIsRightMapByQuestionID(int questionID) {
        HashMap<Integer, Boolean> isCorrectMap = new HashMap<>();
        boolean answerIsRight = false;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer WHERE question_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, questionID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                if (MySQLConnection.rst.getInt("isRight") == 1) {
                    answerIsRight = true;
                }
                isCorrectMap.put(MySQLConnection.rst.getInt("id"), answerIsRight);
                answerIsRight = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isCorrectMap;
    }

    private HashMap<Integer, Boolean> getIsSelectedMapByQuestionID(int questionID) {
        HashMap<Integer, Boolean> isGivenMap = new HashMap<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer WHERE question_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, questionID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                isGivenMap.put(MySQLConnection.rst.getInt("id"), false);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isGivenMap;
    }

    private HashMap<Integer, String> getTextMapByQuestionID(int questionID) {
        HashMap<Integer, String> answerTextMap = new HashMap<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer WHERE question_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, questionID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                answerTextMap.put(MySQLConnection.rst.getInt("id"), MySQLConnection.rst.getString("text"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answerTextMap;
    }

    private ArrayList<Integer> getAnswerIDListByQuestionID(int questionID) {
        ArrayList<Integer> answerIDList = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT id FROM answer WHERE question_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, questionID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                answerIDList.add(MySQLConnection.rst.getInt("id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answerIDList;
    }

    @Override
    public <E> E getById(E elem, int id) {      //Might not work
        Answer a = (Answer) elem;
        ArrayList<Integer> answerIDList = new ArrayList<>();
        HashMap<Integer, Boolean> isGivenMap = new HashMap<>();
        HashMap<Integer, Boolean> isCorrectMap = new HashMap<>();
        boolean answerIsRight = false;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                isGivenMap.put(MySQLConnection.rst.getInt("id"), false);
                answerIDList.add(MySQLConnection.rst.getInt("id"));
                if (MySQLConnection.rst.getInt("isRight") == 1) {
                answerIsRight = true;
            }
            isCorrectMap.put(MySQLConnection.rst.getInt("id"), answerIsRight);
            answerIsRight = false;
            }
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            a.setId(answerIDList);
            while (MySQLConnection.rst.next()) {
                a.getTextMap().put(MySQLConnection.rst.getInt("id"), MySQLConnection.rst.getString("text"));
                a.setQuestion_id(MySQLConnection.rst.getInt("question_id"));
                a.setIsRightMap(isCorrectMap);
                a.setIsSelectedMap(isGivenMap);
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
        HashMap<Integer, String> answerMap = new HashMap<>();
        ArrayList<Integer> answerIDList = new ArrayList<>();
        HashMap<Integer, Boolean> isGivenMap = new HashMap<>();
        HashMap<Integer, Boolean> isCorrectMap = new HashMap<>();
        boolean answerIsRight = false;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                isGivenMap.put(MySQLConnection.rst.getInt("id"), false);
                answerIDList.add(MySQLConnection.rst.getInt("id"));
                if (MySQLConnection.rst.getInt("isRight") == 1) {
                answerIsRight = true;
            }
            isCorrectMap.put(MySQLConnection.rst.getInt("id"), answerIsRight);
            answerIsRight = false;
            }
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                c.add(new Answer(answerIDList, answerMap, MySQLConnection.rst.getInt("question_id"), isCorrectMap, isGivenMap));
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
