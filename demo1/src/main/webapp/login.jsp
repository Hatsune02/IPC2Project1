<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP PAGE</title>
    <link href="css/style_login.css" rel="stylesheet" type="text/css">
</head>
    <%
        String response1 = (String)request.getAttribute("response1");
        if(response1==null) response1=" ";
    %>
<body>
    <div id="cuadro">
        <form method="post" action="LoginController">
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
            <label id="box">
                <select name="combo_box">
                    <option value="admin">Administrador</option>
                    <option value="recep">Recepcionista</option>
                    <option value="carrier">Transportista</option>
                    <option value="user">Usuario</option>
                </select>
            </label>
            <br/><br/>
            <input type="submit" id="button" name="action" value="Login" >
            <br/>
            <p style="color: red"><%= response1%></p>
        </form>
        <br/>
    </div>
</body>
</html>
