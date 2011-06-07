<%-- 
    Document   : thankyou
    Created on : Mar 20, 2011, 10:28:38 PM
    Author     : Kayode Odeyemi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listings Signup Confirmation</title>
    </head>
    <body>
        <div>Session data:</div> <c:out value="${pageContext.session}" />
        <h1>Account Signup Confirmation!</h1>
        <div>Agent Entity data is :</div><c:out value="${agententity.businessName}" />
        <div>Buyer Entity data is :</div><c:out value="${buyerentity}" />
    </body>
</html>
