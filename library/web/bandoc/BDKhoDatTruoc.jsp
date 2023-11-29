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
                <a class="header-btn" href="BDTaiLieuController?action=view">Trang Chủ</a>
            </div>
            <a class="header-btn" href="/new-library/DangNhapController?action=logout">Đăng Xuất</a>
        </div>
        <div class="menu">
            <a href="BDKhoTaiLieu.jsp" class="menu-btn">Kho Tài Liệu</a>
            <a href="BDDatTruocController?action=view" class="menu-btn menu-active">Kho Đặt Trước</a>
        </div>
        <div class="container">
            <h1>Danh Sách Tài Liệu Đặt Trước</h1>
            <table>
                <tr>
                    <th>Mã</th>
                    <th>Tên Tài Liệu</th>
                    <th>Tác Giả</th>
                    <th>Ngày Đặt</th>
                    <th>Ngày Hết Hạn</th>
                    <th>Xóa</th>
                </tr>
                <c:choose>
                    <c:when test="${not empty listTaiLieuDatTruocs}">
                        <c:forEach var="taiLieuDatTruoc" items="${listTaiLieuDatTruocs}">
                            <tr class="tr" id="${taiLieuDatTruoc.id}">
                                <td>${taiLieuDatTruoc.taiLieu.id}</td>
                                <td>${taiLieuDatTruoc.taiLieu.ten}</td>
                                <td>${taiLieuDatTruoc.taiLieu.tacGia}</td>
                                <td>${taiLieuDatTruoc.ngayDat}</td>
                                <td>${taiLieuDatTruoc.ngayHetHan}</td>
                                <td><a class="a-table a-delete" onclick="onDelete(this)"><i class="fa-solid fa-trash"></i></a></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </table>
        </div>

        <script>
            init();
            function init() {
                let list = [];
                let rows = document.querySelectorAll('.tr');
                for(let i = 0; i < rows.length; ++i) {
                    list.push(rows[i].cells[0].textContent);
                }
                console.log(list);
                localStorage.setItem("listDatTruocs", JSON.stringify(list));
            }
            function onDelete(element) {
                let result = confirm("Xác Nhận Xóa?");
                if (result) {
                    let id = element.closest('tr').id;
                    let taiLieuId = element.closest('tr').cells[0].textContent;
                    let url = 'BDDatTruocController?action=delete&id=' + id + '&taiLieuId=' + taiLieuId;
                    window.location.href = url;
                }
            }
        </script>
    </body>
</html>
