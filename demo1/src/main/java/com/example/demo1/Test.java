package com.example.demo1;

import com.example.demo1.entities.objects_library.*;
import com.example.demo1.entities.objects_process.*;
import com.example.demo1.jdbc.*;
import com.example.demo1.entities.module.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.Kernel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
       /* AdminDAO adminDAO = new AdminDAO();
        List<Admin> admins = adminDAO.select();
        for(Admin admin: admins){
            System.out.println("admin = "+admin);
            System.out.println("numero " + adminDAO.validate(admin));
        }

        Admin admin = new Admin();
        Scanner sc = new Scanner(System.in);
        System.out.println("username");
        admin.setUsername(sc.nextLine());
        System.out.println("password");
        admin.setPassword(sc.nextLine());
        System.out.println("validado " + adminDAO.validate(admin));*/

/*        ReceptionistDAO receptionistDAO = new ReceptionistDAO();
        List<Receptionist> receptionists = receptionistDAO.select();
        for(Receptionist receptionist: receptionists){
            System.out.println("recepcionist = "+receptionist);
            System.out.println("numero "+receptionistDAO.validate(receptionist));
        }*/
        /*ExistingBooksDAO existingBooksDAO = new ExistingBooksDAO();
        List<ExistingBooks> books = existingBooksDAO.list();
        for(ExistingBooks book: books){
            System.out.println("existing book = "+book.toStringA());
        }*/

        /*ReceptionistDAO receptionistDAO = new ReceptionistDAO();
        List<Receptionist> receptionists = receptionistDAO.list();
        for(Receptionist receptionist: receptionists){
            System.out.println("recepcionist = "+receptionist);
            System.out.println("numero "+receptionistDAO.validate(receptionist));
        }

        RevocationRequestDAO revocationRequestDAO = new RevocationRequestDAO();
        List<RevocationRequest> revocationRequests = revocationRequestDAO.listPending();
        for(RevocationRequest revocationRequest: revocationRequests) System.out.println(revocationRequest);

        revocationRequests = revocationRequestDAO.listAccept();
        for(RevocationRequest revocationRequest: revocationRequests) System.out.println(revocationRequest);

        revocationRequests = revocationRequestDAO.listDecline();
        for(RevocationRequest revocationRequest: revocationRequests) System.out.println(revocationRequest);

        */

        /*LoanDAO loanDAO = new LoanDAO();

        java.util.Date date = new Date();
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(5);

//        java.sql.Date date1 = new java.sql.Date(date.getTime());
        java.sql.Date date1 = java.sql.Date.valueOf(localDate);

        loanDAO.insert(new Loan(3,1,1,2,date1,"ACTIVO",0));


        List<Loan> loans = loanDAO.select();
        for (Loan loan : loans){
            System.out.println(loan);
        }
        */

        /*LoanApplicationDAO loanApplicationDAO = new LoanApplicationDAO();
        for (LoanApplication loanApplication : loanApplicationDAO.list()){
            System.out.println(loanApplication + " " + loanApplication.getLibrary());
        }

        LoanDAO loanDAO = new LoanDAO();
        System.out.println(loanDAO.countLoansPerUser(1));*/

/*        TransportBetweenLibrariesDAO tDAO = new TransportBetweenLibrariesDAO();
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate);
        for(TransportBetweenLibraries t : tDAO.select()){
            System.out.println(t);
        }

        System.out.println("");
        DetailsTransportDAO dDao = new DetailsTransportDAO();
        for(DetailsTransport d : dDao.select()){
            System.out.println(d);
        }*/
        ParameterDAO parameterDAO = new ParameterDAO();
        Parameter parameterPenaltyFee = parameterDAO.selectOne(new Parameter(6));
        System.out.println(parameterPenaltyFee.getValue());

    }
}
