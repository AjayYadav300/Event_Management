/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author arpit
 */
public class DBConnect {
    private static Connection conn=null;
    public static Connection getConn()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
          conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydemo","root","root");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return conn;
    }
}
