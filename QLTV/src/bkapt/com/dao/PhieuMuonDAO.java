/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import bkapt.com.helper.Connect;
import bkapt.com.model.PhieuMuon;
import bkapt.com.model.Sach;
import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class PhieuMuonDAO extends Connect{
    public ArrayList<PhieuMuon> load(){
        ArrayList<PhieuMuon> lists = new ArrayList<PhieuMuon>();
        try{
          String sql="Select * from PhieuMuon";
          Statement stm=con.createStatement();
          ResultSet rs= stm.executeQuery(sql);
//          lists.clear();
            while(rs.next()){
                    PhieuMuon pm = new PhieuMuon(
                    rs.getString(1),
                    rs.getString(2), 
                    rs.getString(3),
                    rs.getString(4));
                    lists.add(pm);
                
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return lists;
    }

    public int insert(PhieuMuon pm,String sach)
    {
        try
        {
            String sql="call sp_TaoPhieu(?,?,?,?,?)";
            CallableStatement ps =con.prepareCall(sql);
                ps.setString(1,pm.getMaPhieuMuon());
                ps.setString(2, pm.getMaSV());
                ps.setString(3, sach);
                ps.setString(4, pm.getNgayMuon());
                ps.setString(5, pm.getNgayHenTra());
             return ps.executeUpdate();
           
        }
        catch(Exception ex)
        {
            
        }
        return -1;
    }
    public int update(PhieuMuon pm,String sach)
    {
        try
        {
            String sql="{call sp_SuaPhieu}";
            CallableStatement ps =con.prepareCall(sql);
                ps.setString(1,pm.getMaPhieuMuon());
                ps.setString(2, pm.getMaSV());
                ps.setString(3, sach);
                ps.setString(4, pm.getNgayMuon());
                ps.setString(5, pm.getNgayHenTra());
             return ps.executeUpdate();
              
        }
        catch(Exception ex)
        {
            
        }
        return -1;
    }
    public boolean delete(String maPhieuMuon) {
        try {
            String sql = "DELETE FROM PhieuMuon WHERE MaPhieuMuon=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maPhieuMuon);
            return pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public ArrayList<PhieuMuon> SearchMaSV(String ma)
    {
        ArrayList<PhieuMuon> lists = new ArrayList<PhieuMuon>();
        try
        {
            String sql="SELECT * FROM PhieuMuon WHERE MaSV like '%" + ma +  "%'" ;
            Statement statement= con.createStatement();
            ResultSet rs =statement.executeQuery(sql);
          while(rs.next())
          {
            PhieuMuon pm = new PhieuMuon();
            pm.setMaPhieuMuon(rs.getString(1));
            pm.setMaSV(rs.getString(2));
            pm.setNgayMuon(rs.getString(3));
            pm.setNgayHenTra(rs.getString(4));
            lists.add(pm);          
          }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return lists;
    }

    public void inphieumuon(String maPhieu){
        try {
            Hashtable map = new Hashtable();
            JasperReport report = JasperCompileManager.compileReport("src/bkapt/com/print/XuatPhieuMuon.jrxml");
            map.put("MaPhieuMuon", maPhieu);
            JasperPrint p = JasperFillManager.fillReport(report,  map, con );
            JasperViewer.viewReport(p, false);
//            JasperExportManager.exportReportToPdfFile(p, "test.pdf");
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
    public void indsphieumuon() throws JRException{
    try {

        JasperDesign jd = JRXmlLoader.load("src/bkapt/com/print/XuatDSPhieuMuon.jrxml");
        JasperReport jr = JasperCompileManager.compileReport("src/bkapt/com/print/XuatDSPhieuMuon.jrxml");
        JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(), con);
        JasperViewer.viewReport(jp);
//        JasperExportManager.exportReportToPdfFile(jp, "test1.pdf");
        } catch (JRException e) {
        
        }
    }
}
