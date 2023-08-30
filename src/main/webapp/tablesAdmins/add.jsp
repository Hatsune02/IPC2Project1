<%@ page import="com.example.demo1.jdbc.AdminDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo1.entities.module.*" %>
<%--
  Created by IntelliJ IDEA.
  User: dog
  Date: 21/08/23
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <title>Agregar</title>
</head>
<body>
    <div class="container">
        <%
            String error = (String)request.getAttribute("response1");
            if(error==null) error=" ";
        %>
        <h1>Agregar Registros</h1>
        <hr>
        <form action="Table-Controller" class="form-control" style="width: 500px;height: 400px">
            Nombre:
            <input class="form-control" type="text" name="name" required/>
            Username:
            <input class="form-control" type="text" name="username" required/>
            Contraseña:
            <input class="form-control" type="text" name="password" required/>
            Email:
            <input class="form-control" type="text" name="email" required/>
            <br><br>
            <input class="btn btn-primary" type="submit" name="action" value="Guardar"/>
            <a href="Table-Controller?action=list">Regresar</a>
            <p style="color: red"><%=error%></p>
        </form>
    </div>
</body>
</html>
