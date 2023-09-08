<%--
  Created by IntelliJ IDEA.
  User: dog
  Date: 25/08/23
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ page import="java.util.*" %>
<%@ page import="com.example.demo1.entities.objects_library.*" %>
<%@ page import="com.example.demo1.entities.objects_process.TransportBetweenLibraries" %><%--
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
    <title>Tranport</title>
</head>
<body>
<div class="container">
    <h1>SOLICITUDES DE TRANSPORTE ENTRE LIBRERIAS</h1>
    <hr>
    <div class="navbar">
        <form class="">
        </form>
        <form class="form-inline" method="post" action="ReceptionController?menu=applicationT">
            <input type="search" name="search" class="form-control ml-2" >
            <input type="submit" name="action" value="Buscar" class="btn btn-outline-success">
        </form>
    </div>
    <div class="">
        <table class="table table-bordered">
            <tr>
                <th class="text-center">CÓDIGO</th>
                <th class="text-center">SOLICITUD DE LIBRERIA</th>
                <th class="text-center">RECEPCIONISTA</th>
                <th class="text-center">TRANSPORTISTA</th>
                <th class="text-center">FECHA</th>
                <th class="text-center">ESTADO</th>
                <th class="text-center">ACCIONES</th>
            </tr>
            <%
                List<TransportBetweenLibraries> list = (List<TransportBetweenLibraries>) request.getAttribute("transports");
                if(list!=null){
                    for(TransportBetweenLibraries transport:list){
            %>
            <tr>
                <td class="text-center"><%=transport.getId()%></td>
                <td class="text-center"><%=transport.getLibraryO()%></td>
                <td class="text-center"><%=transport.getReceptionist()%></td>
                <td class="text-center"><%=transport.getCarrier()%></td>
                <td class="text-center"><%=transport.getTransportDate()%></td>
                <td class="text-center"><%=transport.getState()%></td>
                <td class="text-center">
                    <a class="btn btn-success" href="ReceptionController?menu=applicationT&action=select&transportId=<%= transport.getId()%>">SELECCIONAR</a>

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