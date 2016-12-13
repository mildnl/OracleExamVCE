package de.bbq.rh.ocase7.card;

import de.bbq.rh.ocase7.database.IMySQLDatabaseDAO;
import de.bbq.rh.ocase7.database.MySQLConnection;
import de.bbq.rh.ocase7.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author $ Lyn Mildner
 */
public class Card implements IMySQLDatabaseDAO {
    private Category cat;
    private int id;
    private String question;
    private ArrayList<Answer> answerList;

    public Category getCat() {
        return this.cat;
    }

    public int getId() {
        return this.id;
    }

    public String getQuestion() {
        return this.question;
    }

    private void setCat(Category cat) {
        this.cat = cat;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public Card(Category cat, int id, String question, ArrayList<Answer> answerList) {
        this.cat = cat;
        this.id = id;
        this.question = question;
        this.answerList = answerList;
    }
    
    public Card(int id) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM question WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                this.id = MySQLConnection.rst.getInt("id");
                this.question = MySQLConnection.rst.getString("text");  
            } 
            this.cat = new Category();
            Answer a = new Answer(id);
            this.answerList = new ArrayList<>();
            this.answerList.add(a);

        } catch (SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
    }
    
    public Card() {
        this(1);
    }

    @Override
    public <E> E getById(E elem, int id) {
        Card c = (Card) elem;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM question WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                c.setId(MySQLConnection.rst.getInt("id"));
                c.setQuestion(MySQLConnection.rst.getString("text"));  
            } 
            c.cat.setId(c.cat.getCategoryIDByQuestionID(c.getId()));
            c.cat.setName(c.cat.getCategoryNameByCategoryID(c.cat.getId()));
            Answer a = new Answer(c.getId());
            a = (Answer) a.getById(a, id);
            c.answerList.add(a);

        } catch (SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
        return (E) c;
    }
    
    private Card constructByQuestionID(Card c, int id) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM question WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                c.setId(MySQLConnection.rst.getInt("id"));
                c.setQuestion(MySQLConnection.rst.getString("text"));  
            } 
            c.cat = new Category();
            c.cat.setId(c.cat.getCategoryIDByQuestionID(c.getId()));
            c.cat.setName(c.cat.getCategoryNameByCategoryID(c.cat.getId()));
            Answer a = new Answer(c.getId());
            a = (Answer) a.getById(a, id);
            c.answerList = new ArrayList<>();
            c.answerList.add(a);

        } catch (SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
        return c;
    }

    @Override
    public ArrayList<Card> getAllList() {
        ArrayList<Card> c =  new ArrayList();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM question";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                c.add(new Card(MySQLConnection.rst.getInt("id")));
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
