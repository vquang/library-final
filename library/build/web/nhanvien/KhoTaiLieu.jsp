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
            <a href="KhoTaiLieu.jsp" class="menu-btn menu-active">Kho Tài Liệu</a>
            <a href="NhapTaiLieu.jsp" class="menu-btn">Nhập Tài Liệu</a>
            <a href="MuonTaiLieu.jsp" class="menu-btn">Mượn Tài Liệu</a>
            <a href="TraTaiLieu.jsp" class="menu-btn">Trả Tài Liệu</a>
        </div>
        <div class="container">
            <div class="container-header">
                <a href="TaoMoiTaiLieu.jsp" class="a-container-header">Tạo mới</a>
                <form class="search-form" action="TaiLieuController" method="post">
                    <input type="hidden" name="action" value="search" />
                    <div class="group">
                        <input class="search-item" type="text" placeholder="Tìm kiếm tài liệu..." name="search" />
                        <input type="hidden" name="from" value="kho" />
                        <button class="search-item" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                    </div>
                </form>
            </div>
            <c:choose>
                <c:when test="${not empty listTaiLieus}">
                    <table>
                        <tr>
                            <th>Mã</th>
                            <th>Tên Tài Liệu</th>
                            <th>Tác Giả</th>
                            <th>Số Lượng</th>
                            <th colspan="2">Thao Tác</th>
                        </tr>
                        <c:forEach var="taiLieu" items="${listTaiLieus}">
                            <tr id="${taiLieu.id}">
                                <td>${taiLieu.id}</td>
                                <td>${taiLieu.ten}</td>
                                <td>${taiLieu.tacGia}</td>
                                <td>${taiLieu.soLuong}</td>
                                <td><a href="ChiTietTaiLieu.jsp?id=${taiLieu.id}" class="a-table a-update"><i class="fa-solid fa-gear"></i></a></td>
                                <td><a class="a-table a-delete" onclick="onDelete(this)"><i class="fa-solid fa-trash"></i></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
            </c:choose>
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
                    let url = 'TaiLieuController?action=delete&id=' + id;
                    window.location.href = url;
                }
            }
        </script>
    </body>
</html>
