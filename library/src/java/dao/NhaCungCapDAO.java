/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.NhaCungCap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhaCungCapDAO {

    private DBConnect dbConnect;

    public List<NhaCungCap> getAll() {
        List<NhaCungCap> list = new ArrayList<>();
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "select * from nhacungcap";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ten = resultSet.getString("ten");
                String diaChi = resultSet.getString("diaChi");
                String soDienThoai = resultSet.getString("soDienThoai");

                NhaCungCap ncc = new NhaCungCap(id, ten, diaChi, soDienThoai);
                list.add(ncc);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean update(NhaCungCap ncc) {
        int rows = 0;
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "update nhacungcap set ten = ?, diaChi = ?, soDienThoai = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ncc.getTen());
            statement.setString(2, ncc.getDiaChi());
            statement.setString(3, ncc.getSoDienThoai());
            statement.setInt(4, ncc.getId());
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
            String sql = "delete from nhacungcap where id = ?";
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

    public boolean create(NhaCungCap ncc) {
        int rows = 0;
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "insert into nhacungcap (ten, diaChi, soDienThoai) "
                    + "values (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ncc.getTen());
            statement.setString(2, ncc.getDiaChi());
            statement.setString(3, ncc.getSoDienThoai());
            rows = statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rows != 0;
    }

    public List<NhaCungCap> searchNhaCungCaps(String searchName) {
        List<NhaCungCap> list = new ArrayList<>();
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            String sql = "select * from nhacungcap where ten like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ten = resultSet.getString("ten");
                String diaChi = resultSet.getString("diaChi");
                String soDienThoai = resultSet.getString("soDienThoai");

                NhaCungCap ncc = new NhaCungCap(id, ten, diaChi, soDienThoai);
                list.add(ncc);
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
