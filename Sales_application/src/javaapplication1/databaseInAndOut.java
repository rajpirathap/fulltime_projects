/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import javax.swing.JOptionPane;

/**
 *
 * @author Nithaparan
 */
public class databaseInAndOut {
    java.sql.PreparedStatement ps;
    java.sql.Statement stmt; 
    String SQL; 
    jdbcConnect connectDB=new jdbcConnect();
    double Price2;
    int Amount2;
    
    databaseInAndOut(){
         connectDB.connectDB();
    }
    public void addNewDressToDB(String Brand,int systemCreatedDressID,String Name,String Type,String Size,
                                String Sizenum,Double Price,int Amount,String Group,String Material){
         try
          {       
                   ps=connectDB.con.prepareStatement("insert into kandiyan_dress values(?,?,?,?,?,?,?,?,?,?)");
                   ps.setString(1,Brand);
                   ps.setString(2,Integer.toString(systemCreatedDressID+1));
                   ps.setString(3,Name);
                   ps.setString(4,Type);
                   ps.setString(5,Size);
                   ps.setString(6,Sizenum);
                   ps.setDouble(7,Price);
                   ps.setInt(8,Amount);
                   ps.setString(9,Group);
                   ps.setString(10,Material);
	           ps.executeUpdate();
                   JOptionPane.showMessageDialog(null,"Dress added successfully","Dress Added",JOptionPane.INFORMATION_MESSAGE);
 
                     System.out.println(Integer.toString(systemCreatedDressID+1));  
                     
                    

          }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean addNewPurchaseToDB(int systemCreatedDressID,String Supplier,Double Cost,
                                   int Amount ,String PurchaseDate){
       boolean status=false;
        try{
                   ps=connectDB.con.prepareStatement("insert into kandiyan_purchasehistory values(?,?,?,?,?)");
                   ps.setString(1,Integer.toString(systemCreatedDressID+1));
                   ps.setString(2,Supplier);
                   ps.setString(3,Double.toString(Cost));
                   ps.setString(4,Integer.toString(Amount));
                   ps.setString(5,PurchaseDate);
                   
	           ps.executeUpdate();
                   //JOptionPane.showMessageDialog(null,"Purchase hisadded successfully","Dress Added",JOptionPane.INFORMATION_MESSAGE);
                   status=true;  
         }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
        
    public void updateAmountIfExisting(int systemCreatedDressID,int Amount,String Supplier,Double Cost,String PurchaseDate){
         try
          {        
              
              ps= connectDB.con.prepareStatement("UPDATE kandiyan_dress SET dress_amount = ? "
                                              + " WHERE dress_id ='"+systemCreatedDressID +"'");
              ps.setString(1, Integer.toString(Amount));
              ps.executeUpdate();
              JOptionPane.showMessageDialog(null,"Amount Updated successfully","UPDATED",JOptionPane.INFORMATION_MESSAGE);   
                   

          }
        catch(Exception e){
            e.printStackTrace();
        }
       boolean ff=  addNewPurchaseToDB(systemCreatedDressID, Supplier, Cost, Amount, PurchaseDate);
    }
    public void updatePriceIfChanged(int systemCreatedDressID,Double Price){
         try
          {        
              
              ps= connectDB.con.prepareStatement("UPDATE kandiyan_dress SET dress_price = ? "
                                              + " WHERE dress_id ='"+systemCreatedDressID +"'");
              ps.setString(1, Double.toString(Price));
              ps.executeUpdate();
              JOptionPane.showMessageDialog(null,"Price Updated successfully","UPDATED",JOptionPane.INFORMATION_MESSAGE); 
                   
                   

          }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void checkPriceAndAssignAmount(String DB_Dress_Price,Double Price,int Amount ,String DB_Dress_Amount,int systemCreatedDressID,String Supplier,Double Cost,String PurchaseDate){
            if(Double.parseDouble(DB_Dress_Price)==Price){
                Amount2=Amount+Integer.parseInt(DB_Dress_Amount);
                updateAmountIfExisting(systemCreatedDressID,Amount2, Supplier, Cost, PurchaseDate);
            }
            else{
                Amount2=Amount+Integer.parseInt(DB_Dress_Amount);
               int g =  JOptionPane.showConfirmDialog(null, "Price You entered Not matching with existing price the old price is " +
                                                             DB_Dress_Price+" the new price is "+Price+"Do you want update the price","WARNING", JOptionPane.YES_NO_OPTION);
               if(g==0){
                   updatePriceIfChanged(systemCreatedDressID,Price);
                   updateAmountIfExisting(systemCreatedDressID,Amount2,Supplier, Cost, PurchaseDate);
               }
               else{
                   Price2=Double.parseDouble(DB_Dress_Price);
                   updateAmountIfExisting(systemCreatedDressID,Amount2,Supplier, Cost, PurchaseDate);
               }
               
            }
        } 
}

