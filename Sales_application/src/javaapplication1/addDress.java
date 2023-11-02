/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
 
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;

/**
 *
 * @author Indiran
 */
public class addDress extends JFrame implements ActionListener {
     
     String Brand,Name,Type,Size,Sizenum,Group,Material,Supplier,PurchaseDate;
     String systemDate;
     String DB_Dress_Brand,DB_Dress_Name,DB_Dress_Type,DB_Dress_Size,
            DB_Dress_Sizenum,DB_Dress_Group,DB_Dress_Material,
            DB_Dress_Price,DB_Dress_Amount;
     public  String[] arr=new String[100];
     Double Cost,Price;
     int Amount,i;
     int systemCreatedDressID;
     int day,month,year;
     JLabel Brandlbl,Namelbl,Typelbl,Sizelbl,Sizenumlbl,Costlbl,Pricelbl,Amountlbl,Grouplbl,Materiallbl,supplierlbl,purchaselbl;
     JTextField BrandText,NameText,TypeText,SizeText,SizenumText,CostText,PriceText,AmountText,MaterialText,supplierText,purchaseText,DEFAULTFIELD;
     java.sql.PreparedStatement ps;
     java.sql.Statement stmt; 
     String SQL; 
     jdbcConnect connectDB;
     JButton addDress;
     JComboBox GroupText;
     JComboBox<String> suggestCombobox;
     public JPanel contentPane;
     JComboBox c;
     JDatePickerImpl datePicker;
     JDatePanelImpl datePanel;
     addDress objaddDress;
     
     JLabel imp;
     
     databaseInAndOut db=new databaseInAndOut();
    
