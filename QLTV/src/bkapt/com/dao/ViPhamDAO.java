/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.dao;

import bkapt.com.helper.Connect;
import bkapt.com.model.TheLoaiSach;
import bkapt.com.model.ViPham;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Admin
 */
public class ViPhamDAO extends Connect{
      public ArrayList<ViPham> load(){
        ArrayList<ViPham> lists = new ArrayList<ViPham>();
        try{
           String sql="SELECT * FROM ViPham";
           Statement stm = con.createStatement();
           ResultSet rs= stm.executeQuery(sql);
           lists.clear();
            while(rs.next()){
                ViPham vp = new ViPham(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3));
                lists.add(vp); 
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
            return lists;
}

    public int insert(ViPham vp)
    {
        try
        {
            String sql="Insert into ViPham values (?,?,?)";
            PreparedStatement ps =con.prepareStatement(sql);
                ps.setString(1, vp.getMaViPham());
                ps.setString(2, vp.getMaSV());
                ps.setString(3, vp.getTen());
             return ps.executeUpdate();
        }
        catch(Exception ex)
        {
            
        }
        return -1;
    }
    public int update(ViPham vp)
    {
        try
        {
            String sql="Update ViPham set MaSV = ? , Ten = ? Where MaViPham = ?";
            PreparedStatement ps =con.prepareStatement(sql);
                ps.setString(1, vp.getMaViPham());
                ps.setString(2, vp.getMaSV());
                ps.setString(3, vp.getTen());
             return ps.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return -1;
    }
    public boolean delete(String maViPham) {
        try {
            String sql = "DELETE FROM ViPham WHERE MaViPham=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maViPham);
            return pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public ArrayList<ViPham> SearchTen(String ma)
    {
        ArrayList<ViPham> lists = new ArrayList<ViPham>();
        try
        {
            String sql="SELECT * FROM ViPham WHERE MaViPham like '%" + ma +  "%' or MaSV like '%" + ma + "%'" ;
            Statement statement= con.createStatement();
            ResultSet rs =statement.executeQuery(sql);
          while(rs.next())
          {
            ViPham vp = new ViPham();
            vp.setMaViPham(rs.getString(1));
            vp.setMaSV(rs.getString(2));
            vp.setTen(rs.getString(3));
            lists.add(vp);          
          }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return lists;
    }

    public void indstls() throws JRException{
    try {

        JasperDesign jd = JRXmlLoader.load("src/bkapt/com/print/XuatDSTheLoai.jrxml");
        JasperReport jr = JasperCompileManager.compileReport("src/bkapt/com/print/XuatDSTheLoai.jrxml");
        JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(), con);
        JasperViewer.viewReport(jp);
//        JasperExportManager.exportReportToPdfFile(jp, "test1.pdf");
        } catch (JRException e) {
        
        }
    }
}
