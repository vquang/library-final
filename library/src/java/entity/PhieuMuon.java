/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhieuMuon {
    private int id;
    private Date ngayMuon;
    private Date ngayPhaiTra;
    private ThanhVien nhanVien;
    private ThanhVien banDoc;
    private List<TaiLieuMuon> listTaiLieuMuons;

    public PhieuMuon() {
    }

    public PhieuMuon(int id, Date ngayMuon, Date ngayPhaiTra, ThanhVien nhanVien, ThanhVien banDoc, List<TaiLieuMuon> listTaiLieuMuons) {
        this.id = id;
        this.ngayMuon = ngayMuon;
        this.ngayPhaiTra = ngayPhaiTra;
        this.nhanVien = nhanVien;
        this.banDoc = banDoc;
        this.listTaiLieuMuons = listTaiLieuMuons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ThanhVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(ThanhVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public ThanhVien getBanDoc() {
        return banDoc;
    }

    public void setBanDoc(ThanhVien banDoc) {
        this.banDoc = banDoc;
    }

    public List<TaiLieuMuon> getListTaiLieuMuons() {
        return listTaiLieuMuons;
    }

    public void setListTaiLieuMuons(List<TaiLieuMuon> listTaiLieuMuons) {
        this.listTaiLieuMuons = listTaiLieuMuons;
    }
    
}
