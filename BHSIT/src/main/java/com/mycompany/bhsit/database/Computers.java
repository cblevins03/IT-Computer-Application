/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bhsit.database;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
        
        /*if(os == null){
            formatted.append("no Operating System ");
        }else{
            formatted.append(" (").append(os).append(") ");
        }
        
        if(ip == null){
            formatted.append("no IP ");
        }else{
            formatted.append(ip).append(" ");
        }
        
        if(sernum == null){
            formatted.append("no Serial ");
        }else{
            formatted.append(" [").append(sernum).append("] ");
        }
        
        if(datepurch == null){
            formatted.append("no Date ");
        }else{
            formatted.append(datepurch).append(" ");
        }*/
        
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
    
    public void delete() throws SQLException{
        if(id != null){
            final String sql = "DELETE FROM computers WHERE id = ?";
            try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){ 
                pstmt.setString(1, id);
                pstmt.execute();
                id = null;
            }
        }
    }
    // Enters values into the database using prepared statements
    public void save() throws SQLException{
        
        final String sql = "INSERT INTO computers(id, compname, os, ip, sernum, datepurch, status, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
        final String sql = "UPDATE computers SET compname = ?, os = ?, ip = ?, sernum = ?, datepurch = ?, status = ?, notes = ? WHERE id = ?";
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
}

