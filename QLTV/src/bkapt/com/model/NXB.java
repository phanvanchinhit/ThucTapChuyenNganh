/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.model;

/**
 *
 * @author Admin
 */
public class NXB {
    public String maNXB;
    public String ten;
    public String diaChi;
    public String Email;

    public NXB() {
    }

    public NXB(String maNXB, String ten, String diaChi, String Email) {
        this.maNXB = maNXB;
        this.ten = ten;
        this.diaChi = diaChi;
        this.Email = Email;
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
        return Email;
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

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return this.ten;
    }
    
    
}
