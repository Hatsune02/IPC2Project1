<%@ page import="com.example.demo1.entities.module.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo1.entities.objects_library.BooksPerLibrary" %><%--
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
    <title>Books</title>
</head>
<body>
<div class="d-flex">
    <div class="card col-3" style="width: 250px">
        <div class="card-body">
            <form action="ReceptionController?menu=transportB" method="post">
                <div class="form-group">
                    <input type="submit" name="action" value="Ver Libros" class="btn btn-info">
                    <input type="submit" name="action" value="Finalizar" class="btn btn-success">
                </div>
            </form>

            <form action="ReceptionController?menu=transportB" method="post">
                <div class="form-group">
                    <label></label>
                    <input type="hidden" name="transportBId" value="${transportId}" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>ISBN</label>
                    <input type="text" name="isbn" value="${isbn}" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Cantidad</label>
                    <input type="text" name="stock" class="form-control" required>
                </div>
                <input type="submit" name="action" value="Agregar" class="btn btn-info">
                <a href="ReceptionController?menu=transportB&action=cancel&transportId=${transportId}">Cancelar</a>
                <br/>
                <p style="color: red">${response1}</p>
            </form>
        </div>
    </div>
    <div class="col-9">
        <div class="navbar">
            <form class="form-inline"><h2>LIBROS</h2></form>
            <form class="form-inline" method="post" action="ReceptionController?menu=transportB">
                <input type="search" name="search" class="form-control ml-2" >
                <input type="submit" name="action" value="Buscar Libro" class="btn btn-outline-success">
            </form>
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th class="text-center">ISBN</th>
                <th class="text-center">NOMBRE</th>
                <th class="text-center">CATEGORÍA</th>
                <th class="text-center">EXISTENCIAS</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<BooksPerLibrary> list = (List<BooksPerLibrary>) request.getAttribute("books");
                if(list!=null){
                    for(BooksPerLibrary booksPerLibrary : list){
            %>
            <tr>
                <td class="text-center"><%=booksPerLibrary.getBookIsbn()%></td>
                <td class="text-center"><%=booksPerLibrary.getBook()%></td>
                <td class="text-center"><%=booksPerLibrary.getCategory()%></td>
                <td class="text-center"><%=booksPerLibrary.getExistence()%></td>
                <td class="text-center">
                    <a class="btn btn-success" href="ReceptionController?menu=transportB&action=selectBook&isbn=<%= booksPerLibrary.getBookIsbn()%>&existence=<%= booksPerLibrary.getExistence()%>">Seleccionar</a>
                </td>
            </tr>

            <%}}%>
            </tbody>
        </table>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>

