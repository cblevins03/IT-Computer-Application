package com.mycompany.bhsit.database;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cgb_000
 */
public class TabletsHelper {
    
    private static final TabletsHelper INSTANCE = new TabletsHelper();
    
    public static TabletsHelper getInstance(){
        return TabletsHelper.INSTANCE;
    }
    
    private TabletsHelper(){
    }
    
    public List<Tablets> getTablets() throws SQLException{
        final List<Tablets> tablets = new ArrayList<>();
        final String sql = "SELECT * FROM tablets ORDER BY tabletid";
        
        try(Connection connection = DbHelper.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
        
            while(rs.next()){
                final Tablets tablet = new Tablets();
                tablet.setId(rs.getString("tabletid"));
                tablet.setTabletname(rs.getString("tabletname"));
                tablet.setOs(rs.getString("tabletos"));
                tablet.setIp(rs.getString("tabletip"));
                tablet.setSernum(rs.getString("tabletsernum"));
                tablet.setDatepurch(rs.getString("tabletdatepurch"));
                tablet.setStatus(rs.getString("tabletstatus"));
                tablet.setNotes(rs.getString("tabletnotes"));
                //computer.setComputers(rs.getString("computers"));
                tablets.add(tablet);
            }  
        }
        return tablets;
    }
}
