package controller;
import java.io.*;
import java.util.List;

import entities.module.*;
import jdbc.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet(name = "ReceptionController", value = "/ReceptionController")
public class ReceptionController extends HttpServlet{
    FinalUserDAO finalUserDAO = new FinalUserDAO();
    String receptionMenu = "receptionist_menu.jsp";
    String receptionOption = "receptionOption/";
    String applicationT = receptionOption+"applicationT.jsp";
    String bookLoan = receptionOption+"bookLoan.jsp";
    String endLoan = receptionOption+"endLoan.jsp";
    String incident = receptionOption+"incident.jsp";
    String transportB = receptionOption+"transportB.jsp";
    String users = receptionOption+"users.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String menu = request.getParameter("menu");
        String action=request.getParameter("action");
        if(menu.equals("recep")){
            request.getRequestDispatcher(receptionMenu).forward(request,response);
        }
        else if(menu.equals("users")){
            switch (action){
                case "list":
                    listUsers(request,response);
                    break;
                case "Agregar":
                    insertFinalUser(request,response);
                    break;
                case "Buscar":
                    searchFinalUser(request,response);
                    break;
            }
        }
        else if(menu.equals("bookLoan")){
            request.getRequestDispatcher(bookLoan).forward(request,response);
        }
        else if(menu.equals("endLoan")){
            request.getRequestDispatcher(endLoan).forward(request,response);
        }
        else if(menu.equals("incident")){
            request.getRequestDispatcher(incident).forward(request,response);
        }
        else if(menu.equals("transportB")){
            request.getRequestDispatcher(transportB).forward(request,response);
        }
        else if(menu.equals("applicationT")){
            request.getRequestDispatcher(applicationT).forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    public void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<FinalUser> userList = finalUserDAO.select();
        request.setAttribute("userList",userList);
        request.getRequestDispatcher(users).forward(request,response);
    }
    public void insertFinalUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String error = " ";
        boolean accept = true;
        List<FinalUser> users1 = finalUserDAO.select();
        if(name!=null && username!=null && password!=null && email!=null){
            for (FinalUser finalUser : users1) {
                if (finalUser.getUsername().equals(username)) {
                    accept = false;
                    error = "Error: Nombre de usuario ya utilizado";
                    break;
                }
                if (finalUser.getEmail().equals(email)) {
                    accept = false;
                    error = "Error: Cuenta de email ya utilizada";
                    break;
                }
            }
            if (accept) {
                finalUserDAO.insert(new FinalUser(name,username,password,email));

            }
            else{
                request.setAttribute("response1",error);
            }
        }
        listUsers(request,response);
    }
    public void searchFinalUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<FinalUser> userList = finalUserDAO.search(text);
            request.setAttribute("userList", userList);
            request.getRequestDispatcher(users).forward(request,response);
        }
        else{
            listUsers(request,response);
        }
    }
}
