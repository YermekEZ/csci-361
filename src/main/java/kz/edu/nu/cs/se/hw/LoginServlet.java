package kz.edu.nu.cs.se.hw;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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

    //DoPost without using ajax
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("uname");
        System.out.println(username);
        String password = request.getParameter("pas");
        System.out.println(password);

        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement mySt  = SelectHelper.loginCheck(con,username,password);
            System.out.println("Im in LoginServlet.doPost");
            ResultSet r = mySt.executeQuery();
            if (r.next()==false) {
                counter = false;
            } else {
                user_status = r.getString(6);
                counter = true;}

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
