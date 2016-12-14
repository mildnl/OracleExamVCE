package de.bbq.rh.ocase7.database;

import java.util.ArrayList;

/**
 *
 * @author mildnl
 */
public interface IMySQLDatabaseDAO {
    
//    String URL = "jdbc:mysql://192.168.2.15:3306/ocase7";
//    String USER = "Petra";
//    String PASSWORD = "Panke";
    
//    String URL = "jdbc:mysql://178.162.194.27:3306/lmildner_OCP6";
//    String USER = "lmildner_Lyn";
//    String PASSWORD = "z2o2an$43,WF";

    String URL = "jdbc:mysql://localhost:3306/lmildner_OCP6";
    String USER = "user1";
    String PASSWORD = "newpass";
    
    public <E extends Object> E getById(E elem, int id);
    
    public ArrayList<?> getAllList();
    
    public void delete(int id);
    
    public <E> void update(E elem);
    
    public <E> void insert(E elem);
}
