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
<h2>Agregar Libro</h2>
<hr>
<div class="row p-0 m-0">
    <div class="col-6">
        <form action="AdminController?menu=books" method="post" class="form-control m-0" style="height: fit-content;">
            <label>ISBN:</label>
            <input class="form-control" type="text" name="isbn" required />
            Nombre:
            <input class="form-control" type="text" name="name" required />
            Precio:
            <input class="form-control" type="text" name="price" required />
            Categoría:
            <select class="custom-select form-inline" name="category">
                <option value="void">Escoge una categoría</option>
                <%
                    List<Category> categories = (List<Category>) request.getAttribute("categories");
                    if(categories!=null){
                        for(Category category:categories){
                %>
                <option value="<%=category.getId()%>"><%=category.getName()%></option>
                <%}}%>
            </select>
            Autor:
            <input class="form-control" type="text" name="author" required />
            <br/>
            <input class="btn btn-primary" type="submit" name="action" value="Guardar Libro" />
            <a href="AdminController?menu=books&action=list">Regresar</a>
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

