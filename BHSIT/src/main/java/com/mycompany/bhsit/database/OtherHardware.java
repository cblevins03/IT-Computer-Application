package com.mycompany.bhsit.database;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * @author cgb_000
 */
public class OtherHardware {
    private String type;
    private String make;
    private String model;
    private String ip;
    private String sernum;
    private String datepurch;
    private String status;
    private String notes;
    private String otherhardware;
    
    // 
    public String getOtherhardware(){
        return otherhardware;
    }
    
    public void SetOtherhardware(final String computers){
        this.otherhardware = otherhardware;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(final String id){
        this.type = type;
    }
    
    public String getMake(){
        return make;
    }
    
    public void setMake(final String compname){
        this.make = make;
    }
    
    public String getModel(){
        return model;
    }
    
    public void setModel(final String os){
        this.model = model;
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
            final String sql = "DELETE FROM otherhardware WHERE sernum = ?";
            try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){ 
                pstmt.setString(1, id);
                pstmt.execute();
                id = null;
            }
        }
    }
    // Enters values into the database using prepared statements
    public void save() throws SQLException{
        
        final String sql = "INSERT INTO otherhardware(type, make, model, ip, sernum, datepurch, status, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){

                pstmt.setString(1, type);
                pstmt.setString(2, make);
                pstmt.setString(3, model);
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
        final String sql = "UPDATE otherhardware SET type = ?, make = ?, model = ?, ip = ?, datepurch = ?, status = ?, notes = ? WHERE sernum = ?";
        try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){

            //pstmt.setString(1, id);
            pstmt.setString(1, type);
            pstmt.setString(2, make);
            pstmt.setString(3, model);
            pstmt.setString(4, ip);
            pstmt.setString(5, datepurch);
            pstmt.setString(6, status);
            pstmt.setString(7, notes);
            pstmt.setString(8, id);
            pstmt.execute();
        }  
    }
}

