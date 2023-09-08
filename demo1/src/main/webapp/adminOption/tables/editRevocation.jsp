<%@ page import="com.example.demo1.entities.objects_process.RevocationRequest" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Editar</title>
</head>
<body>
<h2>Aceptar o Rechazar</h2>
<hr>
<div class="row p-0 m-0">
    <div class="col-8">
        <form action="AdminController?menu=revocation" method="post" class="form-control m-0" style="height: fit-content;">
            <%
                RevocationRequest revocationRequest = (RevocationRequest) request.getAttribute("revocationRequest");
                if(revocationRequest!=null){
            %>
            Código:
            <input class="form-control"  type="text" name="id" readonly="" value="<%= revocationRequest.getId()%>" required/>
            Usuario código:
            <input class="form-control"  type="text" name="userId" readonly="" value="<%= revocationRequest.getFinalUserId()%>" required/>
            Nombre de usuario:
            <input class="form-control"  type="text" name="name" readonly="" value="<%= revocationRequest.getFinalUser()%>" required/>
            Estado:
            <input class="form-control"  type="text" name="state" readonly="" value="<%= revocationRequest.getState()%>" required/>
            Detalle:
            <textarea class="form-control"  type="password" name="detail" readonly="" ><%= revocationRequest.getDetail()%></textarea>
            <br/>
            <%      if(revocationRequest.getState().equals("PENDIENTE".toUpperCase())){%>
            <input class="btn btn-success" type="submit" name="action" value="Aceptar"/>
            <input class="btn btn-danger" type="submit" name="action" value="Rechazar"/>
            <%}}%>
            <a href="AdminController?menu=revocation&action=listP">Regresar</a>
            <br/>
        </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>

