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
            <a href="QLThanhVienController?vaiTro=0&action=view" class="menu-btn menu-active">Bạn Đọc</a>
            <a href="QLThanhVienController?vaiTro=1&action=view" class="menu-btn">Nhân Viên</a>
            <a href="QLNhaCungCapController?action=view" class="menu-btn">Nhà Cung Cấp</a>
            <a href="ThongKe.jsp" class="menu-btn">Thống Kê</a>
        </div>
        <div class="container">
            <div class="container-header">
                <a href="TaoMoiThanhVien.jsp?vaiTro=0" class="a-container-header">Tạo mới</a>
            </div>
            <h1>Danh Sách Bạn Đọc</h1>
            <div class="content">
                <table>
                    <tr>
                        <th>Mã</th>
                        <th>Username</th>
                        <th>Họ Tên</th>
                        <th>Số Điện Thoại</th>
                        <th>Địa Chỉ</th>
                        <th>Email</th>
                        <th>Vai Trò</th>
                        <th colspan="2">Thao Tác</th>
                    </tr>
                    <c:choose>
                        <c:when test="${not empty listThanhViens}">
                            <c:forEach var="thanhVien" items="${listThanhViens}">
                                <c:choose>
                                    <c:when test="${thanhVien.vaiTro == 0}">
                                        <c:set var="vaiTro" value="Bạn Đọc"/>
                                    </c:when>
                                    <c:when test="${thanhVien.vaiTro == 1}">
                                        <c:set var="vaiTro" value="Nhân Viên"/>
                                    </c:when>
                                    <c:when test="${thanhVien.vaiTro == 2}">
                                        <c:set var="vaiTro" value="Admin"/>
                                    </c:when>
                                </c:choose>
                                <tr id="${thanhVien.id}">
                                    <td>${thanhVien.id}</td>
                                    <td>${thanhVien.username}</td>
                                    <td>${thanhVien.hoTen}</td>
                                    <td>${thanhVien.soDienThoai}</td>
                                    <td>${thanhVien.diaChi}</td>
                                    <td>${thanhVien.email}</td>
                                    <td >${vaiTro}</td>
                                    <td><a href="CapNhatThanhVien.jsp?id=${thanhVien.id}" class="a-table a-update"><i class="fa-solid fa-gear"></i></a></td>
                                    <td><a class="a-table a-delete" onclick="onDelete(this)"><i class="fa-solid fa-trash"></i></a></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </table>
            </div>
        </div>

        <script>
            init();
            function init() {
                if (${param.error == 'true'}) {
                    alert("Không thể xóa do có ràng buộc với hóa đơn!");
                }
            }
            function onDelete(element) {
                let result = confirm("Xác Nhận Xóa?");
                if (result) {
                    let id = element.closest('tr').id;
                    let url = 'QLThanhVienController?vaiTro=0&action=delete&id=' + id;
                    window.location.href = url;
                }
            }
        </script>
    </body>
</html>
