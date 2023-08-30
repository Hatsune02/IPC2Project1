<%@ page import="entities.objects_library.ExistingBooks" %>
<%@ page import="jdbc.ExistingBooksDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Editar</title>
</head>
<body>
<h2>Editar Existencias</h2>
<hr>
<div class="row p-0 m-0">
    <div class="col-6">
        <form action="AdminController?menu=books" method="post" class="form-control m-0" style="height: fit-content;">
            Código de Biblioteca:
            <input class="form-control"  type="text" name="libId" readonly="" value="${book.getLibraryID()}" required/>
            Biblioteca:
            <input class="form-control"  type="text" name="library" readonly="" value="${book.getLibrary()}" required/>
            isbn:
            <input class="form-control"  type="text" name="isbn" readonly="" value="${book.getBookIsbn()}" required/>
            Libro:
            <input class="form-control"  type="text" name="book" readonly="" value="${book.getBook()}" required/>
            Existencia:
            <input class="form-control"  type="text" name="stock" value="${book.getExistence()}" required/>
            Descripción:
            <textarea class="form-control" name="description" required></textarea>
            <br/>
            <input class="btn btn-primary" type="submit" name="action" value="Actualizar"/>
            <a href="AdminController?menu=books&action=list">Regresar</a>
            <p style="color: red">${response1}</p>
        </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
