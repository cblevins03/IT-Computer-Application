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
public class OtherHardwareHelper {
    
    private static final OtherHardwareHelper INSTANCE = new OtherHardwareHelper();
    
    public static OtherHardwareHelper getInstance(){
        return OtherHardwareHelper.INSTANCE;
    }
    
    private OtherHardwareHelper(){
    }
    
    public List<OtherHardware> getOtherHardware() throws SQLException{
        final List<OtherHardware> otherHardwareArray = new ArrayList<>();
        final String sql = "SELECT * FROM otherhardware";
        
        try(Connection connection = DbHelper.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
        
            while(rs.next()){
                final OtherHardware otherHardware = new OtherHardware();
                otherHardware.setType(rs.getString("othertype"));
                otherHardware.setMake(rs.getString("othermake"));
                otherHardware.setModel(rs.getString("othermodel"));
                otherHardware.setIp(rs.getString("otherip"));
                otherHardware.setSernum(rs.getString("othersernum"));
                otherHardware.setDatepurch(rs.getString("otherdatepurch"));
                otherHardware.setStatus(rs.getString("otherstatus"));
                otherHardware.setNotes(rs.getString("othernotes"));
                //computer.setComputers(rs.getString("computers"));
                otherHardwareArray.add(otherHardware);
            }  
        }
        return otherHardwareArray;
    }
}
