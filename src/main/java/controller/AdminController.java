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
    ExistingBooksDAO existingBooksDAO = new ExistingBooksDAO();
    CategoryDAO categoryDAO = new CategoryDAO();
    BookDAO bookDAO = new BookDAO();
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
            request.getRequestDispatcher(carriers).forward(request,response);
        }
        if(menu.equals("receptionists")){
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
                    if(request.getParameter("libraryComboBox")==null) System.out.println("si");
                    else System.out.println(request.getParameter("libraryComboBox"));
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
            request.getRequestDispatcher(editBooks).forward(request,response);
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
}
