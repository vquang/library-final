/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class ThongKeTaiLieu {
    private TaiLieu taiLieu;
    private int soLuotMuon;

    public ThongKeTaiLieu() {
    }

    public ThongKeTaiLieu(TaiLieu taiLieu, int soLuotMuon) {
        this.taiLieu = taiLieu;
        this.soLuotMuon = soLuotMuon;
    }

    public TaiLieu getTaiLieu() {
        return taiLieu;
    }

    public void setTaiLieu(TaiLieu taiLieu) {
        this.taiLieu = taiLieu;
    }

    public int getSoLuotMuon() {
        return soLuotMuon;
    }

    public void setSoLuotMuon(int soLuotMuon) {
        this.soLuotMuon = soLuotMuon;
    }
    
}
