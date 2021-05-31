/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.model;

/**
 *
 * @author PhungLong
 */
public class TacGia {
    private String matg;
    private String ten;
    private String ngaysinh;
    private String diachi;
    private String email; 
    
    public TacGia(){
    }

    public TacGia(String matg, String ten, String ngaysinh, String diachi, String email) {
        this.matg = matg;
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.email = email;
    }

    public TacGia(String string, String string0, boolean add) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getMatg() {
        return matg;
    }

    public void setMatg(String matg) {
        this.matg = matg;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.ten; //To change body of generated methods, choose Tools | Templates.
    }
    
}
