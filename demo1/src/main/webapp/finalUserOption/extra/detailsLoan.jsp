<%@ page import="java.util.List" %>
<%@ page import="com.example.demo1.entities.objects_library.*" %>
<%@ page import="com.example.demo1.entities.objects_process.Loan" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.sql.Date" %><%--
  Created by IntelliJ IDEA.
  User: dog
  Date: 27/08/23
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Detalles</title>
</head>
<body>
<h2>Detalles de Préstamos</h2>
<hr>
<div class="row p-0 m-0">
    <div class="col-6">
        <form action="FinalUserController?menu=loans" method="post" class="form-control m-0" style="height: fit-content;">
            ISBN:
            <input class="form-control" type="text" name="isbn" value="${book.getBookIsbn()}" readonly="" required />
            Nombre del Libro:
            <input class="form-control" type="text" name="name" value="${book.getBook()}" readonly="" required />
            Nombre de Categoría:
            <input class="form-control" type="text" name="category" value="${book.getCategory()}" readonly="" required />
            Descripción:
            <textarea class="form-control" name="description" readonly="" required>${category.getDescription()}</textarea>
            Autor:
            <input class="form-control" type="text" name="author" value="${book.getAuthor()}" readonly="" required />
            Precio:
            <input class="form-control" type="text" name="price" value="${book.getPrice()}" readonly="" required />
            Días restantes:
            <input class="form-control" type="text" name="days" value="${days}" readonly="" required />
            Fecha de entrega:
            <input class="form-control" type="text" name="date" value="${loan.getLoanDate()}" readonly="" required />
            <input class="form-control" type="hidden" name="libId" value="${book.getLibraryID()}" readonly="" required />
            <br/>
            <%
                Loan loan = (Loan) request.getAttribute("loan");
                LocalDate localDate = LocalDate.now();
                Date today = Date.valueOf(localDate);
                if(loan.getLoanDate().before(today) || loan.getLoanDate().equals(today)){
            %>
            <p style="color: red">ESTAS ATRASADO CON LA DEVOLUCIÓN DEL LIBRO</p>
            <input class="btn btn-primary" type="submit" name="action" value="Pagar multa" />
            <%}%>
            <a href="FinalUserController?menu=loans&action=list">Regresar</a>
            <p style="color: red">${response1}</p>
            <br/>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
