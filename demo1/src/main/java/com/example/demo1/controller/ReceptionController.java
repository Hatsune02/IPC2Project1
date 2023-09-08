package com.example.demo1.controller;
import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.example.demo1.entities.module.*;
import com.example.demo1.entities.objects_library.*;
import com.example.demo1.entities.objects_process.*;
import com.example.demo1.entities.reports.Loans30Days;
import com.example.demo1.entities.reports.Premium;
import com.example.demo1.entities.reports.SuspendedUsers;
import com.example.demo1.entities.reports.UserIncidents;
import com.example.demo1.jdbc.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet(name = "ReceptionController", value = "/ReceptionController")
public class ReceptionController extends HttpServlet{
    HttpSession session;
    CarrierDAO carrierDAO = new CarrierDAO();
    FinalUserDAO finalUserDAO = new FinalUserDAO();
    ExistingBooksDAO existingBooksDAO = new ExistingBooksDAO();
    LoanDAO loanDAO = new LoanDAO();
    ParameterDAO parameterDAO = new ParameterDAO();
    BookDAO bookDAO = new BookDAO();
    CategoryDAO categoryDAO = new CategoryDAO();
    LibraryDAO libraryDAO = new LibraryDAO();
    LoanApplicationDAO loanApplicationDAO = new LoanApplicationDAO();
    IncidentDAO incidentDAO = new IncidentDAO();
    TransportBetweenLibrariesDAO transportBetweenLibrariesDAO = new TransportBetweenLibrariesDAO();
    DetailsTransportDAO detailsTransportDAO = new DetailsTransportDAO();
    ReportsDAO reportsDAO = new ReportsDAO();
    String receptionMenu = "receptionist_menu.jsp";
    String receptionOption = "receptionOption/";
    String applicationT = receptionOption+"applicationT.jsp";
    String bookLoan = receptionOption+"bookLoan.jsp";
    String endLoan = receptionOption+"endLoan.jsp";
    String incident = receptionOption+"incident.jsp";
    String transportB = receptionOption+"transportB.jsp";
    String users = receptionOption+"users.jsp";
    String extra = "extra/";
    String bookOtherLibs = receptionOption+extra + "bookOtherLibs.jsp";
    String selectBook = receptionOption+extra + "selectBook.jsp";
    String showNewLoan = receptionOption+extra + "showNewLoan.jsp";
    String editLoanApplication = receptionOption+extra + "editLoanApplication.jsp";
    String createIncident = receptionOption+extra+"createIncident.jsp";
    String selectBooksForTransport = receptionOption+extra+"selectBooksForTransport.jsp";
    String detailsTransportB = receptionOption+extra+"detailsTransportB.jsp";
    String detailsApplicationT = receptionOption+extra+"detailsApplicationT.jsp";
    String report1 = receptionOption+"reports/report1.jsp";
    String report2 = receptionOption+"reports/report2.jsp";
    String report3 = receptionOption+"reports/report3.jsp";
    String report4 = receptionOption+"reports/report4.jsp";
    String report5 = receptionOption+"reports/report5.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String menu = request.getParameter("menu");
        String action=request.getParameter("action");
        session = request.getSession();
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
            switch (action){
                case "list":
                    listBooks(request,response);
                    break;
                case "other":
                    listBooksOtherLib(request,response);
                    break;
                case "select":
                    selectBook(request,response);
                    break;
                case "Crear Préstamo":
                    insertLoan(request,response);
                    break;
                case "Buscar":
                    searchBook(request,response);
                    break;
                case "Buscar Libro":
                    searchBookOtherLib(request,response);
                    break;

            }
        }
        else if(menu.equals("endLoan")){
            switch (action) {
                case "list":
                    listLoans(request, response);
                    break;
                case "select":
                    selectLoan(request, response);
                    break;
                case "Finalizar":
                    endLoanApplication(request, response);
                    break;
                case "Buscar":
                    searchLoanA(request, response);
                    break;
            }

        }
        else if(menu.equals("incident")){
            switch (action) {
                case "list":
                    listLoanForIncident(request, response);
                    break;
                case "select":
                    selectLoanForIncident(request, response);
                    break;
                case "Crear Incidencia":
                    createIncident(request, response);
                    break;
                case "Finalizar Sin Incidencia":
                    noIncident(request, response);
                    break;
                case "Buscar":
                    searchLoanForIncident(request, response);
                    break;
            }
        }
        else if(menu.equals("transportB")){
            switch (action) {
                case "list":
                    listLibraries(request, response);
                    break;
                case "select":
                    listBooksForTransport(request, response);
                    break;
                case "Ver Libros":
                    listDetails(request, response);
                    break;
                case "Finalizar":
                    listLibraries(request, response);
                    break;
                case "cancel":
                    cancelTransportB(request, response);
                    break;
                case "selectBook":
                    selectBooksForTransport(request, response);
                    break;
                case "Agregar":
                    addBooksForTransport(request, response);
                    break;
                case "back":
                    onlyListBooksForTransport(request,response);
                    break;
                case "Buscar":
                    searchLibraryTransport(request, response);
                    break;
                case "Buscar Libro":
                    searchBookForTransport(request,response);
                    break;
            }
        }
        else if(menu.equals("applicationT")){
            switch (action) {
                case "list":
                    listApplicationT(request, response);
                    break;
                case "accept":
                    acceptApplicationT(request, response);
                    break;
                case "select":
                    selectApplicationT(request, response);
                    break;
                case "decline":
                    declineApplicationT(request, response);
                    break;
                case "Buscar":
                    searchApplicationT(request, response);
                    break;
            }
        }
        if(menu.equals("report1")){
            report1(request,response);
        }
        if(menu.equals("report2")){
            report2(request,response);
        }
        if(menu.equals("report3")){
            report3(request,response);
        }
        if(menu.equals("report4")){
            report4(request,response);
        }
        if(menu.equals("report5")){
            report5(request,response);
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

    public void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        List<BooksPerLibrary> bookList = existingBooksDAO.listBooksPerLibrary(receptionist.getLibraryID());
        List<Category> categoryList = categoryDAO.select();
        request.setAttribute("bookList",bookList);
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher(bookLoan).forward(request,response);
    }
    public void listBooksOtherLib(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        List<BooksPerLibrary> bookList = existingBooksDAO.listBooksPerLibraryOtherLib(receptionist.getLibraryID());
        request.setAttribute("bookListO",bookList);
        request.getRequestDispatcher(bookOtherLibs).forward(request,response);
    }
    public void selectBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int libId = Integer.parseInt(request.getParameter("libId"));
        int isbn = Integer.parseInt(request.getParameter("isbn"));

        request.setAttribute("libId",libId);
        request.setAttribute("isbn",isbn);
        ExistingBooks books = existingBooksDAO.selectOne(new ExistingBooks(libId,isbn));
        if(books.getExistence()>0){
            request.getRequestDispatcher(selectBook).forward(request,response);
        }
        else{
            listBooks(request,response);
        }
    }
    public void insertLoan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int libId = Integer.parseInt(request.getParameter("libId"));
        int recepId = Integer.parseInt(request.getParameter("recepId"));
        int userId = 0;
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        int days = 0;
        String error = " ";
        boolean accept = false;
        boolean isNumber = false;
        try {
            userId = Integer.parseInt(request.getParameter("userId"));
            days = Integer.parseInt(request.getParameter("days"));
            isNumber = true;
        } catch (Exception e){
            error = "Agregue bien los datos";
        }
        if(isNumber){
            for(FinalUser finalUser: finalUserDAO.select()){
                if(finalUser.getId()==userId){
                    accept = true;
                    break;
                }
                else error="Error: Usuario no válido";
            }
            if(accept){
                Parameter parameterDays = parameterDAO.selectOne(new Parameter(4));
                Parameter parameterLoans = parameterDAO.selectOne(new Parameter(5));

                if(parameterDays.getValue() >= days && days >= 0){
                    if(parameterLoans.getValue() >= loanDAO.countLoansPerUser(userId)){
                        LocalDate localDate = LocalDate.now();
                        Date date = Date.valueOf(localDate.plusDays(days));
                        Loan loan = new Loan(libId,recepId,userId,isbn,date,"ACTIVO",0);
                        loanDAO.insert(loan);
                        loan = loanDAO.selectRecent(loan);
                        request.setAttribute("loan",loan);
                        request.getRequestDispatcher(showNewLoan).forward(request,response);
                    }
                    else{
                        error = "Error: El Usuario ya tiene el limite de préstamos disponibles ("+loanDAO.countLoansPerUser(userId)+")";
                        request.setAttribute("response1",error);
                        selectBook(request,response);
                    }
                }
                else{
                    error = "Error: Días ingresados incorrectos, Consulte los días máximos de préstamo";
                    request.setAttribute("response1",error);
                    selectBook(request,response);
                }
            }
            else {
                request.setAttribute("response1",error);
                selectBook(request,response);
            }
        }
        else{
            request.setAttribute("response1",error);
            selectBook(request,response);
        }
    }
    public void searchBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<BooksPerLibrary> bookList = existingBooksDAO.searchBooksPerLibrary(receptionist.getLibraryID(),text);
            request.setAttribute("bookList", bookList);
            request.getRequestDispatcher(bookLoan).forward(request,response);
        }
        else{
            listBooks(request,response);
        }
    }
    public void searchBookOtherLib(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<BooksPerLibrary> bookListO = existingBooksDAO.searchBooksForTransport(receptionist.getLibraryID(),text);
            request.setAttribute("bookListO", bookListO);
            request.getRequestDispatcher(bookOtherLibs).forward(request,response);
        }
        else{
            listBooksOtherLib(request,response);
        }
    }

    public void listLoans(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        List<LoanApplication> loanList = loanApplicationDAO.listPerRecep(receptionist.getId());
        request.setAttribute("loanList",loanList);
        request.getRequestDispatcher(endLoan).forward(request,response);
    }
    public void selectLoan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        LoanApplication loanA = loanApplicationDAO.selectOne(new LoanApplication(id));
        request.setAttribute("loanA",loanA);
        request.getRequestDispatcher(editLoanApplication).forward(request,response);
    }
    public void endLoanApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        LoanApplication loanA = loanApplicationDAO.selectOne(new LoanApplication(id));
        loanA.setState("FINALIZADO");
        loanApplicationDAO.update(loanA);
        Loan loan = new Loan(loanA.getLibraryId(),loanA.getReceptionistId(),loanA.getFinalUserId(),loanA.getBookIsbn(),loanA.getLoanApplicationDate(),"ACTIVO",0);
        loanDAO.insert(loan);
        listLoans(request,response);
    }
    public void searchLoanA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<LoanApplication> loanList = loanApplicationDAO.search(receptionist.getId(),text);
            request.setAttribute("loanList", loanList);
            request.getRequestDispatcher(endLoan).forward(request,response);
        }
        else{
            listLoans(request,response);
        }
    }

    public void listLoanForIncident(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        List<Loan> loanList = loanDAO.listPerRecep(receptionist.getId());
        request.setAttribute("loanList",loanList);
        request.getRequestDispatcher(incident).forward(request,response);
    }
    public void selectLoanForIncident(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int loanId = Integer.parseInt(request.getParameter("loanId"));
        Loan loan = loanDAO.selectOneById(new Loan(loanId));
        Book book = bookDAO.selectOne(new Book(loan.getBookIsbn()));
        request.setAttribute("loanId",loanId);
        request.setAttribute("book",book);
        request.getRequestDispatcher(createIncident).forward(request,response);
    }
    public void createIncident(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int loanId = Integer.parseInt(request.getParameter("loanId"));
        Loan loan = loanDAO.selectOneById(new Loan(loanId));
        FinalUser finalUser = finalUserDAO.selectOne(new FinalUser(loan.getFinalUserId()));
        finalUser.setBan(true);
        loan.setState("FINALIZADO CON INCIDENCIA");
        finalUserDAO.update(finalUser);
        loanDAO.update(loan);
        String type = request.getParameter("type");
        double penalty = Double.parseDouble(request.getParameter("penalty"));
        incidentDAO.insert(new Incident(loanId,type,penalty));
        listLoanForIncident(request,response);
    }
    public void noIncident(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int loanId = Integer.parseInt(request.getParameter("loanId"));
        Loan loan = loanDAO.selectOneById(new Loan(loanId));
        loan.setState("FINALIZADO");
        loanDAO.update(loan);
        listLoanForIncident(request,response);
    }

    public void searchLoanForIncident(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<Loan> loanList = loanDAO.searchForIncident(receptionist.getId(),text);
            request.setAttribute("loanList",loanList);
            request.getRequestDispatcher(incident).forward(request,response);
        }
        else{
            listLoanForIncident(request,response);
        }
    }

    public void listLibraries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        List<Library> libraries = libraryDAO.listOtherLib(receptionist.getLibraryID());
        request.setAttribute("libraries",libraries);
        request.getRequestDispatcher(transportB).forward(request,response);
    }
    public void listBooksForTransport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        libId = Integer.parseInt(request.getParameter("libId"));
        recepId = receptionist.getId();
        libDId = receptionist.getLibraryID();
        List<BooksPerLibrary> books = existingBooksDAO.listBooksPerLibrary(libId);
        carrierId = carrierDAO.carrierLessTransportsB();
        LocalDate localDate = LocalDate.now();
        date = Date.valueOf(localDate);
        transportBetweenLibrariesDAO.insert(new TransportBetweenLibraries(libDId,libId,recepId,carrierId,date,"EN ESPERA"));
        TransportBetweenLibraries transportB = transportBetweenLibrariesDAO.selectRecent();
        transportId = transportB.getId();
        request.setAttribute("transportId", transportB.getId());
        request.setAttribute("books",books);
        request.setAttribute("libId",libId);
        request.setAttribute("libId",libId);
        request.getRequestDispatcher(selectBooksForTransport).forward(request,response);
    }
    Date date = new Date(1);
    int recepId = 0, carrierId = 0, libDId=0;
    int existence = 0, transportId=0, libId=0;
    public void selectBooksForTransport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        existence = Integer.parseInt(request.getParameter("existence"));
        List<BooksPerLibrary> books = existingBooksDAO.listBooksPerLibrary(libId);

        request.setAttribute("transportId",transportId);
        request.setAttribute("isbn",isbn);
        request.setAttribute("books",books);
        request.getRequestDispatcher(selectBooksForTransport).forward(request,response);
    }
    public void onlyListBooksForTransport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<BooksPerLibrary> books = existingBooksDAO.listBooksPerLibrary(libId);

        request.setAttribute("transportId",transportId);
        request.setAttribute("books",books);
        request.getRequestDispatcher(selectBooksForTransport).forward(request,response);
    }
    public void addBooksForTransport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        int stock = 0;
        String error = " ";
        boolean valid = false;
        try{
            isbn = Integer.parseInt(request.getParameter("isbn"));
            stock = Integer.parseInt(request.getParameter("stock"));
            valid = true;
        }catch (Exception e){
            error = "Error: Ingresa números";
        }
        if(valid){
            if(existence>stock){
                boolean confirm = false;
                for(DetailsTransport details : detailsTransportDAO.select(transportId)){
                    if(details.getBookIsbn()==isbn){
                        detailsTransportDAO.update(new DetailsTransport(transportId,isbn,stock));
                        confirm=true;
                    }
                }
                if(!confirm) detailsTransportDAO.insert(new DetailsTransport(transportId,isbn,stock));

            }
            else {
                error = "Error: Solo puedes solicitar libros dentro del rango";
            }
        }
        request.setAttribute("response1",error);
        onlyListBooksForTransport(request,response);
    }
    public void listDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<DetailsTransport> details = detailsTransportDAO.select(transportId);
        request.setAttribute("details",details);
        request.getRequestDispatcher(detailsTransportB).forward(request,response);
    }
    public void cancelTransportB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        transportBetweenLibrariesDAO.update(new TransportBetweenLibraries(transportId,libDId,libId,recepId,carrierId,date,"CANCELADO"));
        listLibraries(request,response);
    }
    public void searchLibraryTransport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<Library> libraries = libraryDAO.search(receptionist.getLibraryID(),text);
            request.setAttribute("libraries",libraries);
            request.getRequestDispatcher(transportB).forward(request,response);
        }
        else{
            listLibraries(request,response);
        }
    }
    public void searchBookForTransport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<BooksPerLibrary> books = existingBooksDAO.searchBooksForTransport(libId,text);
            request.setAttribute("books", books);
            request.getRequestDispatcher(selectBooksForTransport).forward(request,response);
        }
        else{
            onlyListBooksForTransport(request,response);
        }
    }


    public void listApplicationT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        List<TransportBetweenLibraries> transports = transportBetweenLibrariesDAO.selectWait(receptionist.getLibraryID());
        request.setAttribute("transports",transports);
        request.getRequestDispatcher(applicationT).forward(request,response);
    }
    public void selectApplicationT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int transportId = Integer.parseInt(request.getParameter("transportId"));
        List<DetailsTransport> details = detailsTransportDAO.select(transportId);
        request.setAttribute("transportId",transportId);
        request.setAttribute("details",details);
        request.getRequestDispatcher(detailsApplicationT).forward(request,response);

    }
    public void acceptApplicationT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int transportId = Integer.parseInt(request.getParameter("transportId"));
        TransportBetweenLibraries transport = new TransportBetweenLibraries();
        transport.setId(transportId);
        transport.setState("PENDIENTE");
        transportBetweenLibrariesDAO.updateState(transport);
        listApplicationT(request,response);
    }
    public void declineApplicationT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int transportId = Integer.parseInt(request.getParameter("transportId"));
        TransportBetweenLibraries transport = new TransportBetweenLibraries();
        transport.setId(transportId);
        transport.setState("RECHAZADO");
        transportBetweenLibrariesDAO.updateState(transport);
        listApplicationT(request,response);
    }
    public void searchApplicationT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Receptionist receptionist = (Receptionist) session.getAttribute("recepSession");
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<TransportBetweenLibraries> transports = transportBetweenLibrariesDAO.searchWait(receptionist.getLibraryID(),text);
            request.setAttribute("transports",transports);
            request.getRequestDispatcher(applicationT).forward(request,response);
        }
        else{
            listApplicationT(request,response);
        }
    }

    public void report1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<UserIncidents> userIncidents = reportsDAO.selectUserIncidents();
        request.setAttribute("reports",userIncidents);
        request.getRequestDispatcher(report1).forward(request,response);
    }
    public void report2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<SuspendedUsers> suspendedUsers = reportsDAO.selectSuspendedUsers();
        request.setAttribute("reports",suspendedUsers);
        request.getRequestDispatcher(report2).forward(request,response);
    }
    public void report3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Loans30Days> loans = reportsDAO.selectLoansA();
        request.setAttribute("reports",loans);
        request.getRequestDispatcher(report3).forward(request,response);
    }
    public void report4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Loans30Days> loans = reportsDAO.selectLoansF();
        request.setAttribute("reports",loans);
        request.getRequestDispatcher(report4).forward(request,response);
    }
    public void report5(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Loans30Days> loans = reportsDAO.selectLoansFI();
        request.setAttribute("reports",loans);
        request.getRequestDispatcher(report5).forward(request,response);
    }


}
