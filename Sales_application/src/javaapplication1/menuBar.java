/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSetMetaData;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import org.omg.SendingContext.RunTime;

public class menuBar  implements ActionListener{
    JMenuItem MenuItem1,MenuItem2,MenuItem3,MenuItem4,MenuItem5,MenuItem6,MenuItem7,MenuItem8,
              MenuItem9,MenuItem10,MenuItem11,MenuItem12,MenuItem13,MenuItem14,MenuItem15,MenuItem16;
    JMenu     fileMenu,viewMenu,toolMenu,controlMenu,help,logout,exit;
    
    JMenuBar menuBar;
    Object[] coloumnName,rowName;
    jdbcConnect connectDB;
    java.sql.PreparedStatement ps;
    java.sql.Statement stmt; 
    String SQL; 
    
    Object[][] tableData;
    
    mainFunctionDef functionDef=new mainFunctionDef();
    JFrame mainFrame;
    
    
    
    public menuBar(JFrame main){
    this.mainFrame =  main;

    menuBar = new JMenuBar();

    // ..
    fileMenu = new JMenu("File");
    viewMenu = new JMenu("View");
    toolMenu = new JMenu("Tools");
    controlMenu = new JMenu("Master Control");
    help = new JMenu("Help");
    logout = new JMenu("Logout");
    exit = new JMenu("Exit");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    viewMenu.setMnemonic(KeyEvent.VK_F);
    toolMenu.setMnemonic(KeyEvent.VK_F);
    controlMenu.setMnemonic(KeyEvent.VK_F);
    help.setMnemonic(KeyEvent.VK_F);
    logout.setMnemonic(KeyEvent.VK_F);
    exit.setMnemonic(KeyEvent.VK_F);
    menuBar.add(fileMenu);
    menuBar.add(viewMenu);
    menuBar.add(toolMenu);
    menuBar.add(controlMenu);
    menuBar.add(help);
    menuBar.add(logout);
    menuBar.add(exit);
    
    

    //file menu items
    MenuItem1 = new JMenuItem("Transection Screen", KeyEvent.VK_N);
    //fileMenu.add(MenuItem1);
    MenuItem1.addActionListener(this);
    
    MenuItem2 = new JMenuItem("Reports", KeyEvent.VK_N);
    //fileMenu.add(MenuItem2);
    MenuItem2.addActionListener(this);
    
    MenuItem3 = new JMenuItem("Exit", KeyEvent.VK_N);
    fileMenu.add(MenuItem3);
    MenuItem3.addActionListener(this);
    
    
    //view menu items
    MenuItem4 = new JMenuItem("Toolbar", KeyEvent.VK_N);
    viewMenu.add(MenuItem4);
    MenuItem4.addActionListener(this);
    
    JMenuItem MenuItem5 = new JMenuItem("Statusbar", KeyEvent.VK_N);
    viewMenu.add(MenuItem5);
    MenuItem5.addActionListener(this);
    
    //tool menu items
    MenuItem6 = new JMenuItem("Calculator", KeyEvent.VK_N);
    toolMenu.add(MenuItem6);
    MenuItem6.addActionListener(this);
    
    MenuItem7 = new JMenuItem("Notepad", KeyEvent.VK_N);
    toolMenu.add(MenuItem7);
    MenuItem7.addActionListener(this);
    
    //master control menu items
    MenuItem8 = new JMenuItem("Customer", KeyEvent.VK_N);
    //controlMenu.add(MenuItem8);
    MenuItem8.addActionListener(this);
    
    MenuItem9 = new JMenuItem("Dress Details", KeyEvent.VK_N);
    controlMenu.add(MenuItem9);
    MenuItem9.addActionListener(this);
    
    MenuItem10 = new JMenuItem("Materials", KeyEvent.VK_N);
    controlMenu.add(MenuItem10);
    MenuItem10.addActionListener(this);
    
    MenuItem11 = new JMenuItem("State", KeyEvent.VK_N);
    controlMenu.add(MenuItem11);
    MenuItem11.addActionListener(this);
    
    // help menu items
    MenuItem12 = new JMenuItem("System Requirements", KeyEvent.VK_N);
    help.add(MenuItem12);
    MenuItem12.addActionListener(this);
    
    MenuItem13 = new JMenuItem("About", KeyEvent.VK_N);
    help.add(MenuItem13);
    MenuItem13.addActionListener(this);
    
    MenuItem14 = new JMenuItem("Projects Code Report", KeyEvent.VK_N);
    help.add(MenuItem14);
    MenuItem14.addActionListener(this);
    
    //logout menu item
    MenuItem15 = new JMenuItem("Logout", KeyEvent.VK_N);
    logout.add(MenuItem15);
    MenuItem15.addActionListener(this);
    
    //exit menu items
    MenuItem16 = new JMenuItem("Exit", KeyEvent.VK_N);
    exit.add(MenuItem16);
    MenuItem16.addActionListener(this);
    
    
    
   
    
    

            //setJMenuBar(menuBar);
           
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object menuObject=ae.getSource();
        
        if(menuObject==MenuItem1){ 
            onTransactionScreen();
        }
        if(menuObject==MenuItem2){ 
            onReport();
        }
        if(menuObject==MenuItem3){ 
            onExit();
        }
        if(menuObject==MenuItem4){ 
            onToolbar();
        }
        if(menuObject==MenuItem5){ 
            onStatusbar();
        }
        if(menuObject==MenuItem6){ 
            onCalulator();
        }
        if(menuObject==MenuItem7){ 
            onNotepad();
        }
        if(menuObject==MenuItem8){ 
            onCustomer();
        }
        if(menuObject==MenuItem9){ 
            onDressDetails();
        }
        if(menuObject==MenuItem10){ 
            onMaterial();
        }
        if(menuObject==MenuItem11){ 
            onState();
        }
        if(menuObject==MenuItem12){ 
            onSystemRequirement();
        }
        if(menuObject==MenuItem13){ 
            onAbout();
        }
        if(menuObject==MenuItem14){ 
            onProjectReport();
        }
        if(menuObject==MenuItem15){ 
             this.mainFrame.dispose();
             userLogin2 login=new userLogin2();
             login.show();
        }
        if(menuObject==MenuItem16){ 
            onExit();
        }
        
    }

   
    private void onTransactionScreen() {
        System.out.print("test");
        }

    private void onReport() {
        }
    
    private void onExit() {
        System.exit(0);
    }

    private void onToolbar() {
        }

    private void onStatusbar() {
        }

    private void onCalulator() {
        functionDef.callExternalApplication("calc");
        }

    private void onNotepad() {
        functionDef.callExternalApplication("notepad");
        }

    private void onCustomer() {
        
        checkCutomerAction checkLogin=new checkCutomerAction();
        checkLogin.show();
        //checkLogin.setBounds(250, 150, 430, 230);
        checkLogin.setSize(430, 230);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        checkLogin.setLocation(dim.width/2-checkLogin.getSize().width/2, dim.height/2-checkLogin.getSize().height/2);
        checkLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        }

    private void onDressDetails() {
        tableViewDataParser parser=new tableViewDataParser();
        parser.dataParser("SELECT * FROM kandiyan_dress");
    }

    private void onMaterial() {
        }

    private void onState() {
        }

    private void onSystemRequirement() {
        }

    private void onAbout() {
        }

    private void onProjectReport() {
        }
   
    

}