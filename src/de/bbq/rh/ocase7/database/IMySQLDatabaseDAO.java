package de.bbq.rh.ocase7.database;

import java.util.ArrayList;

/**
 *
 * @author mildnl
 */
public interface IMySQLDatabaseDAO {
    
    String URL = "jdbc:mysql://192.168.2.15:3306/ocase7";
    String USER = "Petra";
    String PASSWORD = "Panke";
    
    public <E extends Object> E getById(E elem, int id);
    
    public ArrayList<?> getAllList();
    
    public void delete(int id);
    
    public <E> void update(E elem);
    
    public <E> void insert(E elem);
}
