<%@ page import="java.util.*" %>
<%@ page import="com.example.demo1.entities.reports.*" %><%--
  Created by IntelliJ IDEA.
  User: dog
  Date: 21/08/23
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Libros</title>
</head>
<body>
<div class="container">
    <h1>TOP 10 LIBROS</h1>
    <hr>
    <div class="">
        <table class="table table-bordered">
            <tr>
                <th class="text-center">CÓDIGO</th>
                <th class="text-center">NOMBRE</th>
                <th class="text-center">PRÉSTAMOS</th>
            </tr>
            <%
                List<BooksLoans> list = (List<BooksLoans>) request.getAttribute("reports");
                if(list!=null){
                    for(BooksLoans booksLoans:list){
            %>
            <tr>
                <td class="text-center"><%=booksLoans.getIsbn()%></td>
                <td class="text-center"><%=booksLoans.getName()%></td>
                <td class="text-center"><%=booksLoans.getLoans()%></td>
            </tr>
            <%}}%>
        </table>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
