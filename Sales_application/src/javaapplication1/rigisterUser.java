/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Nithaparan
 */
public class rigisterUser extends JFrame  implements ActionListener{
    JLabel user,pass;
    JTextField username;
    JPasswordField password;
    JButton register,register2;
    java.sql.PreparedStatement ps;
    java.sql.Statement stmt; 
    String SQL; 
    jdbcConnect connectDB=new jdbcConnect();
    
    
    public  rigisterUser(){
        
        super("Registration Form");
        HelpUtil.centreWindow(this);
        
        connectDB.connectDB();
                user = new JLabel("User Name");
		user.setBounds(10, 10, 180, 25);
		add(user);
                

		username = new JTextField();
		username.setBounds(200, 10, 160, 25);
		add(username);
                
                
                pass = new JLabel("Password");
		pass.setBounds(10, 40, 180, 25);
		add(pass);

		password = new JPasswordField("");
		password.setBounds(200, 40, 160, 25);
	        add(password);
                
                 register = new JButton("Register");
		register.setBounds(200, 70, 100, 25);
		add(register);
                register.addActionListener(this);
                
                register2 = new JButton("Register");
		register2.setBounds(200, 110, 80, 25);
		add(register2);
                register2.addActionListener(this);
                   
                
                setVisible(true);
            
            setLayout(null);
            //setSize (200,200); 
            setResizable(true);
    
    }
    
    public void doRegister(){
        try{
        ps=connectDB.con.prepareStatement("insert into kandiyan_user values(?,?,?)");
                   ps.setString(1,username.getText());
                   ps.setString(2,password.getText());
                   ps.setBoolean(3,false);
                   ps.executeUpdate();
                   JOptionPane.showMessageDialog(null,"User added successfully","Dress Added",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
            e.printStackTrace();
        }
     }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object obj=ae.getSource();
    if(obj==register)
     {
       doRegister();
     }    }
}