       public addDress(){setVisible(true);
            
            Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();//to get the screen size
            setLayout(null);
            setSize (screensize); 
            setResizable(false);
            
            imp=new JLabel("*");
            imp.setForeground(Color.RED);
                    
            
            this.contentPane=new JPanel();
        this.contentPane.setAutoscrolls((true));
        this.contentPane.setBackground(Color.WHITE);
        this.contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
    
                Brandlbl = new JLabel("Brand"+imp.getText());
		Brandlbl.setBounds(10, 10, 180, 25);
		contentPane.add(Brandlbl);
                

		BrandText = new JTextField();
		BrandText.setBounds(200, 10, 160, 25);
		contentPane.add(BrandText);
                BrandText.addCaretListener(new TextFieldCaretListener());
                
                Namelbl = new JLabel("Name");
		Namelbl.setBounds(10, 40, 180, 25);
		contentPane.add(Namelbl);

		NameText = new JTextField();
		NameText.setBounds(200, 40, 160, 25);
		contentPane.add(NameText);
                NameText.addCaretListener(new TextFieldCaretListener());
                
                Grouplbl = new JLabel("Dress Group"+imp.getText());
		Grouplbl.setBounds(10, 70, 180, 25);
		contentPane.add(Grouplbl);
                
                GroupText=new JComboBox();    
                GroupText.setBounds(200, 70, 180, 25);
                GroupText.addItem("Male");
                GroupText.addItem("Female");
                GroupText.addItem("Kids");
                contentPane.add(GroupText);
                
                
                Typelbl = new JLabel("Type"+imp.getText());
		Typelbl.setBounds(10, 100, 180, 25);
		contentPane.add(Typelbl);

		TypeText = new JTextField();
		TypeText.setBounds(200, 100, 160, 25);
                TypeText.addCaretListener(new TextFieldCaretListener());
		contentPane.add(TypeText);
                
                Sizelbl = new JLabel("Size"+imp.getText());
		Sizelbl.setBounds(10, 130, 180, 25);
		contentPane.add(Sizelbl);

		SizeText = new JTextField();
		SizeText.setBounds(200, 130, 160, 25);
		contentPane.add(SizeText);
                
                Sizenumlbl = new JLabel("Size(number)"+imp.getText());
		Sizenumlbl.setBounds(10, 160, 180, 25);
		contentPane.add(Sizenumlbl);

		SizenumText = new JTextField();
		SizenumText.setBounds(200, 160, 160, 25);
		contentPane.add(SizenumText);
                
                Materiallbl = new JLabel("Material"+imp.getText());
		Materiallbl.setBounds(10, 190, 180, 25);
		contentPane.add(Materiallbl);

		MaterialText = new JTextField();
		MaterialText.setBounds(200, 190, 160, 25);
		contentPane.add(MaterialText);
                MaterialText.addCaretListener(new TextFieldCaretListener());
                
                Costlbl = new JLabel("Cost");
		Costlbl.setBounds(10, 220, 180, 25);
		contentPane.add(Costlbl);

		CostText = new JTextField("0.0");
		CostText.setBounds(200, 220, 160, 25);
		contentPane.add(CostText);
                
                Pricelbl = new JLabel("Selling Price");
		Pricelbl.setBounds(10, 250, 180, 25);
		contentPane.add(Pricelbl);

		PriceText = new JTextField("0.0");
		PriceText.setBounds(200, 250, 160, 25);
		contentPane.add(PriceText);
                
                Amountlbl = new JLabel("Amount");
		Amountlbl.setBounds(10, 280, 180, 25);
		contentPane.add(Amountlbl);

		AmountText = new JTextField("0");
		AmountText.setBounds(200, 280, 160, 25);
		contentPane.add(AmountText);
                
                supplierlbl = new JLabel("Supplier");
		supplierlbl.setBounds(10, 310, 180, 25);
		contentPane.add(supplierlbl);

		supplierText = new JTextField();
		supplierText.setBounds(200, 310, 160, 25);
		contentPane.add(supplierText);
                
                
                purchaselbl = new JLabel("Purchase Date");
		purchaselbl.setBounds(10, 340, 180, 25);
		contentPane.add(purchaselbl);

		purchaseText = new JTextField();
		purchaseText.setBounds(200, 340, 160, 25);
	//	add(purchaseText);
                
                addDress=new JButton("ADD");
                addDress.setBounds(200, 370, 160, 25);
                contentPane.add(addDress);
                addDress.addActionListener(this);
                
                UtilDateModel model = new UtilDateModel();;
                Properties p = new Properties();
                p.put("text.today", "Today");
                p.put("text.month", "Month");
                p.put("text.year", "Year");
                datePanel = new JDatePanelImpl(model, p);
                datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
                datePicker.setBounds(200, 340, 160, 25);
                
                contentPane.add(datePicker);
                datePicker.addActionListener(this);
                
                this.suggestCombobox=new JComboBox<String>();
                this.suggestCombobox.setFocusCycleRoot(true);
                this.suggestCombobox.setFocusTraversalPolicyProvider(true);
                this.suggestCombobox.setAutoscrolls(true);
                this.suggestCombobox.setBorder(null);
                this.suggestCombobox.setOpaque(false);
                this.suggestCombobox.addActionListener(new ComboBoxActionListener());
                
               contentPane.add(suggestCombobox);
            
}    
    @Override
       public void actionPerformed(ActionEvent e) {
       Object button=e.getSource();
               if(button==addDress){
                   addDressToDatabase();
               
    }}
       public void addDressToDatabase(){
        connectDB=new jdbcConnect();
        connectDB.connectDB();
        
        getSomeDataFromDatabase();
      
        
        systemDate=getSystemDate();
        
        int selectedValue = datePicker.getModel().getDay();
        day=datePicker.getModel().getDay();
        month=datePicker.getModel().getMonth()+1;
        year=datePicker.getModel().getYear();
        String SelectedDate = Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);
        
        
        Brand=BrandText.getText().trim();
        Name=NameText.getText().trim();
        Type=TypeText.getText().trim();
        Size=SizeText.getText().trim();
        Sizenum=SizenumText.getText().trim();
        Cost=Double.parseDouble(CostText.getText().trim());
        Price=Double.parseDouble(PriceText.getText().trim());
        Amount=Integer.parseInt(AmountText.getText().trim());
        Group=(String)GroupText.getSelectedItem();
        Material=MaterialText.getText().trim();
        Supplier=supplierText.getText().trim();
        PurchaseDate=SelectedDate;
        
        
        
