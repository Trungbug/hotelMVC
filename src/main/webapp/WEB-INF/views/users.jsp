<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<%@page session="false" %>
<html>
<head>
    <title>Thong tin nguoi dung</title>
</head>
<body>
   <h2>Thong tin nguoi dung</h2>
   <hr/>
   <table>
        <tr>
            <th>Username</th>
            <th>Password</th>
            <th></th>
        </tr>
        <c:forEach items="${users}" var="u">
            <tr>
                <td>${u.userName}</td>
                <td>${u.password}</td>
                <td><a href="">Chi tiet</a></td>
            </tr>
        </c:forEach>
   </table>
</body>
</html>