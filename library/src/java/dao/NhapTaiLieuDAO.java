/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.HoaDonNhap;
import entity.NhaCungCap;
import entity.TaiLieu;
import entity.TaiLieuNhap;
import entity.ThanhVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class NhapTaiLieuDAO {

    private DBConnect dbConnect;

    public int insert(HoaDonNhap hoaDonNhap) {
        int hoaDonId = 0;
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "insert into hoadonnhap (ngayNhap, tongTien, nhanVienId, nhaCungCapId) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setDate(1, hoaDonNhap.getNgayNhap());
            statement.setDouble(2, hoaDonNhap.getTongTien());
            statement.setInt(3, hoaDonNhap.getNhanVien().getId());
            statement.setInt(4, hoaDonNhap.getNhaCungCap().getId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                hoaDonId = generatedKeys.getInt(1);
            }

            Map<Integer, Integer> mapTaiLieuIds = new HashMap<>();
            sql = "insert into tailieunhap (soLuong, giaNhap, taiLieuId, hoaDonNhapId) values (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < hoaDonNhap.getListTaiLieuNhaps().size(); ++i) {
                int taiLieuId = hoaDonNhap.getListTaiLieuNhaps().get(i).getTaiLieu().getId();
                int soLuong = hoaDonNhap.getListTaiLieuNhaps().get(i).getSoLuong();
                statement.setInt(1, soLuong);
                statement.setDouble(2, hoaDonNhap.getListTaiLieuNhaps().get(i).getGiaNhap());
                statement.setInt(3, taiLieuId);
                statement.setInt(4, hoaDonId);
                statement.executeUpdate();
                mapTaiLieuIds.put(taiLieuId, soLuong);
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

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return hoaDonId;
    }

    public HoaDonNhap getHoaDonNhap(int id) {
        HoaDonNhap hoaDonNhap = new HoaDonNhap();
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "select * from hoadonnhap "
                    + "join tailieunhap on hoadonnhap.id = tailieunhap.hoaDonNhapId "
                    + "join tailieu on tailieu.id = tailieunhap.taiLIeuId "
                    + "join thanhvien as nhanvien on nhanvien.id = hoadonnhap.nhanVienId "
                    + "join nhacungcap on nhacungcap.id = hoadonnhap.nhaCungCapId "
                    + "where hoadonnhap.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            List<TaiLieuNhap> listTaiLieuNhaps = new ArrayList<>();

            while (resultSet.next()) {
                // lấy từ hóa đơn nhập
                Date ngayNhap = resultSet.getDate("hoadonnhap.ngayNhap");
                double tongTien = resultSet.getDouble("hoadonnhap.tongTien");
                hoaDonNhap.setId(id);
                hoaDonNhap.setNgayNhap(ngayNhap);
                hoaDonNhap.setTongTien(tongTien);

                // lấy từ tài liệu nhập
                int taiLieuNhapId = resultSet.getInt("tailieunhap.id");
                int soLuongNhap = resultSet.getInt("tailieunhap.soLuong");
                double giaNhap = resultSet.getDouble("tailieunhap.giaNhap");
                TaiLieuNhap taiLieuNhap = new TaiLieuNhap();
                taiLieuNhap.setId(taiLieuNhapId);
                taiLieuNhap.setGiaNhap(giaNhap);
                taiLieuNhap.setSoLuong(soLuongNhap);

                // lấy từ tài liệu
                int taiLieuId = resultSet.getInt("tailieu.id");
                String ten = resultSet.getString("tailieu.ten");
                String tacGia = resultSet.getString("tailieu.tacGia");
                String moTa = resultSet.getString("tailieu.moTa");
                int soLuong = resultSet.getInt("tailieu.soLuong");
                String anhBia = resultSet.getString("tailieu.anhBia");
                TaiLieu taiLieu = new TaiLieu(taiLieuId, ten, tacGia, moTa, soLuong, anhBia);

                // lấy từ nhân viên
                int NVId = resultSet.getInt("nhanvien.id");
                String NVhoTen = resultSet.getString("nhanvien.hoTen");
                String NVsoDienThoai = resultSet.getString("nhanvien.soDienThoai");
                String NVdiaChi = resultSet.getString("nhanvien.diaChi");
                String NVemail = resultSet.getString("nhanvien.email");
                int NVvaiTro = resultSet.getInt("nhanvien.vaiTro");
                ThanhVien nhanVien = new ThanhVien(NVId, "", "", NVhoTen, NVsoDienThoai, NVdiaChi, NVemail, NVvaiTro);

                // Lấy từ nhà cung cấp
                int idNCC = resultSet.getInt("nhacungcap.id");
                String tenNCC = resultSet.getString("nhacungcap.ten");
                String diaChiNCC = resultSet.getString("nhacungcap.diaChi");
                String soDienThoaiNCC = resultSet.getString("nhacungcap.soDienThoai");
                NhaCungCap ncc = new NhaCungCap(idNCC, tenNCC, diaChiNCC, soDienThoaiNCC);

                taiLieuNhap.setTaiLieu(taiLieu);
                hoaDonNhap.setNhanVien(nhanVien);
                hoaDonNhap.setNhaCungCap(ncc);
                listTaiLieuNhaps.add(taiLieuNhap);
            }
            hoaDonNhap.setListTaiLieuNhaps(listTaiLieuNhaps);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return hoaDonNhap;
    }
}
