/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.dao;
import bkapt.com.helper.Connect;
import bkapt.com.model.Lop;
import bkapt.com.model.SinhVien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author PhungLong
 */

public class LopDAO extends Connect{
    ArrayList<Lop> lists= new ArrayList<Lop>();
    DefaultComboBoxModel modelCbo;
    public ArrayList<Lop> load(){
        try{
           String sql="SELECT * FROM Lop";
           Statement stm = con.createStatement();
           ResultSet rs= stm.executeQuery(sql);
           lists.clear();
            while(rs.next()){
                Lop lp = new Lop(
                rs.getString(1),
                rs.getString(2));
                lists.add(lp); 
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    return lists;
    }
    public DefaultComboBoxModel loadSelected(){
        return modelCbo;
    }
    public int insert(Lop lp)
        {
            try
            {
                String sql="Insert into Lop values (?,?)";
                PreparedStatement ps =con.prepareStatement(sql);
                    ps.setString(1, lp.getMalop());
                    ps.setString(2, lp.getTen());
                 return ps.executeUpdate();
            }
            catch(Exception ex)
            {

            }
            return -1;
        }
        public int update(Lop lp)
        {
            try
            {
                String sql="Update Lop set Ten = ? WHERE MaLop = ?";
                PreparedStatement ps =con.prepareStatement(sql);
                    ps.setString(1, lp.getTen());
                    ps.setString(2, lp.getMalop());
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
            String sql = "DELETE FROM Lop WHERE Ten=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,ten);
            return pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public ArrayList<Lop> SearchMalop(String ten)
    {
        ArrayList<Lop> lists = new ArrayList<Lop>();
        try
        {
            String sql="SELECT * FROM Lop WHERE Ten like N'%" + ten +  "%' or MaLop like '%" + ten + "%'" ;
            Statement statement= con.createStatement();
            ResultSet rs =statement.executeQuery(sql);
          while(rs.next())
          {
            Lop lp = new Lop();
            lp.setMalop(rs.getString(1));
            lp.setTen(rs.getString(2));
            
            lists.add(lp);          
          }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return lists;
    }

//    public ArrayList<Lop> loadlop(String malop)
//    {
//        ArrayList<Lop> lists = new ArrayList<Lop>();
//        try
//        {
//            String sql="SELECT * FROM Lop WHERE MaLop like '%" + malop +  "%'" ;
//            Statement statement= con.createStatement();
//            ResultSet rs =statement.executeQuery(sql);
//          while(rs.next())
//          {
//            Lop lp = new Lop();
//            lp.setMalop(rs.getString(1));
//            lp.setTen(rs.getString(2));
//            lists.add(lp);          
//          }
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return lists;
//    }
    
}
