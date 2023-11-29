/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.nhanvien;

import dao.TaiLieuDAO;
import entity.TaiLieu;
import entity.ThanhVien;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TaiLieuController extends HttpServlet {
    
    private final TaiLieuDAO taiLieuDAO = new TaiLieuDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ThanhVien tv = new ThanhVien();
        tv.setId(7);
        request.getSession().setAttribute("thanhVien", tv);
        String action = request.getParameter("action");
        if (action.equals("search")) {
            String search = (String) request.getParameter("search");
            List<TaiLieu> list = taiLieuDAO.search(search);
            request.getSession().setAttribute("listTaiLieus", list);
            request.getSession().setAttribute("search", search);
            
            request.getSession().removeAttribute("taiLieu");
            
            String from = (String) request.getParameter("from");
            String url = "";
            if (from.equals("kho")) {
                url = "/nhanvien/KhoTaiLieu.jsp";
            } else if (from.equals("nhap")) {
                url = "/nhanvien/NhapTaiLieu.jsp";
            } else if (from.equals("muon")) {
                url = "/nhanvien/MuonTaiLieu.jsp";
            }
            request.getRequestDispatcher(url).forward(request, response);
        } else if (action.equals("create")) {
            String ten = (String) request.getParameter("ten");
            String tacGia = (String) request.getParameter("tacGia");
            String moTa = (String) request.getParameter("moTa");
            int soLuong = 0;
            String anhBia = (String) request.getParameter("anhBia");
            
            TaiLieu taiLieu = new TaiLieu(-1, ten, tacGia, moTa, soLuong, anhBia);
            taiLieuDAO.create(taiLieu);
            request.getRequestDispatcher("TaiLieuController?action=search&from=kho&search=" + ten).forward(request, response);
        } else if (action.equals("update")) {
            System.out.println("XXXX" + request.getParameter("id"));
            int id = Integer.parseInt(request.getParameter("id"));
            String ten = (String) request.getParameter("ten");
            String tacGia = (String) request.getParameter("tacGia");
            String moTa = (String) request.getParameter("moTa");
            int soLuong = 0;
            String anhBia = (String) request.getParameter("anhBia");
            
            TaiLieu taiLieu = new TaiLieu(id, ten, tacGia, moTa, soLuong, anhBia);
            taiLieuDAO.update(taiLieu);
            request.getRequestDispatcher("TaiLieuController?action=search&from=kho&search=" + ten).forward(request, response);
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            
            boolean ok = taiLieuDAO.delete(id);
            String search = (String) request.getSession().getAttribute("search");
            String url = "TaiLieuController?action=search&from=kho&error=" + !ok + "&search=" + search;
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
