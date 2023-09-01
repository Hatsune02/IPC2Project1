package entities;

import entities.module.*;
import entities.objects_library.Book;
import entities.objects_library.Category;
import entities.objects_library.Library;
import entities.objects_library.ExistingBooks;
import entities.objects_process.*;

import java.util.ArrayList;

public class OBJECTS {
    //ARRAYSLIST DE USUARIOS
    public static ArrayList<Admin> admins = new ArrayList<>();
    public static ArrayList<Carrier> carriers = new ArrayList<>();
    public static ArrayList<FinalUser> finalUsers = new ArrayList<>();
    public static ArrayList<Receptionist> receptionists = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();

    //ARRAYLIST DE OBJETOS DE LIBRERIAS
    public static ArrayList<Book> books = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();
    public static ArrayList<Library> libraries = new ArrayList<>();
    public static ArrayList<ExistingBooks> relationsLB = new ArrayList<>();

    //OBJETOS PARA PRECESOS/TRANSACCIONES
    public static ArrayList<DetailsTransport> detailsT = new ArrayList<>();
    public static ArrayList<Incident> incidents = new ArrayList<>();
    public static ArrayList<Loan> loans = new ArrayList<>();
    public static ArrayList<LoanApplication> loanApplications = new ArrayList<>();
    public static ArrayList<RevocationRequest> revocationRequests = new ArrayList<>();
    public static ArrayList<TransportBetweenLibraries> transportsBL = new ArrayList<>();
    public static ArrayList<TransportToUser> transortsU = new ArrayList<>();
    
    
}
