/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.dao;

import bkapt.com.helper.Connect;
import bkapt.com.model.NXB;
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
public class NXBDAO extends Connect{
    public ArrayList<NXB> load(){
        ArrayList<NXB> lists = new ArrayList<NXB>();
        try{
           String sql="SELECT * FROM NXB";
           Statement stm = con.createStatement();
           ResultSet rs= stm.executeQuery(sql);
           lists.clear();
            while(rs.next()){
                NXB nxb = new NXB(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4));
                lists.add(nxb); 
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
            return lists;
}

    public int insert(NXB vp)
    {
        try
        {
            String sql="Insert into NXB values (?,?,?,?)";
            PreparedStatement ps =con.prepareStatement(sql);
                ps.setString(1, vp.getMaNXB());
                ps.setString(2, vp.getTen());
                ps.setString(3, vp.getDiaChi());
                ps.setString(4, vp.getEmail());
             return ps.executeUpdate();
        }
        catch(Exception ex)
        {
            
        }
        return -1;
    }
    public int update(NXB vp)
    {
        try
        {
            String sql="Update NXB set Ten = ? ,DiaChi = ? ,Email = ? Where MaNXB = ?";
            PreparedStatement ps =con.prepareStatement(sql);
                ps.setString(1, vp.getMaNXB());
                ps.setString(2, vp.getTen());
                ps.setString(3, vp.getDiaChi());
                ps.setString(4, vp.getEmail());
             return ps.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return -1;
    }
    public boolean delete(String maNXB) {
        try {
            String sql = "DELETE FROM NXB WHERE MaNXB=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maNXB);
            return pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public ArrayList<NXB> SearchTen(String key)
    {
        ArrayList<NXB> lists = new ArrayList<NXB>();
        try
        {
            String sql="SELECT * FROM NXB WHERE MaNXB like '%" + key +  "%' or Ten like '%" + key + "%'" ;
            Statement statement= con.createStatement();
            ResultSet rs =statement.executeQuery(sql);
          while(rs.next())
          {
            NXB nxb = new NXB();
            nxb.setMaNXB(rs.getString(1));
            nxb.setTen(rs.getString(2));
            nxb.setDiaChi(rs.getString(3));
            nxb.setEmail(rs.getString(4));
            lists.add(nxb);          
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
