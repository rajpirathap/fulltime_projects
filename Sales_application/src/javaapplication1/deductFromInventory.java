/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Indiran
 */
public class deductFromInventory {
    
     java.sql.PreparedStatement ps;
     java.sql.Statement stmt; 
     String SQL; 
     jdbcConnect connectDB =new jdbcConnect();
    
     String[] amt=new String[100];
     String[] prc=new String[100];
     String[] prid=new String[100];;
     
    public void deductInventory(){
         Iterator it = dosale.billdatas.entrySet().iterator();
  int i=0;
      while (it.hasNext()) {
        
        
                Map.Entry pair = (Map.Entry)it.next();
                String[] saledatas=pair.getValue().toString().split(",");
                amt[i]=saledatas[2];
               // prc[i]=saledatas[1];
                prid[i]=saledatas[0];
                getSomeDataFromDatabase(prid[i],amt[i]);
    }
    i=0;
    }
    
    public void getSomeDataFromDatabase(String id,String am){
    String amt="";
        
        try{
            connectDB.connectDB();
     stmt=connectDB.con.createStatement();
       SQL="SELECT dress_id,dress_amount FROM kandiyan_dress  where dress_id='"+id+"'";
       connectDB.rs=stmt.executeQuery(SQL);
       if(connectDB.rs.last()){
           
           
           amt=connectDB.rs.getString("dress_amount");
           
           
          
           }
           amt=Integer.toString((Integer.parseInt(amt)-Integer.parseInt(am)));
       ps= connectDB.con.prepareStatement("UPDATE kandiyan_dress SET dress_amount = ? "
                                              + " WHERE dress_id ='"+id +"'");
              ps.setString(1,amt );
              ps.executeUpdate();
       
       }
    catch(Exception e){
    e.printStackTrace();
    }
 }
}
