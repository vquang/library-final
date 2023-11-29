/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.nhanvien;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.NhapTaiLieuDAO;
import entity.HoaDonNhap;
import entity.NhaCungCap;
import entity.TaiLieu;
import entity.TaiLieuNhap;
import entity.ThanhVien;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import utils.Time;

/**
 *
 * @author Admin
 */
public class NhapTaiLieuController extends HttpServlet {

    private final NhapTaiLieuDAO nhapTaiLieuDAO = new NhapTaiLieuDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("create")) {
            int nhanVienId = Integer.parseInt(request.getParameter("nhanVienId"));
            int nccId = Integer.parseInt(request.getParameter("nccId"));
            Date ngayNhap = Time.stringToDate(request.getParameter("ngayNhap"));
            double tongTien = Double.parseDouble(request.getParameter("tongTien"));

            // lấy list tài liệu từ session
            List<TaiLieu> listTaiLieus = (List<TaiLieu>) request.getSession().getAttribute("listTaiLieus");

            // lấy list tài liệu nhập
            List<TaiLieuNhap> listTaiLieuNhaps = new ArrayList<>();
            String jsonString = request.getParameter("listTaiLieuNhaps");
            JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                int id = jsonObject.get("id").getAsInt();
                int soLuong = jsonObject.get("soLuong").getAsInt();
                double giaNhap = jsonObject.get("giaNhap").getAsDouble();

                TaiLieuNhap taiLieuNhap = new TaiLieuNhap(-1, soLuong, giaNhap, null);

                for (TaiLieu taiLieu : listTaiLieus) {
                    if (taiLieu.getId() == id) {
                        taiLieuNhap.setTaiLieu(taiLieu);
                    }
                }
                listTaiLieuNhaps.add(taiLieuNhap);
            }

            // tạo nhân viên
            ThanhVien nhanVien = new ThanhVien();
            nhanVien.setId(nhanVienId);

            // tạo nhà cung cấp
            NhaCungCap nhaCungCap = new NhaCungCap();
            nhaCungCap.setId(nccId);

            // tạo hóa đơn nhập
            HoaDonNhap hoaDonNhap = new HoaDonNhap(-1, ngayNhap, tongTien, nhanVien, nhaCungCap, listTaiLieuNhaps);
            int id = nhapTaiLieuDAO.insert(hoaDonNhap);

            // lấy hóa đơn nhập
            hoaDonNhap = nhapTaiLieuDAO.getHoaDonNhap(id);
            request.getSession().setAttribute("hoaDonNhap", hoaDonNhap);
            request.getRequestDispatcher("/nhanvien/HoaDonNhap.jsp").forward(request, response);
        }
    }
}
