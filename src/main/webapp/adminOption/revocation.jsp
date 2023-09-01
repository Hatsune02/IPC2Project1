<%@ page import="java.util.*" %>
<%@ page import="entities.objects_process.*" %><%--
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
    <h1>LISTADO DE SOLICITUDES DE REVOCACIÓN DE SUSPENSIÓN</h1>
    <hr>
    <div class="navbar">
        <form class="">
            <a class="btn btn-success" href="AdminController?menu=revocation&action=listP"> PENDIENTES </a>
            <a class="btn btn-success" href="AdminController?menu=revocation&action=listA"> ACEPTADAS </a>
            <a class="btn btn-success" href="AdminController?menu=revocation&action=listD"> RECHAZADAS </a>
        </form>
        <form class="form-inline" method="post" action="AdminController?menu=revocation">
            <input type="search" name="search" class="form-control ml-2" >
            <input type="submit" name="action" value="Buscar" class="btn btn-outline-success">
        </form>
    </div>
    <div class="">
        <table class="table table-bordered">
            <tr>
                <th class="text-center">CÓDIGO</th>
                <th class="text-center">USUARIO</th>
                <th class="text-center">USUARIO NOMBRE</th>
                <th class="text-center">ESTADO</th>
                <th class="text-center">ACCIONES</th>
            </tr>
            <%
                List<RevocationRequest> list = (List<RevocationRequest>) request.getAttribute("revocationList");
                if(list!=null){
                    for(RevocationRequest revocationRequest:list){
            %>
            <tr>
                <td class="text-center"><%=revocationRequest.getId()%></td>
                <td class="text-center"><%=revocationRequest.getFinalUserId()%></td>
                <td class="text-center"><%=revocationRequest.getFinalUser()%></td>
                <td class="text-center"><%=revocationRequest.getState()%></td>
                <td class="text-center">
                    <a class="btn btn-warning" href="AdminController?menu=revocation&action=edit&id=<%= revocationRequest.getId()%>">VER DETALLES DE SOLICITUD</a>
                </td>
                <%}}%>
            </tr>
        </table>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
