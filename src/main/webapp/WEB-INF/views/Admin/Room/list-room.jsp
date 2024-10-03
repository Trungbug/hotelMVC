<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Base64" %>
<%@ page isELIgnored="false" %>
<%@ page import="org.apache.tomcat.util.http.fileupload.*" %>
<%@page session="false" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="<c:url value='/resource/css/list-room.css'/>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý khách sạn</title>
    <%@ include file="/WEB-INF/views/inc/links.jsp" %>
</head>
<body class="bg-white">
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <div class="container-fluid" id="main-content">
        <div class="row">
            <div class="col-lg-10 ms-auto p-4 overflow-hidden">
                <h3 class="mb-4">Quản lý phòng</h3>

                <div class="card border-0 shadow-sm mb-4">
                    <div class="card-body">

                        <div class="text-end mb-4">
                            <c:url var="urlAddRoom" value="/them-phong"/>
                            <a href="${urlAddRoom}" class="btn btn-success rounded-pill shadow-none btn-sm">
                               Thêm Phòng
                            </a>
                        </div>

                        <div class="table-responsive-md" style="height: 450px; overflow-y: scroll; overflow-x:scroll;">
                             <!-- Bảng phòng -->
                            <table class="table bg-white table-hover border">
                                <thead class="sticky-top sticky-bottom">
                                    <tr class="bg-dark text-light">
                                    <th class="wide-column">Mã phòng</th>
                                    <th class="wide-column">Tên Phòng</th>
                                    <th class="wide-column">Loại phòng</th>
                                    <th class="wide-column">Hình ảnh</th>
                                    <th class="wide-column">Giá một đêm</th>
                                    <th class="wide-column">Diện tích</th>
                                    <th class="wide-column">Số người ở</th>
                                    <th class="wide-column">Mô tả</th>
                                    <th class="wide-column">Trạng thái</th>
                                    <th class="wide-column"></th>
                                    <th class="wide-column"></th>
                                    <th></th>
                                    <th></th>
                                    </tr>
                                </thead>
                                   <c:forEach var="room" items="${rooms}">
                                        <tr>
                                            <td>${room.roomId}</td>
                                            <td>${room.roomName}</td>
                                            <td>${room.roomType.typeName}</td>
                                             <td>
                                                <c:set var="imageRoom" value="${roomMap[room.roomId]}" />
                                                <img src="data:image/png;base64,${imageRoom}" alt="Image" style="width: 100px; height: 100px;">
                                             </td>
                                            <td>
                                                 <fmt:formatNumber value = " ${room.pricePerNight}" type = "currency"/>
                                            </td>
                                            <td>${room.area}</td>
                                            <td>${room.quantity}</td>
                                            <td>${room.roomType.description}</td>
                                            <td>${room.status}</td>
                                            <th><a href="<c:url value='/xoa-phong/id=${room.roomId}'/>" class="btn btn-sm rounded-pill btn-danger" onclick="return confirmDelete();"/>Xóa</th>
                                            <th><a href="<c:url value='/sua-phong/id=${room.roomId}'/>" class="btn btn-sm rounded-pill btn-primary"/>Sửa</th>
                                        </tr>
                                   </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>


   <script>
        function confirmDelete() {
        var result = confirm('Cảnh Báo : Nếu bạn đồng ý xóa đồng nghĩa bạn sẽ xóa các phòng có loại phòng này!Bạn có đồng ý?');
        if (result === true) {
            window.location.href = "list-facilities.jsp";
            return true;
        } else {
            return false;
        }
    }
   </script>

    <%@ include file="/WEB-INF/views/inc/scripts.jsp" %>
</body>
</html>