///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package utils;
//
//import entity.ThanhVien;
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// *
// * @author Admin
// */
//@WebFilter("/*")
//public class MyFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest rq, ServletResponse rp, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) rq;
//        HttpServletResponse response = (HttpServletResponse) rp;
//        String uri = request.getRequestURI();
//        if (uri.endsWith("/DangNhap.jsp")
//                || uri.endsWith(".css") || uri.contains("/images/")
//                || uri.endsWith("DangNhapController")) {
//            // Tiếp tục chuỗi bộ lọc
//            chain.doFilter(request, response);
//            return;
//        }
//
//        ThanhVien thanhVien = (ThanhVien) request.getSession().getAttribute("thanhVien");
//        if (thanhVien == null) {
//            response.sendRedirect(request.getContextPath() + "/DangNhap.jsp");
//        } else {
//            if (thanhVien.getVaiTro() == 0 && !uri.contains("/bandoc/")) {
//                response.sendRedirect(request.getContextPath() + "/DangNhap.jsp");
//            } else if (thanhVien.getVaiTro() == 1 && uri.contains("/bandoc/")) {
//                response.sendRedirect(request.getContextPath() + "/DangNhap.jsp");
//            } else {
//                chain.doFilter(rq, rp);
//            }
//        }
//    }
//
//}
