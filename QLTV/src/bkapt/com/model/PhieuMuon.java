/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.model;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class PhieuMuon {
    private String maPhieuMuon;
    private String maSV;
    private String ngayMuon;
    private String ngayHenTra;

    public PhieuMuon() {
    }

    public PhieuMuon(String maPhieuMuon, String maSV, String ngayMuon, String ngayHenTra) {
        this.maPhieuMuon = maPhieuMuon;
        this.maSV = maSV;
        this.ngayMuon = ngayMuon;
        this.ngayHenTra = ngayHenTra;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public String getNgayHenTra() {
        return ngayHenTra;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public void setNgayHenTra(String ngayHenTra) {
        this.ngayHenTra = ngayHenTra;
    }
    
    
    
   
}
