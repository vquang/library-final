/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.HoaDonTra;
import entity.PhieuTra;
import entity.TaiLieu;
import entity.TaiLieuMuon;
import entity.ThanhVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class TraTaiLieuDAO {

    private DBConnect dbConnect;

    public int insert(HoaDonTra hoaDonTra, Map<Integer, Integer> mapTaiLieuIds) {
        int hoaDonTraId = 0;
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "insert into phieutra (ngayTra, nhanVienId, banDocId) value (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            PhieuTra phieuTra = hoaDonTra.getPhieuTra();

            statement.setDate(1, phieuTra.getNgayTra());
            statement.setInt(2, phieuTra.getNhanVien().getId());
            statement.setInt(3, phieuTra.getBanDoc().getId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int phieuTraId = 0;
            if (generatedKeys.next()) {
                phieuTraId = generatedKeys.getInt(1);
            }

            sql = "update tailieumuon set trangThai = ?, phieuTraId = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < phieuTra.getListTaiLieuMuons().size(); ++i) {
                int taiLieuTraId = phieuTra.getListTaiLieuMuons().get(i).getId();
                statement.setInt(1, 1);
                statement.setInt(2, phieuTraId);
                statement.setInt(3, taiLieuTraId);
                statement.executeUpdate();

            }

            sql = "update tailieu set soLuong = soLuong + ? where id = ?";
            statement = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Integer> entry : mapTaiLieuIds.entrySet()) {
                int id = entry.getKey();
                int count = entry.getValue();

                statement.setInt(1, count);
                statement.setInt(2, id);
                statement.addBatch();
            }
            statement.executeBatch();

            sql = "insert into hoadontra (tienPhat, ghiChu, phieuTraId) values (?, ?, ?)";
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, hoaDonTra.getTienPhat());
            statement.setString(2, hoaDonTra.getGhiChu());
            statement.setInt(3, phieuTraId);
            statement.executeUpdate();
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                hoaDonTraId = generatedKeys.getInt(1);
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return hoaDonTraId;
    }

    public HoaDonTra getHoaDon(int id) {
        HoaDonTra hoaDonTra = new HoaDonTra();

        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "select * from hoadontra "
                    + "join phieutra on hoadontra.phieuTraId = phieutra.id "
                    + "join tailieumuon on phieutra.id = tailieumuon.phieuTraId "
                    + "join tailieu on tailieu.id = tailieumuon.taiLIeuId "
                    + "join thanhvien as bandoc on bandoc.id = phieutra.banDocId "
                    + "join thanhvien as nhanvien on nhanvien.id = phieutra.nhanVienId "
                    + "where hoadontra.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            PhieuTra phieuTra = new PhieuTra();
            List<TaiLieuMuon> listTaiLieuMuons = new ArrayList<>();

            while (resultSet.next()) {
                // lấy từ hóa đơn trả
                double tienPhat = resultSet.getDouble("hoadontra.tienPhat");
                String ghiChu = resultSet.getString("hoaDonTra.ghiChu");
                hoaDonTra.setId(id);
                hoaDonTra.setTienPhat(tienPhat);
                hoaDonTra.setGhiChu(ghiChu);

                // lấy từ phiếu trả
                int phieuTraId = resultSet.getInt("phieutra.id");
                Date ngayTra = resultSet.getDate("phieutra.ngayTra");
                phieuTra.setId(phieuTraId);
                phieuTra.setNgayTra(ngayTra);

                // lấy từ tài liệu mượn
                int taiLieuMuonId = resultSet.getInt("tailieumuon.id");
                int trangThai = resultSet.getInt("tailieumuon.trangThai");
                TaiLieuMuon taiLieuMuon = new TaiLieuMuon();
                taiLieuMuon.setId(taiLieuMuonId);
                taiLieuMuon.setTrangThai(trangThai);

                // lấy từ tài liệu
                int taiLieuId = resultSet.getInt("tailieu.id");
                String ten = resultSet.getString("tailieu.ten");
                String tacGia = resultSet.getString("tailieu.tacGia");
                String moTa = resultSet.getString("tailieu.moTa");
                int soLuong = resultSet.getInt("tailieu.soLuong");
                String anhBia = resultSet.getString("tailieu.anhBia");
                TaiLieu taiLieu = new TaiLieu(taiLieuId, ten, tacGia, moTa, soLuong, anhBia);

                // lấy từ bạn đọc
                int BDId = resultSet.getInt("bandoc.id");
                String BDhoTen = resultSet.getString("bandoc.hoTen");
                String BDsoDienThoai = resultSet.getString("bandoc.soDienThoai");
                String BDdiaChi = resultSet.getString("bandoc.diaChi");
                String BDemail = resultSet.getString("bandoc.email");
                int BDvaiTro = resultSet.getInt("bandoc.vaiTro");
                ThanhVien banDoc = new ThanhVien(BDId, "", "", BDhoTen, BDsoDienThoai, BDdiaChi, BDemail, BDvaiTro);

                // lấy từ nhân viên
                int NVId = resultSet.getInt("nhanvien.id");
                String NVhoTen = resultSet.getString("nhanvien.hoTen");
                String NVsoDienThoai = resultSet.getString("nhanvien.soDienThoai");
                String NVdiaChi = resultSet.getString("nhanvien.diaChi");
                String NVemail = resultSet.getString("nhanvien.email");
                int NVvaiTro = resultSet.getInt("nhanvien.vaiTro");
                ThanhVien nhanVien = new ThanhVien(NVId, "", "", NVhoTen, NVsoDienThoai, NVdiaChi, NVemail, NVvaiTro);

                taiLieuMuon.setTaiLieu(taiLieu);
                phieuTra.setBanDoc(banDoc);
                phieuTra.setNhanVien(nhanVien);
                listTaiLieuMuons.add(taiLieuMuon);
            }
            phieuTra.setListTaiLieuMuons(listTaiLieuMuons);
            hoaDonTra.setPhieuTra(phieuTra);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return hoaDonTra;
    }
}
