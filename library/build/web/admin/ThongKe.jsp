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
            <a href="ThongKe.jsp?clear=true" class="menu-btn menu-active">Thống Kê</a>
        </div>
        <div class="container">
            <div class="container-header">
                <form id="taiLieu" action="ThongKeController" method="post" onsubmit="return beforeSearch(this)">
                    <input type="hidden" name="loai" value="taiLieu" />
                    <input type="hidden" name="thuTu" value="asc" />
                    <button type="submit" class="tk-btn">Tài Liệu</button>
                </form>
                <form id="banDoc" action="ThongKeController" method="post" onsubmit="return beforeSearch(this)">
                    <input type="hidden" name="loai" value="banDoc" />
                    <input type="hidden" name="thuTu" value="asc" />
                    <button type="submit" class="tk-btn">Bạn đọc</button>
                </form>
            </div>
            <h1>Thống Kê</h1>
            <div class="content">
                <table>
                    <c:choose>
                        <c:when test="${not empty listTKTaiLieus}">
                            <h1>Thống Kê Tài Liệu Theo Lượt Mượn</h1>
                            <tr>
                                <th>Mã</th>
                                <th>Tên Tài Liệu</th>
                                <th>Tác Giả</th>
                                <th>Số Lượng</th>
                                <th onclick="changeThuTu(this)">Số Lượt Mượn <i class="arrow fa-solid fa-arrow-up"></i></th>
                            </tr>
                            <c:forEach var="tkTaiLieu" items="${listTKTaiLieus}">
                                <tr>
                                    <td>${tkTaiLieu.taiLieu.id}</td>
                                    <td>${tkTaiLieu.taiLieu.ten}</td>
                                    <td>${tkTaiLieu.taiLieu.tacGia}</td>
                                    <td>${tkTaiLieu.taiLieu.soLuong}</td>
                                    <td class="td-sort">${tkTaiLieu.soLuotMuon}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${not empty listTKBanDocs}">
                            <h1>Thống Bạn Đọc Theo Lượt Mượn</h1>
                            <tr>
                                <th>Mã</th>
                                <th>Họ Tên</th>
                                <th>Số Điện Thoại</th>
                                <th>Địa Chỉ</th>
                                <th>Email</th>
                                <th onclick="changeThuTu(this)">Số Lượt Mượn <i class="arrow fa-solid fa-arrow-up"></i></th>
                            </tr>
                            <c:forEach var="tkBanDoc" items="${listTKBanDocs}">
                                <tr>
                                    <td>${tkBanDoc.banDoc.id}</td>
                                    <td>${tkBanDoc.banDoc.hoTen}</td>
                                    <td>${tkBanDoc.banDoc.soDienThoai}</td>
                                    <td>${tkBanDoc.banDoc.diaChi}</td>
                                    <td>${tkBanDoc.banDoc.email}</td>
                                    <td class="td-sort">${tkBanDoc.tongTaiLieu}</td>
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
                if (${param.clear == 'true'}) {
                    localStorage.clear();
                    localStorage.setItem("thuTu", "asc");
                    return;
                }
                let loai = localStorage.getItem("loai");
                let thuTu = localStorage.getItem("thuTu");
                let forms = document.forms;
                let inputThuTus = document.querySelectorAll('input[name="thuTu"]');
                let arrows = document.querySelectorAll('.arrow');
                if (loai !== null) {
                    for (let i = 0; i < forms.length; ++i) {
                        if (forms[i].id === loai) {
                            forms[i].querySelector('button').classList.add('btn-active');
                        } else {
                            forms[i].querySelector('button').classList.remove('btn-active');
                        }
                    }
                }
                if (thuTu !== null) {
                    for (let i = 0; i < inputThuTus.length; ++i) {
                        inputThuTus[i].value = thuTu;
                    }
                    for (let i = 0; i < arrows.length; ++i) {
                        if (thuTu === 'asc') {
                            arrows[i].classList.remove('fa-arrow-down');
                            arrows[i].classList.add('fa-arrow-up');
                        } else {
                            arrows[i].classList.remove('fa-arrow-up');
                            arrows[i].classList.add('fa-arrow-down');
                        }
                    }
                }
            }
            function beforeSearch(element) {
                let id = element.id;
                localStorage.setItem("loai", id);
                return true;
            }
            function changeThuTu(element) {
                if (element.querySelector('.arrow').classList.contains('fa-arrow-up')) {
                    localStorage.setItem("thuTu", "desc");
                } else {
                    localStorage.setItem("thuTu", "asc");
                }
                let inputThuTus = document.querySelectorAll('input[name="thuTu"]');
                let thuTu = localStorage.getItem("thuTu");
                for (let i = 0; i < inputThuTus.length; ++i) {
                    inputThuTus[i].value = thuTu;
                }
                let loai = localStorage.getItem("loai");
                document.getElementById(loai).submit();
            }
        </script>
    </body>
</html>
