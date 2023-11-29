/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class TaiLieuNhap {
    private int id;
    private int soLuong;
    private double giaNhap;
    private TaiLieu taiLieu;

    public TaiLieuNhap() {
    }

    public TaiLieuNhap(int id, int soLuong, double giaNhap, TaiLieu taiLieu) {
        this.id = id;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.taiLieu = taiLieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public TaiLieu getTaiLieu() {
        return taiLieu;
    }

    public void setTaiLieu(TaiLieu taiLieu) {
        this.taiLieu = taiLieu;
    }
    
}
