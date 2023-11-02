/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Nithaparan
 */
public class mainFunctionDef {
   public void callExternalApplication(String appName){
       
       Runtime rs = Runtime.getRuntime();
 
    try {System.out.println();
          // JOptionPane.showMessageDialog(null,"", "", JOptionPane.INFORMATION_MESSAGE);
            rs.exec(appName);
            
        
        
    }
    catch (IOException e) {
      System.out.println(e);
    } 
       
   }
    
}
