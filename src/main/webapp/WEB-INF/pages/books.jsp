<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Users List</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>

</head>
<body>
<a href="index.jsp">Back to main menu</a>
<br />
<br />
<h1>Books</h1>

<c:if test="${!empty booksList}">
<table class="tg">
    <tr >
        <th width="100">ID</th>
        <th width="100">Title</th>
        <th width="100">Description</th>
        <th width="100">Author</th>
        <th width="100">Isbn</th>
        <th width="100">PrintYear</th>
        <th width="100">ReadAlready</th>
        <th width="100">Edit</th>
        <th width="100">Delete</th>
    </tr>
    <c:forEach items="${booksList}" var="book">
        <tr>
            <td>${book.title}</td>
            <td>${book.description}</td>
            <td>${book.author}</td>
            <td>${book.isbn}</td>
            <td>${book.printYear}</td>
            <td>${book.readAlready}</td>

            <td><a href="<c:url value='/edit/${book.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</c:if>

<h1>Add new book</h1>

<br/>
<br/>
</body>
</html>
