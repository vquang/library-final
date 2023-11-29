/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.TaiLieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TaiLieuDAO {

    private DBConnect dbConnect;

    public List<TaiLieu> search(String searchName) {
        List<TaiLieu> list = new ArrayList<>();
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "select * from tailieu where ten like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String ten = resultSet.getString("ten");
                String tacGia = resultSet.getString("tacGia");
                String moTa = resultSet.getString("moTa");
                int soLuong = resultSet.getInt("soLuong");
                String anhBia = resultSet.getString("anhBia");
                TaiLieu tl = new TaiLieu(id, ten, tacGia, moTa, soLuong, anhBia);
                list.add(tl);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean create(TaiLieu taiLieu) {
        int rows = 0;
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "insert into tailieu (ten, tacGia, moTa, soLuong, anhBia) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, taiLieu.getTen());
            statement.setString(2, taiLieu.getTacGia());
            statement.setString(3, taiLieu.getMoTa());
            statement.setInt(4, taiLieu.getSoLuong());
            statement.setString(5, taiLieu.getAnhBia());
            rows = statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return rows != 0;
    }

    public boolean update(TaiLieu taiLieu) {
        int rows = 0;
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "update tailieu set ten = ?, tacGia = ?, moTa = ?, anhBia = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, taiLieu.getTen());
            statement.setString(2, taiLieu.getTacGia());
            statement.setString(3, taiLieu.getMoTa());
            statement.setString(4, taiLieu.getAnhBia());
            statement.setInt(5, taiLieu.getId());
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
            String sql = "delete from tailieu where id = ?";
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
}
