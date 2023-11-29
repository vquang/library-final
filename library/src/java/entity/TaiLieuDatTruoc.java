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
public class TaiLieuDatTruoc {
    private int id;
    private Date ngayDat;
    private Date ngayHetHan;
    private int trangThai;
    private TaiLieu taiLieu;
    private ThanhVien banDoc;

    public TaiLieuDatTruoc() {
    }

    public TaiLieuDatTruoc(int id, Date ngayDat, Date ngayHetHan, int trangThai, TaiLieu taiLieu, ThanhVien banDoc) {
        this.id = id;
        this.ngayDat = ngayDat;
        this.ngayHetHan = ngayHetHan;
        this.trangThai = trangThai;
        this.taiLieu = taiLieu;
        this.banDoc = banDoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
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

    public ThanhVien getBanDoc() {
        return banDoc;
    }

    public void setBanDoc(ThanhVien banDoc) {
        this.banDoc = banDoc;
    }
    
}
