/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.bandoc;

import dao.TaiLieuDAO;
import dao.ThongKeDAO;
import entity.TaiLieu;
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
public class BDTaiLieuController extends HttpServlet {

    private final ThongKeDAO thongKeDAO = new ThongKeDAO();
    private TaiLieuDAO taiLieuDAO = new TaiLieuDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("view")) {
            List<ThongKeTaiLieu> listThongKes = thongKeDAO.getListTKTaiLieus("desc");
            List<TaiLieu> list = listThongKes.stream().map(thongKe -> thongKe.getTaiLieu()).toList();
            request.getSession().setAttribute("listTaiLieus", list);
            request.getRequestDispatcher("/bandoc/BanDocHome.jsp").forward(request, response);
        } else if (action.equals("search")) {
            String search = (String) request.getParameter("search");
            List<TaiLieu> list = taiLieuDAO.search(search);
            request.getSession().setAttribute("listTaiLieus", list);
            request.getRequestDispatcher("/bandoc/BDKhoTaiLieu.jsp").forward(request, response);
        }
    }
}
