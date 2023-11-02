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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class detailsInTableView_1 extends JFrame implements ActionListener {

    JTable table = new JTable();
    Object[][] data ;
    Object[] columns ;
    Object[] row;
    JTextField textId,textFname,textLname,textAge;
    JButton btnAdd ,btnDelete,btnUpdate;
    int Ccount,Rcount;
    JTextField[] tableTxt;
    
    DefaultTableModel model;
    
   public detailsInTableView_1(Object[] colNames,int Rcount,int Ccount,Object tblData[][]){
       this.Ccount=Ccount;
       this.Rcount=Rcount;
       columns=new Object[Ccount];
        row = new Object[Ccount];
        tableTxt=new JTextField[Ccount];
        columns=colNames;
        
         // create a table model and set a Column Identifiers to this model 
         model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        // set the model to the table
        table.setModel(model);

        for(int a=0;a<Rcount;a++){
             for(int b=0;b<Ccount;b++){
                row[b]=tblData[a][b];
                if(a==0){
                    tableTxt[b]=new JTextField();
                    tableTxt[b].setBounds((1200/Ccount)*(b),500,(1200/Ccount),25);
                    add(tableTxt[b]);
                }
                
            }
            model.addRow(row);
        }
        
        
        
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        
        
        
        
        // create JTextFields
//        textId = new JTextField();
//        textFname = new JTextField();
//        textLname = new JTextField();
//        textAge = new JTextField();
        
        // create JButtons
        btnAdd = new JButton("Add");
        btnDelete = new JButton("Delete");
        btnUpdate = new JButton("Update");     
        
//        textId.setBounds(20, 220, 100, 25);
//        textFname.setBounds(20, 250, 100, 25);
//        textLname.setBounds(20, 280, 100, 25);
//        textAge.setBounds(20, 310, 100, 25);
        
        btnAdd.setBounds(150, 530, 100, 25);
        btnUpdate.setBounds(260, 530, 100, 25);
        btnDelete.setBounds(420, 530, 100, 25);
        
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        
        
        // create JScrollPane
       
        
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 1200, 499);
       System.out.println( pane.getBounds().width);
     
     
       
        Dimension panelD = new Dimension(800, 500);
       pane.setPreferredSize(panelD);
        setLayout(null);
        
        add(pane);
        
        // add JTextFields to the jframe
//        add(textId);
//        add(textFname);
//        add(textLname);
//        add(textAge);
//    
        // add JButtons to the jframe
        add(btnAdd);
        add(btnDelete);
        add(btnUpdate);
//            table.getColumn("dress_brand").setPreferredWidth(1);
//            table.getColumn("dress_brand").setMinWidth(1);
//            table.getColumn("dress_brand").setWidth(1);
//            table.getColumn("dress_brand").setMaxWidth(1);
////        
//        table.getColumn("dress_type").setPreferredWidth(1);
//        table.getColumn("dress_type").setMinWidth(1);
//        table.getColumn("dress_type").setWidth(1);
//        table.getColumn("dress_type").setMaxWidth(1);
//        
//        table.getColumn("dress_amount").setPreferredWidth(1);
//        table.getColumn("dress_amount").setMinWidth(1);
//        table.getColumn("dress_amount").setWidth(1);
//        table.getColumn("dress_amount").setMaxWidth(1);
//        
//        table.getColumn("dress_group").setPreferredWidth(1);
//        table.getColumn("dress_group").setMinWidth(1);
//        table.getColumn("dress_group").setWidth(1);
//        table.getColumn("dress_group").setMaxWidth(1);
//        
//        table.getColumn("dress_material").setPreferredWidth(1);
//        table.getColumn("dress_material").setMinWidth(1);
//        table.getColumn("dress_material").setWidth(1);
//        table.getColumn("dress_material").setMaxWidth(1);
//        
//        
        table.addMouseListener(new MouseAdapter(){
        
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            // i = the index of the selected row
            int i = table.getSelectedRow();
            for(int a=0;a<Ccount;a++){
               tableTxt[a].setText(model.getValueAt(i, a).toString());
            }
            
        }
        });
        
   }   
   public detailsInTableView_1(){
        
        // create JFrame and JTable
        
         
        
        // create a table model and set a Column Identifiers to this model 
        
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        
        // set the model to the table
        table.setModel(model);
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        
        
        // create JTextFields
        textId = new JTextField();
        textFname = new JTextField();
        textLname = new JTextField();
        textAge = new JTextField();
        
        // create JButtons
        btnAdd = new JButton("Add");
        btnDelete = new JButton("Delete");
        btnUpdate = new JButton("Update");     
        
        textId.setBounds(20, 220, 100, 25);
        textFname.setBounds(20, 250, 100, 25);
        textLname.setBounds(20, 280, 100, 25);
        textAge.setBounds(20, 310, 100, 25);
        
        btnAdd.setBounds(150, 220, 100, 25);
        btnUpdate.setBounds(150, 265, 100, 25);
        btnDelete.setBounds(150, 310, 100, 25);
        
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 1200, 500);
        
        setLayout(null);
        
        add(pane);
        
        // add JTextFields to the jframe
        add(textId);
        add(textFname);
        add(textLname);
        add(textAge);
    
        // add JButtons to the jframe
        add(btnAdd);
        add(btnDelete);
        add(btnUpdate);
        
