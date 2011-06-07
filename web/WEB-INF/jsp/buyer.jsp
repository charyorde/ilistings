<%-- 
    Document   : buyer
    Created on : Mar 16, 2011, 12:31:32 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buyer Signup Page</title>
    </head>
    <body>
        <h1>Buyer Signup Page</h1>
        <div>Session data:</div> <c:out value="${pageContext.session}" />
    <!--form:form action="signup/confirm" commandName="buyerentity" method="POST">-->
        <form:form action="buyer/confirm" modelAttribute="buyerentity" method="POST">
        
    </form:form>
    </body>
</html>
