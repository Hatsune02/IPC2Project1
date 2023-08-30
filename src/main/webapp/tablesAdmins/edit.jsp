<%@ page import="com.example.demo1.jdbc.AdminDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo1.entities.module.*" %><%--
  Created by IntelliJ IDEA.
  User: dog
  Date: 21/08/23
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <title>Editar</title>
</head>
<body>
    <div class="container">
      <%
        AdminDAO adminDAO = new AdminDAO();
        int id = Integer.parseInt((String)request.getAttribute("idActual"));
        Admin admin = new Admin();
        List<Admin> admins = adminDAO.select();
        for(Admin admin1: admins){
          if(admin1.getId() == id){
            admin = admin1;
            break;
          }
        }
      %>
      <h1>Editar Registros</h1>
      <hr>
      <form action="Table-Controller" class="form-control" style="width: 500px;height: 400px">
        ID:
        <input class="form-control"  type="text" name="id" readonly="" value="<%= admin.getId()%>"/>
        Nombre:
        <input class="form-control"  type="text" name="name" value="<%= admin.getName()%>" required/>
        Username:
        <input class="form-control"  type="text" name="username" value="<%= admin.getUsername()%>" required/>
        Contraseña:
        <input class="form-control"  type="text" name="password" value="<%= admin.getPassword()%>" required/>
        Email:
        <input class="form-control"  type="text" name="email" value="<%= admin.getEmail()%>" required/>
        <br><br>
        <input class="btn btn-primary" type="submit" name="action" value="Actualizar"/>
        <a href="Table-Controller?action=list">Regresar</a>
      </form>
    </div>
</body>
</html>


