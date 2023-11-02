/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Indiran
 */
public class sales extends JFrame implements ActionListener{
    JPanel contentPane;
    JLabel salesItem,salesItem2 ;
    JTextField DEFAULTFIELD,salesItemText,TypeText;
    JButton addItem,addItem2,addItem3;
    
    java.sql.PreparedStatement ps;
     java.sql.Statement stmt; 
     String SQL; 
     jdbcConnect connectDB;
     JButton addDress;
     
     
     //customer
     public String cunstomerId;
    public String coustomerName;
    public String cunstomerAddress1;
    public String cunstomerAddress2;
    public String cunstomerCity;
    public String pincode; //not defined clearly in the proposal
    JLabel cusId,cusName,cusAddr1,cusAddr2,cusCity,cusPincode;
     JTextField cusIdText,cusNameText,cusAddr1Text,cusAddr2Text,cusCityText,cusPincodeText;
     
     JButton finish=new JButton("Finish");
     public  String[] arr=new String[100];
     
      JComboBox<String> suggestCombobox;
    
    public sales(){
        setVisible(true);
        
        setBounds(100,100,500,500);
        
        
        
        this.contentPane=new JPanel();
        this.contentPane.setAutoscrolls((true));
        this.contentPane.setBackground(Color.WHITE);
        this.contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        
        
        
        cusId = new JLabel("Customer ID");
//		cusId.setBounds(10, 10, 180, 25);
//		contentPane.add(cusId);
//
//		cusIdText = new JTextField();
//		cusIdText.setBounds(200, 10, 160, 25);
//		contentPane.add(cusIdText);
                
                cusName = new JLabel("Customer Name");
		cusName.setBounds(10, 40, 180, 25);
		contentPane.add(cusName);

		cusNameText = new JTextField();
		cusNameText.setBounds(200, 40, 160, 25);
		contentPane.add(cusNameText);
                
                cusAddr1 = new JLabel("Customer Address line 1");
		cusAddr1.setBounds(10, 70, 180, 25);
		contentPane.add(cusAddr1);

		cusAddr1Text = new JTextField();
		cusAddr1Text.setBounds(200, 70, 160, 25);
		contentPane.add(cusAddr1Text);
                
                cusAddr2 = new JLabel("Customer Address line 2");
		cusAddr2.setBounds(10, 100, 180, 25);
		contentPane.add(cusAddr2);

		cusAddr2Text = new JTextField();
		cusAddr2Text.setBounds(200, 100, 160, 25);
		contentPane.add(cusAddr2Text);
                
                cusCity = new JLabel("Customer City");
		cusCity.setBounds(10, 130, 180, 25);
		contentPane.add(cusCity);

		cusCityText = new JTextField();
		cusCityText.setBounds(200, 130, 160, 25);
		contentPane.add(cusCityText);
                
                cusPincode = new JLabel("Rep Number");
		cusPincode.setBounds(10, 160, 180, 25);
		contentPane.add(cusPincode);

		cusPincodeText = new JTextField();
		cusPincodeText.setBounds(200, 160, 160, 25);
		contentPane.add(cusPincodeText);
    
                salesItem = new JLabel("Item Type");
		salesItem.setBounds(10, 190, 180, 25);
		contentPane.add(salesItem);
                

		TypeText = new JTextField();
		TypeText.setBounds(200, 190, 160, 25);
                TypeText.addCaretListener(new TextFieldCaretListener());
		contentPane.add(TypeText);
                
                salesItem2 = new JLabel("Item");
		salesItem2.setBounds(10, 220, 180, 25);
		contentPane.add(salesItem2);
                

		addItem2 = new JButton("Add Item");
		addItem2.setBounds(200, 220, 160, 25);
		contentPane.add(addItem2);
                addItem2.addActionListener(this);
                
                finish = new JButton("FINISH");
		finish.setBounds(200, 250, 160, 25);
		contentPane.add(finish);
                finish.addActionListener(this);
                
                
                this.suggestCombobox=new JComboBox<String>();
                this.suggestCombobox.setFocusCycleRoot(true);
                this.suggestCombobox.setFocusTraversalPolicyProvider(true);
                this.suggestCombobox.setAutoscrolls(true);
                this.suggestCombobox.setBorder(null);
                this.suggestCombobox.setOpaque(false);
                this.suggestCombobox.addActionListener(new ComboBoxActionListener());
                
               contentPane.add(suggestCombobox);
                
            
    }
    public void actionPerformed(ActionEvent e) {
              Object btnAction=e.getSource();
              if (btnAction==addItem2){
                  tableViewDataParser2 parser=new tableViewDataParser2();
                 
                  parser.dataParser("SELECT * FROM kandiyan_dress WHERE dress_type='"+TypeText.getText()+"'");
              }
             else if (btnAction==finish){
                  dosale sale=new dosale();
                  sale.saleComplete(cusNameText.getText(),cusAddr1Text.getText(),cusAddr2Text.getText(),cusCityText.getText(),cusPincodeText.getText());
              }
    }
    
    
    public class TextFieldCaretListener implements CaretListener{
        

        @Override
        public void caretUpdate(CaretEvent e) {
            
            
            Object txtObject=e.getSource();
        if(txtObject==TypeText){
           getDataFromDB("dress_Type");
        }
        
       
  
            
            try{
                
                
               suggestCombobox.removeAllItems();
               suggestCombobox.hidePopup();
               contentPane.remove(suggestCombobox);
               Object text=e.getSource();
               if(text==TypeText){
                   DEFAULTFIELD=TypeText;
                }
               
              
               
               if(e.getMark()>0){
                   
                   for(String string : arr){
                       
                       
                       if(string.toLowerCase().startsWith(DEFAULTFIELD.getText().toLowerCase()))
                         {
                           contentPane.add(suggestCombobox);
                           suggestCombobox.setBounds(200, DEFAULTFIELD.getBounds().y, 160, 25);
                           suggestCombobox.addItem(string);
                           suggestCombobox.showPopup();
                         
                              
                           
                           
                       
                   }
               }
               
           }
            }
           catch(Exception ee){
            ee.printStackTrace();
           }
           if(e.getMark()<0){
              contentPane.remove(suggestCombobox);
           }

        }
    }
    
       public class ComboBoxActionListener implements ActionListener{
    public void actionPerformed(ActionEvent ea){
        try{
            DEFAULTFIELD.setText(suggestCombobox.getSelectedItem().toString());
            suggestCombobox.removeAllItems();
            suggestCombobox.hidePopup();
            contentPane.remove(suggestCombobox);
            
        }
        catch(Exception aeee){
            
        }
    }
}
  
       public void getDataFromDB(String ss){
        
      try{
            int i;
            connectDB=new jdbcConnect();
            connectDB.connectDB();
            stmt=connectDB.con.createStatement();
            SQL="SELECT * FROM kandiyan_dress";
            connectDB.rs=stmt.executeQuery(SQL);
            connectDB.rs.last(); 
            i = connectDB.rs.getRow();
            arr = new String[i];
            int y=0;
            connectDB.rs.beforeFirst();
             while(connectDB.rs.next()){
                 arr[y]= connectDB.rs.getString(ss);
                 y++;
                 
              }
      }
      catch(Exception e){
          e.printStackTrace();
          
      }
    }
  

    
    
    
    

public static void main(String args[]){
        sales ff=new sales();
        ff.show();
        ff.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}