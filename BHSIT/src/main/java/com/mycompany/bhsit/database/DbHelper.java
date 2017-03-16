package com.mycompany.bhsit.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
 * @author cgb_000
 */
public class DbHelper{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DbHelper.class);
    
    private static final DbHelper INSTANCE = new DbHelper();
    
    public static DbHelper getInstance(){
        return DbHelper.INSTANCE;
    }

    private BasicDataSource ds;
    
    private DbHelper(){
    }
    
    public DataSource getDataSource(){
        return ds;
    }
    
    // Creates the Data Source, establishes connection to the database, and executes Flyway
    public void init(){
        DbHelper.LOGGER.debug("Loading properties");
        final Properties properties = new Properties();
        properties.put("db.path", "~/test");
        properties.put("db.username", "sa");
        properties.put("db.password", "");
        try{
            properties.load(getClass().getResourceAsStream("/app.properties"));
        }catch(final IOException e){
            DbHelper.LOGGER.error("Failed to load the properties");
        }
        
        LOGGER.debug("Creating the datasource");
        ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:" + properties.getProperty("db.path"));
        ds.setUsername(properties.getProperty("db.username"));
        ds.setPassword(properties.getProperty("db.password"));
        
        LOGGER.debug("Executing Flyway (database migration)");
        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        //flyway.repair();
        flyway.migrate();
    }
    
    // Closes connection to the Data Source
    public void close(){
        if(ds != null){
            LOGGER.debug("Closing the datasource");
            try {
                ds.close();
            } catch (SQLException e) {
                LOGGER.error("Failed to close the data source", e);
            }
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return getInstance().getDataSource().getConnection();
    }
    
    public void registerShutdownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
            @Override
            public void run(){
                close();
            }
        }));
    }
}
