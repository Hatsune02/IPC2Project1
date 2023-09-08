<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Carrier</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-info">
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="CarrierController?menu=transportsB&action=listPending" target="myFrame">Transporte entre Bibliotecas</a>
            </li>
            <li class="nav-item">
                <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="CarrierController?menu=transportsU&action=listPending" target="myFrame">Transporte a Usuarios</a>
            </li>
        </ul>
    </div>
    <div class="dropdown">
        <button style="border:none" class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown">
            ${carrierSession.getName()}
        </button>
        <div class="dropdown-menu dropdown-menu-right text-center">
            <a class="dropdown-item" href="#">
                <img src="img/loginUserIcon.png" alt="60" width="60"/>
            </a>
            <a class="dropdown-item" href="#">${carrierSession.getUsername()}</a>
            <a class="dropdown-item" href="#">${carrierSession.getEmail()}</a>
            <div class="dropdown-divider"></div>
            <form method="post" action="LoginController">
                <button name="action" value="out" class="dropdown-item" href="#">Cerrar Sesión</button>
            </form>
        </div>
    </div>
</nav>
<div class="m-4" style="height: 550px">
    <iframe name="myFrame" style="height: 100%;width: 100%;border: none">

    </iframe>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
