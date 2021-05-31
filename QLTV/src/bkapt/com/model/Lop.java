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
public class Lop {
    public Lop(){ 
    }
    public String malop;
    public String ten;
  
    public Lop(String malop) {
        this.malop = malop;
    }
  

    public Lop(String malop, String ten) {
        this.malop = malop;
        this.ten = ten;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
   public String toString(){
        return ten;
    }
}
