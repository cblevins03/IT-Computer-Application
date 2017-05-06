/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bhsit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class automateExport {
    /*public static void main(String[] args) {
        DBase db = new DBase();
        Connection conn = db.connect(
                "jdbc:mysql://localhost:3306/test","root","caspian");
         
        if (args.length != 1) {
            System.out.println(
                    "Usage: java automateExport [outputfile path] ");
            return;
        }
        db.exportData(conn,args[0]);
    }*/
     
}
 
class DBase {
    public DBase() {
    }
     
    public Connection connect(String db_connect_str, 
            String db_userid, String db_password) {
        Connection conn;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(db_connect_str,
                    db_userid, db_password);
             
        } catch(Exception e) {
            e.printStackTrace();
            conn = null;
        }
        return conn;
    }
     
    public void exportData(Connection conn,String filename) {
        Statement stmt;
        String query;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
             
            //For comma separated file
            query = "SELECT compid,compname,compos,compip,compsernum,compdatepurch,compstatus,compnotes,into OUTFILE  '"+filename+
                    "' FIELDS TERMINATED BY ',' FROM computers t";
            stmt.executeQuery(query);
            
            query = "SELECT tabletid,tabletname,tabletos,tabletip,tabletsernum,tabletdatepurch,tabletstatus,tabletnotes, into OUTFILE  '"+filename+
                    "' FIELDS TERMINATED BY ',' FROM tablets t";
            stmt.executeQuery(query);
            
            query = "SELECT othertype,othermake,othermodel,otherip,othersernum,otherdatepurch,otherstatus,othernotes into OUTFILE  '"+filename+
                    "' FIELDS TERMINATED BY ',' FROM computers t";
            stmt.executeQuery(query);
             
        } catch(Exception e) {
            e.printStackTrace();
            stmt = null;
        }
    }
};
