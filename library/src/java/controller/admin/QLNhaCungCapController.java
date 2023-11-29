/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.NhaCungCapDAO;
import entity.NhaCungCap;
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
public class QLNhaCungCapController extends HttpServlet {
    private final NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = (String) request.getParameter("action");
        if(action.equals("view")) {
            List<NhaCungCap> list = nhaCungCapDAO.getAll();
            request.getSession().setAttribute("listNCCs", list);
            request.getRequestDispatcher("/admin/QLNhaCungCap.jsp").forward(request, response);
        } else if(action.equals("create")) {
            String ten = (String) request.getParameter("ten");
            String diaChi = (String) request.getParameter("diaChi");
            String soDienThoai = (String) request.getParameter("soDienThoai");
            
            NhaCungCap ncc = new NhaCungCap(-1, ten, diaChi, soDienThoai);
            boolean ok = nhaCungCapDAO.create(ncc);
            if(!ok) {
                request.getRequestDispatcher("/admin/TaoMoiNhaCungCap.jsp?error=" + !ok).forward(request, response);
            }
            request.getRequestDispatcher("QLNhaCungCapController?action=view").forward(request, response);
        } else if(action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String ten = (String) request.getParameter("ten");
            String diaChi = (String) request.getParameter("diaChi");
            String soDienThoai = (String) request.getParameter("soDienThoai");
            
            NhaCungCap ncc = new NhaCungCap(id, ten, diaChi, soDienThoai);
            nhaCungCapDAO.update(ncc);
            request.getRequestDispatcher("QLNhaCungCapController?action=view").forward(request, response);
        } else if(action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            
            boolean ok = nhaCungCapDAO.delete(id);
            request.getRequestDispatcher("QLNhaCungCapController?action=view&error=" + !ok).forward(request, response);
        }
    }
}
