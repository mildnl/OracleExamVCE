/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

    public Card(Category cat, int id, String question) {
        this.cat = cat;
        this.id = id;
        this.question = question;
    }
    
    public Card(int id, String question) {
        this.cat = new Category();
        this.id = id;
        this.question = question;
    }
    
    public Card() {
        this.cat = new Category();
        this.id = 0;
        this.question = "default question";
    }

    @Override
    public Card getById(int id) {
        Category ct = new Category();
        Card c = null;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM question WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                c = new Card(MySQLConnection.rst.getInt("id"), MySQLConnection.rst.getString("question"));
            }         
        } catch (SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        } finally {
            try {
                if (MySQLConnection.pst != null) {
                    MySQLConnection.pst.close();
                }
                if (MySQLConnection.rst != null) {
                    MySQLConnection.rst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
            MySQLConnection.closeConnection();
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
                c.add(new Card(MySQLConnection.rst.getInt("id"), MySQLConnection.rst.getString("question")));
            }
        } catch (SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        } finally {
            try {
                if (MySQLConnection.pst != null) {
                    MySQLConnection.pst.close();
                }
                if (MySQLConnection.rst != null) {
                    MySQLConnection.rst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
            MySQLConnection.closeConnection();
        }
        return c; 
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    

}
