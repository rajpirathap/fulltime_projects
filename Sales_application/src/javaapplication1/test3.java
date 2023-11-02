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
public class test3 extends JFrame {

    JTextField txt1;
    public static String[] arr={"asd","ser"};
    JComboBox<String> combobox;
    public JPanel contentPane;
    public static final long serialVersionUID=1L;
    
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
    public test3(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,407,253);
        this.contentPane=new JPanel();
        this.contentPane.setAutoscrolls((true));
        this.contentPane.setBackground(Color.WHITE);
        this.contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        
        this.txt1=new JTextField();
        this.txt1.setHorizontalAlignment((SwingConstants.CENTER));
        this.txt1.setText("Name");
        this.txt1.addCaretListener(new TextFieldCaretListener());
        this.txt1.setBounds(24, 17, 163, 34);
        this.contentPane.add(this.txt1);
        this.txt1.setColumns(10);
        
        this.combobox=new JComboBox<String>();
        this.combobox.setFocusCycleRoot(true);
        this.combobox.setFocusTraversalPolicyProvider(true);
        this.combobox.setAutoscrolls(true);
        this.combobox.setBorder(null);
        this.combobox.setOpaque(false);
        this.combobox.addActionListener(new ComboBoxActionListener());
        this.combobox.setBounds(24, 51, 163, 28);
        
        
    }
    
    public class TextFieldCaretListener implements CaretListener{
        

        @Override
        public void caretUpdate(CaretEvent e) {
           
            
            
            try{
               combobox.removeAllItems();
               combobox.hidePopup();
               contentPane.remove(combobox);
               
               if(e.getMark()>0){
                   for(String string : arr){
                       if(string.toLowerCase().startsWith(txt1.getText().toLowerCase())){
                           contentPane.add(combobox);
                           combobox.addItem(string);
                           combobox.showPopup();
                       }
                   }
               }
           }
           catch(Exception ee){
               
           }
           if(e.getMark()<2){
               contentPane.remove(combobox);
           }

        }
    }
    
    public class ComboBoxActionListener implements ActionListener{
    public void actionPerformed(ActionEvent ea){
        try{
            txt1.setText(combobox.getSelectedItem().toString());
            combobox.removeAllItems();
            combobox.hidePopup();
            contentPane.remove(combobox);
            
        }
        catch(Exception aeee){
            
        }
    }
}
    
}