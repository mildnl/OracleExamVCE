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
public class Cardbox implements IMySQLDatabaseDAO {
    private Card card;

    public Card getCard() {
        return this.card;
    }

    private void setCard(Card card) {
        this.card = card;
    }
    
    public Cardbox(int id, String question) {
        this.card = new Card(id, question);
    }
    
    public Cardbox() {
        this.card = new Card();
    }

    @Override
    public Object getById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Cardbox> getAllList() {
        ArrayList<Cardbox> c =  new ArrayList();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM question";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                c.add(new Cardbox(MySQLConnection.rst.getInt("id"), MySQLConnection.rst.getString("question")));
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
