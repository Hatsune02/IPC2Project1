package com.example.demo1.controller;
import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.example.demo1.entities.module.*;
import com.example.demo1.entities.objects_library.BooksPerLibrary;
import com.example.demo1.entities.objects_library.Category;
import com.example.demo1.entities.objects_library.ExistingBooks;
import com.example.demo1.entities.objects_process.*;
import com.example.demo1.entities.reports.Transactions;
import com.example.demo1.jdbc.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import static java.time.temporal.ChronoUnit.DAYS;

@WebServlet(name = "FinalUserController", value = "/FinalUserController")
public class FinalUserController extends HttpServlet{
    HttpSession session;
    FinalUserDAO finalUserDAO = new FinalUserDAO();
    ReceptionistDAO receptionistDAO = new ReceptionistDAO();
    CarrierDAO carrierDAO = new CarrierDAO();
    CategoryDAO categoryDAO = new CategoryDAO();
    ExistingBooksDAO existingBooksDAO = new ExistingBooksDAO();
    LoanDAO loanDAO = new LoanDAO();
    LoanApplicationDAO loanApplicationDAO = new LoanApplicationDAO();
    TransportToUserDAO transportToUserDAO = new TransportToUserDAO();
    ParameterDAO parameterDAO = new ParameterDAO();
    RevocationRequestDAO revocationRequestDAO = new RevocationRequestDAO();
    ReportsDAO reportsDAO = new ReportsDAO();

