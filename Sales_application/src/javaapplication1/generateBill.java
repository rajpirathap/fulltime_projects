/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Iterator;
import java.util.Map;
import static javaapplication1.dosale.billdatas;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Indiran
 */
public class generateBill extends JFrame {
    JLabel[] label,amountlbl,pricelbl;
    JLabel title,line,item,qty,pri,tot1,tot2;
    
    java.sql.PreparedStatement ps;
     java.sql.Statement stmt; 
     String SQL; 
     jdbcConnect connectDB =new jdbcConnect();
     
     double total=0.0;
    
     int i=0;
     
   public generateBill(Integer x){
       
        setSize(300,500);
        setVisible(true);
        setLayout(null);
        
         label=new JLabel[x];
         amountlbl=new JLabel[x];
         pricelbl=new JLabel[x];
        title=new JLabel("Bill");
        line=new JLabel("-----------------------");
        
        item =new JLabel("Item");
        qty =new JLabel("Quantity");
        pri =new JLabel("Price");
        tot1 =new JLabel("Total");
        
        Iterator it = dosale.billdatas.entrySet().iterator();
  try{
      while (it.hasNext()) {
        
        
                Map.Entry pair = (Map.Entry)it.next();
                String[] saledatas=pair.getValue().toString().split(",");
            //    System.out.println(saledatas[0]+saledatas[1]+saledatas[2]);
                connectDB.connectDB();
                    stmt=connectDB.con.createStatement();
       SQL="SELECT dress_name FROM kandiyan_dress WHERE dress_id='"+saledatas[0]+"'";
       connectDB.rs=stmt.executeQuery(SQL);
       
              
       
              
              title.setBounds(50,50, 100, 10);
              line.setBounds(50,65, 200, 10);
              item.setBounds(50,80, 100, 10);
              qty.setBounds(110,80, 100, 10);
              pri.setBounds(220,80, 100, 10);
              tot1.setBounds(50, 0, 100, 10);
              
              add(title);
              add(line);
              add(item);
              add(qty);
              add(pri);
       
       
                  while(connectDB.rs.next()){
                   label[i]=new JLabel(connectDB.rs.getString("dress_name"));
                  }
                  label[i].setBounds(50, 50+((i+1)*45), 100, 10);
                  add(label[i]);
                  
                  amountlbl[i]=new JLabel(saledatas[2]);
                  amountlbl[i].setBounds(110,50+((i+1)*45), 100, 10);
                  add(amountlbl[i]);
                  
                  pricelbl[i]=new JLabel(saledatas[1]);
                  pricelbl[i].setBounds(220,50+((i+1)*45), 100, 10);
                  add(pricelbl[i]);
                   tot1.setBounds(50, label[i].getBounds().y+40, 100, 10);
                   add(tot1);
                  total=total+Double.parseDouble(amountlbl[i].getText())*Double.parseDouble(pricelbl[i].getText());
                   tot2 =new JLabel(Double.toString(total));
                  tot2.setBounds(220, label[i].getBounds().y+40, 100, 10);
                  
                  
                   i++;
        it.remove(); // avoids a ConcurrentModificationException
        
  }
        
        
        
    }
    catch(Exception ee){
        ee.printStackTrace();
        
    }
  add(tot2);
  System.out.println(i);
}
}