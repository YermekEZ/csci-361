package kz.edu.nu.cs.se.hw;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "PassengerTicketPurchaseTwoServlet")
public class PassengerTicketPurchaseTwoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String date = request.getParameter("date");
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");
        int vagon = Integer.parseInt(request.getParameter("vagon"));
        int seat = Integer.parseInt(request.getParameter( "seat"));
        String name = request.getParameter("name");
        String username = request.getParameter("username");

        System.out.println(username);
        System.out.println(date);
        System.out.println(departure);
        System.out.println(arrival);
        System.out.println(vagon);
        System.out.println(seat);
        System.out.println(name);

        try{
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement mySt  = InsertHelper.BuyTicket(con,username,seat,vagon,date,departure,arrival,name);
            mySt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
