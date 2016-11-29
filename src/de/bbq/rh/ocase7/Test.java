package de.bbq.rh.ocase7;



import de.bbq.rh.ocase7.database.MySQLConnection;
import de.bbq.rh.ocase7.database.IMySQLDatabaseDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Test Object</h1>
 * Test ist toll
 * @version 0.01
 * @author $ Lyn Mildner
 */
public class Test implements IMySQLDatabaseDAO {
    private int id;
    private String text;
    

    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        this.id = id;
    }
    
    public String getText() {
        return this.text;
    }

    private void setText(String text) {
        this.text = text;
    }

    public Test(int id, String text) {
        this.id = id;
        this.text = text;
    }
    
    public Test(String text) {
        this.id = 0;
        this.text = text;
    }
    
    public Test() {
        this.id = 0;
        this.text = "test";
    }

    @Override
    public String toString() {
        return "Test{" + "id=" + id + ", text=" + text + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Test other = (Test) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.text, other.text);
    }
    
    @Override
    public Test getById(int id) {
        Test t = null;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM test WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                t = new Test(MySQLConnection.rst.getInt("id"), MySQLConnection.rst.getString("text"));
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
        return t;
    }
    
    @Override
    public ArrayList<Test> getAllList() {
        ArrayList<Test> t =  new ArrayList();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM test LIMIT 1000";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                t.add(new Test(MySQLConnection.rst.getInt("id"), MySQLConnection.rst.getString("text")));
            }
            t.forEach((i) -> {
                System.out.println(i.toString());
            });
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
        return t;
    }
    
    @Override
    public void delete(int id) {
    try {
        Connection con = MySQLConnection.getConnection();
        String sql = "DELETE FROM test WHERE id = ?";
        MySQLConnection.pst = con.prepareStatement(sql);
        MySQLConnection.pst.setInt(1, id);
        MySQLConnection.pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (MySQLConnection.pst != null) {
                    MySQLConnection.pst.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            MySQLConnection.closeConnection();
        }
    }

    public void update(Test t) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "UPDATE test SET text = ? WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setString(1, t.getText());
            MySQLConnection.pst.setInt(2, t.getId());
            MySQLConnection.pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (MySQLConnection.pst != null) {
                    MySQLConnection.pst.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            MySQLConnection.closeConnection();
        }
    }

    public void insert(Test t) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO test VALUES(null, ?)";
            if (t.getId() == 0) {
                //1 Object is saved through MySQL
                //created PK should be returned
                MySQLConnection.pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                MySQLConnection.pst.setString(1, t.getText());
                MySQLConnection.pst.executeUpdate();
                MySQLConnection.rst = MySQLConnection.pst.getGeneratedKeys();
                while (MySQLConnection.rst.next()) {
                t.id = MySQLConnection.rst.getInt(1);
                }  
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
    }   
}
