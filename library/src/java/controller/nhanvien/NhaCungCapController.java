/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.nhanvien;

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
public class NhaCungCapController extends HttpServlet {

    private final NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("search")) {
            String search = (String) request.getParameter("search");

            List<NhaCungCap> list = nhaCungCapDAO.searchNhaCungCaps(search);
            request.setAttribute("list", list);
            request.getRequestDispatcher("/nhanvien/NhapTaiLieu.jsp").forward(request, response);
        }
    }
}
