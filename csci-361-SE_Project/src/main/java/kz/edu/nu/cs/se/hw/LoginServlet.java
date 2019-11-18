package kz.edu.nu.cs.se.hw;

import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LoginServlet extends HttpServlet {
        private boolean counter;
        private String user_status;
        private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    //DoPost without using ajax
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ip = request.getRemoteAddr();
    	String username = request.getParameter("uname");
        System.out.println(username);
        String password = request.getParameter("pas");
        System.out.println(password);
        logger.info("Data is incorrect");
        try {
        	Connection con = DatabaseConnection.initializeDatabase();
            Statement mySt  = con.createStatement();
            String rs = "select * from User where Username =" +'"' + username + '"' + " and password=" + '"' + password + '"';
            System.out.println("Im in LoginServlet.doPost");
            ResultSet r = mySt.executeQuery(rs);

            if (r.next()==false) {
                counter = false;
                logger.info("Data is incorrect");
            } else {
                user_status = r.getString(6);
                counter = true;
                logger.info("Successful login");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("User in database: " + counter);
        System.out.println("Current user status: " + user_status);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ArrayList<Object> data = new ArrayList<>();
        data.add(counter);
        data.add(user_status);

        String json = new Gson().toJson(data);

        System.out.println("Sending counter to front_end...");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }


}
