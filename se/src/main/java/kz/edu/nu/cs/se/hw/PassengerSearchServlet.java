package kz.edu.nu.cs.se.hw;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
public class PassengerSearchServlet extends HttpServlet {

    private ArrayList<ArrayList<String>> Passenger_Search_table = new ArrayList<ArrayList<String>>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Passenger_Search_table.clear();
        System.out.println("IM USING DOpost IN PASSENGER SERVLET");
            String    Date = request.getParameter("passenger_back_Date");
            String    Departure = request.getParameter("passenger_back_Departure");
            String    Arrival = request.getParameter("passenger_back_Arrival");

            System.out.println(Date);
            System.out.println(Departure);
            System.out.println(Arrival);

        try{
            String dbDriver = "com.mysql.jdbc.Driver";
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "maslman111");
            Statement mySt  = con.createStatement();

            SelectHelper search_select = new SelectHelper();
            ResultSet r;

            String query =search_select.SearchRouteTicket(Date,Departure,Arrival);

            System.out.println("Passenger Search Query :"+query);
            r = mySt.executeQuery(query);

            while(r.next()) {

                ArrayList<String> single_row = new ArrayList<>();
                single_row.add(r.getString(1));
                single_row.add(r.getString(2));
                single_row.add(r.getString(3));
                single_row.add(r.getString(4));
                single_row.add(r.getString(5));
                Passenger_Search_table.add(single_row);
            }
            System.out.println("Hello "  +Passenger_Search_table);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = new Gson().toJson(Passenger_Search_table);

        System.out.println(Passenger_Search_table);
        System.out.println("IM SENDING PASSENGER TABLE TO FRONT");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
