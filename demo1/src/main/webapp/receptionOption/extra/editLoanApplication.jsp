<%@ page import="com.example.demo1.entities.objects_process.RevocationRequest" %>
<%@ page import="com.example.demo1.entities.objects_process.LoanApplication" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Editar</title>
</head>
<body>
<h2>Finalizar solicitud y crear préstamo</h2>
<hr>
<div class="row p-0 m-0">
    <div class="col-9">
        <form action="ReceptionController?menu=endLoan" method="post" class="form-control m-0" style="height: fit-content;">
            <%
                LoanApplication loanA = (LoanApplication) request.getAttribute("loanA");
                if(loanA!=null){
            %>
            Código:
            <input class="form-control"  type="text" name="id" readonly="" value="<%= loanA.getId()%>" required/>
            Librería:
            <input class="form-control"  type="text" name="library" readonly="" value="<%= loanA.getLibrary()%>" required/>
            Usuario:
            <input class="form-control"  type="text" name="userName" readonly="" value="<%= loanA.getFinalUser()%>" required/>
            ISBN:
            <input class="form-control"  type="text" name="isbn" readonly="" value="<%= loanA.getBookIsbn()%>" required/>
            Libro:
            <input class="form-control"  type="text" name="book" readonly="" value="<%= loanA.getBook()%>" required/>
            Fecha:
            <input class="form-control"  type="text" name="date" readonly="" value="<%= loanA.getLoanApplicationDate()%>" required/>
            <br/>
            <%      if(loanA.getState().equals("PENDIENTE".toUpperCase())){%>
            <input class="btn btn-success" type="submit" name="action" value="Finalizar"/>
            <%}}%>
            <a href="ReceptionController?menu=endLoan&action=list">Regresar</a>
            <br/>
        </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
