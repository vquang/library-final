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
            <a href="QLNhaCungCapController?action=view" class="menu-btn menu-active">Nhà Cung Cấp</a>
            <a href="ThongKe.jsp" class="menu-btn">Thống Kê</a>
        </div>
        <div class="container">
            <div class="container-header">
                <a href="TaoMoiNhaCungCap.jsp" class="a-container-header">Tạo mới</a>
            </div>
            <h1>Danh Sách Nhà Cung Cấp</h1>
            <div class="content">
                <table>
                    <tr>
                        <th>Mã</th>
                        <th>Tên</th>
                        <th>Email</th>
                        <th>Địa Chỉ</th>
                        <th colspan="2">Thao Tác</th>
                    </tr>
                    <c:choose>
                        <c:when test="${not empty listNCCs}">
                            <c:forEach var="ncc" items="${listNCCs}">
                                <tr id="${ncc.id}">
                                    <td>${ncc.id}</td>
                                    <td>${ncc.ten}</td>
                                    <td>${ncc.diaChi}</td>
                                    <td>${ncc.soDienThoai}</td>
                                    <td><a href="CapNhatNhaCungCap.jsp?id=${ncc.id}" class="a-table a-update"><i class="fa-solid fa-gear"></i></a></td>
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
                    let url = 'QLNhaCungCapController?action=delete&id=' + id;
                    window.location.href = url;
                }
            }
        </script>
    </body>
</html>
