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
public class NhaXB {
    public String maNXB;
    public String ten;
    public String diaChi;
    public String email;

    public NhaXB() {
    }

    public NhaXB(String maNXB, String ten, String diaChi, String email) {
        this.maNXB = maNXB;
        this.ten = ten;
        this.diaChi = diaChi;
        this.email = email;
    }

    public String getMaNXB() {
        return maNXB;
    }

    public String getTen() {
        return ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.ten; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
