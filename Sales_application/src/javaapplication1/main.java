/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import org.omg.SendingContext.RunTime;

public class main extends JFrame implements ActionListener{
    JMenuItem MenuItem1,MenuItem2,MenuItem3,MenuItem4,MenuItem5,MenuItem6,MenuItem7,MenuItem8,
              MenuItem9,MenuItem10,MenuItem11,MenuItem12,MenuItem13,MenuItem14,MenuItem15,MenuItem16;
    JMenu     fileMenu,viewMenu,toolMenu,controlMenu,help,logout,exit;
    
    private int ScreenWidth ;
    private int ScreenHeight;
    
    Object[][] tableData;
    Object[] coloumnName,rowName;
    JButton addDress,purchaseHistory,sale;
    java.sql.PreparedStatement ps;
     java.sql.Statement stmt; 
     String SQL; 
     jdbcConnect connectDB;
    
    JPanel left,right;
    
    mainFunctionDef functionDef=new mainFunctionDef();
    
    
    public main(boolean is_admin){
       super("Abisheka Mandapaya");

        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();//to get the screen size
        ScreenWidth=screensize.width;
        ScreenHeight=screensize.height;
        setLayout(null);
        setSize (screensize); 
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //add menubar to Jframe
        menuBar menu=new menuBar(this);
        setJMenuBar(menu.menuBar);
        
        

        JPanel right = new JPanel();
        right.setSize(ScreenWidth-200, ScreenHeight);
        right.setBackground(Color.WHITE);
        right.setVisible(true);

        left = new JPanel();
        left.setSize(200, ScreenHeight);
        left.setBackground(Color.getHSBColor(100, 150, 10));
        left.setVisible(true);
        try {
            addCompornent(is_admin);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        JSplitPane splitPane = new JSplitPane();
        splitPane.setSize(ScreenWidth, ScreenHeight);
        splitPane.setDividerSize(4);
        splitPane.setDividerLocation(150);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(left);
        splitPane.setRightComponent(right);

        this.add(splitPane);
    
    
    
    
            
     
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object menuObject=ae.getSource();
        if(menuObject==addDress){
            addDress add=new addDress();
            add.show();
            add.setBounds(250, 150, 450, 450);
            add.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        if(menuObject==purchaseHistory){
            tableViewDataParser_1 parser=new tableViewDataParser_1();
        parser.dataParser("SELECT * FROM kandiyan_purchasehistory");
        }
        if(menuObject==sale){
            sales sale=new sales();
       sale.show();
       
        }
        
       
    }
     void addCompornent(boolean is_admin) throws IOException{
                 Dimension d = new Dimension();
                 d.height =80;
                 d.width = 100;
                 ImageIcon img_icon1 = new ImageIcon("C:\\Users\\Compaq\\Desktop\\kandyan\\kandiyan\\third-6-2-2017\\JavaApplication1\\src\\javaapplication1\\img_icon1.jpg");
                 ImageIcon img_icon2 = new ImageIcon("C:\\Users\\Compaq\\Desktop\\kandyan\\kandiyan\\third-6-2-2017\\JavaApplication1\\src\\javaapplication1\\img_icon2.jpg");
                 ImageIcon img_icon3 = new ImageIcon("C:\\Users\\Compaq\\Desktop\\kandyan\\kandiyan\\third-6-2-2017\\JavaApplication1\\src\\javaapplication1\\img_icon3.jpg");
                 
                 
              
                 addDress = new JButton("Add Dress");
                 addDress.setIcon(img_icon1);
                 addDress.setPreferredSize(d);
		 //addDress.setBounds(60, 40, 50, 50);
		 add(addDress);
                 addDress.addActionListener(this);
                 if(!is_admin){
                     addDress.setEnabled(is_admin);
                 }
                     
                 
                 purchaseHistory = new JButton("Purchase History");
		 //purchaseHistory.setBounds(60, 80, 80, 25);
                 purchaseHistory.setIcon(img_icon2);
                 purchaseHistory.setPreferredSize(d);
		 add(purchaseHistory);
                 purchaseHistory.addActionListener(this);
                 
                 
                 sale = new JButton("Sales");
                 sale.setIcon(img_icon3);
                 sale.setPreferredSize(d);
		 //sale.setBounds(30, 120, 80, 25);
		 add(sale);
                 sale.addActionListener(this);
                 
                 
         
         left.add(addDress);
         left.add(purchaseHistory);
         left.add(sale);
         
         
     }
    
     public void onLogout() {
       
        this.dispose();
        userLogin2 login=new userLogin2();
        login.show();
        }
}