package de.bbq.rh.OracleExamVCE.database;

import java.util.ArrayList;

/**
 *
 * @author mildnl
 */
public interface IMySQLDatabaseDAO {
    
//    String URL = "jdbc:mysql://178.162.194.27:3306/lmildner_JavaCertificates";
//    String USER = "lmildner_user";
//    String PASSWORD = ";+xNeDXt3c63";

    String URL = "jdbc:mysql://localhost:3306/lmildner_JavaCertificates";
    String USER = "lmildner_user";
    String PASSWORD = ";+xNeDXt3c63";
    
    public <E extends Object> E getById(E elem, int id);
    
    public ArrayList<?> getAllList();
    
    public void delete(int id);
    
    public <E> void update(E elem);
    
    public <E> void insert(E elem);
}
