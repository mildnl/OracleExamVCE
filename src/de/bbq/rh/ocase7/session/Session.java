/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bbq.rh.ocase7.session;

import de.bbq.rh.ocase7.Test;
import de.bbq.rh.ocase7.card.Cardbox;
import de.bbq.rh.ocase7.database.IMySQLDatabaseDAO;
import de.bbq.rh.ocase7.database.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author $ Lyn Mildner
 */
public class Session implements IMySQLDatabaseDAO {

    private int id;
    private User user;
    private SimpleDateFormat begin;
    private Cardbox sessionBox;

    public int getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public SimpleDateFormat getBegin() {
        return this.begin;
    }

    public Cardbox getSessionBox() {
        return this.sessionBox;
    }

    public void setBegin(SimpleDateFormat begin) {
        this.begin = begin;
    }

    public void setSessionBox(Cardbox sessionBox) {
        this.sessionBox = sessionBox;
    }

    public Session(User user) {
        insertSessionIDByUserID(user.getUserID());
        this.id = fetchSessionIDByUserID(user.getUserID());
        this.user = user;
        this.begin = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        this.sessionBox = new Cardbox();

        
    }
    
    private void insertSessionIDByUserID(int userID) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO lmildner_OCP6.`session` (`begin`, user_id) \n" +
"	VALUES (CURRENT_TIMESTAMP, ?)";
            MySQLConnection.pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            MySQLConnection.pst.setInt(1, userID); //Die 1 bedeutet das erste "?" des INSERT Statments
            MySQLConnection.pst.executeUpdate();        //Bei nicht SELECT kommt executeUpdate!
            MySQLConnection.rst = MySQLConnection.pst.getGeneratedKeys();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private int fetchSessionIDByUserID(int userID) {
        int sessionID = 0;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT id FROM session WHERE user_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, userID); 
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                MySQLConnection.rst.last();
                sessionID = MySQLConnection.rst.getInt("id");
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sessionID;
    }

    @Override
    public <E> E getById(E elem, int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<?> getAllList() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        Session s = (Session) elem;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO category VALUES(null, ?)";
            if (s.getId() == 0) {
                //1 Object is saved through MySQL
                //created PK should be returned
                MySQLConnection.pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                MySQLConnection.pst.setInt(1, s.getId());
                MySQLConnection.pst.executeUpdate();
                MySQLConnection.rst = MySQLConnection.pst.getGeneratedKeys();
                while (MySQLConnection.rst.next()) {
                s.id = MySQLConnection.rst.getInt(1);
                }  
            }       
        } catch (SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
    }
}
