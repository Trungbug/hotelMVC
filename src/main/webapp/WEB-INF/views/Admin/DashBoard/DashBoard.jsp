<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<%@page session="false" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value='/resource/css/index.css'/>">
    <title>Quản lý khách sạn</title>
    <%@ include file="/WEB-INF/views/inc/links.jsp" %>
</head>
<body class="bg-white">

   <%@ include file="/WEB-INF/views/inc/header.jsp" %>

    <div class="container-fluid" id="main-content">
        <div class="row">
            <div class="col-lg-10 ms-auto p-4 overflow-hidden">

            </div>
        </div>
    </div>

   <%@ include file="/WEB-INF/views/inc/scripts.jsp" %>
</body>
</html>