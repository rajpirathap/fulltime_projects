/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Indiran
 */
public class jdbcConnect {
    
 public   Connection con;
public PreparedStatement ps;
public static Statement st;
public ResultSet rs;
    
    public  void connectDB(){
        
        
        
         try
       	   {
           Class.forName("com.mysql.jdbc.Driver");
           con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/kandyan_inventory","root","root");
           }
      	  catch(Exception e)
           {
               System.out.println(e);
           JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
           System.exit(0);
           }
    }
    
}
