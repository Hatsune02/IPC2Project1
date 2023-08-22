<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP PAGE</title>
    <link href="css/style_login.css" rel="stylesheet" type="text/css">
</head>
    <%
        String response1 = "";
        if(request.getParameter("record")!=null){
            int r = Integer.parseInt(request.getParameter("record"));
            if(r==0){
                response1 = "Error: Datos incorrectos";
            }
        }
    %>
<body>
    <div id="cuadro">
        <form method="get" action="LoginController">
            <p id="title"> INICIAR SESIÓN</p>
            <hr>
            <br/><br/>
            <label id="subtitle1">NOMBRE USUARIO</label>
            <br/><br/>
            <input type="text" class="entry" id="username" name="username" required/>
            <br/><br/>
            <label id="subtitle2">CONTRASEÑA</label>
            <br/><br/>
            <input type="password" class="entry" id="password" name="password" required>
            <br/><br/>
            <label id="combo_box">
                <select>
                    <option>Administrador</option>
                    <option>Recepcionista</option>
                    <option>Transportista</option>
                    <option>Usuario</option>
                </select>
            </label>
            <br/><br/>
            <input type="submit" value="Login" id="button" name="button">
            <br/>
            <p style="color: red"><%= response1%></p>
        </form>
        <br/>
    </div>
</body>
</html>
