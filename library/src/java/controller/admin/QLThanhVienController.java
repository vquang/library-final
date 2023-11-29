/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ThanhVienDAO;
import entity.ThanhVien;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import utils.VaiTro;

/**
 *
 * @author Admin
 */
public class QLThanhVienController extends HttpServlet {
    private final ThanhVienDAO thanhVienDAO = new ThanhVienDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String to = "";
        String action = request.getParameter("action");
        if(action.equals("view")) {
            int vaiTro = Integer.parseInt(request.getParameter("vaiTro"));
            List<ThanhVien> list  = thanhVienDAO.getAll(vaiTro);
            request.getSession().setAttribute("listThanhViens", list);
            if(vaiTro == VaiTro.BANDOC.getValue())  {
                to = "/admin/QLBanDoc.jsp";
            } else if(vaiTro == VaiTro.NHANVIEN.getValue()) {
                to = "/admin/QLNhanVien.jsp";
            }
            request.getRequestDispatcher(to).forward(request, response);
        } else if(action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = (String)request.getParameter("username");
            String hoTen = (String)request.getParameter("hoTen");
            String soDienThoai = (String)request.getParameter("soDienThoai");
            String diaChi = (String)request.getParameter("diaChi");
            String email = (String)request.getParameter("email");
            int vaiTro = Integer.parseInt(request.getParameter("vaiTro"));
            
            ThanhVien thanhVien = new ThanhVien(id, username, "", hoTen, soDienThoai, diaChi, email, vaiTro);
            thanhVienDAO.update(thanhVien);
            request.getRequestDispatcher("QLThanhVienController?action=view").forward(request, response);
        } else if(action.equals("create")) {
            String username = (String)request.getParameter("username");
            String password = (String)request.getParameter("password");
            String hoTen = (String)request.getParameter("hoTen");
            String soDienThoai = (String)request.getParameter("soDienThoai");
            String diaChi = (String)request.getParameter("diaChi");
            String email = (String)request.getParameter("email");
            int vaiTro = Integer.parseInt(request.getParameter("vaiTro"));
            
            ThanhVien thanhVien = new ThanhVien(-1, username, password, hoTen, soDienThoai, diaChi, email, vaiTro);
            boolean ok = thanhVienDAO.create(thanhVien);
            if(!ok) {
                request.getRequestDispatcher("/admin/TaoMoiThanhVien.jsp?error=" + !ok).forward(request, response);
            }
            request.getRequestDispatcher("QLThanhVienController?action=view").forward(request, response);
        } else if(action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int vaiTro = Integer.parseInt(request.getParameter("vaiTro"));
            boolean ok = thanhVienDAO.delete(id);
            String url = "QLThanhVienController?action=view&vaiTro=" + vaiTro + "&error=" + !ok;
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

}
