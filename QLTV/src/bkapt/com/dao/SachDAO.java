/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
import bkapt.com.model.Sach;
import java.sql.CallableStatement;


/**
 *
 * @author PC
 */
public class SachDAO extends Connect{
    public ArrayList<Sach> load(){
        ArrayList<Sach> lists = new ArrayList<Sach>();
        try{
          String sql="SELECT * FROM Sach";
          Statement stm=con.createStatement();
          ResultSet rs= stm.executeQuery(sql);
//          lists.clear();
            while(rs.next()){
                Sach s = new Sach(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),        
                rs.getString(9));
                lists.add(s);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return lists;
    }

    
    public int insert(Sach s)
    {
        try
        {
            String sql="Insert into Sach values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps =con.prepareStatement(sql);
                ps.setString(1, s.getMaSach());
                ps.setString(2, s.getTenSach());
                ps.setString(3, s.getMaTheLoai());
                ps.setString(4, s.getTacGia());
                ps.setInt(5, s.getSoLuong());
                ps.setString(6, s.getNxb());
                ps.setString(7, s.getNgayNhap());
                ps.setString(8, s.getNdtt());
                ps.setString(9, s.getHinh());
             return ps.executeUpdate();
        }
        catch(Exception ex)
        {
           
        }
        return -1;
    }
    public int update(Sach s)
    {
        try
        {
            String sql="Update Sach set TenSach=?, MaTheLoai=?, MaTG=?, SoLuong=?, MaNXB=?, NgayNhap=?, NDTT=?, Hinh=? Where MaSach=?";
            PreparedStatement ps =con.prepareStatement(sql);
                ps.setString(1, s.getTenSach());
                ps.setString(2, s.getMaTheLoai());
                ps.setString(3, s.getTacGia());
                ps.setInt(4, s.getSoLuong());
                ps.setString(5, s.getNxb());
                ps.setString(6, s.getNgayNhap());
                ps.setString(7, s.getNdtt());
                ps.setString(8, s.getHinh());
                ps.setString(9, s.getMaSach());
             return ps.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return -1;
    }
    public boolean delete(String maSach) {
        try {
            String sql = "DELETE FROM Sach WHERE MaSach=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maSach);
            return pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public ArrayList<Sach> SearchTen(String tenSach)
    {
        ArrayList<Sach> lists = new ArrayList<Sach>();
        try
        {
            String sql="SELECT * FROM Sach WHERE TenSach like N'%" + tenSach +  "%' or MaTheLoai like '%" + tenSach + "%' or MaSach like '%" + tenSach + "%'" ;
            Statement statement= con.createStatement();
            ResultSet rs =statement.executeQuery(sql);
          while(rs.next())
          {
            Sach s= new Sach();
            s.setMaSach(rs.getString(1));
            s.setTenSach(rs.getString(2));
            s.setMaTheLoai(rs.getString(3));
            s.setTacGia(rs.getString(4));
            s.setSoLuong(rs.getInt(5));
            s.setNxb(rs.getString(6));
            s.setNgayNhap(rs.getString(7));
            s.setNdtt(rs.getString(8));
            s.setHinh(rs.getString(9));
            lists.add(s);          
          }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return lists;
    }
    public Sach findId(String maSach)
    {
        try
        {
            String sql="SELECT * FROM Sach WHERE MaSach=?" ;
            
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, maSach);
            ResultSet rs =cs.executeQuery();
            
          while(rs.next())
          {
            Sach s= new Sach();
            s.setMaSach(rs.getString(1));
            s.setTenSach(rs.getString(2));
            s.setMaTheLoai(rs.getString(3));
            s.setTacGia(rs.getString(4));
            s.setSoLuong(rs.getInt(5));
            s.setNxb(rs.getString(6));
            s.setNgayNhap(rs.getString(7));
            s.setNdtt(rs.getString(8));
            s.setHinh(rs.getString(9));
            return s;         
          }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    public ArrayList<Sach> SearchTenSachMTL(String tenSach)
    {
        ArrayList<Sach> lists = new ArrayList<Sach>();
        try
        {
            String sql="SELECT * FROM Sach WHERE TenSach like N'%" + tenSach +  "%' or MaTheLoai like '%" + tenSach + "%'" ;
            Statement statement= con.createStatement();
            ResultSet rs =statement.executeQuery(sql);
          while(rs.next())
          {
            Sach s= new Sach();
            s.setMaSach(rs.getString(1));
            s.setTenSach(rs.getString(2));
            s.setMaTheLoai(rs.getString(3));
            s.setTacGia(rs.getString(4));
            s.setSoLuong(rs.getInt(5));
            s.setNxb(rs.getString(6));
            s.setNgayNhap(rs.getString(7));
            s.setNdtt(rs.getString(8));
            s.setHinh(rs.getString(9));
            lists.add(s);          
          }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return lists;
    }

    public void indssach() throws JRException{
    try {

        JasperDesign jd = JRXmlLoader.load("src/bkapt/com/print/XuatDSSach.jrxml");
        JasperReport jr = JasperCompileManager.compileReport("src/bkapt/com/print/XuatDSSach.jrxml");
        JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(), con);
        JasperViewer.viewReport(jp);
//        JasperExportManager.exportReportToPdfFile(jp, "test1.pdf");
        } catch (JRException e) {
        
        }
    }
}

