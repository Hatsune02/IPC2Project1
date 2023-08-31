package controller;
import java.io.*;
import java.util.*;

import entities.module.*;
import entities.objects_library.*;
import jdbc.*;
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


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String action=request.getParameter("action");
        if(menu.equals("admin")){
            request.getRequestDispatcher(adminMenu).forward(request,response);
        }
        if(menu.equals("admins")){
            switch (action){
                case "list":
                    /*//List list = adminDAO.select();
                    //request.setAttribute("admins",list);*/
                    break;
                case "Agregar":
                    insertUserAdmin(request,response);
                    break;
                case "edit":
                    editUserAdmin(request,response);
                    break;
                case "Actualizar":
                    updateUserAdmin(request,response);
                    break;
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    adminDAO.delete(new Admin(id));
                    request.getRequestDispatcher("AdminController?menu=admins&action=list").forward(request,response);
                    break;
            }
            request.getRequestDispatcher(admins).forward(request,response);
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
            request.getRequestDispatcher(receptionists).forward(request,response);
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
            //request.getRequestDispatcher(books).forward(request,response);
        }
        if(menu.equals("revocation")){
            request.getRequestDispatcher(revocation).forward(request,response);
        }
        if(menu.equals("parameters")){
            request.getRequestDispatcher(parameters).forward(request,response);
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
    public void insertUserAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String error = " ";
        boolean accept = true;
        List<Admin> admins = adminDAO.select();
        if(name!=null && username!=null && password!=null && email!=null){
            for (Admin admin : admins) {
                if (admin.getUsername().equals(username)) {
                    accept = false;
                    error = "Error: Nombre de usuario ya utilizado";
                    break;
                }
                if (admin.getEmail().equals(email)) {
                    accept = false;
                    error = "Error: Cuenta de email ya utilizada";
                    break;
                }
            }
            if (accept) {
                adminDAO.insert(new Admin(name,username,password,email));
                //request.getRequestDispatcher("AdminController?menu=admins&action=list").forward(request,response);
            }
            else{
                request.setAttribute("response1",error);
            }
        }
        request.getRequestDispatcher("AdminController?menu=admins&action=list").forward(request,response);
    }
    public void editUserAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Admin actualAdmin = new Admin(id);
        actualAdmin = adminDAO.selectOne(actualAdmin);
        request.setAttribute("admin",actualAdmin);
        request.getRequestDispatcher("AdminController?menu=admins&action=list").forward(request,response);
    }
    public void updateUserAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String error = " ";
        boolean accept = true;
        List<Admin> admins = adminDAO.select();
        admins.removeIf(admin1 -> admin1.getId() == id);

        if(name!=null && username!=null && password!=null && email!=null){
            for (Admin admin : admins) {
                if (admin.getUsername().equals(username)) {
                    accept = false;
                    error = "Error: Nombre de usuario ya utilizado";
                    break;
                }
                if (admin.getEmail().equals(email)) {
                    accept = false;
                    error = "Error: Cuenta de email ya utilizada";
                    break;
                }
            }
            if (accept) {
                adminDAO.update(new Admin(id,name,username,password,email));
                //request.getRequestDispatcher("AdminController?menu=admins&action=list").forward(request,response);
            }
            else{
                request.setAttribute("response1",error);
            }
        }
        request.getRequestDispatcher("AdminController?menu=admins&action=list").forward(request,response);
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
        ExistingBooksDAO existingBooksDAO = new ExistingBooksDAO();
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
            bookDAO.insert(new Book(isbn,name,price,category,author));
            listBooksExistence(request,response);
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
            System.out.println(id);
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
        List<Carrier> receptionistsList = carrierDAO.select();
        request.setAttribute("carriersList",receptionistsList);
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
        System.out.println(text);
        if(!text.isEmpty()){
            List<Carrier> carriersList = carrierDAO.search(text);
            request.setAttribute("carriersList", carriersList);
            request.getRequestDispatcher(carriers).forward(request,response);
        }
        else{
            listCarriers(request,response);
        }
    }
}
