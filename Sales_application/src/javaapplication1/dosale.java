/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static javaapplication1.addSaleTableView.mapKeyCounter;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Indiran
 */
public class dosale {
    
     public static HashMap<Integer,String> billdatas=new HashMap<Integer,String>();     
    
     java.sql.PreparedStatement ps;
     java.sql.Statement stmt; 
     String SQL; 
     jdbcConnect connectDB =new jdbcConnect();
     
    
     int systemCreatedDressID;
     String cusNameText,cusAddr1Text,cusAddr2Text,cusCityText,cusPincodeText;
    
    public void saleComplete(String s1,String s2,String s3,String s4,String s5){
        cusNameText=s1;
        cusAddr1Text=s2;
        cusAddr2Text=s3;
        cusCityText=s4;
        cusPincodeText=s5;
        
        addCustomer();
        getCustomerIdFromDb();
        addSales();
    }
    
  void  getCustomerIdFromDb(){
        
        try{
       
       connectDB.connectDB();
       stmt=connectDB.con.createStatement();
       SQL="SELECT * FROM kandiyan_customer  ORDER BY customer_id * 1";
       connectDB.rs=stmt.executeQuery(SQL);
       
       if(connectDB.rs.last()){
           
           systemCreatedDressID=Integer.parseInt(connectDB.rs.getString("customer_id"));
          
           
          // System.out.println(Integer.parseInt(connectDB.rs.getString("customer_id")));
           }
           
       
        else{
           systemCreatedDressID=0;
          
           
           }
       
       }
    catch(Exception e){
    e.printStackTrace();
    }
        
    }
  void addCustomer(){
      
      try{
         
          
          
                   connectDB.connectDB();
                   ps=connectDB.con.prepareStatement("insert into kandiyan_customer (customer_name,customer_address1,customer_address2,customer_city,customer_pincode) values(?,?,?,?,?)");
                   ps.setString(1,cusNameText);
                   ps.setString(2,cusAddr1Text);
                   ps.setString(3,cusAddr2Text);
                   ps.setString(4,cusCityText);
                   ps.setString(5,cusPincodeText);
                   ps.executeUpdate();
                   JOptionPane.showMessageDialog(null,"customer added","Customer Added",JOptionPane.INFORMATION_MESSAGE);
      }
      catch(Exception ee){
          ee.printStackTrace();
      }
  }
  void addSales(){
      String id=null;
      String pr=null;
      Integer mapKeyCounter2=0;
      Iterator it = salesData.hm.entrySet().iterator();
  try{
      
     String systemDate=getSystemDate();
      while (it.hasNext()) {
        
        
        Map.Entry pair = (Map.Entry)it.next();
        String[] saledatas=pair.getValue().toString().split(",");
       System.out.println(saledatas[2]);
                   connectDB.connectDB();
                   ps=connectDB.con.prepareStatement("insert into kandiyan_sales (customer_id,dress_id,price,amount,date) values(?,?,?,?,?)");
                   ps.setString(1,Integer.toString(systemCreatedDressID));
                   ps.setString(2,saledatas[0]);
                   ps.setString(3,saledatas[1]);
                   ps.setString(4,saledatas[2]);
                   ps.setString(5,systemDate);
                   
                   ps.executeUpdate();
        
                   billdatas.put(mapKeyCounter2, pair.getValue().toString());
                   mapKeyCounter2++;
        it.remove(); // avoids a ConcurrentModificationException
        
  }
     JOptionPane.showMessageDialog(null,"Sale completed","Success",JOptionPane.INFORMATION_MESSAGE); 
    }
        catch(Exception ae){
    ae.printStackTrace();
    }
  addSaleTableView.mapKeyCounter=0;
  deductFromInventory deduct=new deductFromInventory();
  deduct.deductInventory();
  generateBill bill=new generateBill(mapKeyCounter2);
  mapKeyCounter2=0;
}
public String getSystemDate(){
        
        Date date = null;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
          LocalDate localDate = LocalDate.now();
         
        
        return localDate.toString();
    }

}
