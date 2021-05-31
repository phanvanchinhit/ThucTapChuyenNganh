/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.dao;

import bkapt.com.helper.Connect;
import bkapt.com.model.SachPhieuMuon;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SachPhieuMuonDao extends Connect{
    public List<SachPhieuMuon> findId(String id){
        List<SachPhieuMuon> list = new ArrayList<>();
        CallableStatement cs;
        try {
            cs = con.prepareCall("{call sp_MaSachLoad(?)}");
             cs.setString(1, id);
             ResultSet rs = cs.executeQuery();
             while (rs.next()) { 
                SachPhieuMuon muon = new SachPhieuMuon();
                muon.setMaSach(rs.getString(1));
                list.add(muon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SachPhieuMuonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return list;
    }
    public int insert(String maPhieu,String maSach){
        String sql = "INSERT INTO SachPhieuMuon VALUES(?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(sql);
            ps.setString(1, maPhieu);
            ps.setString(2, maSach);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }
    public int delete(String maPhieu){
        String sql = "DELETE FROM SachPhieuMuon WHERE MaPhieu = "+ maPhieu;
        PreparedStatement ps;
        try {
            ps = con.prepareCall(sql);
            ps.executeUpdate();
            return ps.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }
}
