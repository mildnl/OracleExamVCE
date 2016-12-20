/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bbq.rh.OracleExamVCE.session;

import de.bbq.rh.OracleExamVCE.card.Card;
import de.bbq.rh.OracleExamVCE.database.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 *
 * @author $ Lyn Mildner
 */
public class User {

    private final int userID;
    private final String name;
    private final String password;
    private Session userSession;
    private ArrayList<Integer> userSessionsList;

    public int getUserID() {
        return this.userID;
    }

    public String getName() {
        return this.name;
    }

    public Session getUserSession() {
        return this.userSession;
    }

    public ArrayList<Integer> getUserSessionsList() {
        return this.userSessionsList;
    }

    public void setUserSessionsList(ArrayList<Integer> userSessionsList) {
        this.userSessionsList = userSessionsList;
    }

    public User(int user_id, String name, String password, Session userSession) {
        this.userID = user_id;
        this.name = name;
        this.password = password;
        this.userSession = userSession;
    }

    public User(int userID) {
        this.userID = userID;
        this.name = fetchUserNameByUserID(userID);
        this.password = fetchUserPasswordByUserID(userID);
        this.userSession = new Session(userID);
        this.userSessionsList = fetchUserSessionsList(userID);
    }

    public User(String userName) {
        this.userID = fetchUserIDByUserName(userName);
        this.name = fetchUserNameByUserID(this.userID);
        this.password = fetchUserPasswordByUserID(this.userID);
        this.userSession = new Session(fetchUserIDByUserName(userName));
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

    private String fetchUserPasswordByUserID(int userID) {
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

    public void insertUserAnswersIDIntoDB(User u) {
        ArrayList<Integer> userAnswerList = new ArrayList<>();
        for (int i = 0; i < u.getUserSession().getSessionBox().getCardList().size(); i++) {
            for (Entry<Integer, Boolean> key : u.getUserSession().getSessionBox().getCardList().get(i).getAnswer().getIsSelectedMap().entrySet()) {
                if (key.getValue()) {
                    userAnswerList.add(key.getKey());
                }
            }
        }
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO lmildner_OCP6.`useranswer` (`user_id`, `answer_id`) \n"
                    + "	VALUES (?, ?)";
            MySQLConnection.pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            for (Integer answerID : userAnswerList) {
                MySQLConnection.pst.setInt(1, u.getUserID());
                MySQLConnection.pst.setInt(2, answerID);
                MySQLConnection.pst.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        insertSessionTUserAnswersIDIntoDB(u.getUserSession().getId(), userAnswerList);
    }

    private void insertSessionTUserAnswersIDIntoDB(int sessionID, ArrayList<Integer> userAnswerList) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO lmildner_OCP6.`session2useranswer` (`session_id`, `userAnswer_id`) \n"
                    + "	VALUES (?, ?)";
            MySQLConnection.pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            for (Integer answerID : userAnswerList) {
                MySQLConnection.pst.setInt(1, sessionID);
                MySQLConnection.pst.setInt(2, answerID);
                MySQLConnection.pst.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertSessionTAgainIntoDB(User u) {
        ArrayList<Integer> userIsSolutionGivenList = new ArrayList<>();
        ArrayList<Integer> userToBeRevisedList = new ArrayList<>();

        for (Card card : u.getUserSession().getSessionBox().getCardList()) {
            if (card.isSolutionGiven()) {
                userIsSolutionGivenList.add(card.getId());
            }
            if (card.isToBeRevised()) {
                userToBeRevisedList.add(card.getId());
            }
        }
        
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO lmildner_OCP6.`again` (`session_id`, `question_id`, `cheatedOrAgain`) \n"
                    + "	VALUES (?, ?, ?)";
            MySQLConnection.pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            for (Integer answerID : userIsSolutionGivenList) {
                MySQLConnection.pst.setInt(1, u.getUserSession().getId());
                MySQLConnection.pst.setInt(2, answerID);
                MySQLConnection.pst.setString(3,"S");
                MySQLConnection.pst.executeUpdate();
            }
            for (Integer answerID : userToBeRevisedList) {
                MySQLConnection.pst.setInt(1, u.getUserSession().getId());
                MySQLConnection.pst.setInt(2, answerID);
                MySQLConnection.pst.setString(3,"R");
                MySQLConnection.pst.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertUserQuestionsIntoDB(User u) {
        ArrayList<Integer> userQuestionList = new ArrayList<>();
        u.getUserSession().getSessionBox().getCardList().forEach((cardQuestionID) -> {
            userQuestionList.add(cardQuestionID.getId());
        });
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO lmildner_OCP6.`session2question` (`session_id`, `question_id`) \n"
                    + "	VALUES (?, ?)";
            MySQLConnection.pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            for (Integer questionID : userQuestionList) {
                MySQLConnection.pst.setInt(1, u.getUserSession().getId());
                MySQLConnection.pst.setInt(2, questionID);
                MySQLConnection.pst.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<Integer> fetchUserSessionsList(int userID) {
        ArrayList<Integer> sessionsList = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT id FROM session WHERE user_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, userID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                sessionsList.add(MySQLConnection.rst.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sessionsList;
    }
}
