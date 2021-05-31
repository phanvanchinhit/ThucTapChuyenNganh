/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.dao;

import bkapt.com.helper.Connect;
import bkapt.com.model.Lop;
import bkapt.com.model.TacGia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author PhungLong
 */
public class TacGiaDAO extends Connect{
    ArrayList<TacGia> lists = new ArrayList<TacGia>();
    public ArrayList<TacGia> load(){
        try{
           String sql="SELECT * FROM TacGia";
           Statement stm = con.createStatement();
           ResultSet rs= stm.executeQuery(sql);
           lists.clear();
            while(rs.next()){
                TacGia tg = new TacGia(
                rs.getString(1),
                rs.getString(2),
                rs.getDate(3).toString(),
                rs.getString(4),
                rs.getString(5));
                lists.add(tg); 
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    return lists;
    }
    public int insert(TacGia tg){
            try
            {
                String sql="Insert into TacGia values (?,?,?,?,?)";
                PreparedStatement ps =con.prepareStatement(sql);
                    ps.setString(1, tg.getMatg());
                    ps.setString(2, tg.getTen());
                    ps.setString(3, tg.getNgaysinh());
                    ps.setString(4, tg.getDiachi());
                    ps.setString(5, tg.getEmail());
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
                String sql="Update TacGia set Ten = ?, NgaySinh = ?, DiaChi = ? ,Email =? WHERE MaTG = ?";
                PreparedStatement ps =con.prepareStatement(sql);
                    ps.setString(1, tg.getTen());
                    ps.setString(2, tg.getNgaysinh());
                    ps.setString(3, tg.getDiachi());
                    ps.setString(4, tg.getEmail());
                    ps.setString(5, tg.getMatg());
                 return ps.executeUpdate();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            return -1;
        }
    public boolean delete(String ten) {
        try {
            String sql = "DELETE FROM TacGia WHERE Ten=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,ten);
            return pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public ArrayList<TacGia> SearchMatg(String ten)
    {
        ArrayList<TacGia> lists = new ArrayList<TacGia>();
        try
        {
            String sql="SELECT * FROM TacGia WHERE Ten like N'%" + ten+  "%' or MaTG like '%" + ten+ "%'" ;
            Statement statement= con.createStatement();
            ResultSet rs =statement.executeQuery(sql);
          while(rs.next())
          {
            TacGia tg = new TacGia();
            tg.setMatg(rs.getString(1));
            tg.setTen(rs.getString(2));
            tg.setNgaysinh(rs.getString(3));
            tg.setDiachi(rs.getString(4));
            tg.setEmail(rs.getString(5));
            
            lists.add(tg);          
          }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return lists;
    }
}
