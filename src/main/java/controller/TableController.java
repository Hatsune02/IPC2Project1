package controller;

import java.io.*;
import entities.module.*;
import jdbc.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.util.List;
import java.io.IOException;

@WebServlet(name = "TableController", value = "/Table-Controller")
public class TableController extends HttpServlet {
    String list = "tablesAdmins/table_admins.jsp";
    String add = "tablesAdmins/add.jsp";
    String edit = "tablesAdmins/edit.jsp";
    AdminDAO adminDAO = new AdminDAO();
    List<Admin> admins;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String access = "";
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("list")){
            access = list;
        }
        else if(action.equalsIgnoreCase("add")){
            access = add;
        }
        else if (action.equalsIgnoreCase("Guardar")) {
            String name, username, password, email, error=" ";
            boolean accept = true;
            name = request.getParameter("name");
            username = request.getParameter("username");
            password = request.getParameter("password");
            email = request.getParameter("email");
            admins = adminDAO.select();
            if(name!=null && username!=null && password!=null && email!=null){
                for (Admin admin : admins) {
                    if (admin.getUsername().equals(username)) {
                        accept = false;
                        error = "Error: Nombre de usuario ya utilizado";
                        break;
                    }
                    if (admin.getEmail().equals(email)) {
                        accept = false;
                        error = "Error: Nombre de usuario ya utilizado";
                        break;
                    }
                }
                if (accept) {
                    adminDAO.insert(new Admin(name,username,password,email));
                    access = list;
                }
                else{
                    request.setAttribute("response1",error);
                    access = add;
                }
            }
        }
        else if (action.equalsIgnoreCase("edit")){
            request.setAttribute("idActual", request.getParameter("id"));
            access = edit;
        }
        else if (action.equalsIgnoreCase("Actualizar")){
            int id = Integer.parseInt(request.getParameter("id"));
            String name, username, password, email, error=" ";
            boolean accept = true;
            name = request.getParameter("name");
            username = request.getParameter("username");
            password = request.getParameter("password");
            email = request.getParameter("email");
            adminDAO.update(new Admin(id,name,username,password,email));
            access = list;
        }
        else if(action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            adminDAO.delete(new Admin(id));
            access = list;
        }
        else if(action.equalsIgnoreCase("Buscar")){
            access = list;
        }
        RequestDispatcher vista = request.getRequestDispatcher(access);
        vista.forward(request,response);
    }
}
