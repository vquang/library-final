/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.PhieuMuon;
import entity.TaiLieu;
import entity.TaiLieuMuon;
import entity.ThanhVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
public class MuonTaiLieuDAO {

    private DBConnect dbConnect;

    public List<PhieuMuon> getListByBanDoc(int banDocId) {
        List<PhieuMuon> list = new ArrayList<>();
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "select * from phieumuon join tailieumuon "
                    + "on phieumuon.id = tailieumuon.phieuMuonId "
                    + "join tailieu "
                    + "on tailieu.id = tailieumuon.taiLieuId "
                    + "where phieumuon.banDocId = ? and tailieumuon.trangThai = 0";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, banDocId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("phieumuon.id");
                Date ngayMuon = resultSet.getDate("phieumuon.ngayMuon");
                Date ngayPhaiTra = resultSet.getDate("phieumuon.ngayPhaiTra");

                int taiLieuMuonId = resultSet.getInt("tailieumuon.id");
                int trangThai = resultSet.getInt("tailieumuon.trangThai");

                int taiLieuId = resultSet.getInt("tailieu.id");
                String ten = resultSet.getString("tailieu.ten");
                String tacGia = resultSet.getString("tailieu.tacgia");
                String moTa = resultSet.getString("moTa");
                int soLuong = resultSet.getInt("soLuong");
                String anhBia = resultSet.getString("anhBia");

                TaiLieu taiLieu = new TaiLieu(taiLieuId, ten, tacGia, moTa, soLuong, anhBia);
                TaiLieuMuon taiLieuMuon = new TaiLieuMuon(taiLieuMuonId, trangThai, taiLieu, ngayMuon, ngayPhaiTra);

                boolean check = false;
                for (PhieuMuon phieuMuon : list) {

                    if (phieuMuon.getId() == id) {
                        check = true;
                        phieuMuon.getListTaiLieuMuons().add(taiLieuMuon);
                        break;
                    }
                }
                if (!check) {
                    PhieuMuon phieuMuon2 = new PhieuMuon(id, ngayMuon, ngayPhaiTra, null, null, new ArrayList<>());
                    List<TaiLieuMuon> listTaiLieuMuons = new ArrayList<>();
                    listTaiLieuMuons.add(taiLieuMuon);
                    phieuMuon2.setListTaiLieuMuons(listTaiLieuMuons);
                    list.add(phieuMuon2);
                }
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int insert(PhieuMuon phieuMuon, List<Integer> datTruocIds) {
        int id = 0;
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();

            String sql = "insert into phieumuon (ngayMuon, ngayPhaiTra, nhanVienId, banDocId) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setDate(1, phieuMuon.getNgayMuon());
            statement.setDate(2, phieuMuon.getNgayPhaiTra());
            statement.setInt(3, phieuMuon.getNhanVien().getId());
            statement.setInt(4, phieuMuon.getBanDoc().getId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            List<Integer> listTaiLieuIds = new ArrayList<>();
            sql = "insert into tailieumuon (trangThai, taiLieuId, phieuMuonId) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < phieuMuon.getListTaiLieuMuons().size(); ++i) {
                int taiLieuId = phieuMuon.getListTaiLieuMuons().get(i).getTaiLieu().getId();
                statement.setInt(1, 0);
                statement.setInt(2, taiLieuId);
                statement.setInt(3, id);
                statement.executeUpdate();
                listTaiLieuIds.add(taiLieuId);
            }

            String placeholders = listTaiLieuIds.stream().map(i -> "?").collect(Collectors.joining(", "));
            sql = "update tailieu set soLuong = soLuong - 1 where id in (" + placeholders + ")";
            statement = connection.prepareStatement(sql);
            for (int i = 1; i <= listTaiLieuIds.size(); ++i) {
                statement.setInt(i, listTaiLieuIds.get(i - 1));
            }
            statement.executeUpdate();

            placeholders = datTruocIds.stream().map(i -> "?").collect(Collectors.joining(", "));
            sql = "update tailieudattruoc set trangthai = 1 where id in (" + placeholders + ")";
            statement = connection.prepareStatement(sql);
            for (int i = 1; i <= datTruocIds.size(); ++i) {
                statement.setInt(i, datTruocIds.get(i - 1));
            }
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    public PhieuMuon getPhieuMuon(int id) {
        PhieuMuon phieuMuon = new PhieuMuon();
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "select * from phieumuon "
                    + "join tailieumuon on phieumuon.id = tailieumuon.phieuMuonId "
                    + "join tailieu on tailieu.id = tailieumuon.taiLIeuId "
                    + "join thanhvien as bandoc on bandoc.id = phieumuon.banDocId "
                    + "join thanhvien as nhanvien on nhanvien.id = phieumuon.nhanVienId "
                    + "where phieumuon.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            List<TaiLieuMuon> listTaiLieuMuons = new ArrayList<>();

            while (resultSet.next()) {
                // lấy từ phiếu mượn
                Date ngayMuon = resultSet.getDate("phieumuon.ngayMuon");
                Date ngayPhaiTra = resultSet.getDate("phieumuon.ngayPhaiTra");
                phieuMuon.setId(id);
                phieuMuon.setNgayMuon(ngayMuon);
                phieuMuon.setNgayPhaiTra(ngayPhaiTra);

                // lấy từ tài liệu mượn
                int taiLieuMuonId = resultSet.getInt("tailieumuon.id");
                int trangThai = resultSet.getInt("tailieumuon.trangThai");
                TaiLieuMuon taiLieuMuon = new TaiLieuMuon();
                taiLieuMuon.setId(taiLieuMuonId);
                taiLieuMuon.setNgayMuon(ngayMuon);
                taiLieuMuon.setNgayPhaiTra(ngayPhaiTra);
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
                phieuMuon.setBanDoc(banDoc);
                phieuMuon.setNhanVien(nhanVien);
                listTaiLieuMuons.add(taiLieuMuon);
            }
            phieuMuon.setListTaiLieuMuons(listTaiLieuMuons);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return phieuMuon;
    }
}
