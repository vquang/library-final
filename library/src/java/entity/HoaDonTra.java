/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class HoaDonTra {
    private int id;
    private double tienPhat;
    private String ghiChu;
    private PhieuTra phieuTra;

    public HoaDonTra() {
    }

    public HoaDonTra(int id, double tienPhat, String ghiChu, PhieuTra phieuTra) {
        this.id = id;
        this.tienPhat = tienPhat;
        this.ghiChu = ghiChu;
        this.phieuTra = phieuTra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(double tienPhat) {
        this.tienPhat = tienPhat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public PhieuTra getPhieuTra() {
        return phieuTra;
    }

    public void setPhieuTra(PhieuTra phieuTra) {
        this.phieuTra = phieuTra;
    }
    
}
