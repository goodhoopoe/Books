<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Books Page</title>
    <meta charset="utf-8"/>
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
        .pagination li {
            display: inline-block;
            list-style: none;
            padding: 0.25em 0.5em;
        }
    </style>
</head>
<body>
<a href="${method == 'search' ?  './books' : '../../index.jsp'}">Back to main menu</a>

<br/>
<br/>

<h1>Book List</h1>

<c:if test="${!empty listBooks}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Title</th>
            <th width="120">Description</th>
            <th width="120">Author</th>
            <th width="120">ISBN</th>
            <th width="120">printYear</th>
            <th width="120">readAlready</th>
            <th width="120">Delete</th>
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td>${book.id}</td>
                <td><a href="/view/${book.id}">${book.title}</a></td>
                <td>${book.description}</td>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
                <td>${book.printYear}</td>
                <td>${book.readAlready}</td>
                <td><a href="<c:url value='/books/remove/${book.id}'/>">Remove</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${method != 'search'}">
<ul class="pagination">
    <li>Pages</li>
    <c:forEach begin="1" end="${pages}" var="page">
        <li>
            <a href="<c:url value='/books/${page}'/>">${page}</a>
        </li>
    </c:forEach>

</ul>
</c:if>
<br/>
<br/>
<form:form action="/search">
<table>
    <tr>
        <td>
            <label>
                Keywords
            </label>
        </td>
        <td>
            <input name="keywords" value="${keywords}"/>
        </td>
    </tr>
    <tr>
        <td>
            <input type="submit"
                   value="Search"/>
        </td>
    </tr>
</table>
</form:form>


<h1><a href="/add">Add a Book</a></h1>


</body>
</html>
