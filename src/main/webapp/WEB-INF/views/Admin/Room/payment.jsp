<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Base64" %>
<%@ page isELIgnored="false" %>
<%@ page import="org.apache.tomcat.util.http.fileupload.*" %>
<%@page session="false" %>
<%@ page import="java.util.Base64" %>
<html>
<head>
    <title>Thông tin chi tiết</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/views/inc/links.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resource/css/edit-facilities-type.css'/>">
</head>

<body class="bg-white">
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <h4>Thông tin chi tiết</h4>
    <c:url value="/thanh-toan-phong" var="url"/>
    <form:form modelAttribute="payment" method="post" action="${url}">
         <form:hidden path="paymentId"/>
         <form:hidden path="booking.bookingId"/>
         <form:hidden path="booking.customer.customerId"/>
         <form:hidden path="booking.room.roomId"/>

         <p>Tên khách hàng</p> <form:input path="booking.customer.name" readonly="true"/>
         <p style="color:red;"><form:errors path="booking.customer.name"> </form:errors> </p>

         <p>Email</p> <form:input path="booking.customer.email" readonly="true"/>
         <p style="color:red;"><form:errors path="booking.customer.email"> </form:errors> </p>

         <p>Số điện thoại</p> <form:input path="booking.customer.phoneNumber" readonly="true"/>
         <p style="color:red;"><form:errors path="booking.customer.phoneNumber"> </form:errors> </p>

         <p>Giới tính</p> <form:input path="booking.customer.gender" readonly="true"/>
         <p style="color:red;"><form:errors path="booking.customer.gender"> </form:errors> </p>

         <p>Phòng ở</p> <form:input path="booking.room.roomName" readonly="true"/>
         <p style="color:red;"><form:errors path="booking.room.roomName"> </form:errors> </p>

         <p>Số người ở</p> <form:input path="booking.numberOfPeople" readonly="true"/>
         <p style="color:red;"><form:errors path="booking.numberOfPeople"> </form:errors> </p>

         <p>Check-in</p> <form:input type="date" id="date-buy" name="date-buy" class="mb-3" path="booking.checkIn" readonly="true" />
         <p style="color:red;"><form:errors path="booking.checkIn"> </form:errors> </p>

         <p>Check-out</p> <form:input type="date" id="date-buy" name="date-buy" class="mb-3" path="booking.checkOut" readonly="true" />
         <p style="color:red;"><form:errors path="booking.checkOut"> </form:errors> </p>

         <p>Yêu cầu</p> <form:input path="booking.requiredSpecial" readonly="true"/>
         <p style="color:red;"><form:errors path="booking.requiredSpecial"> </form:errors> </p>

         <p>Tổng tiền</p> <form:input path="booking.totalAmount" readonly="true"/>
         <p style="color:red;"><form:errors path="booking.totalAmount"> </form:errors> </p>

         <p>Tiền khách trả</p> <form:input path="transactionAmount"/>
         <p style="color:red;"><form:errors path="transactionAmount"> </form:errors> </p>

         <p>Tiền trả lại</p> <form:input path="refund" readonly="true"/>
         <p style="color:red;"><form:errors path="refund"> </form:errors> </p>

         <p>Ngày thanh toán</p> <form:input type="date" path="transactionDate" readonly="true"/>
         <p style="color:red;"><form:errors path="transactionDate"> </form:errors> </p>


         <p><p><button type="submit" class="btn btn-sm rounded-pill btn-primary">Thanh toán</button>
         <button type="button" value="Hủy" class="btn btn-sm rounded-pill btn-danger" onclick="history.go(-1)">Hủy</button>
    </form:form>
    <%@ include file="/WEB-INF/views/inc/scripts.jsp" %>
</body>
</html>