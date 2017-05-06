package com.mycompany.bhsit.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * @author cgb_000
 */
public class OtherHardware{
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
    
    public void SetOtherhardware(final String otherhardware){
        this.otherhardware = otherhardware;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(final String type){
        this.type = type;
    }
    
    public String getMake(){
        return make;
    }
    
    public void setMake(final String make){
        this.make = make;
    }
    
    public String getModel(){
        return model;
    }
    
    public void setModel(final String model){
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
    
    public void delete(OtherHardware selected) throws SQLException{
        if(selected.getIp() != null){
            final String sql = "DELETE FROM otherhardware WHERE othertype = ? AND othermake = ? AND othermodel = ? AND otherip = ? AND othersernum = ? AND otherdatepurch = ? AND otherstatus = ? AND othernotes = ?";
            try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){ 
                pstmt.setString(1, selected.getType());
                pstmt.setString(2, selected.getMake());
                pstmt.setString(3, selected.getModel());
                pstmt.setString(4, selected.getIp());
                pstmt.setString(5, selected.getSernum());
                pstmt.setString(6, selected.getDatepurch());
                pstmt.setString(7, selected.getStatus());
                pstmt.setString(8, selected.getNotes());
                pstmt.execute();
                selected.setIp(null);
            }
        }
    }
    // Enters values into the database using prepared statements
    
    public void save() throws SQLException{
        
        final String sql = "INSERT INTO otherhardware VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
    }
    
    
    public void update(String id) throws SQLException{
        final String sql = "UPDATE otherhardware SET othertype = ?, othermake = ?, othermodel = ?, otherip = ?, otherdatepurch = ?, otherstatus = ?, othernotes = ? WHERE othersernum = ?";
        try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){

            //pstmt.setString(1, id);
            pstmt.setString(1, type);
            pstmt.setString(2, make);
            pstmt.setString(3, model);
            pstmt.setString(4, ip);
            pstmt.setString(5, datepurch);
            pstmt.setString(6, status);
            pstmt.setString(7, notes);
            //pstmt.setString(8, id);
            pstmt.execute();
        }  
    }
    
    public void Inserts() throws SQLException{
        
        final String sql = "INSERT INTO otherhardware VALUES ('Mouse', 'Logitech', 'Lightning', '127.0.0.1', '84RG8F7Y8', '2009-09-09', 'Storage', 'Room 123')";
            try(Connection connection = DbHelper.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)){
                pstmt.execute();
            }   
    }
    
    @Override
    public String toString(){
        final StringBuilder formatted = new StringBuilder();
        if(type == null){
            formatted.append("[No ID] ");
        } else {
            formatted.append("[").append(type).append("] ");
        }
        
        if(make == null){
            formatted.append("no Name ");
        }else{
            formatted.append(make).append(" ");
        }
        
        if(model == null){
            formatted.append("no Model");
        }else{
            formatted.append(" [").append(model).append("] ");
        }
        
        if(status == null){
            formatted.append("no Status ");
        }else{
            formatted.append(" [").append(status).append("] ");
        }
        
        return formatted.toString();
    }
}

