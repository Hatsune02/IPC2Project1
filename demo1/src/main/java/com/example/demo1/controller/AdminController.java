package com.example.demo1.controller;
import java.io.*;
import java.util.*;

import com.example.demo1.entities.module.*;
import com.example.demo1.entities.objects_library.*;
import com.example.demo1.entities.objects_process.*;
import com.example.demo1.entities.reports.BooksLoans;
import com.example.demo1.entities.reports.CarriersEndTransports;
import com.example.demo1.entities.reports.Premium;
import com.example.demo1.entities.reports.ReceptionistEndLoans;
import com.example.demo1.jdbc.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet(name = "AdminController", value = "/AdminController")
public class AdminController extends HttpServlet{
    //Admin admin = new Admin();
    AdminDAO adminDAO = new AdminDAO();
    ReceptionistDAO receptionistDAO = new ReceptionistDAO();
    CarrierDAO carrierDAO = new CarrierDAO();
    ExistingBooksDAO existingBooksDAO = new ExistingBooksDAO();
    CategoryDAO categoryDAO = new CategoryDAO();
    BookDAO bookDAO = new BookDAO();
    LibraryDAO libraryDAO = new LibraryDAO();
    RevocationRequestDAO revocationRequestDAO = new RevocationRequestDAO();
    ParameterDAO parameterDAO = new ParameterDAO();
    ReportsDAO reportsDAO = new ReportsDAO();
    String adminMenu = "admin_menu.jsp";
    String adminOption = "adminOption/";
    String admins = adminOption+"admins.jsp";
    String books = adminOption+"books.jsp";
    String carriers = adminOption+"carriers.jsp";
    String parameters = adminOption+"parameters.jsp";
    String receptionists = adminOption+"receptionists.jsp";
    String revocation = adminOption+"revocation.jsp";
    String addBooks = adminOption+"tables/addBook.jsp";
    String editBooks = adminOption+"tables/editBook.jsp";
    String addReceptionist = adminOption+"tables/addReceptionist.jsp";
    String editReceptionist = adminOption+"tables/editReceptionist.jsp";
    String addCarrier = adminOption+"tables/addCarrier.jsp";
    String editCarrier = adminOption+"tables/editCarrier.jsp";
    String editRevocation = adminOption+"tables/editRevocation.jsp";
    String editParameter = adminOption+"tables/editParameter.jsp";
    String report1 = adminOption+"reports/report1.jsp";
    String report2 = adminOption+"reports/report2.jsp";
    String report3 = adminOption+"reports/report3.jsp";
    String report4 = adminOption+"reports/report4.jsp";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String action=request.getParameter("action");
        if(menu.equals("admin")){
            request.getRequestDispatcher(adminMenu).forward(request,response);
        }
        if(menu.equals("carriers")){
            switch (action){
                case "list":
                    listCarriers(request,response);
                    break;
                case "add":
                    addCarriers(request,response);
                    break;
                case "edit":
                    editCarrier(request,response);
                    break;
                case "Guardar":
                    insertCarriers(request,response);
                    break;
                case "Actualizar":
                    updateCarrier(request,response);
                    break;
                case "Buscar":
                    searchCarrier(request,response);
                    break;
            }
        }
        if(menu.equals("receptionists")){
            switch (action){
                case "list":
                    listReceptionists(request,response);
                    break;
                case "add":
                    addReceptionist(request,response);
                    break;
                case "edit":
                    editUserReceptionist(request,response);
                    break;
                case "Guardar":
                    insertReceptionist(request,response);
                    break;
                case "Actualizar":
                    updateUserReceptionist(request,response);
                    break;
                case "Buscar":
                    searchReceptionist(request,response);
                    break;

            }
        }
        if(menu.equals("books")){
            switch (action){
                case"list":
                    listBooksExistence(request,response);
                    break;
                case"add":
                    addBook(request,response);
                    break;
                case"edit":
                    editBooks(request,response);
                    break;
                case"Guardar Libro":
                    insertBook(request,response);
                    break;
                case"Actualizar":
                    editBookExistence(request,response);
                    break;
                case"Buscar":
                    searchBooks(request,response);
                    break;
            }
        }
        if(menu.equals("revocation")){
            switch (action){
                case "listP":
                    listRevocationRequestPending(request,response);
                    break;
                case "listA":
                    listRevocationRequestAccept(request,response);
                    break;
                case "listD":
                    listRevocationRequestDecline(request,response);
                    break;
                case "edit":
                    editRevocationRequest(request,response);
                    break;
                case "Aceptar":
                    acceptRevocationRequest(request,response);
                    break;
                case "Rechazar":
                    declineRevocationRequest(request,response);
                    break;
                case "Buscar":
                    searchRequest(request,response);
                    break;
            }
        }
        if(menu.equals("parameters")){
            switch (action){
                case "list":
                    listParameters(request,response);
                    break;
                case "edit":
                    editParameter(request,response);
                    break;
                case "Actualizar":
                    updateParameter(request,response);
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

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    public void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryDAO.select();
        request.setAttribute("categories",categories);
        request.getRequestDispatcher(addBooks).forward(request,response);
    }
    public void listBooksExistence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<ExistingBooks> booksList = existingBooksDAO.list();
        List<ExistingBooks> libraries = existingBooksDAO.libraries();
        request.setAttribute("booksList",booksList);
        request.setAttribute("libraries", libraries);
        request.getRequestDispatcher(books).forward(request,response);
    }
    public void editBookExistence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int stock = 0;
        String error = " ";
        boolean isNumber = false;
        System.out.println("entro al metodo");
        try {
            stock = Integer.parseInt(request.getParameter("stock"));
            isNumber = true;
        }catch (Exception e){
            error = "escribe un número";
        }
        int libId = Integer.parseInt(request.getParameter("libId"));
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        System.out.println(libId+" "+isbn+" " + stock);
        if(isNumber){
            existingBooksDAO.update(new ExistingBooks(libId,isbn,stock));
            listBooksExistence(request,response);
        }
        else {
            request.setAttribute("response1",error);
            editBooks(request,response);
        }
    }
    public void editBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ExistingBooks book = new ExistingBooks();
        int libId = Integer.parseInt(request.getParameter("libId"));
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        List<ExistingBooks> existingBooksList = existingBooksDAO.list();
        for(ExistingBooks existingBooks : existingBooksList){
            if(existingBooks.getBookIsbn()==isbn && existingBooks.getLibraryID()==libId){
                book = existingBooks;
                break;
            }
        }
        request.setAttribute("book",book);
        request.getRequestDispatcher(editBooks).forward(request,response);
    }
    public void insertBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int isbn=0,category=0;
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        double price=0;
        String error = " ";
        boolean isNumber = false;
        boolean isPrice = false;
        boolean accept = true;
        try{
            isbn=Integer.parseInt(request.getParameter("isbn"));
            category = Integer.parseInt(request.getParameter("category"));
            isNumber = true;
        }catch (Exception e){
            error = "Llene los campos correctamente";
        }
        try{
            price = Double.parseDouble(request.getParameter("price"));
            isPrice = true;
        }catch (Exception e){
            error = "Llene los campos correctamente";
        }
        if(isNumber && isPrice){
            for(Book book: bookDAO.select()){
                if(book.getIsbn()==isbn){
                    accept = false;
                    error = "Error: No se puede repetir isbn";
                    break;
                }
                if(book.getName().equals(name)){
                    accept = false;
                    error = "Error: No se puede repetir nombre";
                    break;
                }
            }
            if(accept){
                bookDAO.insert(new Book(isbn,name,price,category,author));
                listBooksExistence(request,response);
            }
            else{
                request.setAttribute("response1", error);
                addBook(request,response);
            }
        }
        else{
            request.setAttribute("response1", error);
            addBook(request,response);
        }
    }
    public void searchBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("search");
        System.out.println(text);
        if(text.isEmpty() && !request.getParameter("libraryComboBox").equals("void")){
            int id = Integer.parseInt(request.getParameter("libraryComboBox"));
            List<ExistingBooks> booksList = existingBooksDAO.filterList(id);
            List<ExistingBooks> libraries = existingBooksDAO.libraries();
            request.setAttribute("booksList", booksList);
            request.setAttribute("libraries", libraries);
            request.getRequestDispatcher(books).forward(request,response);
        }
        else if(!text.isEmpty() && request.getParameter("libraryComboBox").equals("void")){
            List<ExistingBooks> booksList = existingBooksDAO.search(text);
            List<ExistingBooks> libraries = existingBooksDAO.libraries();
            request.setAttribute("booksList", booksList);
            request.setAttribute("libraries", libraries);
            request.getRequestDispatcher(books).forward(request,response);
        }
        else if(!text.isEmpty() && !request.getParameter("libraryComboBox").equals("void")){
            int id = Integer.parseInt(request.getParameter("libraryComboBox"));
            List<ExistingBooks> booksList = existingBooksDAO.searchAndFilter(id,text);
            List<ExistingBooks> libraries = existingBooksDAO.libraries();
            request.setAttribute("booksList", booksList);
            request.setAttribute("libraries", libraries);
            request.getRequestDispatcher(books).forward(request,response);
        }
        else{
            listBooksExistence(request,response);
        }
    }

    public void listReceptionists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Receptionist> receptionistsList = receptionistDAO.list();
        List<Receptionist> libraries = receptionistDAO.libraries();
        request.setAttribute("receptionistsList",receptionistsList);
        request.setAttribute("libraries", libraries);
        request.getRequestDispatcher(receptionists).forward(request,response);
    }
    public void insertReceptionist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        int library = 0;
        if(!request.getParameter("libId").equals("void")) library = Integer.parseInt(request.getParameter("libId"));
        String error = " ";
        boolean accept = true;
        List<Receptionist> receptionists1 = receptionistDAO.select();
        if(name!=null && username!=null && password!=null && email!=null){
            for (Receptionist receptionist : receptionists1) {
                if (receptionist.getUsername().equals(username)) {
                    accept = false;
                    error = "Error: Nombre de usuario ya utilizado";
                    break;
                }
                if (receptionist.getEmail().equals(email)) {
                    accept = false;
                    error = "Error: Cuenta de email ya utilizada";
                    break;
                }
            }
            if (accept) {
                receptionistDAO.insert(new Receptionist(name,username,password,email,library));
                listReceptionists(request,response);
            }
            else{
                request.setAttribute("response1",error);
                addReceptionist(request,response);
            }
        }
    }
    public void addReceptionist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Library> libraries = libraryDAO.select();
        request.setAttribute("libraries",libraries);
        request.getRequestDispatcher(addReceptionist).forward(request,response);
    }
    public void editUserReceptionist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Receptionist actualReceptionist = new Receptionist(id);
        actualReceptionist = receptionistDAO.selectOne(actualReceptionist);
        request.setAttribute("receptionist",actualReceptionist);
        request.getRequestDispatcher(editReceptionist).forward(request,response);
    }
    public void updateUserReceptionist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        int library = Integer.parseInt(request.getParameter("libId"));
        String error = " ";
        boolean accept = true;
        List<Receptionist> receptionists = receptionistDAO.select();
        receptionists.removeIf(receptionist -> receptionist.getId() == id);

        if(name!=null && username!=null && password!=null && email!=null){
            for (Receptionist receptionist : receptionists) {
                if (receptionist.getUsername().equals(username)) {
                    accept = false;
                    error = "Error: Nombre de usuario ya utilizado";
                    break;
                }
                if (receptionist.getEmail().equals(email)) {
                    accept = false;
                    error = "Error: Cuenta de email ya utilizada";
                    break;
                }
            }
            if (accept) {
                receptionistDAO.update(new Receptionist(id,name,username,password,email,library));
                listReceptionists(request,response);
            }
            else{
                request.setAttribute("response1",error);
                editUserReceptionist(request,response);
            }
        }
    }
    public void searchReceptionist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("search");
        System.out.println(text);
        if(text.isEmpty() && !request.getParameter("libraryComboBox").equals("void")){
            int id = Integer.parseInt(request.getParameter("libraryComboBox"));
            List<Receptionist> receptionistsList = receptionistDAO.filterList(id);
            List<Receptionist> libraries = receptionistDAO.libraries();
            request.setAttribute("receptionistsList", receptionistsList);
            request.setAttribute("libraries", libraries);
            request.getRequestDispatcher(receptionists).forward(request,response);
        }
        else if(!text.isEmpty() && request.getParameter("libraryComboBox").equals("void")){
            List<Receptionist> receptionistsList = receptionistDAO.search(text);
            List<Receptionist> libraries = receptionistDAO.libraries();
            request.setAttribute("receptionistsList", receptionistsList);
            request.setAttribute("libraries", libraries);
            request.getRequestDispatcher(receptionists).forward(request,response);
        }
        else if(!text.isEmpty() && !request.getParameter("libraryComboBox").equals("void")){
            int id = Integer.parseInt(request.getParameter("libraryComboBox"));
            List<Receptionist> receptionistsList = receptionistDAO.searchAndFilter(id,text);
            List<Receptionist> libraries = receptionistDAO.libraries();
            request.setAttribute("receptionistsList", receptionistsList);
            request.setAttribute("libraries", libraries);
            request.getRequestDispatcher(receptionists).forward(request,response);
        }
        else{
            listReceptionists(request,response);
        }
    }

    public void listCarriers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Carrier> carriersList = carrierDAO.select();
        request.setAttribute("carriersList",carriersList);
        request.getRequestDispatcher(carriers).forward(request,response);
    }
    public void insertCarriers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String error = " ";
        boolean accept = true;
        List<Carrier> carriers1 = carrierDAO.select();
        if(name!=null && username!=null && password!=null && email!=null){
            for (Carrier carrier : carriers1) {
                if (carrier.getUsername().equals(username)) {
                    accept = false;
                    error = "Error: Nombre de usuario ya utilizado";
                    break;
                }
                if (carrier.getEmail().equals(email)) {
                    accept = false;
                    error = "Error: Cuenta de email ya utilizada";
                    break;
                }
            }
            if (accept) {
                carrierDAO.insert(new Carrier(name,username,password,email));
                listCarriers(request,response);
            }
            else{
                request.setAttribute("response1",error);
                addCarriers(request,response);
            }
        }
    }
    public void addCarriers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(addCarrier).forward(request,response);
    }
    public void editCarrier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Carrier actualCarrier = new Carrier(id);
        actualCarrier = carrierDAO.selectOne(actualCarrier);
        request.setAttribute("carrier",actualCarrier);
        request.getRequestDispatcher(editCarrier).forward(request,response);
    }
    public void updateCarrier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String error = " ";
        boolean accept = true;
        List<Carrier> carriers1 = carrierDAO.select();
        carriers1.removeIf(carrier -> carrier.getId() == id);

        if(name!=null && username!=null && password!=null && email!=null){
            for (Carrier carrier : carriers1) {
                if (carrier.getUsername().equals(username)) {
                    accept = false;
                    error = "Error: Nombre de usuario ya utilizado";
                    break;
                }
                if (carrier.getEmail().equals(email)) {
                    accept = false;
                    error = "Error: Cuenta de email ya utilizada";
                    break;
                }
            }
            if (accept) {
                carrierDAO.update(new Carrier(id,name,username,password,email));
                listCarriers(request,response);
            }
            else{
                request.setAttribute("response1",error);
                editCarrier(request,response);
            }
        }
    }
    public void searchCarrier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("search");
        if(!text.isEmpty()){
            List<Carrier> carriersList = carrierDAO.search(text);
            request.setAttribute("carriersList", carriersList);
            request.getRequestDispatcher(carriers).forward(request,response);
        }
        else{
            listCarriers(request,response);
        }
    }

    int list = 0; //Saber en que lista estamos (pendiente, aceptados o rechazados) (1,2,3)
    public void listRevocationRequestPending(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        list=0;
        List<RevocationRequest> revocationList = revocationRequestDAO.listPending();
        request.setAttribute("revocationList",revocationList);
        request.getRequestDispatcher(revocation).forward(request,response);
    }
    public void listRevocationRequestAccept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        list=1;
        List<RevocationRequest> revocationList = revocationRequestDAO.listAccept();
        request.setAttribute("revocationList",revocationList);
        request.getRequestDispatcher(revocation).forward(request,response);
    }
    public void listRevocationRequestDecline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        list=2;
        List<RevocationRequest> revocationList = revocationRequestDAO.listDecline();
        request.setAttribute("revocationList",revocationList);
        request.getRequestDispatcher(revocation).forward(request,response);
    }
    public void editRevocationRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RevocationRequest revocationRequest = new RevocationRequest(id);
        revocationRequest = revocationRequestDAO.selectOne(revocationRequest);
        request.setAttribute("revocationRequest",revocationRequest);
        request.getRequestDispatcher(editRevocation).forward(request,response);
    }
    public void acceptRevocationRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        //cambiar estado de usuario final
        int userId = Integer.parseInt(request.getParameter("userId"));
        FinalUserDAO finalUserDAO = new FinalUserDAO();
        FinalUser finalUser = finalUserDAO.selectOne(new FinalUser(userId));
        finalUser.setBan(false);
        finalUserDAO.update(finalUser);

        RevocationRequest request1 = new RevocationRequest();
        request1.setId(id);
        request1.setState("ACEPTADO");
        revocationRequestDAO.updateState(request1);
        listRevocationRequestPending(request,response);
    }
    public void declineRevocationRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RevocationRequest request1 = new RevocationRequest();
        request1.setId(id);
        request1.setState("RECHAZADO");
        revocationRequestDAO.updateState(request1);
        listRevocationRequestPending(request,response);
    }
    public void searchRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("search");
        if(list == 0){ //pendientes
            if(!text.isEmpty()){
                List<RevocationRequest> revocationList = revocationRequestDAO.searchP(text);
                request.setAttribute("revocationList", revocationList);
                request.getRequestDispatcher(revocation).forward(request,response);
            }
            else{
                listRevocationRequestPending(request,response);
            }
        }
        else if(list == 1){ //aceptados
            if(!text.isEmpty()){
                List<RevocationRequest> revocationList = revocationRequestDAO.searchA(text);
                request.setAttribute("revocationList", revocationList);
                request.getRequestDispatcher(revocation).forward(request,response);
            }
            else{
                listRevocationRequestAccept(request,response);
            }
        }
        else if(list == 2){ //rechazados
            if(!text.isEmpty()){
                List<RevocationRequest> revocationList = revocationRequestDAO.searchD(text);
                request.setAttribute("revocationList", revocationList);
                request.getRequestDispatcher(revocation).forward(request,response);
            }
            else{
                listRevocationRequestDecline(request,response);
            }
        }
    }

    public void listParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Parameter> parameterList = parameterDAO.select();
        request.setAttribute("parameterList",parameterList);
        request.getRequestDispatcher(parameters).forward(request,response);
    }
    public void editParameter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Parameter actualParameter = new Parameter(id);
        actualParameter = parameterDAO.selectOne(actualParameter);
        request.setAttribute("parameter",actualParameter);
        request.getRequestDispatcher(editParameter).forward(request,response);
    }
    public void updateParameter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String parameterName = request.getParameter("name");
        String error = " ";
        boolean accept = false;

        if(id == 4 || id == 5 || id == 7){
            int parameterValue = 0;
            try{
                parameterValue = Integer.parseInt(request.getParameter("value"));
                accept = true;
            } catch (Exception e){
                error = "Error: Ingrese una número";
            }
            if(accept){
                parameterDAO.update(new Parameter(id,parameterName, parameterValue));
                listParameters(request,response);
            }
            else {
                request.setAttribute("response1",error);
                editParameter(request,response);
            }
        }
        else{
            double parameterValue = 0;
            try{
                parameterValue = Double.parseDouble(request.getParameter("value"));
                accept = true;
            } catch (Exception e){
                error = "Error: Ingrese una valor monetario";
            }
            if(accept){
                parameterDAO.update(new Parameter(id,parameterName, parameterValue));
                listParameters(request,response);
            }
            else {
                request.setAttribute("response1",error);
                editParameter(request,response);
            }
        }
    }

    public void report1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Premium> premiums = reportsDAO.selectPremium();
        request.setAttribute("reports",premiums);
        request.getRequestDispatcher(report1).forward(request,response);
    }
    public void report2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<ReceptionistEndLoans> recep = reportsDAO.selectReceptionistEndLoans();
        request.setAttribute("reports",recep);
        request.getRequestDispatcher(report2).forward(request,response);
    }
    public void report3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<CarriersEndTransports> carrier = reportsDAO.selectCarriersEndT();
        request.setAttribute("reports",carrier);
        request.getRequestDispatcher(report3).forward(request,response);
    }
    public void report4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<BooksLoans> books = reportsDAO.selectBooksLoans();
        request.setAttribute("reports",books);
        request.getRequestDispatcher(report4).forward(request,response);
    }
}
