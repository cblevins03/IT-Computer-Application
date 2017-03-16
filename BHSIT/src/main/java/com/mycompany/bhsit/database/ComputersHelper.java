/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    private ComputersHelper(){
    }
    
    public List<Computers> getComputers() throws SQLException{
        final List<Computers> computers = new ArrayList<>();
        final String sql = "SELECT * FROM computers ORDER BY id";
        
        try(Connection connection = DbHelper.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
        
            while(rs.next()){
                final Computers computer = new Computers();
                computer.setId(rs.getString("id"));
                computer.setCompname(rs.getString("compname"));
                computer.setOs(rs.getString("os"));
                computer.setIp(rs.getString("ip"));
                computer.setSernum(rs.getString("sernum"));
                computer.setDatepurch(rs.getString("datepurch"));
                computer.setStatus(rs.getString("status"));
                computer.setNotes(rs.getString("notes"));
                //computer.setComputers(rs.getString("computers"));
                computers.add(computer);
            }  
        }
        return computers;
    }
}
