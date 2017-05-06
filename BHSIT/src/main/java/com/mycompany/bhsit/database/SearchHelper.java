package com.mycompany.bhsit.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cgb_000
 */
public class SearchHelper {
    private static final SearchHelper INSTANCE = new SearchHelper();
    
    public static SearchHelper getInstance(){
        return SearchHelper.INSTANCE;
    }
    
    public SearchHelper(){
    }
    
    public List<Search> getSearch() throws SQLException{
        final List<Search> searches = new ArrayList<>();
        final String csql = "SELECT * FROM computers ORDER BY compid";
        final String tsql = "SELECT * FROM tablets ORDER BY tabletid";
        final String osql = "SELECT * FROM otherhardware";
     
        try(Connection connection = DbHelper.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(csql);
            ResultSet rs = pstmt.executeQuery()){
        
            while(rs.next()){
                final Search search = new Search();
                search.setCompId(rs.getString("compid"));
                search.setCompname(rs.getString("compname"));
                search.setCompOs(rs.getString("compos"));
                search.setCompIp(rs.getString("compip"));
                search.setCompSernum(rs.getString("compsernum"));
                search.setCompDatepurch(rs.getString("compdatepurch"));
                search.setCompStatus(rs.getString("compstatus"));
                search.setCompNotes(rs.getString("compnotes"));
                searches.add(search);
            }  
        }
        
        try(Connection connection = DbHelper.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(tsql);
            ResultSet rs = pstmt.executeQuery()){
        
            while(rs.next()){
                final Search search = new Search();
                search.setTabletId(rs.getString("tabletid"));
                search.setTabletname(rs.getString("tabletname"));
                search.setTabletOs(rs.getString("tabletos"));
                search.setTabletIp(rs.getString("tabletip"));
                search.setTabletSernum(rs.getString("tabletsernum"));
                search.setTabletDatepurch(rs.getString("tabletdatepurch"));
                search.setTabletStatus(rs.getString("tabletstatus"));
                search.setTabletNotes(rs.getString("tabletnotes"));
                searches.add(search);
            }  
        }
        
        try(Connection connection = DbHelper.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(osql);
            ResultSet rs = pstmt.executeQuery()){
        
            while(rs.next()){
                final Search search = new Search();
                search.setType(rs.getString("othertype"));
                search.setMake(rs.getString("othermake"));
                search.setModel(rs.getString("othermodel"));
                search.setOtherIp(rs.getString("otherip"));
                search.setOtherSernum(rs.getString("othersernum"));
                search.setOtherDatepurch(rs.getString("otherdatepurch"));
                search.setOtherStatus(rs.getString("otherstatus"));
                search.setOtherNotes(rs.getString("othernotes"));
                searches.add(search);
            }  
        }
        return searches;
    }
    
    public List<Search> Search(String query) throws SQLException{
        final List<Search> searches = new ArrayList<>();
        final String csql = "SELECT * FROM computers WHERE compid LIKE '%" + query + "%' OR compname LIKE '%" + query + "%' OR compos LIKE '%" + query + "%' OR compip LIKE '%" + query + "%' OR compsernum LIKE '%" + query + "%' OR compdatepurch LIKE '%" + query + "%' OR compstatus LIKE '%" + query + "%' OR compnotes LIKE '%" + query + "%';";
        final String tsql = "SELECT * FROM tablets WHERE tabletid LIKE '%" + query + "%' OR tabletname LIKE '%" + query + "%' OR tabletos LIKE '%" + query + "%' OR tabletip LIKE '%" + query + "%' OR tabletsernum LIKE '%" + query + "%' OR tabletdatepurch LIKE '%" + query + "%' OR tabletstatus LIKE '%" + query + "%' OR tabletnotes LIKE '%" + query + "%';";
        final String osql = "SELECT * FROM otherhardware WHERE othertype LIKE '%" + query + "%' OR othermake LIKE '%" + query + "%' OR othermodel LIKE '%" + query + "%' OR otherip LIKE '%" + query + "%' OR othersernum LIKE '%" + query + "%' OR otherdatepurch LIKE '%" + query + "%' OR otherstatus LIKE '%" + query + "%' OR othernotes LIKE '%" + query + "%';";
        try(Connection connection = DbHelper.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(csql);
            ResultSet rs = pstmt.executeQuery()){
        
            while(rs.next()){
                final Search search = new Search();
                search.setCompId(rs.getString("compid"));
                search.setCompname(rs.getString("compname"));
                search.setCompOs(rs.getString("compos"));
                search.setCompIp(rs.getString("compip"));
                search.setCompSernum(rs.getString("compsernum"));
                search.setCompDatepurch(rs.getString("compdatepurch"));
                search.setCompStatus(rs.getString("compstatus"));
                search.setCompNotes(rs.getString("compnotes"));
                searches.add(search);
            }  
        }
        
        try(Connection connection = DbHelper.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(tsql);
            ResultSet rs = pstmt.executeQuery()){
        
            while(rs.next()){
                final Search search = new Search();
                search.setTabletId(rs.getString("tabletid"));
                search.setTabletname(rs.getString("tabletname"));
                search.setTabletOs(rs.getString("tabletos"));
                search.setTabletIp(rs.getString("tabletip"));
                search.setTabletSernum(rs.getString("tabletsernum"));
                search.setTabletDatepurch(rs.getString("tabletdatepurch"));
                search.setTabletStatus(rs.getString("tabletstatus"));
                search.setTabletNotes(rs.getString("tabletnotes"));
                searches.add(search);
                
            }  
        }
        
        try(Connection connection = DbHelper.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(osql);
            ResultSet rs = pstmt.executeQuery()){
        
            while(rs.next()){
                final Search search = new Search();
                search.setType(rs.getString("othertype"));
                search.setMake(rs.getString("othermake"));
                search.setModel(rs.getString("othermodel"));
                search.setOtherIp(rs.getString("otherip"));
                search.setOtherSernum(rs.getString("othersernum"));
                search.setOtherDatepurch(rs.getString("otherdatepurch"));
                search.setOtherStatus(rs.getString("otherstatus"));
                search.setOtherNotes(rs.getString("othernotes"));
                searches.add(search);
            }  
        }
        //ExportDB();
        return searches;
    }
    
