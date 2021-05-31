/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.dao;

import bkapt.com.helper.Connect;
import bkapt.com.model.NhaXB;
import bkapt.com.model.TacGia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhaXBDAO extends Connect{
     ArrayList<NhaXB> lists = new ArrayList<NhaXB>();
    public ArrayList<NhaXB> load(){
        try{
           String sql="SELECT * FROM NXB";
           Statement stm = con.createStatement();
           ResultSet rs= stm.executeQuery(sql);
           lists.clear();
            while(rs.next()){
                NhaXB nxb = new NhaXB(
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
    public int insert(TacGia tg){
            try
            {
                String sql="Insert into NXB values (?,?,?,?)";
                PreparedStatement ps =con.prepareStatement(sql);
                    ps.setString(1, tg.getMatg());
                    ps.setString(2, tg.getTen());
                    ps.setString(3, tg.getDiachi());
                    ps.setString(4, tg.getEmail());
                 return ps.executeUpdate();
            }
            catch(Exception ex){
            }
            return -1;
        }
    public int update(TacGia tg)
        {
            try
            {
                String sql="Update NhaXB set Ten = ?,DiaChi = ? ,Email =? WHERE MaNXB = ?";
                PreparedStatement ps =con.prepareStatement(sql);
                    ps.setString(1, tg.getTen());
                    ps.setString(2, tg.getDiachi());
                    ps.setString(3, tg.getEmail());
                    ps.setString(4, tg.getMatg());
                 return ps.executeUpdate();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            return -1;
        }
    public boolean delete(String MaNXB) {
        try {
            String sql = "DELETE FROM NXB WHERE MaNXB=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,MaNXB);
            return pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public ArrayList<NhaXB> SearchMatg(String ten)
    {
        ArrayList<NhaXB> lists = new ArrayList<NhaXB>();
        try
        {
            String sql="SELECT * FROM NXB WHERE Ten like N'%" + ten+  "%' or MaNXB like '%" + ten+ "%'" ;
            Statement statement= con.createStatement();
            ResultSet rs =statement.executeQuery(sql);
          while(rs.next())
          {
            NhaXB nxb = new NhaXB();
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
}
