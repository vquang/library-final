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
                <a class="header-btn header-btn-active" href="BDTaiLieuController?action=view">Trang Chủ</a>
            </div>
            <a class="header-btn" href="/new-library/DangNhapController?action=logout">Đăng Xuất</a>
        </div>
        <div class="menu">
            <a href="BDKhoTaiLieu.jsp" class="menu-btn">Kho Tài Liệu</a>
            <a href="BDDatTruocController?action=view" class="menu-btn">Kho Đặt Trước</a>
        </div>
        <div class="container">
            <c:choose>
                <c:when test="${not empty listTaiLieus}">
                    <div class="content-frame">
                        <h1>Top 5 Phổ Biến</h1>
                        <div class="content-imgs">
                            <c:forEach var="taiLieu" items="${listTaiLieus}" varStatus="loop">
                                <c:if test="${loop.index < 5}">
                                    <a class="content-item" href="BDChiTietTaiLieu.jsp?id=${taiLieu.id}">
                                        <img class="content-item-image" src="/library/commons/${taiLieu.anhBia}" />
                                        <span class="content-item-text" style="color:black;font-size: 20px;">${taiLieu.ten}</span>
                                        <span class="content-item-text" >Tác giả: ${taiLieu.tacGia}</span>
                                        <span class="content-item-text" >Số lượng: ${taiLieu.soLuong}</span>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="content-frame">
                        <h1>Kho Tài Liệu</h1>
                        <div class="content-imgs">
                            <c:forEach var="taiLieu" items="${listTaiLieus}">
                                <a class="content-item" href="BDChiTietTaiLieu.jsp?id=${taiLieu.id}">
                                    <img class="content-item-image" src="/library/commons/${taiLieu.anhBia}" />
                                    <span class="content-item-text" style="color:black;font-size: 20px;">${taiLieu.ten}</span>
                                    <span class="content-item-text" >Tác giả: ${taiLieu.tacGia}</span>
                                    <span class="content-item-text" >Số lượng: ${taiLieu.soLuong}</span>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </body>
</html>
