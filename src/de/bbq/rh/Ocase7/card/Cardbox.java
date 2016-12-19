package de.bbq.rh.oracleExams.card;

import de.bbq.rh.oracleExams.Test;
import de.bbq.rh.oracleExams.database.IMySQLDatabaseDAO;
import de.bbq.rh.oracleExams.database.MySQLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author $ Lyn Mildner
 */
public class Cardbox implements IMySQLDatabaseDAO {

    private ArrayList<Card> cardList;

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    public Cardbox() {
        this.cardList = new ArrayList<>();
        this.cardList.add(new Card());
    }

    public <E> E getCardboxByCategoryID(E elem, int id) {
        Cardbox c = (Cardbox) elem;
        c.getCardList().clear();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT question_id FROM category2question WHERE category_id = ?";
            MySQLConnection.pst = con.prepareStatement(sql);
            MySQLConnection.pst.setInt(1, id);
            ResultSet resultset = MySQLConnection.pst.executeQuery();
            while (resultset.next()) {
                c.getCardList().add(new Card(resultset.getInt("question_id")));
            }
        } catch (SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
        return (E) c;
    }

    public Cardbox getCardboxByMultipleCategoryIDs(Cardbox c, ArrayList<Integer> givenCategoryIDs) {
        c.getCardList().clear();
        for (Integer categoryID : givenCategoryIDs) {
            Cardbox categoryCardbox = getCardboxByCategoryID(c, categoryID);
            c.getCardList().addAll(categoryCardbox.getCardList());
        }
        return c;
    }

    @Override
    public <E> E getById(E elem, int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Card> getAllList() {
        ArrayList<Card> c = new ArrayList();
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
