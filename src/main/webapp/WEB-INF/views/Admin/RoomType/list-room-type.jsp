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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý khách sạn</title>
    <%@ include file="/WEB-INF/views/inc/links.jsp" %>

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</head>

<script>
const BASE_URL = 'http://localhost:8080/DemoMVC/list-room-type'

// Sinh Vien
const API_STUDENT_LIST = '/list-room-type'
const API_STUDENT_ADD = '/api/student/add'
const API_STUDENT_UPDATE = '/api/student/update'
const API_STUDENT_DELETE = '/api/student/delete'

</script>


<body class="bg-white">
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <div class="container-fluid" id="main-content">
        <div class="row">
            <div class="col-lg-10 ms-auto p-4 overflow-hidden">
                <h3 class="mb-4">Quản lý loại phòng</h3>

                <div class="card border-0 shadow-sm mb-4">
                    <div class="card-body">

                        <div class="text-end mb-4">
                            <c:url var="urlAddRoomType" value="/them-loai-phong"/>
                            <a href="${urlAddRoomType}" class="btn btn-success rounded-pill shadow-none btn-sm">
                               Thêm loại phòng
                            </a>
                        </div>

                        <div class="table-responsive-md" style="height: 450px; overflow-y: scroll; overflow-x:scroll;">
                             <!-- Bảng loại phòng -->
                            <table class="table bg-white table-hover border">
                                <thead class="sticky-top">
                                    <tr class="bg-dark text-light">
                                    <th>Mã loại phòng</th>
                                    <th>Tên loại Phòng</th>
                                    <th>Mô tả</th>
                                    <th></th>
                                    <th></th>
                                    </tr>
                                </thead>
                                   <c:forEach var="roomType" items="${roomTypes}">
                                        <tr>
                                            <td>${roomType.roomTypeId}</td>
                                            <td>${roomType.typeName}</td>
                                            <td>${roomType.description}</td>
                                            <th><a href="<c:url value='/xoa-loai-phong/roomTypeId=${roomType.roomTypeId}'/>" class="btn btn-sm rounded-pill btn-danger" onclick="return confirmDelete();"/>Xóa</th>
                                            <th><a href="<c:url value='/sua-loai-phong/roomTypeId=${roomType.roomTypeId}'/>" class="btn btn-sm rounded-pill btn-primary"/>Sửa</th>
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
                window.location.href = "list-room-type.jsp";
                return true;
            } else {
                return false;
            }
        }
       </script>
    <%@ include file="/WEB-INF/views/inc/scripts.jsp" %>
</body>
</html>