/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bhsit.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

/*
 * @author cgb_000
 */
public class ComputersTest {
    
    @Before
    public void init() throws SQLException{
        DbHelper.getInstance().init();
    
        try(Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()){
            stmt.execute("TRUNCATE TABLE computers");
            //stmt.execute("ALTER TABLE computers ALTER COLUMN id RESTART WITH " + c.getId());
        }
    }
    
    @After
    public void close(){
        DbHelper.getInstance().close();
    }
    
    @Test
    public void testSave() throws SQLException {
        Computers c = new Computers();
        c.setId("02 computerlab");
        c.setCompname("BHS05");
        c.setOs("OS X Lion");
        c.setIp("127.0.0.1");
        c.setSernum("4478FE58T4");
        c.setDatepurch("2007-5-9");
        c.setStatus("In Use");
        c.setNotes("This better work!");
        c.save();
        
        try(Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()){
            try(ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM computers")){
                Assert.assertTrue("Count should return at least one row", rs.next());
                Assert.assertEquals(1L, rs.getLong(1));
                Assert.assertTrue("Count should not return more than one row", rs.next());
            }
            try(ResultSet rs = stmt.executeQuery("SELECT * FROM computers")){
                Assert.assertTrue("Select should return at least one row", rs.next());
                Assert.assertEquals("02 computerlab", rs.getString("id"));
                Assert.assertEquals("BHS05", rs.getString("compname"));
                Assert.assertTrue("Select should not return more than one row", rs.next());
            }
        }
    }
    
}
