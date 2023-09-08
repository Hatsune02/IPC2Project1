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
        <form action="ReceptionController?menu=bookLoan" method="post" class="form-control m-0" style="height: fit-content;">
            Código de Pŕestamo:
            <input class="form-control"  type="text" name="id" value="${loan.getId()}" required/>
            Codigo de Librería:
            <input class="form-control"  type="text" name="libID" value="${loan.getLibraryId()}" required/>
            Codigo de Recepcionista:
            <input class="form-control"  type="text" name="recepId" value="${loan.getReceptionistId()}" required/>
            Codigo de Usuario:
            <input class="form-control"  type="text" name="userId" value="${loan.getFinalUserId()}" required/>
            ISBN:
            <input class="form-control"  type="text" name="isbn" value="${loan.getBookIsbn()}" required/>
            Fecha de Devolución:
            <input class="form-control"  type="text" name="date" value="${loan.getLoanDate()}" required/>
            <br/>
            <a href="ReceptionController?menu=bookLoan&action=list">Regresar</a>
        </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
