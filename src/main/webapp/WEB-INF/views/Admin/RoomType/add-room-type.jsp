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
    <title>Thêm loại phòng</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/views/inc/links.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resource/css/edit-room-type.css'/>">
</head>

<body class="bg-white">
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <h4>Thêm loại phòng</h4>
    <c:url value="/them-loai-phong" var="url"/>
    <form:form modelAttribute="roomType" method="post" action="${url}">
        <path="roomTypeId"/>
        <p>Tên loại phòng</p> <form:input path="typeName" id="room-type" name="room-type"/>
        <p style="color:red;"><form:errors path="typeName"> </form:errors> </p>
        <p>Mô tả</p> <form:input path="description" id="description" name="description"/>
        <p style="color:red;"><form:errors path="description"> </form:errors> </p>
        <p><p><button type="submit" class="btn btn-sm rounded-pill btn-primary">Thêm</button>
        <button type="button" value="Hủy" class="btn btn-sm rounded-pill btn-danger" onclick="history.go(-1)">Hủy</button>
    </form:form>
    <%@ include file="/WEB-INF/views/inc/scripts.jsp" %>
</body>
</html>