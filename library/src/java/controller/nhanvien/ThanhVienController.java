/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.nhanvien;

import dao.ThanhVienDAO;
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
public class ThanhVienController extends HttpServlet {

    private final ThanhVienDAO thanhVienDAO = new ThanhVienDAO();

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

            List<ThanhVien> list = thanhVienDAO.searchBanDocs(search);
            request.setAttribute("list", list);
            String from = (String) request.getParameter("from");
            request.getRequestDispatcher(from).forward(request, response);
        }
    }
}
