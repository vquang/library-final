/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class TaiLieuMuon {
    private int id;
    private int trangThai;
    private TaiLieu taiLieu;
    private Date ngayMuon;
    private Date ngayPhaiTra;

    public TaiLieuMuon() {
    }

    public TaiLieuMuon(int id, int trangThai, TaiLieu taiLieu, Date ngayMuon, Date ngayPhaiTra) {
        this.id = id;
        this.trangThai = trangThai;
        this.taiLieu = taiLieu;
        this.ngayMuon = ngayMuon;
        this.ngayPhaiTra = ngayPhaiTra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public TaiLieu getTaiLieu() {
        return taiLieu;
    }

    public void setTaiLieu(TaiLieu taiLieu) {
        this.taiLieu = taiLieu;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayPhaiTra() {
        return ngayPhaiTra;
    }

    public void setNgayPhaiTra(Date ngayPhaiTra) {
        this.ngayPhaiTra = ngayPhaiTra;
    }
    
}
