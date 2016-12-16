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
    private Answer answer;
    private boolean isSelected;
    private boolean solutionGiven;

    public Category getCat() {
        return this.cat;
    }

    public int getId() {
        return this.id;
    }

    public String getQuestion() {
        return this.question;
    }

    public boolean isIsSelected() {
        return this.isSelected;
    }

    public boolean isSolutionGiven() {
        return this.solutionGiven;
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

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setSolutionGiven(boolean solutionGiven) {
        this.solutionGiven = solutionGiven;
    }

    public Answer getAnswer() {
        return answer;
    }
    
    public Card(int id) {
        this.cat = new Category(categoryIDByQuestionID(id));
        this.id = id;
        this.question = questionTextByQuestionID(id);
        this.answer = new Answer(id);
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
            c.answer = (Answer) a.getById(a, id);

        } catch (SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
        return (E) c;
    }
    
    private int categoryIDByQuestionID(int id) {
        int categoryID = 0;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM category2question WHERE question_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                categoryID = MySQLConnection.rst.getInt("category_id");  
            } 

        } catch (SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
        return categoryID;
    }
    
    private String questionTextByQuestionID(int id) {
        String questionText = "empty";
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT text FROM question WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                questionText = MySQLConnection.rst.getString("text");  
            } 

        } catch (SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
        return questionText;
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
