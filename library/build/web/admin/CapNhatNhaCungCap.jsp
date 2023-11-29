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
                <a class="header-btn" href="AdminHome.jsp">Trang Chủ</a>
            </div>
            <a class="header-btn" href="/new-library/DangNhapController?action=logout">Đăng Xuất</a>
        </div>
        <div class="menu">
            <a href="QLThanhVienController?vaiTro=0&action=view" class="menu-btn">Bạn Đọc</a>
            <a href="QLThanhVienController?vaiTro=1&action=view" class="menu-btn">Nhân Viên</a>
            <a href="QLNhaCungCapController?action=view" class="menu-btn">Nhà Cung Cấp</a>
            <a href="ThongKe.jsp" class="menu-btn">Thống Kê</a>
        </div>
        <div class="container">
            <h1>Cập Nhật Thông Tin Nhà Cung Cấp</h1>
            <div class="content">
                <form action="QLNhaCungCapController" method="post" onsubmit="return validateForm(this)">
                    <h3>Form cập nhật</h3>
                    <input type="hidden" name="action" value="update" />
                    <c:choose>
                        <c:when test="${not empty listNCCs}">
                            <c:forEach var="ncc" items="${listNCCs}">
                                <c:if test="${ncc.id == param.id}">
                                    <div class="group">
                                        <label>Id: </label>
                                        <input type="text" name="id" value="${ncc.id}" readonly />
                                    </div>
                                    <div class="group">
                                        <label>Tên: </label>
                                        <input type="text" name="ten" value="${ncc.ten}" required />
                                    </div>
                                    <div class="group">
                                        <label>Địa Chỉ: </label>
                                        <input type="text" name="diaChi" value="${ncc.diaChi}" required />
                                    </div>
                                    <div class="group">
                                        <label>Số Điện Thoại: </label>
                                        <input type="text" name="soDienThoai" value="${ncc.soDienThoai}"required />
                                    </div>
                                    <button type="submit" class="btn-form">Submit</button>
                                </c:if>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </form>
            </div>
        </div>
        
        <script>
            function validateForm(element) {
                let phoneRegex = /^[0-9]{10}$/;
                let phoneInput = element.querySelector('input[name="soDienThoai"]');
                let soDienThoai = phoneInput.value;
                if(!phoneRegex.test(soDienThoai)) {
                    phoneInput.style.border = '2px solid red';
                    phoneInput.style.boxShadow = '2px 2px 5px red';
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>
