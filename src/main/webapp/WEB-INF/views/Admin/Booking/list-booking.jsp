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
                <h3 class="mb-4">Danh sách đặt phòng</h3>
                <div class="card border-0 shadow-sm mb-4">
                    <div class="card-body">

                        <div class="text-end mb-4">
                            <c:url var="urlBookingRoom" value="/dat-phong"/>
                            <a href="${urlBookingRoom}" class="btn btn-success rounded-pill shadow-none btn-sm">
                               Thêm mới
                            </a>

                             <c:url var="urlCheckIn" value="/nhan-phong"/>
                      `          <a href="urlCheckIn" class="btn btn-sm rounded-pill btn btn-danger">
                                    Nhận phòng
                                </a>`
                        </div>

                        <div class="table-responsive-md" style="height: 450px; overflow-y: scroll; overflow-x:scroll;">
                             <!-- Bảng phòng -->
                            <table class="table bg-white table-hover border">
                                <thead class="sticky-top sticky-bottom">
                                    <tr class="bg-dark text-light">
                                    <th class="wide-column">Mã đặt phòng</th>
                                    <th class="wide-column">Khách đặt</th>
                                    <th class="wide-column">Phòng đặt</th>
                                    <th class="wide-column">Số người ở</th>
                                    <th class="wide-column">Check-in</th>
                                    <th class="wide-column">Check-out</th>
                                    <th class="wide-column">Yêu cầu</th>
                                    <th class="wide-column">Tổng tiền</th>
                                    <th class="wide-column">Trạng thái</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    </tr>
                                </thead>
                                   <c:forEach var="booking" items="${bookings}">
                                        <tr>
                                            <td>${booking.bookingId}</td>
                                            <td>${booking.customer.name}</td>
                                            <td>${booking.room.roomName}</td>
                                            <td>${booking.numberOfPeople}</td>
                                            <td>${booking.checkIn}</td>
                                            <td>${booking.checkOut}</td>
                                            <td>${booking.requiredSpecial}</td>
                                            <td>${booking.totalAmount}</td>
                                            <td>${booking.paymentStatus}</td>
                                            <th><a href="<c:url value='/xoa-dat-phong/id=${booking.bookingId}'/>" class="btn btn-sm rounded-pill btn-danger" onclick="return confirmDelete();"/>Xóa</th>
                                            <th><a href="<c:url value='/sua-dat-phong/id=${booking.bookingId}'/>" class="btn btn-sm rounded-pill btn-primary"/>Sửa</th>
                                            <th><a href="<c:url value='/nhan-phong-da-dat/id=${booking.bookingId}'/>" class="btn btn-sm rounded-pill btn btn-light" onclick="return confirmCheckIn();">Nhận phòng</a></th>
                                        </tr>
                                   </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>


   <script>
        function confirmDelete() {
            var result = confirm('Bạn có chắc chắn muốn xóa không?');
            if (result === true) {
                window.location.href = "list-booking.jsp";
                return true;
            } else {
                return false;
            }
        }

         function confirmCheckIn() {
            var result = confirm('Bạn có chắc chắn muốn nhận phòng không?');
            if (result === true) {
                window.location.href = "occupied-room.jsp";
                return true;
            } else {
                return false;
            }
         }
   </script>

    <%@ include file="/WEB-INF/views/inc/scripts.jsp" %>
</body>
</html>