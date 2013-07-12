<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<jsp:include page="WEB-INF/jspf/header.jspf" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>
    <c:out value="${param.title}" />
</h1>
<p>
    <c:out value="${param.content}"/>
</p>

<jsp:include page="WEB-INF/jspf/footer.jspf" />  