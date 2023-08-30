package controller;
import java.io.*;
import entities.module.*;
import jdbc.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet{
    Admin admin = new Admin();
    AdminDAO adminDAO= new AdminDAO();
    Receptionist receptionist = new Receptionist();
    ReceptionistDAO receptionistDAO= new ReceptionistDAO();
    Carrier carrier = new Carrier();
    CarrierDAO carrierDAO= new CarrierDAO();
    FinalUser finalUser = new FinalUser();
    FinalUserDAO finalUserDAO= new FinalUserDAO();
    String login = "login.jsp";
    String adminMenu = "AdminController?menu=admin&action=list";
    String carrierMenu = "CarrierController?action=carrier";
    String userMenu = "FinalUserController?action=user";
    String receptionMenu = "ReceptionController?action=recep";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String access = "";
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        System.out.println("entro dopos");
        if(action == null) action="log";
        if(action.equalsIgnoreCase("log")){
            access = login;
        }
        else if(action.equalsIgnoreCase("Login")){
            int record = 0;
            System.out.println("intento");
            String user = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(user+password);
            if(request.getParameter("combo_box").equals("admin") && session.getAttribute("admin") == null){
                admin.setUsername(user);
                admin.setPassword(password);
                record = adminDAO.validate(admin);
                access = adminMenu;
                admin = adminDAO.selectOne(admin);
                session.setAttribute("admin",admin);
            }
            else if(request.getParameter("combo_box").equals("recep") && session.getAttribute("recep") == null){
                receptionist.setUsername(user);
                receptionist.setPassword(password);
                record = receptionistDAO.validate(receptionist);
                access = receptionMenu;
                receptionist = receptionistDAO.selectOne(receptionist);
                session.setAttribute("recep",receptionist);
            }
            else if(request.getParameter("combo_box").equals("carrier") && session.getAttribute("carrier") == null){
                carrier.setUsername(user);
                carrier.setPassword(password);
                record = carrierDAO.validate(carrier);
                access = carrierMenu;
                carrier = carrierDAO.selectOne(carrier);
                session.setAttribute("carrier",carrier);
            }
            else if(request.getParameter("combo_box").equals("user") && session.getAttribute("user") == null){
                finalUser.setUsername(user);
                finalUser.setPassword(password);
                record = finalUserDAO.validate(finalUser);
                access = userMenu;
                finalUser = finalUserDAO.selectOne(finalUser);
                session.setAttribute("user",finalUser);
            }
            String error = " ";
            if(record == 0) {
                session.invalidate();
                access = login;
                error = "Error: Datos incorrectos";
                request.setAttribute("response1",error);
            }
            System.out.println("record" + record);
        }
        else if(action.equalsIgnoreCase("out")){
            session.invalidate();
            response.setHeader("Cache-Control","no-cache");
            response.setHeader("Cache-Control","no-store");
            response.setDateHeader("Expires",0);
            access = login;
        }

        RequestDispatcher vista = request.getRequestDispatcher(access);
        vista.forward(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String access = "";
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        System.out.println("entro doget");
        if(action==null) access=login;
        if(session.getAttribute("admin") != null){
            access = adminMenu;
        }
        else if(session.getAttribute("recep") != null){
            access = receptionMenu;
        }
        else if(session.getAttribute("carrier") != null){
            access = carrierMenu;
        }
        else if(session.getAttribute("user") != null){
            access = userMenu;
        }

        RequestDispatcher vista = request.getRequestDispatcher(access);
        vista.forward(request,response);
    }

    public void destroy() {
    }
}
