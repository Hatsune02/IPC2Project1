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
    <title>RECARGA DE SALDO</title>
</head>
<body>
<h2>Recarga de Saldo</h2>
<hr>
<div class="row p-0 m-0 ml-10">
    <div class="col-6">
        <form action="FinalUserController?menu=recharge" method="post" class="form-control m-0" style="height: fit-content;">
            <div class="col-auto" style="height: fit-content">
                Cantidad que desea recargar:
                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <div class="input-group-text">Q</div>
                    </div>
                    <input class="form-control" type="number" name="balance" placeholder="000.00" required />
                </div>
            </div>
            Número de tarjeta:
            <input class="form-control" type="text" name="name" required />
            Direccion:
            <input class="form-control" type="text" name="category" required />
            <br/>
            <input class="btn btn-primary" type="submit" name="action" value="Recargar" />
            <br/>
            <p style="color: green">${response}</p>
            <p style="color: red">${response1}</p>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
