package controller.nhanvien;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dao.DatTruocDAO;
import dao.MuonTaiLieuDAO;
import entity.PhieuMuon;
import entity.TaiLieu;
import entity.TaiLieuDatTruoc;
import entity.TaiLieuMuon;
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
public class MuonTaiLieuController extends HttpServlet {

    private final DatTruocDAO datTruocDAO = new DatTruocDAO();
    private final MuonTaiLieuDAO muonTaiLieuDAO = new MuonTaiLieuDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        if (action.equals("order")) {
            int banDocId = Integer.parseInt(request.getParameter("id"));
            List<TaiLieuDatTruoc> list = datTruocDAO.getList(banDocId);
            request.getSession().setAttribute("listTaiLieuDatTruocs", list);
            request.getRequestDispatcher("/nhanvien/MuonTaiLieu.jsp").forward(request, response);
        } else if (action.equals("create")) {
            int nhanVienId = Integer.parseInt(request.getParameter("nhanVienId"));
            int banDocId = Integer.parseInt(request.getParameter("banDocId"));
            String[] taiLieuIds = request.getParameterValues("ids[]");
            Date ngayMuon = Time.stringToDate(request.getParameter("ngayMuon"));
            Date ngayPhaiTra = Time.stringToDate(request.getParameter("ngayPhaiTra"));

            // tạo nhân viên
            ThanhVien nhanVien = new ThanhVien();
            nhanVien.setId(nhanVienId);

            // tạo bạn đọc
            ThanhVien banDoc = new ThanhVien();
            banDoc.setId(banDocId);

            // tạo list tài liệu mượn
            List<TaiLieuMuon> listTaiLieuMuons = new ArrayList<>();
            for (String taiLieuId : taiLieuIds) {
                TaiLieu taiLieu = new TaiLieu();
                taiLieu.setId(Integer.parseInt(taiLieuId));

                TaiLieuMuon taiLieuMuon = new TaiLieuMuon();
                taiLieuMuon.setTaiLieu(taiLieu);

                listTaiLieuMuons.add(taiLieuMuon);
            }

            // tạo phiếu mượn
            PhieuMuon phieuMuon = new PhieuMuon(-1, ngayMuon, ngayPhaiTra, nhanVien, banDoc, listTaiLieuMuons);
            
            // lấy list tài liệu đặt trước
            List<TaiLieuDatTruoc> listTaiLieuDatTruocs = (List<TaiLieuDatTruoc>) request.getSession().getAttribute("listTaiLieuDatTruocs");
            List<Integer> datTruocIds = listTaiLieuDatTruocs.stream().map(i -> i.getId()).toList();
            int id = muonTaiLieuDAO.insert(phieuMuon, datTruocIds);
            phieuMuon = muonTaiLieuDAO.getPhieuMuon(id);

            request.getSession().setAttribute("phieuMuon", phieuMuon);
            request.getRequestDispatcher("/nhanvien/PhieuMuon.jsp").forward(request, response);
        }
    }
}
