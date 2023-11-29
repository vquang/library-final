/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class ThongKeBanDoc {
    private ThanhVien banDoc;
    private int tongTaiLieu;

    public ThongKeBanDoc() {
    }

    public ThongKeBanDoc(ThanhVien banDoc, int tongTaiLieu) {
        this.banDoc = banDoc;
        this.tongTaiLieu = tongTaiLieu;
    }

    public ThanhVien getBanDoc() {
        return banDoc;
    }

    public void setBanDoc(ThanhVien banDoc) {
        this.banDoc = banDoc;
    }

    public int getTongTaiLieu() {
        return tongTaiLieu;
    }

    public void setTongTaiLieu(int tongTaiLieu) {
        this.tongTaiLieu = tongTaiLieu;
    }
    
}
