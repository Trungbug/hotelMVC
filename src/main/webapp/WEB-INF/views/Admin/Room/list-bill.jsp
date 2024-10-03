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
    <title>Danh sách hóa đơn phòng</title>
    <%@ include file="/WEB-INF/views/inc/links.jsp" %>
</head>
<body class="bg-white">
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <div class="container-fluid" id="main-content">
        <div class="row">
            <div class="col-lg-10 ms-auto p-4 overflow-hidden">
                <h3 class="mb-4">Danh sách hóa đơn phòng</h3>

                <div class="card border-0 shadow-sm mb-4">
                    <div class="card-body">

                      <!--  <div class="text-end mb-4">
                            <c:url var="urlAddRoom" value="/them-phong"/>
                            <a href="${urlAddRoom}" class="btn btn-success rounded-pill shadow-none btn-sm">
                               Thêm Phòng
                            </a>
                        </div> -->

                        <div class="table-responsive-md" style="height: 450px; overflow-y: scroll; overflow-x:scroll;">
                             <!-- Bảng phòng -->
                            <table class="table bg-white table-hover border">
                                <thead class="sticky-top sticky-bottom">
                                    <tr class="bg-dark text-light">
                                    <th class="wide-column">Mã hóa đơn</th>
                                    <th class="wide-column">Tên khách hàng</th>
                                    <th class="wide-column">Tên phòng</th>
                                    <th class="wide-column">Tiền phòng</th>
                                    <th class="wide-column">Tiền khách trả</th>
                                    <th class="wide-column">Còn nợ</th>
                                    <th class="wide-column">Ngày lập hóa đơn</th>
                                    <th class="wide-column">Trạng thái</th>
                                    <th class="wide-column"></th>
                                    <th class="wide-column"></th>
                                    <th></th>
                                    <th></th>
                                    </tr>
                                </thead>
                                   <c:forEach var="payment" items="${payments}">
                                        <tr>
                                            <td>${payment.paymentId}</td>
                                            <td>${payment.booking.customer.name}</td>
                                            <td>${payment.booking.room.roomName}</td>
                                            <td>${payment.booking.totalAmount}</td>
                                            <td>${payment.transactionAmount}</td>
                                            <td>${payment.refund}</td>
                                            <td>${payment.transactionDate}</td>
                                            <td>Đã thanh toán</td>
                                            <th><a href="<c:url value='/xuat-file-excel/id=${payment.paymentId}'/>" class="btn btn-sm rounded-pill btn btn-success"/>Excel</th>
                                        </tr>
                                   </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
    <%@ include file="/WEB-INF/views/inc/scripts.jsp" %>
</body>
</html>