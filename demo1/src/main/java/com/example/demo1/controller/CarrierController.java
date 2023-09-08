package com.example.demo1.controller;
import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import com.example.demo1.entities.module.*;
import com.example.demo1.entities.objects_process.Loan;
import com.example.demo1.entities.objects_process.Parameter;
import com.example.demo1.entities.objects_process.TransportBetweenLibraries;
import com.example.demo1.entities.objects_process.TransportToUser;
import com.example.demo1.jdbc.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet(name = "CarrierController", value = "/CarrierController")
public class CarrierController extends HttpServlet{
    HttpSession session;
    ReceptionistDAO receptionistDAO = new ReceptionistDAO();
    LoanDAO loanDAO = new LoanDAO();
    TransportBetweenLibrariesDAO transportBetweenLibrariesDAO = new TransportBetweenLibrariesDAO();
    TransportToUserDAO transportToUserDAO = new TransportToUserDAO();
    ParameterDAO parameterDAO = new ParameterDAO();
    String carrierMenu = "carrier_menu.jsp";
    String carrierOption = "carrierOption/";
    String transportsB = carrierOption+"transportsB.jsp";
    String transportsBOnTheWay = carrierOption+"transportsBOnTheWay.jsp";
    String transportsU = carrierOption+"transportsU.jsp";
    String transportsUOnTheWay = carrierOption+"transportsUOnTheWay.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String menu = request.getParameter("menu");
        String action=request.getParameter("action");
        session = request.getSession();
        if(menu.equals("carrier")){
            request.getRequestDispatcher(carrierMenu).forward(request,response);
        }
        else if(menu.equals("transportsB")){
            switch (action){
                case "listPending":
                    listTransportBetweenLibrariesPending(request,response);
                    break;
                case "listOnTheWay":
                    listTransportBetweenLibrariesOnTheWay(request,response);
                    break;
                case "onTheWay":
                    onTheWayTransportBetweenLibraries(request,response);
                    break;
                case "end":
                    endTransportBetweenLibraries(request,response);
                    break;
                case "Buscar":
                    searchPendingTransportBetweenLibraries(request,response);
                    break;
                case "Buscar_":
                    searchWAYTransportBetweenLibraries(request,response);
                    break;
            }

        }
        else if(menu.equals("transportsU")){
            switch (action){
                case "listPending":
                    listTransportToUsersPending(request,response);
                    break;
                case "listOnTheWay":
                    listTransportToUsersOnTheWay(request,response);
                    break;
                case "onTheWay":
                    onTheWayTransportToUsers(request,response);
                    break;
                case "end":
                    endTransportToUsers(request,response);
                    break;
                case "Buscar":
                    searchPendingTransportToUsers(request,response);
                    break;
                case "Buscar_":
                    searchWAYTransportToUsers(request,response);
                    break;
            }

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

    protected void listTransportBetweenLibrariesPending(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrier carrier = (Carrier) session.getAttribute("carrierSession");
        List<TransportBetweenLibraries> transports = transportBetweenLibrariesDAO.selectPerCarrier(carrier.getId());
        request.setAttribute("transports",transports);
        request.getRequestDispatcher(transportsB).forward(request,response);
    }
    protected void listTransportBetweenLibrariesOnTheWay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrier carrier = (Carrier) session.getAttribute("carrierSession");
        List<TransportBetweenLibraries> transports = transportBetweenLibrariesDAO.selectPerCarrierOTW(carrier.getId());
        request.setAttribute("transports",transports);
        request.getRequestDispatcher(transportsBOnTheWay).forward(request,response);
    }
    protected void onTheWayTransportBetweenLibraries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int transportId = Integer.parseInt(request.getParameter("transportId"));
        TransportBetweenLibraries transport = new TransportBetweenLibraries();
        transport.setId(transportId);
        transport.setState("EN CAMINO");
        transportBetweenLibrariesDAO.updateState(transport);
        listTransportBetweenLibrariesPending(request,response);
    }
    protected void endTransportBetweenLibraries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int transportId = Integer.parseInt(request.getParameter("transportId"));
        TransportBetweenLibraries transport = new TransportBetweenLibraries();
        transport.setId(transportId);
        transport.setState("FINALIZADO");
        transportBetweenLibrariesDAO.updateState(transport);
        listTransportBetweenLibrariesOnTheWay(request,response);
    }
    protected void searchPendingTransportBetweenLibraries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrier carrier = (Carrier) session.getAttribute("carrierSession");
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<TransportBetweenLibraries> transports = transportBetweenLibrariesDAO.searchPerCarrier(carrier.getId(),text);
            request.setAttribute("transports",transports);
            request.getRequestDispatcher(transportsB).forward(request,response);
        }
        else{
            listTransportBetweenLibrariesPending(request,response);
        }
    }

    protected void searchWAYTransportBetweenLibraries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrier carrier = (Carrier) session.getAttribute("carrierSession");
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<TransportBetweenLibraries> transports = transportBetweenLibrariesDAO.searchPerCarrierOTW(carrier.getId(),text);
            request.setAttribute("transports",transports);
            request.getRequestDispatcher(transportsBOnTheWay).forward(request,response);
        }
        else{
            listTransportBetweenLibrariesOnTheWay(request,response);
        }
    }


    protected void listTransportToUsersPending(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrier carrier = (Carrier) session.getAttribute("carrierSession");
        List<TransportToUser> transports = transportToUserDAO.selectPerCarrier(carrier.getId());
        request.setAttribute("transports",transports);
        request.getRequestDispatcher(transportsU).forward(request,response);
    }
    protected void listTransportToUsersOnTheWay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrier carrier = (Carrier) session.getAttribute("carrierSession");
        List<TransportToUser> transports = transportToUserDAO.selectPerCarrierOTW(carrier.getId());
        request.setAttribute("transports",transports);
        request.getRequestDispatcher(transportsUOnTheWay).forward(request,response);
    }
    protected void onTheWayTransportToUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int transportId = Integer.parseInt(request.getParameter("transportId"));
        TransportToUser transport = new TransportToUser();
        transport.setId(transportId);
        transport.setState("EN CAMINO");
        transportToUserDAO.updateState(transport);
        listTransportToUsersPending(request,response);
    }
    protected void endTransportToUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int transportId = Integer.parseInt(request.getParameter("transportId"));
        TransportToUser transport = new TransportToUser();
        transport.setId(transportId);
        transport.setState("FINALIZADO");
        transportToUserDAO.updateState(transport);
        transport = transportToUserDAO.selectOne(transport);

        Parameter parameterDays = parameterDAO.selectOne(new Parameter(4));
        int days = (int) parameterDays.getValue();
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate.plusDays(days));
        Loan loan = new Loan(transport.getLibraryId(), receptionistDAO.receptionistLessLoans(), transport.getFinalUserId(),transport.getBookIsbn(),transport.getTransportDate(),"ACTIVO",0);
        loanDAO.insert(loan);
        listTransportToUsersOnTheWay(request,response);
    }
    protected void searchPendingTransportToUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrier carrier = (Carrier) session.getAttribute("carrierSession");
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<TransportToUser> transports = transportToUserDAO.searchPending(carrier.getId(),text);
            request.setAttribute("transports",transports);
            request.getRequestDispatcher(transportsU).forward(request,response);
        }
        else{
            listTransportToUsersPending(request,response);
        }
    }

    protected void searchWAYTransportToUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrier carrier = (Carrier) session.getAttribute("carrierSession");
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<TransportToUser> transports = transportToUserDAO.searchOnTheWay(carrier.getId(),text);
            request.setAttribute("transports",transports);
            request.getRequestDispatcher(transportsUOnTheWay).forward(request,response);
        }
        else{
            listTransportToUsersOnTheWay(request,response);
        }
    }
}
