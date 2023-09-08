<%@ page import="java.util.*" %>
<%@ page import="com.example.demo1.entities.objects_library.*" %><%--
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
    <h1>LISTADO DE LIBROS</h1>
    <hr>
    <div class="navbar">
        <form class="">
            <a class="btn btn-success" href="AdminController?menu=books&action=add"> Agregar Libro </a>
        </form>
        <form class="form-inline" method="post" action="AdminController?menu=books">
            <select name="libraryComboBox" class="custom-select form-inline" style="width: 300px">
                <option value="void">Escoge una libreria</option>
                <%
                    List<ExistingBooks> libraries = (List<ExistingBooks>) request.getAttribute("libraries");
                    if(libraries!=null){
                    for(ExistingBooks library: libraries){
                %>
                <option value="<%=library.getLibraryID()%>"><%=library.getLibrary()%></option>
                <%}}%>
            </select>
            <input type="search" name="search" class="form-control ml-2" >
            <input type="submit" name="action" value="Buscar" class="btn btn-outline-success">
        </form>
    </div>
    <div class="">
        <table class="table table-bordered">
            <tr>
                <th class="text-center">CÓDIGO B</th>
                <th class="text-center">BIBLIOTECA</th>
                <th class="text-center">ISBN</th>
                <th class="text-center">LIBRO</th>
                <th class="text-center">EXISTENCIAS</th>
                <th class="text-center">ACCIONES</th>
            </tr>
            <%
                List<ExistingBooks> list = (List<ExistingBooks>) request.getAttribute("booksList");
                //List<ExistingBooks> list = existingBooksDAO.list();
                if(list!=null){
                    for(ExistingBooks book:list){
            %>
            <tr>
                <td class="text-center"><%=book.getLibraryID()%></td>
                <td class="text-center"><%=book.getLibrary()%></td>
                <td class="text-center"><%=book.getBookIsbn()%></td>
                <td class="text-center"><%=book.getBook()%></td>
                <td class="text-center"><%=book.getExistence()%></td>
                <td class="text-center">
                    <a class="btn btn-warning" href="AdminController?menu=books&action=edit&libId=<%= book.getLibraryID()%>&isbn=<%= book.getBookIsbn()%>">Editar</a>
                    <a class="btn btn-danger" href="AdminController?menu=books&action=delete&id=">Eliminar</a>
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
