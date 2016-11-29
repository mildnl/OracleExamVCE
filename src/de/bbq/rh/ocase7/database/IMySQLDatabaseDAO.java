/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public Object getById(int id);
    
    public ArrayList<?> getAllList();
    
    public void delete(int id);
    
//    public void update();
//    
//    public void insert();
}
