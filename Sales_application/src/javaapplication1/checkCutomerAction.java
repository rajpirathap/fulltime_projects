/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Nithaparan
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Indiran
 */
public class checkCutomerAction extends JFrame implements ActionListener {
    public String cunstomerId;
    public String coustomerName;
    public String cunstomerAddress1;
    public String cunstomerAddress2;
    public String cunstomerCity;
    public String pincode; //not defined clearly in the proposal
      
    
     JLabel cusId,cusName,cusAddr1,cusAddr2,cusCity,cusPincode;
     JTextField cusIdText,cusNameText,cusAddr1Text,cusAddr2Text,cusCityText,cusPincodeText;
     
     JButton addCustomer,updateCustomer,viewCustomer;
    
    public checkCutomerAction(){
           
        
            setVisible(true);
            Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();//to get the screen size
            setLayout(null);
            setResizable(false);
            
    
               
                
                addCustomer= new JButton("Add");
                add(addCustomer);
                addCustomer.setBounds(50, 50, 100, 60);
                addCustomer.addActionListener(this);
                
                updateCustomer= new JButton("Update");
                add(updateCustomer);
                updateCustomer.setBounds(170, 50, 100, 60);
                
                viewCustomer= new JButton("View");
                add(viewCustomer);
                viewCustomer.setBounds(290, 50, 100, 60);
        
    }    
    
    public void addCustomer(){
        customer cusAdd=new customer();
        this.hide();
        cusAdd.show();
        cusAdd.setBounds(250, 50, 500, 400);
        cusAdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        
    }
    public void updateCustomer(){
        
    }
    public void viewCustomer(){
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object cus=ae.getSource();
        if(cus==addCustomer){
            addCustomer();
        }
        if(cus==updateCustomer){
            updateCustomer();
        }
        if(cus==viewCustomer){
            viewCustomer();
        }
    }
    
   
    
}
