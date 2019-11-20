package kz.edu.nu.cs.se.hw;

import com.google.gson.Gson;

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
import java.util.ArrayList;

@WebServlet(name = "PassengerTicketPurchaseServlet")
public class PassengerTicketPurchaseServlet extends HttpServlet {
    ArrayList<Integer> free = new ArrayList<>();
    ArrayList<Integer> bussy = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        free.clear();
        bussy.clear();

        System.out.println("IM USING DOpost IN TICKET --> PASSENGER SERVLET");
        String Date = request.getParameter("date");
        String Departure = request.getParameter("departure");
        String Arrival = request.getParameter("arrival");
        int vagon = Integer.parseInt(request.getParameter("vagon"));


        System.out.println(Date);
        System.out.println(Departure);
        System.out.println(Arrival);
        System.out.println(vagon);


        try{
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement mySt  = SelectHelper.BoughtTickets(con, Date,Departure,Arrival,vagon);
            ResultSet r = mySt.executeQuery();
            System.out.println("Passenger Search Query :"+mySt.toString());

            while(r.next()) {
                bussy.add(r.getInt(1));
            }
            System.out.println("Current Bussy seat list:" + bussy);

            for (int i = 1; i<21; i++) {
                if (!bussy.contains(i)) {
                    free.add(i);
                }
            }
            System.out.println("Current Free seat list:" + free);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = new Gson().toJson(free);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
