<%@ page import="com.example.demo1.jdbc.*" %>
<%@ page import="com.example.demo1.entities.module.*" %><%--
  Created by IntelliJ IDEA.
  User: dog
  Date: 24/08/23
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Admins</title>
</head>
<body>
    <div class="d-flex">
        <div class="card col-4" style="width: 250px">
            <div class="card-body">
                <form action="AdminController?menu=admins" method="post">
                    <div class="form-group">
                        <label>Código</label>
                        <input type="text" value="${admin.getId()}" name="id" class="form-control" readonly="">
                    </div>
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" value="${admin.getName()}" name="name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" value="${admin.getUsername()}" name="username" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Contraseña</label>
                        <input type="text" value="${admin.getPassword()}" name="password" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" value="${admin.getEmail()}" name="email" class="form-control" required>
                    </div>
                    <input type="submit" name="action" value="Agregar" class="btn btn-info">
                    <input type="submit" name="action" value="Actualizar" class="btn btn-info">
                    <p style="color: red">${response1}</p>
                </form>
            </div>
        </div>
        <div class="col-8">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th class="text-center">CÓDIGO</th>
                    <th class="text-center">NOMBRE</th>
                    <th class="text-center">USERNAME</th>
                    <th class="text-center">CONTRASEÑA</th>
                    <th class="text-center">EMAIL</th>
                    <th class="text-center">ACCIONES</th>
                </tr>
                </thead>
                <tbody>
                <%
                    AdminDAO adminDAO = new AdminDAO();
                    for(Admin ad : adminDAO.select()){
                %>
                    <tr>
                        <td class="text-center"><%=ad.getId()%></td>
                        <td class="text-center"><%=ad.getName()%></td>
                        <td class="text-center"><%=ad.getUsername()%></td>
                        <td class="text-center"><%=ad.getPassword()%></td>
                        <td class="text-center"><%=ad.getEmail()%></td>
                        <td class="text-center">
                            <a class="btn btn-warning" href="AdminController?menu=admins&action=edit&id=<%=ad.getId()%>">Editar</a>
                            <a class="btn btn-danger" href="AdminController?menu=admins&action=delete&id=${admin.getId()}">Delete</a>
                        </td>
                    </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
