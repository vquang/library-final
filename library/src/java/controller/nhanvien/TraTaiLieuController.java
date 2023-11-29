package controller.nhanvien;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dao.MuonTaiLieuDAO;
import dao.TraTaiLieuDAO;
import entity.HoaDonTra;
import entity.PhieuMuon;
import entity.PhieuTra;
import entity.TaiLieuMuon;
import entity.ThanhVien;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import utils.Time;

/**
 *
 * @author Admin
 */
public class TraTaiLieuController extends HttpServlet {

    private final MuonTaiLieuDAO muonTaiLieuDAO = new MuonTaiLieuDAO();
    private final TraTaiLieuDAO traTaiLieuDAO = new TraTaiLieuDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        if (action.equals("search")) {
            int banDocId = Integer.parseInt(request.getParameter("id"));
            List<PhieuMuon> listPhieuMuons = muonTaiLieuDAO.getListByBanDoc(banDocId);

            request.getSession().setAttribute("listPhieuMuons", listPhieuMuons);
            request.getRequestDispatcher("/nhanvien/TraTaiLieu.jsp").forward(request, response);
        } else if (action.equals("create")) {
            int nhanVienId = Integer.parseInt(request.getParameter("nhanVienId"));
            int banDocId = Integer.parseInt(request.getParameter("banDocId"));
            Date ngayTra = Time.stringToDate(request.getParameter("ngayTra"));
            double tienPhat = Double.parseDouble(request.getParameter("tienPhat"));
            String ghiChu = request.getParameter("ghiChu");

            List<Integer> taiLieuMuonIds = Arrays.stream(request.getParameterValues("ids[]"))
                    .map(i -> Integer.valueOf(i))
                    .toList();
            Map<Integer, Integer> mapTaiLieuIds = taiLieuMuonIds.stream()
                    .collect(Collectors.groupingBy(e -> e, Collectors.summingInt(e -> 1)));
            // lấy list tài liệu mượn
            List<TaiLieuMuon> listTaiLieuMuons = new ArrayList<>();
            List<TaiLieuMuon> listTaiLieuMuonsSS = (List<TaiLieuMuon>) request.getSession().getAttribute("listPhieuMuons");
            for (TaiLieuMuon taiLieuMuon : listTaiLieuMuonsSS) {
                for (int id : taiLieuMuonIds) {
                    if (taiLieuMuon.getId() == id) {
                        listTaiLieuMuons.add(taiLieuMuon);
                    }
                }
            }

            // tạo nhân viên
            ThanhVien nhanVien = new ThanhVien();
            nhanVien.setId(nhanVienId);

            // tạo bạn đọc
            ThanhVien banDoc = new ThanhVien();
            banDoc.setId(banDocId);

            // tạo phiếu trả
            PhieuTra phieuTra = new PhieuTra(-1, ngayTra, nhanVien, banDoc, listTaiLieuMuons);

            // tạo hóa đơn trả
            HoaDonTra hoaDon = new HoaDonTra(-1, tienPhat, ghiChu, phieuTra);

            // lấy hóa đơn trả
            int id = traTaiLieuDAO.insert(hoaDon, mapTaiLieuIds);
            HoaDonTra hoaDonTra = traTaiLieuDAO.getHoaDon(id);
            hoaDonTra.getPhieuTra().setListTaiLieuMuons(listTaiLieuMuons);

            request.getSession().setAttribute("hoaDonTra", hoaDonTra);
            request.getRequestDispatcher("/nhanvien/HoaDonTra.jsp").forward(request, response);
        }
    }
}
