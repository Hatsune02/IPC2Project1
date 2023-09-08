<%@ page import="java.util.List" %>
<%@ page import="com.example.demo1.entities.objects_library.*" %><%--
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
<h2>SOLICITUD DE PRÉSTAMO</h2>
<hr>
<div class="row p-0 m-0">
    <div class="col-5">
        <form action="FinalUserController?menu=loanApplication" method="post" class="form-control m-0" style="height: fit-content;">
            <br>
            Tipo de Obtencion:
            <select class="custom-select form-inline" name="type">
                <option value="pick">Recoger en tienda</option>
                <option value="domicile">Entrega a domicilio</option>
            </select>
            Precio de entrega a domicilio:
            <input class="form-control" type="text" name="price" value="${priceD}" readonly="" required />
            Días de préstamo:
            <input class="form-control" type="number" name="days" min="1" max="${maxDays}" required />
            <br/>
            <input class="btn btn-primary" type="submit" name="action" value="Generar" />
            <a href="FinalUserController?menu=loanApplication&action=list">Cancelar</a>
            <br/>
            <p style="color: red">${response1}</p>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
