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
            <a href="BDDatTruocController?action=view" class="menu-btn">Kho Đặt Trước</a>
        </div>
        <div class="container">
            <div class="error">
                <h3>Đặt Trước Thành Công</h3>
            </div>
            <div class="detail-frame">
                <c:choose>
                    <c:when test="${not empty listTaiLieus}">
                        <c:forEach var="taiLieu" items="${listTaiLieus}">
                            <c:if test="${taiLieu.id == param.id}">
                                <div class="detail-left" style="width:40%;">
                                    <img class="detail-img" src="/library/commons/${taiLieu.anhBia}" />
                                </div>
                                <div class="detail-right" style="width:60%;">
                                    <form id="${taiLieu.id}" action="BDDatTruocController" method="post" onsubmit="return beforeOrder(this)">
                                        <input type="hidden" name="action" value="create" />
                                        <input type="hidden" name="taiLieuId" value="${taiLieu.id}" />
                                        <input type="hidden" name="ngayDat" />
                                        <input type="hidden" name="ngayHetHan" />
                                        <input type="hidden" name="soLuong" value="${taiLieu.soLuong}" />
                                        <div class="form-header">
                                            <h1>Thông Tin Tài Liệu</h1>
                                        </div>
                                        <div class="group">
                                            <label>Tên Tài Liệu:</label>
                                            <span>${taiLieu.ten}</span>
                                        </div>
                                        <div class="group">
                                            <label>Tác Giả:</label>
                                            <span>${taiLieu.tacGia}</span>
                                        </div>
                                        <div class="group">
                                            <label>Số Lượng:</label>
                                            <span>${taiLieu.soLuong}</span>
                                        </div>
                                        <p style="width:90%;">${taiLieu.moTa}</p>
                                        <button class="btn-order" type="submit">Đặt Trước</button>
                                    </form>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </div>
        </div>

        <script>
            init();
            function init() {
                if (${param.error == 'false'}) {
                    document.querySelector('.error').style.display = 'block';
                }
                let list = JSON.parse(localStorage.getItem("listDatTruocs"));
                if (list.includes('${param.id}')) {
                    console.log("hehe");
                    document.querySelector('.btn-order').type = 'button';
                    document.querySelector('.btn-order').style.opacity = '0.2';
                }
            }
            function beforeOrder(element) {
                let list = [];
                let listDatTruocs = JSON.parse(localStorage.getItem("listDatTruocs"));
                
                if (listDatTruocs !== null) {
                    list = listDatTruocs;
                }
                let id = element.id;
                let soLuong = Number(document.querySelector('input[name="soLuong"]').value);
                if (list.includes(id)) {
                    return false;
                }
                if(soLuong <= 0) {
                    alert("Không đủ tài liệu trong kho!");
                    return false;
                }
                list.push(id);
                localStorage.setItem("listDatTruocs", JSON.stringify(list));

                let date = new Date();
                element.querySelector('input[name="ngayDat"]').value = convertDate(date);
                date.setDate(date.getDate() + 3);
                element.querySelector('input[name="ngayHetHan"]').value = convertDate(date);
                return true;
            }
            function convertDate(date) {
                var nam = date.getFullYear();
                var thang = date.getMonth() + 1;
                var ngay = date.getDate();
                var newDate = nam + '-' + (thang < 10 ? '0' : '') + thang + '-' + (ngay < 10 ? '0' : '') + ngay;
                return newDate;
            }
        </script>
    </body>
</html>
