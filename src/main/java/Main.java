import entities.module.*;
import jdbc.AdminDAO;

import java.util.*;

public class Main {

    public static void main(String[] args){
        /*JDBC jdbc = new JDBC();
        jdbc.show();*/
        AdminDAO adminDao = new AdminDAO();
        //adminDao.insert(new Admin("Administrador3","admin3","1234","example3@examples"));
        //adminDao.update(new Admin(1,"Administrador3","admin3","1234","example3@examples"));
        //adminDao.delete(new Admin(1));
        List<Admin> admins = adminDao.select();
        for (Admin admin: admins) {
            System.out.println("admin = " + admin);
        }

    }
}
