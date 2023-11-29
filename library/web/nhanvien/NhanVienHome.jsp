<%-- 
    Document   : AdminHome
    Created on : Nov 28, 2023, 5:21:43 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="/library/commons/styles.css">
        <link rel="stylesheet" type="text/css" href="/library/commons/icons/css/all.css">
    </head>
    <body>
        <div class="header">
            <div class="header-left">
                <a class="header-btn header-btn-active" href="NhanVienHome.jsp">Trang Chủ</a>
            </div>
            <a class="header-btn" href="/new-library/DangNhapController?action=logout">Đăng Xuất</a>
        </div>
        <div class="menu">
            <a href="KhoTaiLieu.jsp" class="menu-btn">Kho Tài Liệu</a>
            <a href="NhapTaiLieu.jsp" class="menu-btn">Nhập Tài Liệu</a>
            <a href="MuonTaiLieu.jsp" class="menu-btn">Mượn Tài Liệu</a>
            <a href="TraTaiLieu.jsp" class="menu-btn">Trả Tài Liệu</a>
        </div>
        <div class="container">
            <h1>Nhân Viên Home</h1>
        </div>
    </body>
</html>
