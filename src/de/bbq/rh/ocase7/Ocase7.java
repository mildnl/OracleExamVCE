/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bbq.rh.ocase7;

import de.bbq.rh.ocase7.database.MySQLConnection;

/**
 *
 * @author PaulsBook
 */
public class Ocase7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MySQLConnection mySQLDB = new MySQLConnection();
        Test t = new Test();
        Test test = t.getById(1);
        System.out.println(test.toString());
        t.update(new Test(2, "Test 1 3"));
        t.insert(new Test("Jeronimo"));
        t.getAllList().forEach((i) -> {
            System.out.println(i.toString());
        });
        t.delete(4);
        t.delete(5);
        t.getAllList().forEach((i) -> {
            System.out.println(i.toString());
        });
    }
    
}
