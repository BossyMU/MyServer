<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>SELECT Operation</title>
</head>
<body>

<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                   url="jdbc:mysql://localhost/nanidb"
                   user="root"  password="TorpongJuntree"/>


<sql:query dataSource="${snapshot}" var="result">
    SELECT * from userLogIn;
</sql:query>

<table border="1" width="100%">
    <tr>
        <th>username</th>
        <th>password</th>
    </tr>
    <c:forEach var="row" items="${result.rows}">
        <tr>
            <td><c:out value="${row.username}"/></td>
            <td><c:out value="${row.password}"/></td>
            <td>
                <form onsubmit="return confirm('Do you really want to submit the form?');" action="/delete" method="post">
                    <input type="hidden" name="Delete" value="${row.username}">
                    <input type="submit" value="Delete">
                </form>
                <form action="/edit" method="get">
                    <input type="hidden" name="usernameEditFirst" value="${row.username}">
                    <input type="submit" value="Edit">
                </form>
            </td>
        </tr>
    </c:forEach>
    <form onsubmit="return confirm('Do you really want to Log out ?');" action="/logout" method="get">
        <input type="submit" value="Log out">
    </form>
    <form action="/add" method="get">
        <%--<input type="hidden" name="Add" value="${row.username}">--%>
        <input type="submit" value="Add">
    </form>
    <h1>Current User: ${currentUser}</h1>
</table>

</body>
</html>