
package de.bbq.rh.ocase7.database;

import de.bbq.rh.ocase7.Test;
import de.bbq.rh.ocase7.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lyn Mildner
 */
public class MySQLConnection implements IMySQLDatabaseDAO {
    private static Connection con = null;
    public static Statement st = null;
    public static PreparedStatement pst = null;
    public static ResultSet rst = null;
    
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connection established.");
            } catch (SQLException ex) {
                Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return con;
    }
    
    public static void  closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Connection closed.");
            } catch (SQLException ex) {
                Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }

    @Override
    public Test getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public ArrayList<?> getAllList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
