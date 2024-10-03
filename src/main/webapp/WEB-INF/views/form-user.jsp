<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Hello Page</title>
</head>
<body>
    <p>form nguoi dung </p>
    <c:url value="/form-user" var="url"/>
    <form:form modelAttribute="user" method="post" action="${url}">
        <p>Ten nguoi dung</p> <form:input path="userName"/>
        <p style="color:red;"><form:errors path="userName"> </form:errors> </p>
        <p>Mat khau</p> <form:input path="password"/>
          <p style="color:red;"><form:errors path="password"> </form:errors> </p>
        <p><p><button type="submit">Submit</button>
    </form:form>
</body>
</html>