//                    table.getColumn("dress_brand").setPreferredWidth(1);
//            table.getColumn("dress_brand").setMinWidth(1);
//            table.getColumn("dress_brand").setWidth(1);
//            table.getColumn("dress_brand").setMaxWidth(1);
////        
//        table.getColumn("dress_type").setPreferredWidth(1);
//        table.getColumn("dress_type").setMinWidth(1);
//        table.getColumn("dress_type").setWidth(1);
//        table.getColumn("dress_type").setMaxWidth(1);
//        
//        table.getColumn("dress_amount").setPreferredWidth(1);
//        table.getColumn("dress_amount").setMinWidth(1);
//        table.getColumn("dress_amount").setWidth(1);
//        table.getColumn("dress_amount").setMaxWidth(1);
//        
//        table.getColumn("dress_group").setPreferredWidth(1);
//        table.getColumn("dress_group").setMinWidth(1);
//        table.getColumn("dress_group").setWidth(1);
//        table.getColumn("dress_group").setMaxWidth(1);
//        
//        table.getColumn("dress_material").setPreferredWidth(1);
//        table.getColumn("dress_material").setMinWidth(1);
//        table.getColumn("dress_material").setWidth(1);
//        table.getColumn("dress_material").setMaxWidth(1);
//        
        
         table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            // i = the index of the selected row
            int i = table.getSelectedRow();
            
            textId.setText(model.getValueAt(i, 0).toString());
            textFname.setText(model.getValueAt(i, 1).toString());
            textLname.setText(model.getValueAt(i, 2).toString());
            textAge.setText(model.getValueAt(i, 3).toString());
        }
        });
        
   }   
   
   
        
        // button add row
        
            public void actionPerformed(ActionEvent e) {
              Object btnAction=e.getSource();
              if (btnAction==btnAdd){
                  for(int a=0;a<Ccount;a++){
                   row[a] = tableTxt[a].getText();
                  }

                
                // add row to the model
                model.addRow(row);
            }
              if (btnAction==btnDelete){
                  int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
              if (btnAction==btnUpdate){
                    // i = the index of the selected row
                int i = table.getSelectedRow();
                
                if(i >= 0) 
                {
                    for(int a=0;a<Ccount;a++){
                   model.setValueAt(tableTxt[a].getText(), i, a);
                  // model.fireTableRowsUpdated(i, a);
                  } 
                  
                  
                }
                else{
                    System.out.println("Update Error");
                }
              }
            }
     
    }
        