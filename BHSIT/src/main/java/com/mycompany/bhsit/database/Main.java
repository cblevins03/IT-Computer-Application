package com.mycompany.bhsit.database;

//import java.sql.SQLException;
//import java.util.List;
//import org.apache.commons.dbcp.BasicDataSource;
import javax.swing.JFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.flywaydb.core.Flyway;
import javax.swing.SwingUtilities;
import java.awt.event.*;
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
            Application app = new Application();
            app.setTitle("Bethlehem High School IT Tracker");
            app.setSize(800,600);
            app.setLocationRelativeTo(null);
            app.setDefaultCloseOperation(Application.EXIT_ON_CLOSE);
            app.setVisible(true);
            /*app.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e){
                    Main.LOGGER.debug("Done");
                    DbHelper.getInstance().close();
                }
            });*/
        }
    });
    // Creates a new Computers object and sets the values of the object in the database to be stored
    //Computers c = new Computers();
    /*try{
        final Computers c = new Computers();
        c.setId("65874");
        c.setCompname("03 maclibrary");
        c.setOs("OS X Lion");
        c.setIp("127.0.0.2");
        c.setSernum("C1MGTDEMDV13");
        c.setDatepurch("2007-5-9");
        c.setStatus("In Storage");
        c.setNotes("This better work!");
        //c.update(c.getId());
        //c.delete("979a");
        c.save();
        
        List<Computers> computers = ComputersHelper.getInstance().getComputers();
        for(Computers computer : computers)
            Main.LOGGER.debug(" >> [{}] {} ({}) {}, {}, {}, ({}) {}",computer.getId(), computer.getCompname(), computer.getOs(), computer.getIp(), computer.getSernum(), computer.getDatepurch(), computer.getStatus(), computer.getNotes(), computer.getComputers()); 
        */
        /*
        try(Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()){
            try(ResultSet rs = stmt.executeQuery("SELECT * FROM computers")){
                while(rs.next()){
                    LOGGER.debug(" >> [{}] {} ({}) {}, {}, {}, ({}) {}", 
                        new Object[] {rs.getString("id"), rs.getString("compname"),
                        rs.getString("os"), rs.getString("ip"), rs.getString("sernum"),
                        rs.getString("datepurch"), rs.getString("status"), rs.getString("notes")});
                }
            }
        }*/
    /*}catch(SQLException e){
        LOGGER.error("Failed to save the computer.", e);
    }
    // Closes the connection to the Data Source at the end of execution
    DbHelper.getInstance().close();
    LOGGER.info("Done");
    */    
    /*  LOGGER.debug("Creating the datasource");
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:~/test");
        ds.setUsername("sa");
        ds.setPassword("");
        
        try {
            LOGGER.debug("Executing Flyway (database migration)");
            Flyway flyway = new Flyway();
            flyway.setDataSource(ds);
            flyway.repair();
            flyway.migrate();
        
            LOGGER.debug("Executing Queries");
            try(Connection connection = ds.getConnection(); Statement stat = connection.createStatement()) {
                stat.executeUpdate("INSERT INTO computers(id, compname, os, ip, sernum, datepurch, status, notes) VALUES ('01 maclibrary', 'BHS1', 'Windows 7', '127.0.0.1', '545614F', '2006-11-1', 'In storage', 'Is good.')");
                
                LOGGER.debug("Computers");
                try(ResultSet rs = stat.executeQuery("SELECT * FROM computers")) {
                    while(rs.next()){
                    LOGGER.debug("  >> [{}] {} ({}) {}, {}, {}, ({}) {}", rs.getString("id"), rs.getString("compname"), 
                            rs.getString("os"), rs.getString("ip"), rs.getString("sernum"), 
                            rs.getString("datepurch"), rs.getString("status"), rs.getString("notes"));                    
                    }
                }
            }
        }catch (SQLException e){
            LOGGER.error("Failed", e);
        }finally {
            try{
                ds.close();
            } catch (SQLException e) {
                LOGGER.error("Failed to close the data source", e);
            }
        }
    */} 
}