        if(Brand.equals("")||Type.equals("")||(Size.equals("")&&Sizenum.equals(""))||Group.equals("")||Material.equals(""))
        {
            //test
            JOptionPane.showMessageDialog(null,"Please fill all details","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
        
       if(checkDressExistingOrNot()){
           
           db.checkPriceAndAssignAmount(DB_Dress_Price, Price, Amount, DB_Dress_Amount, systemCreatedDressID,Supplier, Cost, PurchaseDate
           );
           
           //checkPriceAndAssignAmount();
          // updateAmountIfExistingDress();
           
            
        }
       else{
           db.addNewDressToDB(Brand, systemCreatedDressID, Name, Type, Size, Sizenum, Price, Amount, Group, Material);
         boolean r=  db.addNewPurchaseToDB(systemCreatedDressID, Supplier, Cost, Amount, PurchaseDate);
         if(r=true){
           //  reset();
         }
       }
       }
    }
       public String getSystemDate(){
        
        Date date = null;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
          LocalDate localDate = LocalDate.now();
         
        
        return localDate.toString();
    }
       public void getSomeDataFromDatabase(){
    try{
     stmt=connectDB.con.createStatement();
       SQL="SELECT * FROM kandiyan_dress  ORDER BY dress_id * 1";
       connectDB.rs=stmt.executeQuery(SQL);
       if(connectDB.rs.last()){
           
           systemCreatedDressID=Integer.parseInt(connectDB.rs.getString("dress_id"));
           DB_Dress_Brand=connectDB.rs.getString("dress_brand");
           DB_Dress_Name=connectDB.rs.getString("dress_name");
           DB_Dress_Type=connectDB.rs.getString("dress_type");
           DB_Dress_Size=connectDB.rs.getString("dress_size");
           DB_Dress_Sizenum=connectDB.rs.getString("dress_size_num");
           DB_Dress_Group=connectDB.rs.getString("dress_group");
           DB_Dress_Material=connectDB.rs.getString("dress_material");
           DB_Dress_Price=connectDB.rs.getString("dress_price");
           DB_Dress_Amount=connectDB.rs.getString("dress_amount");
           
           System.out.println(Integer.parseInt(connectDB.rs.getString("dress_id")));
           }
           
       
        else{
           systemCreatedDressID=0;
           DB_Dress_Brand="";
           DB_Dress_Name="";
           DB_Dress_Type="";
           DB_Dress_Size="";
           DB_Dress_Sizenum="";
           DB_Dress_Group="";
           DB_Dress_Material="";
           
           }
       
       }
    catch(Exception e){
    e.printStackTrace();
    }
 }
       public boolean checkDressExistingOrNot(){
        boolean status=false;
        if(DB_Dress_Brand.equalsIgnoreCase(Brand)&&
           DB_Dress_Name.equalsIgnoreCase(Name)&&
           DB_Dress_Type.equalsIgnoreCase(Type)&&
           DB_Dress_Size.equalsIgnoreCase(Size)&&
           DB_Dress_Sizenum.equalsIgnoreCase(Sizenum)&&
           DB_Dress_Group.equalsIgnoreCase(Group)&&
           DB_Dress_Material.equalsIgnoreCase(Material)
                ){
            
            status=true;
        }
        
        
        
        return status;
    }
       public void reset(){
        BrandText.setText("");
        NameText.setText("");
        TypeText.setText("");
        SizeText.setText("");
        SizenumText.setText("");
        CostText.setText("");
        PriceText.setText("");
        AmountText.setText("");
        
        MaterialText.setText("");
        supplierText.setText("");
        
       }
       
       
      
       
       
        
    
      
    

       public class TextFieldCaretListener implements CaretListener{
        

        @Override
        public void caretUpdate(CaretEvent e) {
            
            
            Object txtObject=e.getSource();
        if(txtObject==BrandText){
           getDataFromDB("dress_brand");
        }
        if(txtObject==NameText){
           getDataFromDB("dress_name");
        }
        if(txtObject==MaterialText){
           getDataFromDB("dress_material");
        }
        if(txtObject==TypeText){
           getDataFromDB("dress_Type");
        }
       
  
            
            try{
                
                
               suggestCombobox.removeAllItems();
               suggestCombobox.hidePopup();
               contentPane.remove(suggestCombobox);
               Object text=e.getSource();
               if(text==BrandText){
                   DEFAULTFIELD=BrandText;
                }
               if(text==NameText){
                   DEFAULTFIELD=NameText;
                }
               if(text==MaterialText){
                   DEFAULTFIELD=MaterialText;
                }
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
  
}
