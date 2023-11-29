/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ThongKeDAO;
import entity.ThongKeBanDoc;
import entity.ThongKeTaiLieu;
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
public class ThongKeController extends HttpServlet {
    private final ThongKeDAO thongKeDAO = new ThongKeDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("ThongKe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loai = request.getParameter("loai");
        if(loai.equals("taiLieu")) {
            String thuTu = request.getParameter("thuTu");
            List<ThongKeTaiLieu> list = thongKeDAO.getListTKTaiLieus(thuTu);
            request.setAttribute("listTKTaiLieus", list);
            request.removeAttribute("listTKBanDocs");
            request.getRequestDispatcher("ThongKe.jsp").forward(request, response);
        } else if(loai.equals("banDoc")) {
            String thuTu = request.getParameter("thuTu");
            List<ThongKeBanDoc> list = thongKeDAO.getListTKBanDocs(thuTu);
            request.setAttribute("listTKBanDocs", list);
            request.removeAttribute("listTKTaiLieus");
            request.getRequestDispatcher("ThongKe.jsp").forward(request, response);
        }
    }
}
