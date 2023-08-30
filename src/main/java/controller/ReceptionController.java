package controller;
import java.io.*;
import entities.module.*;
import jdbc.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet(name = "ReceptionController", value = "/ReceptionController")
public class ReceptionController extends HttpServlet{
    String receptionMenu = "receptionist_menu.jsp";
    String receptionOption = "receptionOption/";
    String applicationT = receptionOption+"applicationT.jsp";
    String bookLoan = receptionOption+"bookLoan.jsp";
    String endLoan = receptionOption+"endLoan.jsp";
    String incident = receptionOption+"incident.jsp";
    String transportB = receptionOption+"transportB.jsp";
    String users = receptionOption+"users.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        switch (action){
            case "recep":
                request.getRequestDispatcher(receptionMenu).forward(request,response);
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        switch (action){
            case "recep":
                request.getRequestDispatcher(receptionMenu).forward(request,response);
                break;
            case "users":
                request.getRequestDispatcher(users).forward(request,response);
                break;
            case "bookLoan":
                request.getRequestDispatcher(bookLoan).forward(request,response);
                break;
            case "endLoan":
                request.getRequestDispatcher(endLoan).forward(request,response);
                break;
            case "incident":
                request.getRequestDispatcher(incident).forward(request,response);
                break;
            case "transportB":
                request.getRequestDispatcher(transportB).forward(request,response);
                break;
            case "applicationT":
                request.getRequestDispatcher(applicationT).forward(request,response);
                break;

            default:
                throw new AssertionError();
        }
    }
}
