package controller;
import java.io.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet(name = "CarrierController", value = "/CarrierController")
public class CarrierController extends HttpServlet{
    String carrierMenu = "carrier_menu.jsp";
    String carrierOption = "carrierOption/";
    String transportsB = carrierOption+"transportsB.jsp";
    String transportsU = carrierOption+"transportsU.jsp";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "carrier":
                request.getRequestDispatcher(carrierMenu).forward(request,response);
                break;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "carrier":
                request.getRequestDispatcher(carrierMenu).forward(request,response);
                break;
            case "transportsB":
                request.getRequestDispatcher(transportsB).forward(request,response);
                break;
            case "transportsU":
                request.getRequestDispatcher(transportsU).forward(request,response);
                break;
        }
    }
}
