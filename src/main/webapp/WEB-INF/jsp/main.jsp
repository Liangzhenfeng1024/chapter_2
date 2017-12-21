<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Mr.L论坛</title>
    </head>
    <body>
        ${user.userName},欢迎登录，您当前的积分为${user.credits};
    </body>
</html>