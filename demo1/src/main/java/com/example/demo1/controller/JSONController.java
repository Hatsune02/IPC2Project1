package com.example.demo1.controller;

import java.io.*;

import com.example.demo1.JsonParser;
import com.example.demo1.entities.module.*;
import com.example.demo1.jdbc.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;

@WebServlet(name = "JSONController", value = "/JSONController")
@MultipartConfig
public class JSONController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String inputFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        StringBuilder json = new StringBuilder();
        InputStream inputStream = filePart.getInputStream();
        try{
            InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            //br.lines().forEach(line -> System.out.println(line));

            String read = br.readLine();
            while(read != null){
                json.append(read);
                read = br.readLine();
            }
            br.close();
        } catch (Exception e){}

        JsonParser.parser(json.toString());
        response.sendRedirect("LoginController");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
