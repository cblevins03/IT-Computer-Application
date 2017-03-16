package com.mycompany.bhsit.database;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * @author cgb_000
 */
public class Tablets {
    private String id;
    private String tabletname;
    private String os;
    private String ip;
    private String sernum;
    private String datepurch;
    private String status;
    private String notes;
    private String tablets;
    
    // 
    public String getTablets(){
        return tablets;
    }
    
    public void setTablets(final String computers){
        this.tablets = tablets;
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(final String id){
        this.id = id;
    }
    
    public String getTabletname(){
        return tabletname;
    }
    
    public void setTabletname(final String compname){
        this.tabletname = tabletname;
    }
    
    public String getOs(){
        return os;
    }
    
    public void setOs(final String os){
        this.os = os;
    }
    
    public String getIp(){
        return ip;
    }
    
    public void setIp(final String ip){
        this.ip = ip;
    }
    
    public String getSernum(){
        return sernum;
    }
    
    public void setSernum(final String sernum){
        this.sernum = sernum;
    }
    
    public String getDatepurch(){
        return datepurch;
    }
    
    public void setDatepurch(final String datepurch){
        this.datepurch = datepurch;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(final String status){
        this.status = status;
    }
    
    public String getNotes(){
        return notes;
    }
    
    public void setNotes(final String notes){
        this.notes = notes;
    }
    
    public void delete(String id) throws SQLException{
        if(id != null){
            final String sql = "DELETE FROM tablets WHERE id = ?";
            try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){ 
                pstmt.setString(1, id);
                pstmt.execute();
                id = null;
            }
        }
    }
    // Enters values into the database using prepared statements
    public void save() throws SQLException{
        
        final String sql = "INSERT INTO tablets(id, tabletname, os, ip, sernum, datepurch, status, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){

                pstmt.setString(1, id);
                pstmt.setString(2, tabletname);
                pstmt.setString(3, os);
                pstmt.setString(4, ip);
                pstmt.setString(5, sernum);
                pstmt.setString(6, datepurch);
                pstmt.setString(7, status);
                pstmt.setString(8, notes);
                pstmt.execute();
            }
        /*try(Connection connection = DbHelper.getConnection()){
            //int ids = Integer.parseInt(id);

            if(id == null){
                final String sql = "INSERT INTO computers(id, compname, os, ip, sernum, datepurch, status, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try(PreparedStatement pstmt = connection.prepareStatement(sql)){

                    pstmt.setString(1, id);
                    pstmt.setString(2, compname);
                    pstmt.setString(3, os);
                    pstmt.setString(4, ip);
                    pstmt.setString(5, sernum);
                    pstmt.setString(6, datepurch);
                    pstmt.setString(7, status);
                    pstmt.setString(8, notes);
                    pstmt.execute();
                     
                
                }
            }else{
                //This might not run due to the tutorial's use of a gradually increasing ID and my use of a manually entered ID.
                //If error persists, try to update the computers table without the id column.
                final String sql = "UPDATE computers SET compname = ?, os = ?, ip = ?, sernum = ?, datepurch = ?, status = ?, notes = ? WHERE id = ?";
                try(PreparedStatement pstmt = connection.prepareStatement(sql)){
                    
                    //pstmt.setString(1, id);
                    pstmt.setString(1, compname);
                    pstmt.setString(2, os);
                    pstmt.setString(3, ip);
                    pstmt.setString(4, sernum);
                    pstmt.setString(5, datepurch);
                    pstmt.setString(6, status);
                    pstmt.setString(7, notes);
                    pstmt.setString(8, id);
                    pstmt.execute();
                }    
            }
        }*/    
    }
    
    public void update(String id) throws SQLException{
        final String sql = "UPDATE tablets SET tabletname = ?, os = ?, ip = ?, sernum = ?, datepurch = ?, status = ?, notes = ? WHERE id = ?";
        try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){

            //pstmt.setString(1, id);
            pstmt.setString(1, tabletname);
            pstmt.setString(2, os);
            pstmt.setString(3, ip);
            pstmt.setString(4, sernum);
            pstmt.setString(5, datepurch);
            pstmt.setString(6, status);
            pstmt.setString(7, notes);
            pstmt.setString(8, id);
            pstmt.execute();
        }  
    }
}

