/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.ThanhVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.VaiTro;

/**
 *
 * @author Admin
 */
public class ThanhVienDAO {

    private DBConnect dbConnect;

    public List<ThanhVien> getAll(int vaiTro) {
        List<ThanhVien> list = new ArrayList<>();
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "select * from thanhvien where vaiTro = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, vaiTro);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String hoTen = resultSet.getString("hoTen");
                String soDienThoai = resultSet.getString("soDienThoai");
                String diaChi = resultSet.getString("diaChi");
                String email = resultSet.getString("email");
                ThanhVien tv = new ThanhVien(id, username, "", hoTen, soDienThoai, diaChi, email, vaiTro);
                list.add(tv);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean update(ThanhVien thanhVien) {
        int rows = 0;
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "update thanhvien set hoTen = ?, soDienThoai = ?, diaChi = ?, email = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, thanhVien.getHoTen());
            statement.setString(2, thanhVien.getSoDienThoai());
            statement.setString(3, thanhVien.getDiaChi());
            statement.setString(4, thanhVien.getEmail());
            statement.setInt(5, thanhVien.getId());
            rows = statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rows != 0;
    }

    public boolean delete(int id) {
        int rows = 0;
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "delete from thanhvien where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rows = statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rows != 0;
    }

    public boolean create(ThanhVien thanhVien) {
        int rows = 0;
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "insert into thanhvien (username, password, hoTen, soDienThoai, diaChi, email, vaiTro) "
                    + "values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, thanhVien.getUsername());
            statement.setString(2, thanhVien.getPassword());
            statement.setString(3, thanhVien.getHoTen());
            statement.setString(4, thanhVien.getSoDienThoai());
            statement.setString(5, thanhVien.getDiaChi());
            statement.setString(6, thanhVien.getEmail());
            statement.setInt(7, thanhVien.getVaiTro());
            rows = statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rows != 0;
    }

    public List<ThanhVien> searchBanDocs(String searchName) {
        List<ThanhVien> list = new ArrayList<>();
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "select * from thanhvien where hoTen like ? and vaiTro = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + searchName + "%");
            statement.setInt(2, VaiTro.BANDOC.getValue());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String hoTen = resultSet.getString("hoTen");
                String soDienThoai = resultSet.getString("soDienThoai");
                String diaChi = resultSet.getString("diaChi");
                String email = resultSet.getString("email");
                int vaiTro = resultSet.getInt("vaiTro");

                ThanhVien tv = new ThanhVien(id, "", "", hoTen, soDienThoai, diaChi, email, vaiTro);
                list.add(tv);
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