    public String GetTotals(int choose, String table, String status) throws SQLException{
        if(choose == 1){
            final String sql = "SELECT count(*) AS rowcount FROM " + table + " WHERE compid LIKE '%" + status + "%' OR compname LIKE '%" + status + "%' OR compos LIKE '%" + status + "%' OR compip LIKE '%" + status + "%' OR compsernum LIKE '%" + status + "%' OR compdatepurch LIKE '%" + status + "%' OR compstatus LIKE '%" + status + "%' OR compnotes LIKE '%" + status + "%';";
            try(Connection connection = DbHelper.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
                rs.next();
                String compuse = rs.getString("rowcount");
                return compuse;
            }
        }else if(choose == 2){      
            final String sql = "SELECT count(*) AS rowcount FROM " + table + " WHERE tabletid LIKE '%" + status + "%' OR tabletname LIKE '%" + status + "%' OR tabletos LIKE '%" + status + "%' OR tabletip LIKE '%" + status + "%' OR tabletsernum LIKE '%" + status + "%' OR tabletdatepurch LIKE '%" + status + "%' OR tabletstatus LIKE '%" + status + "%' OR tabletnotes LIKE '%" + status + "%';";
            try(Connection connection = DbHelper.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
                rs.next();
                String compuse = rs.getString("rowcount");
                return compuse;
            }
        }else{      
            final String sql = "SELECT count(*) AS rowcount FROM " + table + " WHERE othertype LIKE '%" + status + "%' OR othermake LIKE '%" + status + "%' OR othermake LIKE '%" + status + "%' OR otherip LIKE '%" + status + "%' OR othersernum LIKE '%" + status + "%' OR otherdatepurch LIKE '%" + status + "%' OR otherstatus LIKE '%" + status + "%' OR othernotes LIKE '%" + status + "%';";
            try(Connection connection = DbHelper.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
                rs.next();
                String compuse = rs.getString("rowcount");
                return compuse;
            }
        }
    }
    
    public String GetCategoryTotals(int choose, String table) throws SQLException{
        if(choose == 1){
            final String sql = "SELECT count(*) AS rowcount FROM " + table + ";";
            try(Connection connection = DbHelper.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
                rs.next();
                String compuse = rs.getString("rowcount");
                return compuse;
            }
        }else if(choose == 2){      
            final String sql = "SELECT count(*) AS rowcount FROM " + table + ";";
            try(Connection connection = DbHelper.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
                rs.next();
                String compuse = rs.getString("rowcount");
                return compuse;
            }
        }else{      
            final String sql = "SELECT count(*) AS rowcount FROM " + table + ";";
            try(Connection connection = DbHelper.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
                rs.next();
                String compuse = rs.getString("rowcount");
                return compuse;
            }
        }
    }
    /*
    public void ExportDB() throws SQLException{
        DBase db = new DBase();
        try(Connection connection = DbHelper.getConnection();){
            db.exportData(connection, "test.csv");
        }
    }
    */
}
