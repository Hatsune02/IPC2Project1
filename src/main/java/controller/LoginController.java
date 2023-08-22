package controller;
import java.io.*;
import java.util.List;

import entities.module.*;
import jdbc.AdminDAO;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        Admin admin = new Admin();
        AdminDAO adminDAO= new AdminDAO();
        int record = 0;
        if(request.getParameter("button")!=null){
            String user = request.getParameter("username");
            String password = request.getParameter("password");

            admin.setUsername(user);
            admin.setPassword(password);

            record = adminDAO.validateAdmin(admin);
            //List<Admin> admins = adminDAO.select();

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + admin.getUsername() + "</h1>");
            out.println("<h1>" + admin.getPassword() + "</h1>");
            out.println("</body></html>");
            //response.sendRedirect("/demo1_war_exploded/admin_menu.jsp");
                /*if(record!=0){
                    response.sendRedirect("admin_menu.jsp");
                }
                else{
                    response.sendRedirect("login.jsp?record="+record);
                }*/
/*
                if(request.getParameter("combo_box").equals("Administrador")){

                }
                else if(request.getParameter("combo_box").equals("Recepcionista")){

                }
                else if(request.getParameter("combo_box").equals("Transportista")){

                }
                else if(request.getParameter("combo_box").equals("Usuario")){

                }*/
        }
    }

    public void destroy() {
    }
}
