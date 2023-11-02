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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class addSaleTableView extends JFrame implements ActionListener {

    public static int mapKeyCounter=0;
    
    JTable table = new JTable();
    Object[][] data ;
    Object[] columns ;
    Object[] row;
    JTextField textId,pricetxt,amountTxt,searchByType;
    JButton btnAdd ,btnFinish,btnUpdate;
    int Ccount,Rcount;
   
    JLabel id,price,amount;
    
    DefaultTableModel model;
    
    String cusName,cusAdd1,cusAdd2,cusCity,cusPincode;
    
      
    
   public addSaleTableView(Object[] colNames,int Rcount,int Ccount,Object tblData[][]){
       this.Ccount=Ccount;
       this.Rcount=Rcount;
       columns=new Object[Ccount];
        row = new Object[Ccount];
      
        columns=colNames;
        
        
        
         // create a table model and set a Column Identifiers to this model 
         model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        // set the model to the table
        table.setModel(model);

        for(int a=0;a<Rcount;a++){
             for(int b=0;b<Ccount;b++){
                row[b]=tblData[a][b];
                
             }
            model.addRow(row);
        }
        id=new JLabel("Item ID");
        id.setBounds((1200/Ccount)-120,500,(1200/Ccount),25);
        add(id);
        textId=new JTextField();
        textId.setBounds((1200/Ccount),500,(1200/Ccount),25);
        add(textId);
                    
        price=new JLabel("Price");
        price.setBounds((1200/Ccount)-120,530,(1200/Ccount),25);
        add(price);
        pricetxt=new JTextField();
        pricetxt.setBounds((1200/Ccount),530,(1200/Ccount),25);
        add(pricetxt);
        
        amount=new JLabel("Amount");
        amount.setBounds((1200/Ccount)-120,560,(1200/Ccount),25);
        add(amount);
        amountTxt=new JTextField();
        amountTxt.setBounds((1200/Ccount),560,(1200/Ccount),25);
        add(amountTxt);
        
        
        
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        
        // create JButtons
        btnAdd = new JButton("Add");
        btnFinish = new JButton("Finish");
      //  btnUpdate = new JButton("Update");     
        
     
        btnAdd.setBounds(150, 590, 100, 25);
        btnFinish.setBounds(260, 590, 100, 25);
        //btnDelete.setBounds(420, 530, 100, 25);
        
        btnAdd.addActionListener(this);
        btnFinish.addActionListener(this);
       // btnDelete.addActionListener(this);
        
        
        // create JScrollPane
       
        
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 1200, 499);
       System.out.println( pane.getBounds().width);
     
     
       
        Dimension panelD = new Dimension(800, 500);
       pane.setPreferredSize(panelD);
        setLayout(null);
        
        add(pane);
        
       
//    
        // add JButtons to the jframe
        add(btnAdd);
        add(btnFinish);
        
        
         table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            // i = the index of the selected row
            int i = table.getSelectedRow();
            
               textId.setText(model.getValueAt(i, 1).toString());
           
            
        }
        });
        
   }   
  // button add row
        
            public void actionPerformed(ActionEvent e) {
              Object btnAction=e.getSource();
              if (btnAction==btnAdd){
                 addItem();
              }
              else if(btnAction==btnFinish){
                  proceed();
              }
               
            } 
            
            public void addItem(){
                 

                if(pricetxt.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Price cant be empty","Enter the price",JOptionPane.ERROR_MESSAGE);
                    
                }else {
                try{
               Double pr=Double.valueOf(pricetxt.getText());
                }
                catch(NumberFormatException ee){
                    JOptionPane.showMessageDialog(null,"Price must be in Number","Error",JOptionPane.ERROR_MESSAGE);
                }
               
              
                
                }
                
                if(Double.parseDouble(pricetxt.getText())>=0.0){
                    JOptionPane.showMessageDialog(null,"Item Added Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    
                }
                
                salesData.hm.put(mapKeyCounter,textId.getText()+","+pricetxt.getText()+","+amountTxt.getText());
                mapKeyCounter++;
            }
            
            public void proceed(){
                this.hide();
            }
}
   