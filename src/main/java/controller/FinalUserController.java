package controller;
import java.io.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet(name = "FinalUserController", value = "/FinalUserController")
public class FinalUserController extends HttpServlet{
    String userMenu = "final_user_menu.jsp";
    String finalUserOption = "finalUserOption/";
    String books = finalUserOption+"books.jsp";
    String loanApplication = finalUserOption+"loanApplication.jsp";
    String loans = finalUserOption+"loans.jsp";
    String profile = finalUserOption+"profile.jsp";
    String recharge = finalUserOption+"recharge.jsp";
    String revocation = finalUserOption+"revocation.jsp";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "user":
                request.getRequestDispatcher(userMenu).forward(request,response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "user":
                request.getRequestDispatcher(userMenu).forward(request,response);
                break;
            case "books":
                request.getRequestDispatcher(books).forward(request,response);
                break;
            case "loanApplication":
                request.getRequestDispatcher(loanApplication).forward(request,response);
                break;
            case "loans":
                request.getRequestDispatcher(loans).forward(request,response);
                break;
            case "profile":
                request.getRequestDispatcher(profile).forward(request,response);
                break;
            case "recharge":
                request.getRequestDispatcher(recharge).forward(request,response);
                break;
            case "revocation":
                request.getRequestDispatcher(revocation).forward(request,response);
                break;
        }
    }
}
