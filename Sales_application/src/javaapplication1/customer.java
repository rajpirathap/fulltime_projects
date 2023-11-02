/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Indiran
 */
public class customer extends JFrame {
    public String cunstomerId;
    public String coustomerName;
    public String cunstomerAddress1;
    public String cunstomerAddress2;
    public String cunstomerCity;
    public String pincode; //not defined clearly in the proposal
      
    
     JLabel cusId,cusName,cusAddr1,cusAddr2,cusCity,cusPincode;
     JTextField cusIdText,cusNameText,cusAddr1Text,cusAddr2Text,cusCityText,cusPincodeText;
     
     JButton addCustomer;
    
    public customer(){
            setVisible(true);
            
            Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();//to get the screen size
            setLayout(null);
            setSize (screensize); 
            setResizable(false);
    
                cusId = new JLabel("Customer ID");
		cusId.setBounds(10, 10, 180, 25);
		add(cusId);

		cusIdText = new JTextField();
		cusIdText.setBounds(200, 10, 160, 25);
		add(cusIdText);
                
                cusName = new JLabel("Customer Name");
		cusName.setBounds(10, 40, 180, 25);
		add(cusName);

		cusNameText = new JTextField();
		cusNameText.setBounds(200, 40, 160, 25);
		add(cusNameText);
                
                cusAddr1 = new JLabel("Customer Address line 1");
		cusAddr1.setBounds(10, 70, 180, 25);
		add(cusAddr1);

		cusAddr1Text = new JTextField();
		cusAddr1Text.setBounds(200, 70, 160, 25);
		add(cusAddr1Text);
                
                cusAddr2 = new JLabel("Customer Address line 2");
		cusAddr2.setBounds(10, 100, 180, 25);
		add(cusAddr2);

		cusAddr2Text = new JTextField();
		cusAddr2Text.setBounds(200, 100, 160, 25);
		add(cusAddr2Text);
                
                cusCity = new JLabel("Customer City");
		cusCity.setBounds(10, 130, 180, 25);
		add(cusCity);

		cusCityText = new JTextField();
		cusCityText.setBounds(200, 130, 160, 25);
		add(cusCityText);
                
                cusPincode = new JLabel("Pin Code");
		cusPincode.setBounds(10, 160, 180, 25);
		add(cusPincode);

		cusPincodeText = new JTextField();
		cusPincodeText.setBounds(200, 160, 160, 25);
		add(cusPincodeText);
                
                addCustomer= new JButton("Add");
                addCustomer.setBounds(200,190,70,40);
                add(addCustomer);
                
        
    }    
    
    public void addCustomer(){
        
    }
    public void updateCustomer(){
        
    }
    public void getCustomerDetails(){
        
    }
    
    
    
}
