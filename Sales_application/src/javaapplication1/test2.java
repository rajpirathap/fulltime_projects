/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;



/**
 *
 * @author marwen
 */
public class test2 extends JFrame {

    JTextField txt1,txt2,DEFAULTFIELD;
    public  String[] arr=new String[100];
    JComboBox<String> combobox;
    public JPanel contentPane;
    public static final long serialVersionUID=1L;
    
    int i;
    
    jdbcConnect connectDB;
    
    java.sql.PreparedStatement ps;
    java.sql.Statement stmt;
    String SQL;
    
    public static void main(String[] args) {
        
        
        
       EventQueue.invokeLater(new Runnable(){
           public void run(){
               
               
               try{
                   test2 frame=new test2();
                   frame.setVisible(true);
               }
               catch(Exception e){
                   e.printStackTrace();
                   
               }
           }
       });
    }
    public test2(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,407,253);
        this.contentPane=new JPanel();
        this.contentPane.setAutoscrolls((true));
        this.contentPane.setBackground(Color.WHITE);
        this.contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        
        this.txt1=new JTextField("");
        this.txt1.setHorizontalAlignment((SwingConstants.CENTER));
        this.txt1.addCaretListener(new TextFieldCaretListener());
        this.txt1.setBounds(24, 17, 163, 34);
        this.contentPane.add(this.txt1);
        this.txt1.setColumns(10);
        
       
        
        this.txt2=new JTextField("");
        this.txt2.setHorizontalAlignment((SwingConstants.CENTER));
        this.txt2.addCaretListener(new TextFieldCaretListener());
        this.txt2.setBounds(24, 55, 163, 34);
        this.contentPane.add(this.txt2);
        this.txt2.setColumns(10);
        
        this.combobox=new JComboBox<String>();
        this.combobox.setFocusCycleRoot(true);
        this.combobox.setFocusTraversalPolicyProvider(true);
        this.combobox.setAutoscrolls(true);
        this.combobox.setBorder(null);
        this.combobox.setOpaque(false);
        this.combobox.addActionListener(new ComboBoxActionListener());
        
        
        
        
    }
    
    public class TextFieldCaretListener implements CaretListener{
        

        @Override
        public void caretUpdate(CaretEvent e) {
            
            
            Object txtObject=e.getSource();
        if(txtObject==txt1){
            
            getDataFromDB("dress_brand");
        }
  
            
            try{
               combobox.removeAllItems();
               combobox.hidePopup();
               contentPane.remove(combobox);
               Object text=e.getSource();
               if(text==txt1){
                   DEFAULTFIELD=txt1;
                   
               }
               if(text==txt2){
                   DEFAULTFIELD=txt2;
                   
               }
               
               if(e.getMark()>0){
                   
                   for(String string : arr){
                       
                       
                       if(string.toLowerCase().startsWith(DEFAULTFIELD.getText().toLowerCase()))
                         {
                           contentPane.add(combobox);
                           combobox.setBounds(24, DEFAULTFIELD.getBounds().y+34, 163, 28);
                           combobox.addItem(string);
                           combobox.showPopup();
                       }
                   }
               }
               
           }
           catch(Exception ee){
            ee.printStackTrace();
           }
           if(e.getMark()<2){
               contentPane.remove(combobox);
           }

        }
    }
    
    public class ComboBoxActionListener implements ActionListener{
    public void actionPerformed(ActionEvent ea){
        try{
            DEFAULTFIELD.setText(combobox.getSelectedItem().toString());
            combobox.removeAllItems();
            combobox.hidePopup();
            contentPane.remove(combobox);
            
        }
        catch(Exception aeee){
            
        }
    }
}
  
    public void getDataFromDB(String ss){
        
      try{
            
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
                 arr[y]= connectDB.rs.getString("dress_brand");
                 y++;
                 
              }
      }
      catch(Exception e){
          e.printStackTrace();
          
      }
    }
  
}