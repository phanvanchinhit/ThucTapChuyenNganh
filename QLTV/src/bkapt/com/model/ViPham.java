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
public class ViPham {
    public String maViPham;
    public String maSV;
    public String ten;

    public ViPham() {
    }

    public ViPham(String maViPham, String maSV, String ten) {
        this.maViPham = maViPham;
        this.maSV = maSV;
        this.ten = ten;
    }

    public String getMaViPham() {
        return maViPham;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getTen() {
        return ten;
    }

    public void setMaViPham(String maViPham) {
        this.maViPham = maViPham;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return "ViPham{" + "maViPham=" + maViPham + ", maSV=" + maSV + ", ten=" + ten + '}';
    }
    
    
}