    String userMenu = "final_user_menu.jsp";
    String finalUserOption = "finalUserOption/";
    String books = finalUserOption+"books.jsp";
    String loanApplication = finalUserOption+"loanApplication.jsp";
    String loans = finalUserOption+"loans.jsp";
    String profile = finalUserOption+"profile.jsp";
    String recharge = finalUserOption+"recharge.jsp";
    String revocation = finalUserOption+"revocation.jsp";
    String extra = "extra/";
    String detailsBook = finalUserOption+extra+"detailsBook.jsp";
    String loanA1 = finalUserOption+extra+"loanA1.jsp";
    String loanA2 = finalUserOption+extra+"loanA2.jsp";
    String detailsLoan = finalUserOption+extra+"detailsLoan.jsp";
    String report = finalUserOption+extra+"report.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String menu = request.getParameter("menu");
        String action = request.getParameter("action");
        session = request.getSession();
        if(menu.equals("user")){
            request.getRequestDispatcher(userMenu).forward(request,response);
        }
        else if(menu.equals("books")){
            switch (action){
                case "list":
                    listBooks(request,response);
                    break;
                case "details":
                    detailsBooks(request,response);
                    break;
                case "Generar Préstamo":
                    createLoanApplication1(request,response);
                    break;
                case "Generar":
                    insertLoanApplication1(request,response);
                    break;
                case "Buscar":
                    listBooks(request,response);
                    break;

            }
        }
        else if(menu.equals("loanApplication")){
            switch (action){
                case "list":
                    listBooksLoanA(request,response);
                    break;
                case "select":
                    createLoanApplication2(request,response);
                    break;
                case "Generar":
                    insertLoanApplication2(request,response);
                    break;
                case "Buscar":
                    listBooksLoanA(request,response);
                    break;

            }
            request.getRequestDispatcher(loanApplication).forward(request,response);
        }
        else if(menu.equals("loans")){
            switch (action){
                case "list":
                    listLoans(request,response);
                    break;
                case "select":
                    selectLoan(request,response);
                    break;
                case "Pagar multa":
                    payPenaltyFee(request,response);
                    break;
                case "Buscar":
                    listLoans(request,response);
                    break;
            }
            request.getRequestDispatcher(loans).forward(request,response);
        }
        else if(menu.equals("profile")){
            switch (action){
                case "list":
                    profile(request,response);
                    break;
                case "Actualizar":
                    updateProfile(request,response);
                    break;
                case "premium":
                    premium(request,response);
                    break;
            }
        }
        else if(menu.equals("recharge")){
            if(action.equals("list")){
                request.getRequestDispatcher(recharge).forward(request,response);
            }
            if(action.equals("Recargar")){
                recharge(request,response);
            }

        }
        else if(menu.equals("revocation")){
            if(action.equals("list")){
                request.getRequestDispatcher(revocation).forward(request,response);
            }
            if(action.equals("Enviar")){
                createRevocationRequest(request,response);
            }

        }
        if(menu.equals("report")){
            report(request,response);
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

    protected void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BooksPerLibrary> bookList = existingBooksDAO.listBooksForUser();
        request.setAttribute("bookList",bookList);
        request.getRequestDispatcher(books).forward(request,response);
    }
    protected void detailsBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        libId = Integer.parseInt(request.getParameter("libId"));
        isbn = Integer.parseInt(request.getParameter("isbn"));
        BooksPerLibrary book = new BooksPerLibrary();
        book.setBookIsbn(isbn);
        book.setLibraryID(libId);
        book = existingBooksDAO.selectOneBookPL(book);
        Category category = new Category();
        for(Category category1: categoryDAO.select()){
            if(category1.getName().equals(book.getCategory())) category = category1;
        }
        request.setAttribute("category",category);
        request.setAttribute("book",book);
        request.getRequestDispatcher(detailsBook).forward(request,response);
    }
    int libId=0,isbn=0;
    protected void createLoanApplication1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinalUser user = (FinalUser) session.getAttribute("userSession");
        Parameter parameterPriceSend = parameterDAO.selectOne(new Parameter(2));
        Parameter parameterDiscount = parameterDAO.selectOne(new Parameter(3));
        Parameter parameterDays = parameterDAO.selectOne(new Parameter(4));
        double priceD = parameterPriceSend.getValue();
        if(user.isPremium()) priceD = priceD*parameterDiscount.getValue();

        int maxDays = (int) parameterDays.getValue();
        request.setAttribute("maxDays",maxDays);
        request.setAttribute("priceD",priceD);
        request.getRequestDispatcher(loanA1).forward(request,response);
    }
    protected void insertLoanApplication1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinalUser user = (FinalUser) session.getAttribute("userSession");
        int days = Integer.parseInt(request.getParameter("days"));
        double price = Double.parseDouble(request.getParameter("price"));
        String error = " ";
        Parameter parameterDays = parameterDAO.selectOne(new Parameter(4));
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate.plusDays(days));

        if(parameterDays.getValue() >= days && days > 0){
            if(request.getParameter("type").equals("pick")){
                LoanApplication loanA = new LoanApplication(libId,receptionistDAO.receptionistLessLoans(),user.getId(),isbn,date,"PENDIENTE","RECOGER",carrierDAO.carrierLessTransportsU());
                loanApplicationDAO.insert(loanA);
                listBooks(request,response);
            }
            if(request.getParameter("type").equals("domicile")){
                if(user.getBalance()>=price){
                    user.setBalance(user.getBalance()-price);
                    session.setAttribute("userSession",user);
                    finalUserDAO.update(user);
                    LoanApplication loanA = new LoanApplication(libId,receptionistDAO.receptionistLessLoans(),user.getId(),isbn,date,"PENDIENTE","DOMICILIO",carrierDAO.carrierLessTransportsU());
                    TransportToUser transport = new TransportToUser(libId,user.getId(),carrierDAO.carrierLessTransportsU(),date,"PENDIENTE",isbn);
                    transportToUserDAO.insert(transport);
                    loanApplicationDAO.insert(loanA);
                    listBooks(request,response);
                }
                else{
                    error = "Error: No cuentas con saldo suficiente";
                    request.setAttribute("response1",error);
                    createLoanApplication1(request,response);
                }
            }
        }
        else{
            error = "Error: Días ingresados incorrectos";
            request.setAttribute("response1",error);
            createLoanApplication1(request,response);
        }

    }
    protected void listBooksLoanA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BooksPerLibrary> bookList = existingBooksDAO.listBooksForUser();
        request.setAttribute("bookList",bookList);
        request.getRequestDispatcher(loanApplication).forward(request,response);
    }
    int libId2=0,isbn2=0;
    protected void createLoanApplication2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinalUser user = (FinalUser) session.getAttribute("userSession");
        libId2 = Integer.parseInt(request.getParameter("libId"));
        isbn2 = Integer.parseInt(request.getParameter("isbn"));

        Parameter parameterPriceSend = parameterDAO.selectOne(new Parameter(2));
        Parameter parameterDiscount = parameterDAO.selectOne(new Parameter(3));
        Parameter parameterDays = parameterDAO.selectOne(new Parameter(4));
        double priceD = parameterPriceSend.getValue();
        if(user.isPremium()) priceD = priceD*parameterDiscount.getValue();

        int maxDays = (int) parameterDays.getValue();
        request.setAttribute("maxDays",maxDays);
        request.setAttribute("priceD",priceD);
        request.getRequestDispatcher(loanA2).forward(request,response);
    }
    protected void insertLoanApplication2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinalUser user = (FinalUser) session.getAttribute("userSession");
        int days = Integer.parseInt(request.getParameter("days"));
        double price = Double.parseDouble(request.getParameter("price"));
        String error = " ";
        Parameter parameterDays = parameterDAO.selectOne(new Parameter(4));
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate.plusDays(days));

        if(parameterDays.getValue() >= days && days > 0){
            if(request.getParameter("type").equals("pick")){
                LoanApplication loanA = new LoanApplication(libId2,receptionistDAO.receptionistLessLoans(),user.getId(),isbn2,date,"PENDIENTE","RECOGER",carrierDAO.carrierLessTransportsU());
                loanApplicationDAO.insert(loanA);
                listBooksLoanA(request,response);
            }
            if(request.getParameter("type").equals("domicile")){
                if(user.getBalance()>=price){
                    user.setBalance(user.getBalance()-price);
                    session.setAttribute("userSession",user);
                    finalUserDAO.update(user);
                    LoanApplication loanA = new LoanApplication(libId2,receptionistDAO.receptionistLessLoans(),user.getId(),isbn2,date,"PENDIENTE","DOMICILIO",carrierDAO.carrierLessTransportsU());
                    TransportToUser transport = new TransportToUser(libId2,user.getId(),carrierDAO.carrierLessTransportsU(),date,"PENDIENTE",isbn2);
                    transportToUserDAO.insert(transport);
                    loanApplicationDAO.insert(loanA);
                    listBooksLoanA(request,response);
                }
                else{
                    error = "Error: No cuentas con saldo suficiente";
                    request.setAttribute("response1",error);
                    createLoanApplication2(request,response);
                }
            }
        }
        else{
            error = "Error: Días ingresados incorrectos";
            request.setAttribute("response1",error);
            createLoanApplication2(request,response);
        }

    }
    protected void listLoans(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinalUser user = (FinalUser) session.getAttribute("userSession");
        List<Loan> loanList = loanDAO.listPerUser(user.getId());
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate);
        if(!user.isBan()){
            for(Loan loan : loanList){
                if(loan.getLoanDate().before(date)){
                    user.setBan(true);
                    finalUserDAO.update(user);
                    session.setAttribute("userSession",user);
                    break;
                }
            }
        }
        request.setAttribute("loanList",loanList);
        request.getRequestDispatcher(loans).forward(request,response);
    }
    protected void selectLoan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinalUser user = (FinalUser) session.getAttribute("userSession");
        int loanId = Integer.parseInt(request.getParameter("loanId"));
        Loan loan = loanDAO.selectOneById(new Loan(loanId));
        LocalDate localDate = LocalDate.now();
        Date date = loan.getLoanDate();
        LocalDate entrega = date.toLocalDate();
        int dayI = localDate.getDayOfYear();
        int dayF = entrega.getDayOfYear();
        int days = dayF - dayI;

        BooksPerLibrary book = new BooksPerLibrary();
        book.setBookIsbn(loan.getBookIsbn());
        book.setLibraryID(loan.getLibraryId());
        book = existingBooksDAO.selectOneBookPL(book);
        Category category = new Category();
        for(Category category1: categoryDAO.select()){
            if(category1.getName().equals(book.getCategory())) category = category1;
        }
        request.setAttribute("category",category);
        request.setAttribute("book",book);
        request.setAttribute("loan",loan);
        request.setAttribute("days",days);
        request.getRequestDispatcher(detailsLoan).forward(request,response);
    }
    protected void payPenaltyFee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("entro");
        FinalUser user = (FinalUser) session.getAttribute("userSession");
        Parameter parameterPenaltyFee = parameterDAO.selectOne(new Parameter(6));
        if(user.getBalance()>= parameterPenaltyFee.getValue()){
            user.setBalance(user.getBalance()-parameterPenaltyFee.getValue());
            finalUserDAO.update(user);
            session.setAttribute("userSession",user);
            reportsDAO.insertTransaction(new Transactions(parameterPenaltyFee.getValue(),0.0,"Se pagó la multa por retraso de libro"));
            listLoans(request,response);
        }
        else{
            request.setAttribute("response1","No tienes suficiente saldo para realizar esta acción");
            listLoans(request,response);
        }
    }

    protected void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Parameter parameterPremium = parameterDAO.selectOne(new Parameter(1));
        double price = parameterPremium.getValue();
        request.setAttribute("price",price);
        request.getRequestDispatcher(profile).forward(request,response);
    }
    protected void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinalUser user = (FinalUser) session.getAttribute("userSession");
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String error = " ";
        boolean accept = true;
        List<FinalUser> users = finalUserDAO.select();
        users.removeIf(user1 -> user1.getId() == user.getId());

        if(name!=null && username!=null && password!=null && email!=null){
            for(FinalUser user2 : users){
                if(user2.getUsername().equals(username)){
                    accept = false;
                    error = "Error: Nombre de usuario ya utilizado";
                    break;
                }
                if(user2.getEmail().equals(email)){
                    accept = false;
                    error = "Error: Cuenta de email ya utilizada";
                    break;
                }
            }
            if(accept){
                FinalUser userUpdate = new FinalUser(user.getId(),name,username,password,email);
                finalUserDAO.update(userUpdate);
                session.setAttribute("userSession",userUpdate);
                request.setAttribute("response","Se han guardado los cambios");
                profile(request,response);
            }
            else{
                request.setAttribute("response1",error);
                profile(request,response);
            }
        }
    }
    protected void premium(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinalUser user = (FinalUser) session.getAttribute("userSession");
        Parameter parameterPremium = parameterDAO.selectOne(new Parameter(1));
        double price = parameterPremium.getValue();

        if(user.getBalance()>=price && !user.isPremium()){
            user.setPremium(true);
            finalUserDAO.update(user);
            session.setAttribute("userSession",user);
            reportsDAO.insertTransaction(new Transactions(price,0.0,"Se gastó para la obtención de una suscripción"));
            request.setAttribute("response2","TE HAS HECHO PREMIUM");
            profile(request,response);
        }
        else if(user.getBalance()>=price && user.isPremium()){
            request.setAttribute("response2","YA ERES PREMIUM");
            profile(request,response);
        }
        else{
            request.setAttribute("response3","NO TIENES SALDO SUFICIENTE");
            profile(request,response);
        }
    }

    protected void recharge(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinalUser user = (FinalUser) session.getAttribute("userSession");
        String error = " ";
        double price = 0;
        boolean accept = false;
        try{
            price = Double.parseDouble(request.getParameter("balance"));
            if(price < 0){
                error = "Coloque bien los datos";
            }
            accept = true;
        } catch (Exception e){
            error = "Coloque bien los datos";
        }
        if(accept){
            user.setBalance(user.getBalance()+price);
            finalUserDAO.update(user);
            session.removeAttribute("userSession");
            session.setAttribute("userSession",user);
            reportsDAO.insertTransaction(new Transactions(0.0,price,"Se recargo saldo a la cuenta"));
            request.setAttribute("response","SE HA RECARGADO EXITOSAMENTE " + price);
        }
        else{
            request.setAttribute("response1",error);
        }
        request.getRequestDispatcher(recharge).forward(request,response);
    }

    protected void createRevocationRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinalUser user = (FinalUser) session.getAttribute("userSession");
        String detail = request.getParameter("detail");
        if(!detail.isEmpty()){
            revocationRequestDAO.insert(new RevocationRequest(user.getId(),"PENDIENTE",detail));
            request.setAttribute("response1", "Solicitu enviada con éxito");
            request.getRequestDispatcher(revocation).forward(request,response);
        }
        else{
            request.getRequestDispatcher(revocation).forward(request,response);
        }
    }

    protected void report(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Transactions> list = reportsDAO.selectTransactions();
        request.setAttribute("reports",list);
        request.getRequestDispatcher(report).forward(request,response);
    }

}
