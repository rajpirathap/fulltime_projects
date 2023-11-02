/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Indiran
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.control.CheckBox;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class userLogin2 extends JFrame implements ActionListener{
    
   JTextField userText;
   JPasswordField passwordText;
   String password="";
   JButton loginButton,registerButton;
   JLabel title;
   JCheckBox admincheck;
JPanel contentPane;
public static boolean is_admin = false;
    
     public userLogin2(){
    //setBackground(Color.getHSBColor(100, 150, 10));
		//ImageIcon image = new ImageIcon(this.getClass().getResource("/JavaApplication1/Images/img_1.jpg"));
		//setContentPane(new JLabel(image));
setContentPane(new JLabel(new ImageIcon("C://Users//Compaq//Desktop//kandyan//kandiyan//third-6-2-2017//JavaApplication1//Resources//img_2.jpg")));

	   title=new JLabel("Abisheka Mandapaya");
                title.setFont(new Font("Papyrus", Font.ITALIC, 50));
                title.setBounds(360, 10, 800, 200);
                add(title);
                
            setVisible(true);
            Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();//to get the screen size
            setLayout(null);
            setSize (screensize); 
            setResizable(false);
               

             this.contentPane=new JPanel();
        this.contentPane.setAutoscrolls((true));
        this.contentPane.setBackground(Color.WHITE);
        //this.contentPane.setBorder(new EmptyBorder(5,5,5,5));
        //setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
            
            
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		contentPane.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		contentPane.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		contentPane.add(passwordLabel);

		 passwordText = new JPasswordField("");
		passwordText.setBounds(100, 40, 160, 25);
		contentPane.add(passwordText);
                
                admincheck = new JCheckBox("admin");
                admincheck.setBounds(5, 80, 80, 25);
                contentPane.add(admincheck);
                admincheck.addActionListener(this);
                  
		 loginButton = new JButton("login");
		loginButton.setBounds(85, 80, 80, 25);
		contentPane.add(loginButton);
                loginButton.addActionListener(this);
		
		 registerButton = new JButton("register");
		registerButton.setBounds(180, 80, 80, 25);
		contentPane.add(registerButton);
                registerButton.addActionListener(this);
  

              contentPane.setBounds(500, 580, 300, 110);
              add(contentPane);
              contentPane.setVisible(true);
    
    
    }

     
      @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
    if(obj==loginButton)
     {
       doLogin();
     }
    else if(obj==registerButton)
     {
       rigisterUser rigister=new rigisterUser();
       
       rigister.setVisible(true);
       rigister.setSize(400, 250);

       //rigister.setBounds(100, 100, 400, 250);
       rigister.show();
       
     }
    }
    
   public void  doLogin(){
     
   jdbcConnect connectObj=new jdbcConnect();
   connectObj.connectDB();
   int isadmin = 0;
   boolean is_admin = admincheck.isSelected();
   if (is_admin == true){
       isadmin = 1;
   }
       
   
       try
        {
           java.sql.Statement stmt=connectObj.con.createStatement();
           String SQL="SELECT * FROM kandiyan_user WHERE kandiyan_username='"+userText.getText()+"' AND is_admin='"+isadmin + "'";
           connectObj.rs=stmt.executeQuery(SQL);
           if(connectObj.rs.next()){
           password=connectObj.rs.getString("kandiyan_password");
           }
          
           
        }
        catch(Exception e)
        {
            //System.out.println(e);
        }
       
       
       
       
       
       
        if (password.equals(passwordText.getText())){
            userLogin2.is_admin = admincheck.isSelected();
            main ob=new main(userLogin2.is_admin);
            
            this.hide();
            ob.show();
            
            
            }
        else{
                    JOptionPane.showMessageDialog( null, "Login details are wrong", "ERROR", JOptionPane.ERROR_MESSAGE);
                     
                    }

   }
   
     
     
   
    

  public static void main(final String args[]) {
      
      userLogin2 loginObject= new userLogin2();
      loginObject.show();
      loginObject.setDefaultCloseOperation(EXIT_ON_CLOSE);
      
    
    
   
   
    

    
  }

   
  
  
  
}