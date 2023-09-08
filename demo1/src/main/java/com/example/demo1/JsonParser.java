package com.example.demo1;
import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import com.example.demo1.entities.module.*;
import com.example.demo1.entities.objects_library.*;
import com.example.demo1.entities.objects_process.*;
import com.example.demo1.jdbc.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class JsonParser {
    private static final AdminDAO adminDAO = new AdminDAO();
    private static final BookDAO bookDAO = new BookDAO();
    private static final CarrierDAO carrierDAO = new CarrierDAO();
    private static final CategoryDAO categoryDAO = new CategoryDAO();
    private static final DetailsTransportDAO detailsTransportDAO = new DetailsTransportDAO();
    private static final ExistingBooksDAO existingBooksDAO = new ExistingBooksDAO();
    private static final FinalUserDAO finalUserDAO = new FinalUserDAO();
    private static final IncidentDAO incidentDAO = new IncidentDAO();
    private static final LibraryDAO libraryDAO = new LibraryDAO();
    private static final LoanApplicationDAO loanApplicationDAO = new LoanApplicationDAO();
    private static final LoanDAO loanDAO = new LoanDAO();
    private static final ReceptionistDAO receptionistDAO = new ReceptionistDAO();
    private static final RevocationRequestDAO revocationRequestDAO = new RevocationRequestDAO();
    private static final TransportBetweenLibrariesDAO transportBetweenLibrariesDAO = new TransportBetweenLibrariesDAO();
    private static final TransportToUserDAO transportToUserDAO = new TransportToUserDAO();
    public static void parser(FileReader reader){
        JSONParser jsonParser = new JSONParser();
        try{
            Object obj = jsonParser.parse(reader);



            JSONObject jsonObject = (JSONObject) obj;

            JSONObject admin = (JSONObject) jsonObject.get("admin");
            JSONArray receptionists = (JSONArray) jsonObject.get("usuariosRecepcion");
            JSONArray carriers = (JSONArray) jsonObject.get("usuariosTransporte");
            JSONArray users = (JSONArray) jsonObject.get("usuarios");


            JSONArray categories = (JSONArray) jsonObject.get("categorias");
            JSONArray books = (JSONArray) jsonObject.get("libros");
            JSONArray libraries = (JSONArray) jsonObject.get("bibliotecas");
            JSONArray loanApplications = (JSONArray) jsonObject.get("solicitudesPrestamo");
            JSONArray loans = (JSONArray) jsonObject.get("prestamos");


            JSONArray transportsB = (JSONArray) jsonObject.get("transportesB");
            JSONArray transportsU = (JSONArray) jsonObject.get("transportesU");


            JSONArray incidents = (JSONArray) jsonObject.get("incidencias");
            JSONArray revocationRequests = (JSONArray) jsonObject.get("solicitudesRevocacion");



            admin(admin);

            for(Object carrier:carriers){
                carriers((JSONObject) carrier);
            }
            for(Object user:users){
                users((JSONObject) user);
            }
            for(Object category:categories){
                categories((JSONObject) category);
            }

            for(Object library:libraries){
                libraries((JSONObject) library);
            }
            for(Object book:books){
                books((JSONObject) book);
            }
            for(Object library:libraries){
                existingBooks((JSONObject) library);
            }

            for(Object recep:receptionists){
                receptionists((JSONObject) recep);
            }

            for(Object loanA : loanApplications){
                loanApplications((JSONObject) loanA);
            }

            for(Object loan : loans){
                loans((JSONObject) loan);
            }
            for(Object transport : transportsB){
                transportsB((JSONObject) transport);
            }
            for(Object transport : transportsU){
                transportsU((JSONObject) transport);
            }
            for(Object incident : incidents){
                incidents((JSONObject) incident);
            }
            for(Object revocation : revocationRequests){
                revocationRequests((JSONObject) revocation);
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    public static void parser(String json){
        JSONParser jsonParser = new JSONParser();
        try{

            JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

            JSONObject admin = (JSONObject) jsonObject.get("admin");
            JSONArray receptionists = (JSONArray) jsonObject.get("usuariosRecepcion");
            JSONArray carriers = (JSONArray) jsonObject.get("usuariosTransporte");
            JSONArray users = (JSONArray) jsonObject.get("usuarios");


            JSONArray categories = (JSONArray) jsonObject.get("categorias");
            JSONArray books = (JSONArray) jsonObject.get("libros");
            JSONArray libraries = (JSONArray) jsonObject.get("bibliotecas");
            JSONArray loanApplications = (JSONArray) jsonObject.get("solicitudesPrestamo");
            JSONArray loans = (JSONArray) jsonObject.get("prestamos");


            JSONArray transportsB = (JSONArray) jsonObject.get("transportesB");
            JSONArray transportsU = (JSONArray) jsonObject.get("transportesU");


            JSONArray incidents = (JSONArray) jsonObject.get("incidencias");
            JSONArray revocationRequests = (JSONArray) jsonObject.get("solicitudesRevocacion");



            admin(admin);

            for(Object carrier:carriers){
                carriers((JSONObject) carrier);
            }
            for(Object user:users){
                users((JSONObject) user);
            }
            for(Object category:categories){
                categories((JSONObject) category);
            }

            for(Object library:libraries){
                libraries((JSONObject) library);
            }
            for(Object book:books){
                books((JSONObject) book);
            }
            for(Object library:libraries){
                existingBooks((JSONObject) library);
            }

            for(Object recep:receptionists){
                receptionists((JSONObject) recep);
            }

            for(Object loanA : loanApplications){
                loanApplications((JSONObject) loanA);
            }

            for(Object loan : loans){
                loans((JSONObject) loan);
            }
            for(Object transport : transportsB){
                transportsB((JSONObject) transport);
            }
            for(Object transport : transportsU){
                transportsU((JSONObject) transport);
            }
            for(Object incident : incidents){
                incidents((JSONObject) incident);
            }
            for(Object revocation : revocationRequests){
                revocationRequests((JSONObject) revocation);
            }

        } catch (ParseException e){
            e.printStackTrace();
        }
    }
    private static void admin(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            String name = (String) object.get("nombre");
            String username = (String) object.get("username");
            String password = (String) object.get("password");
            String email = (String) object.get("email");

            adminDAO.insertId(new Admin(id1,name,username,password,email));
        }catch (Exception ignored){}

    }
    private static void carriers(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            String name = (String) object.get("nombre");
            String username = (String) object.get("username");
            String password = (String) object.get("password");
            String email = (String) object.get("email");

            carrierDAO.insertId(new Carrier(id1,name,username,password,email));
        }catch (Exception ignored){}

    }
    private static void users(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            String name = (String) object.get("nombre");
            String username = (String) object.get("username");
            String password = (String) object.get("password");
            String email = (String) object.get("email");
            double balance = 0;
            boolean decimal = true;
            if(decimal){
                try{
                    balance = (Double) object.get("saldoInicial");
                    finalUserDAO.insertId(new FinalUser(id1,name,username,password,email,balance));
                }catch (Exception e){
                    decimal = false;
                }
            }
            if(!decimal){
                try{
                    balance = (Long) object.get("saldoInicial");
                    finalUserDAO.insertId(new FinalUser(id1,name,username,password,email,balance));
                }catch (Exception e){
                }
            }

        }catch (Exception ignored){}

    }
    private static void categories(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            String name = (String) object.get("nombre");
            String description = (String) object.get("descripcion");

            categoryDAO.insertId(new Category(id1,name,description));
        }catch (Exception ignored){}

    }

    private static void libraries(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            String name = (String) object.get("nombre");
            String address = (String) object.get("direccion");

            libraryDAO.insertId(new Library(id1,name,address));
        }catch (Exception ignored){}

    }
    private static void books(JSONObject object){
        try{

            Long isbn = (Long) object.get("isbn");
            int isbn1 = Math.toIntExact(isbn);
            String name = (String) object.get("nombre");
            double price1 = 0;
            Long category = (Long) object.get("categoria");
            int category1 = Math.toIntExact(category);
            String author = (String) object.get("autor");

            boolean decimal = true;
            if(decimal){
                try{
                    price1 = (Double) object.get("costo");
                    bookDAO.insertId(new Book(isbn1,name,price1,category1,author));
                }catch (Exception e){
                    decimal = false;
                }
            }
            if(!decimal){
                try{
                    price1 = (Long) object.get("costo");
                    bookDAO.insertId(new Book(isbn1,name,price1,category1,author));
                }catch (Exception e){
                }
            }


            for(Library lib : libraryDAO.select()){
                existingBooksDAO.insert(new ExistingBooks(lib.getId(),isbn1,0));
            }
        }catch (Exception e){ e.printStackTrace(System.out);}

    }
    private static void existingBooks(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            JSONArray existingBooks = (JSONArray) object.get("libros");
            for(Object books : existingBooks){
                JSONObject b = (JSONObject) books;
                Long isbn = (Long) b.get("isbn");
                int isbn1 = Math.toIntExact(isbn);
                Long existences = (Long) b.get("existencias");
                int existences1 = Math.toIntExact(existences);
                existingBooksDAO.update(new ExistingBooks(id1,isbn1,existences1));
            }
        }catch (Exception ignored){}

    }

    private static void receptionists(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            String name = (String) object.get("nombre");
            String username = (String) object.get("username");
            String password = (String) object.get("password");
            String email = (String) object.get("email");
            Long library = (Long) object.get("biblioteca");
            int library1 = Math.toIntExact(library);
            receptionistDAO.insertId(new Receptionist(id1,name,username,password,email,library1));
        }catch (Exception ignored){}

    }
    private static void loanApplications(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            Long lib = (Long) object.get("biblioteca");
            int lib1 = Math.toIntExact(lib);
            Long recep = (Long) object.get("recepcionista");
            int recep1 = Math.toIntExact(recep);
            Long user = (Long) object.get("usuario");
            int user1 = Math.toIntExact(user);
            Long isbn = (Long) object.get("isbn");
            int isbn1 = Math.toIntExact(isbn);
            String date = (String) object.get("fecha");
            Date date1 = new Date(1);
            String state = (String) object.get("estado");
            String type = (String) object.get("tipoEntrega");
            boolean valid = false;
            Long carrier = (Long) object.get("transportista");
            try{
                LocalDate localDate = LocalDate.parse(date);
                date1 = Date.valueOf(localDate);
                valid = true;
            } catch (Exception ignored){}

            if(valid && carrier!=null){
                int carrier1 = Math.toIntExact(carrier);
                loanApplicationDAO.insertId(new LoanApplication(id1,lib1,recep1,user1,isbn1,date1,state,type,carrier1));
            }
            else if(valid && carrier==null){
                int carrier1 = carrierDAO.carrierLessTransportsU();
                loanApplicationDAO.insertId(new LoanApplication(id1,lib1,recep1,user1,isbn1,date1,state,type,carrier1));
            }
        }catch (Exception e){
            e.printStackTrace(System.out);
        }

    }

    private static void loans(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            Long lib = (Long) object.get("biblioteca");
            int lib1 = Math.toIntExact(lib);
            Long recep = (Long) object.get("recepcionista");
            int recep1 = Math.toIntExact(recep);
            Long user = (Long) object.get("usuario");
            int user1 = Math.toIntExact(user);
            Long isbn = (Long) object.get("isbn");
            int isbn1 = Math.toIntExact(isbn);
            String date = (String) object.get("fecha");
            Date date1 = new Date(1);
            String state = (String) object.get("estado");
            double penaltyFee1 = 0;
            boolean valid = false;
            boolean decimal = true;
            try{
                LocalDate localDate = LocalDate.parse(date);
                date1 = Date.valueOf(localDate);
                valid = true;
            } catch (Exception e){}

            if(valid ){
                if(decimal){
                    try{
                        penaltyFee1 = (Double) object.get("multa");
                        loanDAO.insertId(new Loan(id1,lib1,recep1,user1,isbn1,date1,state,penaltyFee1));
                    }catch (Exception e){
                        decimal = false;
                    }
                }
                if(!decimal){
                    try{
                        penaltyFee1 = (Long) object.get("multa");
                        loanDAO.insertId(new Loan(id1,lib1,recep1,user1,isbn1,date1,state,penaltyFee1));
                    }catch (Exception e){
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace(System.out);
        }


    }

    private static void transportsB(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            Long libO = (Long) object.get("bibliotecaOrigen");
            int libO1 = Math.toIntExact(libO);
            Long libD = (Long) object.get("bibliotecaDestino");
            int libD1 = Math.toIntExact(libD);
            Long recep = (Long) object.get("recepcionista");
            int recep1 = Math.toIntExact(recep);
            Long carrier = (Long) object.get("transportista");
            int carrier1 = Math.toIntExact(carrier);
            String date = (String) object.get("fecha");
            Date date1 = new Date(1);
            String state = (String) object.get("estado");
            boolean valid = false;
            System.out.println(" ");
            try{
                LocalDate localDate = LocalDate.parse(date);
                date1 = Date.valueOf(localDate);
                valid = true;
            } catch (Exception ignored){}

            if(valid ){
                transportBetweenLibrariesDAO.insertId(new TransportBetweenLibraries(id1,libO1,libD1,recep1,carrier1,date1,state));
                JSONArray detailsT = (JSONArray) object.get("detalle");
                for(Object detailT : detailsT){
                    JSONObject e = (JSONObject) detailT;
                    Long isbn = (Long) e.get("isbn");
                    int isbn1 = Math.toIntExact(isbn);
                    Long units = (Long) e.get("unidades");
                    int units1 = Math.toIntExact(units);
                    detailsTransportDAO.update(new DetailsTransport(id1,isbn1,units1));
                }
            }
        }catch (Exception e){
            e.printStackTrace(System.out);
        }

    }

    private static void transportsU(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            Long lib = (Long) object.get("biblioteca");
            int lib1 = Math.toIntExact(lib);
            Long user = (Long) object.get("usuario");
            int user1 = Math.toIntExact(user);
            Long carrier = (Long) object.get("transportista");
            int carrier1 = Math.toIntExact(carrier);
            String date = (String) object.get("fecha");
            Date date1 = new Date(1);
            String state = (String) object.get("estado");
            Long isbn = (Long) object.get("isbn");
            int isbn1 = Math.toIntExact(isbn);
            boolean valid = false;

            try{
                LocalDate localDate = LocalDate.parse(date);
                date1 = Date.valueOf(localDate);
                valid = true;
            } catch (Exception e){}

            if(valid ){
                transportToUserDAO.insertId(new TransportToUser(id1,lib1,user1,isbn1,date1,state,carrier1));
            }
        }catch (Exception e){
            e.printStackTrace(System.out);
        }

    }

    private static void incidents(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            Long loan = (Long) object.get("prestamo");
            int loan1 = Math.toIntExact(loan);
            String type = (String) object.get("tipo");
            double amountPaid1 = 0;

            boolean decimal = true;
            if(decimal){
                try{
                    amountPaid1 = (Double) object.get("cantidadPagada");
                    incidentDAO.insertId(new Incident(id1,loan1,type,amountPaid1));
                    if(type.equals("FINALIZADO CON INCIDENCIA")){
                        Loan loan2 = loanDAO.selectOneById(new Loan(loan1));
                        int userId = loan2.getFinalUserId();
                        FinalUser user = finalUserDAO.selectOne(new FinalUser(userId));
                        user.setBan(true);
                        finalUserDAO.update(user);
                    }
                }catch (Exception e){
                    decimal = false;
                }
            }
            if(!decimal){
                try{
                    amountPaid1 = (Long) object.get("cantidadPagada");
                    incidentDAO.insertId(new Incident(id1,loan1,type,amountPaid1));
                    if(type.equals("FINALIZADO CON INCIDENCIA")){
                        Loan loan2 = loanDAO.selectOneById(new Loan(loan1));
                        int userId = loan2.getFinalUserId();
                        FinalUser user = finalUserDAO.selectOne(new FinalUser(userId));
                        user.setBan(true);
                        finalUserDAO.update(user);
                    }
                }catch (Exception e){
                }
            }


        } catch (Exception ignored){}

    }

    private static void revocationRequests(JSONObject object){
        try{
            Long id = (Long) object.get("codigo");
            int id1 = Math.toIntExact(id);
            Long user = (Long) object.get("usuario");
            int user1 = Math.toIntExact(user);
            String state = (String) object.get("estado");
            String detail = (String) object.get("detalle");
            revocationRequestDAO.insertId(new RevocationRequest(id1,user1,state,detail));
        } catch (Exception e){
            e.printStackTrace(System.out);
        }
    }

}
