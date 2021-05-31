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
import bkapt.com.model.Lop;
import bkapt.com.model.SinhVien;
import java.sql.CallableStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;


/**
 *
 * @author PC
 */
public class SinhVienDAO extends Connect{
    public SinhVien dangnhap(String maSV, String password){
    SinhVien sv = null;
    try{
        String sql = "Select * from SinhVien where MaSV=? and Password=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSV);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            sv = new SinhVien();
            sv.setMaSV(rs.getString(1));
            sv.setPassword(rs.getString(2));
            sv.setMalop(rs.getString(3));
            sv.setHoTen(rs.getString(4));
            sv.setNgaySinh(rs.getString(5));
            sv.setGioiTinh(rs.getBoolean(6));
            sv.setDiaChi(rs.getString(7));
            sv.setSdt(rs.getString(8));
            sv.setEmail(rs.getString(9));
            
        }
    }catch(Exception e){
        e.getMessage();
    }
        return sv;   
    }
    public ArrayList<SinhVien> load(){
        ArrayList<SinhVien> lists = new ArrayList<SinhVien>();
        try{
            String sql="SELECT *FROM SINHVIEN";
            Statement stm = con.createStatement();
            ResultSet rs= stm.executeQuery(sql);
            lists.clear();
            while(rs.next()){
                SinhVien sv = new SinhVien(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getBoolean(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9));
                lists.add(sv); 
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    return lists;
    }
    
    public int insert(SinhVien sv)
    {
        try
        {
            String sql="Insert into SinhVien values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps =con.prepareStatement(sql);
                ps.setString(1, sv.getMaSV());
                ps.setString(2, sv.getPassword());
                ps.setString(3, sv.getMalop());
                ps.setString(4, sv.getHoTen());
                ps.setString(5, sv.getNgaySinh());
                ps.setBoolean(6, sv.isGioiTinh());
                ps.setString(7, sv.getDiaChi());
                ps.setString(8, sv.getSdt());
                ps.setString(9, sv.getEmail());
             return ps.executeUpdate();
        }
        catch(Exception ex)
        {
            
        }
        return -1;
    }
    public int update(SinhVien sv)
    {
        try
        {
            String sql="Update SinhVien set Password = ?,MaLop = ?, HoTen = ?, NgaySinh  = ?, GioiTinh = ?, DiaChi = ?, SDT = ?, Email = ? WHERE MaSV = ?";
            PreparedStatement ps =con.prepareStatement(sql);
                ps.setString(1, sv.getPassword());
                ps.setString(2, sv.getMalop());
                ps.setString(3, sv.getHoTen());
                ps.setString(4, sv.getNgaySinh());
                ps.setBoolean(5, sv.isGioiTinh());
                ps.setString(6, sv.getDiaChi());
                ps.setString(7, sv.getSdt());
                ps.setString(8, sv.getEmail());
                ps.setString(9, sv.getMaSV());
             return ps.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return -1;
    }
    public boolean delete(String maSV) {
        try {
            String sql = "DELETE FROM SINHVIEN WHERE MASV=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maSV);
            return pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public ArrayList<SinhVien> SearchTen(String tenSV)
    {
        ArrayList<SinhVien> lists = new ArrayList<SinhVien>();
        try
        {
            String sql="SELECT * FROM SINHVIEN WHERE HOTEN like N'%" + tenSV +  "%' or MaSV like '%" + tenSV + "%'" ;
            Statement statement= con.createStatement();
            ResultSet rs =statement.executeQuery(sql);
          while(rs.next())
          {
            SinhVien sv = new SinhVien();
            sv.setMaSV(rs.getString(1));
            sv.setPassword(rs.getString(2));
            sv.setMalop(rs.getString(3));
            sv.setHoTen(rs.getString(4));
            sv.setNgaySinh(rs.getString(5));
            sv.setGioiTinh(rs.getBoolean(6));
            sv.setDiaChi(rs.getString(7));
            sv.setSdt(rs.getString(8));
            sv.setEmail(rs.getString(9));
            lists.add(sv);          
          }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return lists;
    }

    public ArrayList<SinhVien> LoadMa(String maSV)
    {
        ArrayList<SinhVien> lists = new ArrayList<SinhVien>();
        try
        {
            String sql="SELECT * FROM SINHVIEN WHERE MASV LIKE '%" + maSV +  "%'" ;
            Statement statement= con.createStatement();
            ResultSet rs =statement.executeQuery(sql);
          while(rs.next())
          {
            SinhVien sv = new SinhVien();
            sv.setMaSV(rs.getString(1));
            sv.setPassword(rs.getString(2));
            sv.setMalop(rs.getString(3));
            sv.setHoTen(rs.getString(4));
            sv.setNgaySinh(rs.getString(5));
            sv.setGioiTinh(rs.getBoolean(6));
            sv.setDiaChi(rs.getString(7));
            sv.setSdt(rs.getString(8));
            sv.setEmail(rs.getString(9));
            lists.add(sv);          
          }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return lists;
    }
    public void indssv() throws JRException{
    try {
        
        JasperDesign jd = JRXmlLoader.load("src/bkapt/com/print/XuatDSSinhVien.jrxml");
        JasperReport jr = JasperCompileManager.compileReport("src/bkapt/com/print/XuatDSSinhVien.jrxml");
        JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(), con);
        JasperViewer.viewReport(jp);
//        JasperExportManager.exportReportToPdfFile(jp, "test1.pdf");
        } catch (JRException e) {
        
        }
    }
}
