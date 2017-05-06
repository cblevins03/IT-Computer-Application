package com.mycompany.bhsit.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * @author cgb_000
 */
public class Computers {
    private String id;
    private String compname;
    private String os;
    private String ip;
    private String sernum;
    private String datepurch;
    private String status;
    private String notes;
    private String computers;
    
    
    public String getComputers(){
        return computers;
    }
    
    public void setComputers(final String computers){
        this.computers = computers;
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(final String id){
        this.id = id;
    }
    
    public String getCompname(){
        return compname;
    }
    
    public void setCompname(final String compname){
        this.compname = compname;
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
    
    
    
    @Override
    public String toString(){
        final StringBuilder formatted = new StringBuilder();
        if(id == null){
            formatted.append("[No ID] ");
        } else {
            formatted.append("[").append(id).append("] ");
        }
        
        if(compname == null){
            formatted.append("no Name ");
        }else{
            formatted.append(compname).append(" ");
        }
        
        if(status == null){
            formatted.append("no Status ");
        }else{
            formatted.append(" [").append(status).append("] ");
        }
        
        /*if(notes == null){
            formatted.append("no Notes ");
        }else{
            formatted.append(notes);
        }*/
        
        return formatted.toString();
    }
    
    public void delete(Computers selected) throws SQLException{
        if(selected.getId() != null){
            final String sql = "DELETE FROM computers WHERE compid = ? AND compname = ? AND compos = ? AND compip = ? AND compsernum = ? AND compdatepurch = ? AND compstatus = ? AND compnotes = ?";
            try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){ 
                pstmt.setString(1, selected.getId());
                pstmt.setString(2, selected.getCompname());
                pstmt.setString(3, selected.getOs());
                pstmt.setString(4, selected.getIp());
                pstmt.setString(5, selected.getSernum());
                pstmt.setString(6, selected.getDatepurch());
                pstmt.setString(7, selected.getStatus());
                pstmt.setString(8, selected.getNotes());
                pstmt.execute();
                selected.setId(null);
            }
        }
    }
    // Enters values into the database using prepared statements
    public void save() throws SQLException{
        
        final String sql = "INSERT INTO computers VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){

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
    }
    
    public void update(String id) throws SQLException{
        final String sql = "UPDATE computers SET compname = ?, compos = ?, compip = ?, compsernum = ?, compdatepurch = ?, compstatus = ?, compnotes = ? WHERE compid = ?";
        try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){

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
    
    public void Inserts() throws SQLException{
        
        final String sql = "INSERT INTO computers VALUES ('5789', 'maclibrary', 'OS X', '127.0.0.1', '84RG8F7Y8', '2009-09-09', 'Use', 'Room 305')";
            try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){
                pstmt.execute();
            }   
    }
    
    public void SetLaunch(int launch) throws SQLException{
        
        final String sql = "UPDATE launch SET launch = '" + launch + "'";
            try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){
                pstmt.execute();
            }   
    }
}

