<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.proxy.core.Book"%>

<html>
<head>
    <title>Books List</title>
</head>
<body>
    <h1>List of Books</h1>
    
       
        <c:forEach var="book" items="${books}">
           
                <td><c:out value="${book.id}"/></td>
                <td><c:out value="${book.title}"/></td>
                
                <td><c:out value="${book.author}"/></td>
              
            <br>
            <hr>
           
        </c:forEach>
        
        
   
    
    
</body>
</html>