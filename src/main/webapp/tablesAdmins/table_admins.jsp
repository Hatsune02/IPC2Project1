<%@ page import="com.example.demo1.jdbc.AdminDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo1.entities.module.*" %><%--
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
    <%--<link href="css/bootstrap.css" rel="stylesheet" type="text/css">--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Tabla Admins</title>
</head>
<body>
    <div class="container">
        <h1>TABLA DE ADMINISTRADORES</h1>
        <hr>
        <div class="navbar">
            <a class="btn btn-success" href="Table-Controller?action=add"> Agregar </a>
            <label>
                <select name="libraryComboBox" class="custom-select form-inline" style="width: 300px">
                    <option value="admin">Escoge una libreria</option>
                    <option value="recep">Recepcionista</option>
                    <option value="carrier">Transportista</option>
                </select>
            </label>
            <form class="form-inline">
                <input type="search" name="search" class="form-control">
                <input type="submit" name="action" value="Buscar" class="btn btn-outline-success">
            </form>
        </div>
        <br/>
        <div class="">
            <table class="table table-bordered">
                <tr>
                    <th class="text-center">CÓDIGO</th>
                    <th class="text-center">NOMBRE</th>
                    <th class="text-center">USERNAME</th>
                    <th class="text-center">EMAIL</th>
                    <th class="text-center">ACCIONES</th>
                </tr>
                <%
                    AdminDAO adminDAO = new AdminDAO();
                    List<Admin> admins = adminDAO.select();
                    for(Admin admin:admins){
                %>
                <tr>
                    <td class="text-center"><%= admin.getId()%></td>
                    <td><%= admin.getName()%></td>
                    <td class="text-center"><%= admin.getUsername()%></td>
                    <td class="text-center"><%= admin.getEmail()%></td>
                    <td class="text-center">
                        <a class="btn btn-warning" href="Table-Controller?action=edit&id=<%= admin.getId()%>">Editar</a>
                        <a class="btn btn-danger" href="Table-Controller?action=delete&id=<%= admin.getId()%>">Eliminar</a>
                    </td>
                    <%}%>
                </tr>
            </table>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
