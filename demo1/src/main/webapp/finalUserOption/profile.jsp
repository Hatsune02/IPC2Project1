<%--
Created by IntelliJ IDEA.
User: dog
Date: 21/08/23
Time: 23:29
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Editar</title>
</head>
<body>
<div class="row p-0 m-0">
    <div class="col-6">
        <h1>Perfil</h1>
        <hr>
        <h3>Cambiar datos</h3>
        <form action="FinalUserController?menu=profile" method="post" class="form-control m-0" style="height: fit-content;">
            Nombre:
            <input class="form-control"  type="text" name="name" value="${userSession.getName()}" required/>
            Username:
            <input class="form-control"  type="text" name="username" value="${userSession.getUsername()}" required/>
            Contraseña:
            <input class="form-control"  type="password" name="password" value="${userSession.getPassword()}" required/>
            Email:
            <input class="form-control"  type="text" name="email" value="${userSession.getEmail()}" required/>
            Saldo:
            <input class="form-control"  type="text" name="email" value="${userSession.getBalance()}" readonly="" required/>
            Premium:
            <input class="form-control"  type="text" name="email" value="${userSession.isPremium()}" readonly="" required/>
            Suspendido:
            <input class="form-control"  type="text" name="email" value="${userSession.isBan()}" readonly="" required/>
            <br><br>
            <input class="btn btn-primary" type="submit" name="action" value="Actualizar"/>
            <a href="FinalUserController?menu=profile&action=list">Regresar</a>
            <p style="color: green">${response}</p>
            <p style="color: red">${response1}</p>
        </form>
    </div>
    <div class="col-3">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-success ml-6" data-toggle="modal" data-target="#exampleModalCenter">
            VOLVERSE PREMIUM
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalCenterTitle">HAZTE PREMIUM</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Vuelvete premium y obten muchos beneficios a la hora de usar nuestra aplicación web
                            <br>tales como:
                            <br>- Capacidad de prestar más libros
                            <br>- Puedes prestar los libros por más tiempo
                            <br>- Hasta un 40% de descuento en entregas a domicilio
                            <br>- Puedes apartar libros que no esten disponibles por el momento
                            <br>Todo esto y más solo por Q${price}.
                            <br>
                            <br>¿Qué esperas para hacerte premium?
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">No gracias</button>
                        <a type="button" class="btn btn-primary" href="FinalUserController?menu=profile&action=premium" >Por supuesto</a>
                    </div>
                </div>
            </div>
        </div>
        <p style="color: green">${response2}</p>
        <p style="color: red">${response3}</p>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
