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
            <h1>Tạo Mới Thông Tin Thành Viên</h1>
            <div class="error">
                <h3>Username đã tồn tại</h3>
            </div>
            <div class="content">
                <form action="QLThanhVienController" method="post" onsubmit="return validateForm(this)">
                    <h3>Form Tạo Mới</h3>
                    <input type="hidden" name="action" value="create" />
                    <div class="group">
                        <label>Username: </label>
                        <input type="text" name="username" required />
                    </div>
                    <div class="group">
                        <label>Password: </label>
                        <input type="password" name="password" required />
                    </div>
                    <div class="group">
                        <label>Họ Tên: </label>
                        <input type="text" name="hoTen" required />
                    </div>
                    <div class="group">
                        <label>Số Điện Thoại: </label>
                        <input type="text" name="soDienThoai" required />
                    </div>
                    <div class="group">
                        <label>Địa Chỉ: </label>
                        <input type="text" name="diaChi"required />
                    </div>
                    <div class="group">
                        <label>Email: </label>
                        <input type="text" name="email" required />
                    </div>
                    <button type="submit" class="btn-form">Submit</button>
                    <input type="hidden" name="vaiTro" value="${param.vaiTro}" />
                </form>
            </div>
        </div>

        <script>
            init();
            function init() {
                if (${param.error == 'true'}) {
                    document.querySelector('.error').style.display = 'block';
                }
            }
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
