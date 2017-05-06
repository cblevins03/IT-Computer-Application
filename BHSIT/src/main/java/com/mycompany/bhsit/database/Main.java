package com.mycompany.bhsit.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.SwingUtilities;
import java.sql.SQLException;
import java.util.logging.Level;
/*
 * @author cgb_000
 */
public class Main {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DbHelper.class);
    
    
    public static void main(final String[] args){
    
    DbHelper.getInstance().init();
    DbHelper.getInstance().registerShutdownHook();
    
        
    
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            Main.LOGGER.debug("Starting Application");
            
            ApplicationInterface appint = new ApplicationInterface();            
            
            appint.setTitle("Bethlehem High School IT Tracker");
            appint.setSize(900,700);
            appint.setLocationRelativeTo(null);
            appint.setDefaultCloseOperation(ApplicationInterface.EXIT_ON_CLOSE);
            appint.setVisible(true);
            try {
                appint.Launch();
                
                /*appint.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e){
                Main.LOGGER.debug("Done");
                DbHelper.getInstance().close();
                }
                });*/
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    });    
    
    
    
   } 
}
