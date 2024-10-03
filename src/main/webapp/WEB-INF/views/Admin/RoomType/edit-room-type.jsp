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
    <%@ include file="/WEB-INF/views/inc/links.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resource/css/edit-room-type.css'/>">
    <title>Sửa loại phòng</title>
</head>
<body class="bg-white">
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <p>Sửa loại phòng</p>
    <c:url value="/sua-loai-phong" var="url"/>
    <form:form modelAttribute="roomType" method="post" action="${url}">
        <form:hidden path="roomTypeId"/>
        <p>Tên loại phòng</p> <form:input path="typeName"/>
        <p style="color:red;"><form:errors path="typeName"> </form:errors> </p>
        <p>Mô tả</p> <form:input path="description"/>
        <p style="color:red;"><form:errors path="description"> </form:errors> </p>
        <p><p><button type="submit">Sửa</button>
    </form:form>
    <%@ include file="/WEB-INF/views/inc/scripts.jsp" %>
</body>
</html>