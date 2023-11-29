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
public class PhieuTra {
    private int id;
    private Date ngayTra;
    private ThanhVien nhanVien;
    private ThanhVien banDoc;
    private List<TaiLieuMuon> listTaiLieuMuons;

    public PhieuTra() {
    }

    public PhieuTra(int id, Date ngayTra, ThanhVien nhanVien, ThanhVien banDoc, List<TaiLieuMuon> listTaiLieuMuons) {
        this.id = id;
        this.ngayTra = ngayTra;
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

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
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
