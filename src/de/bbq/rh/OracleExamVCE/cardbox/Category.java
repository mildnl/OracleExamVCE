package de.bbq.rh.OracleExamVCE.cardbox;

import de.bbq.rh.OracleExamVCE.database.IMySQLDatabaseDAO;
import de.bbq.rh.OracleExamVCE.database.MySQLConnection;
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
public class Category implements IMySQLDatabaseDAO {
    private int id;
    private String name;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Category(int id) {
        this.id = id;
        this.name = getTextByCategoryIDs(id); 
   }
    
    public Category() {
        this(4);
    }
    
    private String getTextByCategoryIDs(int id) {
        String categoryName = "empty";
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT text FROM category WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                categoryName = MySQLConnection.rst.getString("text");
            }         
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categoryName;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + this.id + ", name=" + this.name + '}';
    }
    
    @Override
    public <E> E getById(E elem, int id) {
        Category c = (Category) elem;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM category WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                c = new Category(MySQLConnection.rst.getInt("id"), MySQLConnection.rst.getString("text"));
            }         
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (E) c;
    }
    
    public String getCategoryNameByCategoryID(int id) {
        String categoryName = null;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT text FROM category WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                categoryName = MySQLConnection.rst.getString("text");
            }         
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categoryName;
    }
    
    public int getCategoryIDByQuestionID(int questionID) {
        int categoryID = 0;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM category2question WHERE question_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, questionID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                categoryID = MySQLConnection.rst.getInt("category_id");
            }         
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categoryID;
    }
    
    public ArrayList<Integer> getQuestionIDListByCategoryID(int categoryID) {
        ArrayList<Integer> questionIDList = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM category2question WHERE category_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, categoryID);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                questionIDList.add(MySQLConnection.rst.getInt("question_id"));
            }         
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return questionIDList;
    }
    
    @Override
    public ArrayList<Category> getAllList() {
        ArrayList<Category> c =  new ArrayList();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM category";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.rst = MySQLConnection.pst.executeQuery();
            while (MySQLConnection.rst.next()) {
                c.add(new Category(MySQLConnection.rst.getInt("id"), MySQLConnection.rst.getString("text")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
    
    @Override
    public void delete(int id) {
    try {
        Connection con = MySQLConnection.getConnection();
        String sql = "DELETE FROM category WHERE id = ?";
        MySQLConnection.pst = con.prepareStatement(sql);
        MySQLConnection.pst.setInt(1, id);
        MySQLConnection.pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public <E> void update(E elem) {
        Category c = (Category)elem;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "UPDATE category SET text = ? WHERE id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setString(1, c.getName());
            MySQLConnection.pst.setInt(2, c.getId());
            MySQLConnection.pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public <E> void insert(E elem) {
        Category c = (Category)elem;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO category VALUES(null, ?)";
            if (c.getId() == 0) {
                //1 Object is saved through MySQL
                //created PK should be returned
                MySQLConnection.pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                MySQLConnection.pst.setString(1, c.getName());
                MySQLConnection.pst.executeUpdate();
                MySQLConnection.rst = MySQLConnection.pst.getGeneratedKeys();
                while (MySQLConnection.rst.next()) {
                c.id = MySQLConnection.rst.getInt(1);
                }  
            }       
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }   
}
