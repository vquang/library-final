/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.TaiLieu;
import entity.TaiLieuDatTruoc;
import entity.ThanhVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import utils.VaiTro;

/**
 *
 * @author Admin
 */
public class DatTruocDAO {

    private DBConnect dbConnect;
    
    public boolean create(TaiLieuDatTruoc taiLieuDatTruoc) {
        int rows = 0;
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            
            // đặt trước tài liệu
            String sql = "insert into tailieudattruoc (ngayDat, ngayHetHan, trangThai, taiLieuId, banDocId) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, taiLieuDatTruoc.getNgayDat());
            statement.setDate(2, taiLieuDatTruoc.getNgayHetHan());
            statement.setInt(3, taiLieuDatTruoc.getTrangThai());
            statement.setInt(4, taiLieuDatTruoc.getTaiLieu().getId());
            statement.setInt(5, taiLieuDatTruoc.getBanDoc().getId());
            rows = statement.executeUpdate();
            
            // giảm số lượng tài liệu đi 1
            sql = "update tailieu set soLuong = soLuong - 1 where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taiLieuDatTruoc.getTaiLieu().getId());
            statement.executeUpdate();
            
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rows != 0;
    }
    
    public boolean delete(int id, int taiLieuId) {
        int rows = 0;
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            
            // xóa tài liệu đặt trước
            String sql = "delete from tailieudattruoc where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rows = statement.executeUpdate();
            
            // tăng số lượng tài liệu lên 1
            sql = "update tailieu set soLuong = soLuong + 1 where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taiLieuId);
            statement.executeUpdate();
            
            statement.close();
            connection.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return rows != 0;
    }
    
    public List<TaiLieuDatTruoc> getList(int banDocId) {
        List<TaiLieuDatTruoc> list = new ArrayList<>();
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "select * from tailieudattruoc join tailieu "
                    + "on tailieudattruoc.taiLieuId = taiLieu.id "
                    + "join thanhvien "
                    + "on tailieudattruoc.banDocId = thanhvien.id "
                    + "where tailieudattruoc.banDocId = ? and tailieudattruoc.trangThai = 0";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, banDocId);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("tailieudattruoc.id");
                Date ngayDat = resultSet.getDate("tailieudattruoc.ngayDat");
                Date ngayHetHan = resultSet.getDate("tailieudattruoc.ngayHetHan");
                int trangThai = resultSet.getInt("tailieudattruoc.trangThai");

                // lấy thông tin tài liệu
                int taiLieuId = resultSet.getInt("tailieu.id");
                String ten = resultSet.getString("tailieu.ten");
                String tacGia = resultSet.getString("tailieu.tacGia");
                String moTa = resultSet.getString("tailieu.moTa");
                int soLuong = resultSet.getInt("tailieu.soLuong");
                String anhBia = resultSet.getString("tailieu.anhBia");
                TaiLieu taiLieu = new TaiLieu(taiLieuId, ten, tacGia, moTa, soLuong, anhBia);

                // lấy thông tin bạn đọc
                String username = resultSet.getString("thanhvien.username");
                String hoTen = resultSet.getString("thanhvien.hoTen");
                String soDienThoai = resultSet.getString("thanhvien.soDienThoai");
                String diaChi = resultSet.getString("thanhvien.diaChi");
                String email = resultSet.getString("thanhvien.email");
                ThanhVien banDoc = new ThanhVien(banDocId, username, "", hoTen, soDienThoai, diaChi, email, VaiTro.BANDOC.getValue());

                // tạo tài liệu đặt trước
                TaiLieuDatTruoc taiLieuDatTruoc = new TaiLieuDatTruoc(id, ngayDat, ngayHetHan, trangThai, taiLieu, banDoc);
                list.add(taiLieuDatTruoc);
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
