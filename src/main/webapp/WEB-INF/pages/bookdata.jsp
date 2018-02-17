<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>BookData</title>
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
  </style>

</head>
<body>
<a href="/books">Back to bookshelf</a>
<c:if test="${method != 'add'}">
<h1>Book Details</h1>

<table class="tg">
  <tr>
    <th width="80">ID</th>
    <th width="120">Title</th>
    <th width="120">Description</th>
    <th width="120">Author</th>
    <th width="120">ISBN</th>
    <th width="120">printYear</th>
    <th width="120">readAlready</th>
    <c:if test="${book.readAlready == false}">
      <th width="120">Read</th>
    </c:if>
    <th width="120">Delete</th>
  </tr>
  <tr>
    <td>${book.id}</td>
    <td>${book.title}</td>
    <td>${book.description}</td>
    <td>${book.author}</td>
    <td>${book.isbn}</td>
    <td>${book.printYear}</td>
    <td>${book.readAlready}</td>
    <c:if test="${book.readAlready == false}">
      <td><a href="<c:url value='/books/read/${book.id}'/>">Read</a></td>
    </c:if>
    <td><a href="<c:url value='/books/remove/${book.id}'/>">Remove</a></td>
  </tr>
</table>
  </c:if>
<c:if test="${method == 'add'}">
  <h1>Add a Book</h1>
</c:if>

<c:if test="${method == 'replace'}">
  <h1>Replace a Book</h1>
</c:if>

  <c:url var="addAction" value="/books/${method}"/>

  <form:form action="${addAction}" commandName="book">
    <table>
      <c:if test="${!empty book.title}">
        <tr>
          <td>
            <form:label path="id">
              <spring:message text="ID"/>
            </form:label>
          </td>
          <td>
            <form:input path="id" readonly="true" size="8"/>
          </td>
        </tr>
      </c:if>
      <tr>
        <td>
          <form:label path="title">
            <spring:message text="Title"/>
          </form:label>
        </td>
        <td>
          <form:input path="title"/>
        </td>
      </tr>

      <tr>
        <td>
          <form:label path="description">
            <spring:message text="Description"/>
          </form:label>
        </td>
        <td>
          <form:textarea path="description"/>
        </td>
      </tr>
      <tr>
        <td>
          <form:label path="author">
            <spring:message text="Author"/>
          </form:label>
        </td>
        <td>
          <form:input path="author"/>
        </td>
      </tr>
      <tr>
        <td>
          <form:label path="isbn">
            <spring:message text="ISBN"/>
          </form:label>
        </td>
        <td>
          <form:input path="isbn"/>
        </td>
      </tr>
      <tr>
        <td>
          <form:label path="printYear">
            <spring:message text="Year"/>
          </form:label>
        </td>
        <td>
          <form:input path="printYear"/>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <c:if test="${!empty book.title}">
            <input type="submit"
                   value="<spring:message text="Replace Book"/>"/>
          </c:if>
          <c:if test="${empty book.title}">
            <input type="submit"
                   value="<spring:message text="Add Book"/>"/>
          </c:if>
        </td>
      </tr>
    </table>
  </form:form>
</body>
</html>