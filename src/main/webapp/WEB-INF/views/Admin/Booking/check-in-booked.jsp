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
    <title>Nhận phòng</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/views/inc/links.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resource/css/edit-facilities-type.css'/>">
</head>

<body class="bg-white">
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <h4>Nhận phòng</h4>
    <c:url value="/nhan-phong-da-dat" var="url"/>
    <form:form modelAttribute="booking" method="post" action="${url}">
         <form:hidden path="bookingId"/>
           <p>Phòng</p>
              <form:select path="roomId">
                 <c:forEach var="room" items="${rooms}">
                     <form:option value="${room.roomId}">${room.roomName}</form:option>
                 </c:forEach>
              </form:select>
           <p style="color:red;"><form:errors path="roomId"> </form:errors> </p>

           <p>Số người ở</p> <form:input path="numberOfPeople"/>
           <p style="color:red;"><form:errors path="numberOfPeople"> </form:errors> </p>

          <p>Check-in</p> <form:input type="date" id="date-buy" name="date-buy" class="mb-3" path="checkIn" />
          <p style="color:red;"><form:errors path="checkIn"> </form:errors> </p>

          <p>Check-out</p> <form:input type="date" id="date-buy" name="date-buy" class="mb-3" path="checkOut" />
          <p style="color:red;"><form:errors path="checkOut"> </form:errors> </p>

          <p>Yêu cầu</p> <form:input path="requiredSpecial"/>
          <p style="color:red;"><form:errors path="requiredSpecial"> </form:errors> </p>

          <p>Tổng tiền</p> <form:input path="totalAmount"/>
          <p style="color:red;"><form:errors path="totalAmount"> </form:errors> </p>

          <!-- Thẻ form:form cho đối tượng Customer -->
          <form:form modelAttribute="customer" method="post">
                 <form:hidden path="customerId"/>

                 <p>Tên khách hàng</p> <form:input path="name"/>
                 <p style="color:red;"><form:errors path="name"> </form:errors> </p>

                 <p>Email</p> <form:input path="email"/>
                 <p style="color:red;"><form:errors path="email"> </form:errors> </p>

                 <p>Số điện thoại</p> <form:input path="phoneNumber"/>
                 <p style="color:red;"><form:errors path="phoneNumber"> </form:errors> </p>

                 <p>Giới tính</p>
                 <form:select path="gender">
                    <form:option value="male">Nam</form:op5tion>
                    <form:option value="female">Nữ</form:option>
                 </form:select>
                  <p><p><button type="submit" class="btn btn-sm rounded-pill btn-primary">Nhận phòng</button>
                  <button type="button" value="Hủy" class="btn btn-sm rounded-pill btn-danger" onclick="history.go(-1)">Quay lại</button>
             </form:form>
    </form:form>
    <%@ include file="/WEB-INF/views/inc/scripts.jsp" %>
</body>
</html>