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
public class ComputersHelper {
    
    private static final ComputersHelper INSTANCE = new ComputersHelper();
    
    public static ComputersHelper getInstance(){
        return ComputersHelper.INSTANCE;
    }
    
    public ComputersHelper(){
    }
    
    public List<Computers> getComputers() throws SQLException{
        final List<Computers> computers = new ArrayList<>();
        final String sql = "SELECT * FROM computers ORDER BY compid";
        
        try(Connection connection = DbHelper.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
        
            while(rs.next()){
                final Computers computer = new Computers();
                computer.setId(rs.getString("compid"));
                computer.setCompname(rs.getString("compname"));
                computer.setOs(rs.getString("compos"));
                computer.setIp(rs.getString("compip"));
                computer.setSernum(rs.getString("compsernum"));
                computer.setDatepurch(rs.getString("compdatepurch"));
                computer.setStatus(rs.getString("compstatus"));
                computer.setNotes(rs.getString("compnotes"));
                //computer.setComputers(rs.getString("computers"));
                computers.add(computer);
            }  
        }
        return computers;
    }
    
    public int getLaunch() throws SQLException{
        final int computers;
        final String sql = "SELECT * FROM launch";
        
        try(Connection connection = DbHelper.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
                rs.next();
                computers = rs.getInt("launch");  
        }
        return computers;
    }
}
