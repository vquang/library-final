/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.TaiLieu;
import entity.ThanhVien;
import entity.ThongKeBanDoc;
import entity.ThongKeTaiLieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeDAO {

    private DBConnect dbConnect;

    public List<ThongKeTaiLieu> getListTKTaiLieus(String thuTu) {
        List<ThongKeTaiLieu> list = new ArrayList<>();
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "SELECT TL.id, TL.ten, TL.tacGia, TL.moTa, TL.soLuong, TL.anhBia, COUNT(TM.id) AS soLuotMuon "
                    + "FROM TaiLieu TL "
                    + "LEFT JOIN TaiLieuMuon TM ON TL.id = TM.taiLieuId "
                    + "GROUP BY TL.id, TL.ten, TL.tacGia, TL.moTa, TL.soLuong, TL.anhBia "
                    + "ORDER BY soLuotMuon " + thuTu;

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ten = resultSet.getString("ten");
                String tacGia = resultSet.getString("tacGia");
                String moTa = resultSet.getString("moTa");
                int soLuong = resultSet.getInt("soLuong");
                String anhBia = resultSet.getString("anhBia");
                int soLuotMuon = resultSet.getInt("soLuotMuon");
                TaiLieu tl = new TaiLieu(id, ten, tacGia, moTa, soLuong, anhBia);
                ThongKeTaiLieu thongKeTaiLieu = new ThongKeTaiLieu(tl, soLuotMuon);
                list.add(thongKeTaiLieu);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ThongKeBanDoc> getListTKBanDocs(String thuTu) {
        List<ThongKeBanDoc> list = new ArrayList<>();
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "SELECT tv.id, tv.username, tv.hoTen, tv.soDienThoai, tv.diaChi, tv.email, tv.vaiTro, COUNT(tm.id) AS tongTaiLieu "
                    + "FROM ThanhVien tv "
                    + "JOIN PhieuMuon pm ON tv.id = pm.banDocId "
                    + "LEFT JOIN TaiLieuMuon tm ON pm.id = tm.phieuMuonId "
                    + "GROUP BY tv.id, tv.username, tv.hoTen, tv.soDienThoai, tv.diaChi, tv.email, tv.vaiTro "
                    + "ORDER BY tongTaiLieu " + thuTu;

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String hoTen = resultSet.getString("hoTen");
                String soDienThoai = resultSet.getString("soDienThoai");
                String diaChi = resultSet.getString("diaChi");
                String email = resultSet.getString("email");
                int tongTaiLieu = resultSet.getInt("tongTaiLieu");
                int vaiTro = resultSet.getInt("vaiTro");
                ThanhVien tv = new ThanhVien(id, username, "", hoTen, soDienThoai, diaChi, email, vaiTro);
                ThongKeBanDoc thongKeBanDoc = new ThongKeBanDoc(tv, tongTaiLieu);
                list.add(thongKeBanDoc);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
