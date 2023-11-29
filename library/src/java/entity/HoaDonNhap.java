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
public class HoaDonNhap {
    private int id;
    private Date ngayNhap;
    private double tongTien;
    private ThanhVien nhanVien;
    private NhaCungCap nhaCungCap;
    private List<TaiLieuNhap> listTaiLieuNhaps;

    public HoaDonNhap() {
    }

    public HoaDonNhap(int id, Date ngayNhap, double tongTien, ThanhVien nhanVien, NhaCungCap nhaCungCap, List<TaiLieuNhap> listTaiLieuNhaps) {
        this.id = id;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
        this.nhanVien = nhanVien;
        this.nhaCungCap = nhaCungCap;
        this.listTaiLieuNhaps = listTaiLieuNhaps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public ThanhVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(ThanhVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public List<TaiLieuNhap> getListTaiLieuNhaps() {
        return listTaiLieuNhaps;
    }

    public void setListTaiLieuNhaps(List<TaiLieuNhap> listTaiLieuNhaps) {
        this.listTaiLieuNhaps = listTaiLieuNhaps;
    }
    
}
