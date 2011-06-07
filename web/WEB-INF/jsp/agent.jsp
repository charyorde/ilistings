<%-- 
    Document   : agent
    Created on : Mar 16, 2011, 12:30:25 AM
    Author     : Administrator
--%>

<%@page import="com.google.gwt.place.shared.Prefix"%>
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
        <title>Agent Signup Page</title>
    </head>
    <body>
        <h1>Agent Signup Page</h1>
        <c:out value="${param.email}" /><br />
        <div>Session id:</div> <c:out value="${pageContext.session.id}" />
    <!--form:form action="/agent" commandName="agententity" method="POST">-->
        <!--form:form action="confirm" commandName="agententity" method="POST">-->
        <form:form action="agent/confirm" modelAttribute="agententity" method="POST">
        <table>
            <tr>
                <td><label>Business Name:</label></td>
                <td><form:input path="businessName" /></td>
            </tr>
            <tr>
                <td><label>Address:</label></td>
                <td><form:input path="address" /></td>
            </tr>
            <tr>
                <td><label>About Company:</label></td>
                <td><form:textarea path="aboutCompany" rows="3" cols="20" /></td>
            </tr>
            <tr>
                <td><label>Year of Establishment:</label></td>
                <td><form:input path="yearofestablishment" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Signup" /></td>
            </tr>
        </table>
    </form:form>
    </body>
</html>
