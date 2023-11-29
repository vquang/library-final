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
                <a class="header-btn" href="NhanVienHome.jsp">Trang Chủ</a>
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
            <h1>Tạo Mới Tài Liệu</h1>
            <div class="detail-frame">
                <div class="detail-left">
                    <c:choose>
                        <c:when test="${not empty taiLieu}">
                            <img class="detail-img" src="/library/commons/${taiLieu.anhBia}" />
                        </c:when>
                        <c:otherwise>
                            <img class="detail-img" src="" alt="ảnh bìa" />
                        </c:otherwise>
                    </c:choose>
                    <form class="form-img" action="UploadImageController" method="post" enctype="multipart/form-data">
                        <input type="file" name="file" required />
                        <input type="hidden" name="from" value="TaoMoiTaiLieu.jsp" />
                        <button type="submit" ><i class="fa-solid fa-upload"></i></button>
                    </form>
                </div>
                <div class="detail-right" style="width:60%;">
                    <form action="TaiLieuController" method="post" style="width: 94%">
                        <input type="hidden" name="action" value="create" />
                        <div class="form-header">
                            <h1>Thông Tin Tài Liệu</h1>
                        </div>
                        <div class="group">
                            <label>Tên Tài Liệu:</label>
                            <input type="text" placeholder="ten" name="ten" required/>
                        </div>
                        <div class="group">
                            <label>Tác Giả:</label>
                            <input type="text" placeholder="tacGia" name="tacGia" required/>
                        </div>
                        <div class="group">
                            <label>Mô Tả:</label>
                            <textarea rows="4" style="width:200px;" name="moTa" ></textarea>
                        </div>
                        <c:if test="${not empty taiLieu}">
                            <input type="hidden" name="anhBia" value="${taiLieu.anhBia}" />
                        </c:if>
                        <button class="btn-order" type="submit">Tạo Mới</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
