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
public class SachPhieuMuon {
    public String maPhieuMuon;
    public String maSach;

    public SachPhieuMuon() {
    }

    public SachPhieuMuon(String maPhieuMuon, String maSach) {
        this.maPhieuMuon = maPhieuMuon;
        this.maSach = maSach;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }
    
}
