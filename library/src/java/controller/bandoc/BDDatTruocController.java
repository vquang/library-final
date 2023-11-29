/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.bandoc;

import dao.DatTruocDAO;
import entity.TaiLieu;
import entity.TaiLieuDatTruoc;
import entity.ThanhVien;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import utils.Time;

/**
 *
 * @author Admin
 */
public class BDDatTruocController extends HttpServlet {
    private final DatTruocDAO datTruocDAO = new DatTruocDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ThanhVien tv = new ThanhVien();
        tv.setId(1);
        request.getSession().setAttribute("thanhVien", tv);
        
        String action = (String) request.getParameter("action");
        if(action.equals("view")) {
            int banDocId = ((ThanhVien) request.getSession().getAttribute("thanhVien")).getId();
            List<TaiLieuDatTruoc> list = datTruocDAO.getList(banDocId);
            request.setAttribute("listTaiLieuDatTruocs", list);
            request.getRequestDispatcher("/bandoc/BDKhoDatTruoc.jsp").forward(request, response);
        } else if(action.equals("create")) {
            Date ngayDat = Time.stringToDate((String)request.getParameter("ngayDat"));
            Date ngayHetHan = Time.stringToDate((String)request.getParameter("ngayHetHan"));
            int trangThai = 0;
            int taiLieuId = Integer.parseInt(request.getParameter("taiLieuId"));
            int banDocId = ((ThanhVien) request.getSession().getAttribute("thanhVien")).getId();
            TaiLieu taiLieu = new TaiLieu();
            taiLieu.setId(taiLieuId);
            ThanhVien banDoc = new ThanhVien();
            banDoc.setId(banDocId);
            TaiLieuDatTruoc taiLieuDatTruoc = new TaiLieuDatTruoc(-1, ngayDat, ngayHetHan, trangThai, taiLieu, banDoc);
            datTruocDAO.create(taiLieuDatTruoc);
            
            List<TaiLieu> list = (List<TaiLieu>)request.getSession().getAttribute("listTaiLieus");
            for(int i = 0; i < list.size(); ++i) {
                if(list.get(i).getId() == taiLieuId) {
                    list.get(i).setSoLuong(list.get(i).getSoLuong() - 1);
                    break;
                }
            }
            request.getSession().setAttribute("listTaiLieus", list);
            
            request.getRequestDispatcher("/bandoc/BDChiTietTaiLieu.jsp?error=false&id=" + taiLieuId).forward(request, response);
        } else if(action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int taiLieuId = Integer.parseInt(request.getParameter("taiLieuId"));
            datTruocDAO.delete(id, taiLieuId);
            
            List<TaiLieu> list = (List<TaiLieu>)request.getSession().getAttribute("listTaiLieus");
            for(int i = 0; i < list.size(); ++i) {
                if(list.get(i).getId() == taiLieuId) {
                    list.get(i).setSoLuong(list.get(i).getSoLuong() + 1);
                    break;
                }
            }
            request.getSession().setAttribute("listTaiLieus", list);
            
            request.getRequestDispatcher("BDDatTruocController?action=view").forward(request, response);
        }
    }
}
