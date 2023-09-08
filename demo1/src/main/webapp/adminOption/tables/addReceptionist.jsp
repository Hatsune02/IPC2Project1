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
    <title>Agregar</title>
</head>
<body>
<h2>Agregar Receptionista</h2>
<hr>
<div class="row p-0 m-0">
    <div class="col-6">
        <form action="AdminController?menu=receptionists" method="post" class="form-control m-0" style="height: fit-content;">
            Nombre:
            <input class="form-control" type="text" name="name" required />
            Username:
            <input class="form-control" type="text" name="username" required />
            Email:
            <input class="form-control" type="text" name="email" required />
            Contraseña:
            <input class="form-control" type="password" name="password" required />
            Biblioteca:
            <select class="custom-select form-inline" name="libId">
                <option value="void">Escoge una biblioteca</option>
                <%
                    List<Library> libraries = (List<Library>) request.getAttribute("libraries");
                    if(libraries!=null){
                        for(Library library:libraries){
                %>
                <option value="<%=library.getId()%>"><%=library.getName()%></option>
                <%}}%>
            </select>
            <br><br>
            <input class="btn btn-primary" type="submit" name="action" value="Guardar" />
            <a href="AdminController?menu=receptionists&action=list">Regresar</a>
            <p style="color: red">${response1}</p>
        </form>
    </div>
    <div class="col-3">
        <a class="btn btn-success" href="AdminController?menu=books&action=add"> Agregar Categoria </a>
        <a class="btn btn-success mt-2" href="AdminController?menu=books&action=add"> Ver Categorias    </a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>

