package kz.edu.nu.cs.se.hw;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.servlet.http.Cookie;

import com.google.gson.Gson;

@Path("/search")
public class SearchService {

    private ArrayList<ArrayList<String>> Search_table = new ArrayList<ArrayList<String>>();


    public  SearchService() {
    }

    @POST
    public Response postSearch(@FormParam("back_Date") String Date, @FormParam("back_Departure") String Departure, @FormParam("back_Arrival") String Arrival) {
        Search_table.clear();
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            Statement mySt  = con.createStatement();

            SelectHelper search_select = new SelectHelper();
            ResultSet r;
            String query =search_select.Search(Date,Departure,Arrival);
//            boolean added = false;
//
//            if(!Date.isEmpty()){
//                System.out.println("I AM  not EMPTY");
//                if(!added) {
//                    added = true;
//                    query += " Date =" + '"'+Date+'"';}
//            }
//            if(!Departure.isEmpty()) {
//                if(!added) {
//                    added=true;
//                    query += " Departure = " + '"' + Departure +'"';
//                }else {
//                    query += " and Departure = " + '"' + Departure +'"';
//                }
//            }
//            if(!Arrival.isEmpty()) {
//                if(!added) {
//                    added = true;
//                    query += " Arrival = " + '"' + Arrival +'"';
//                }
//                else {
//                    query += " and Arrival = " + '"' + Arrival +'"';
//                }
//            }
//            if(!added) {
//                query = query.substring(0, 19);
//            }
            System.out.println("QUERY!!!!:"+query);
            r = mySt.executeQuery(query);

            // теперь после того как получил всю отсортированную дату пихаю ее в ArrayList<String>
            // ArrayList[][0] = Date ; ArrayList[][1] = Departure ; ArrayList[][2] = Arrival


            while(r.next()) {

                ArrayList<String> single_row = new ArrayList<>();
                single_row.add(r.getString(3));
                single_row.add(r.getString(2));
                single_row.add(r.getString(4));
                single_row.add(r.getString(5));
                Search_table.add(single_row);
            }
            System.out.println(Search_table);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return Response.ok().build();
    }

//    @POST
//    public Response postPassengerSearch (@FormParam("passenger_back_Date") String Date, @FormParam("passenger_back_Departure") String Departure, @FormParam("passenger_back_Arrival") String Arrival) {
//        Passenger_Search_table.clear();
//        try{
//            Connection con = DatabaseConnection.initializeDatabase();
//
//            Statement mySt  = con.createStatement();
//            SelectHelper search_select = new SelectHelper();
//            ResultSet r;
//            String query =search_select.SearchRouteTicket(Date,Departure,Arrival);
//
//            System.out.println("Passenger Search Query :"+query);
//            r = mySt.executeQuery(query);
//
//            while(r.next()) {
//
//                ArrayList<String> single_row = new ArrayList<>();
//                single_row.add(r.getString(1));
//                single_row.add(r.getString(2));
//                single_row.add(r.getString(3));
//                single_row.add(r.getString(4));
//                single_row.add(r.getString(5));
//                Passenger_Search_table.add(single_row);
//            }
//            System.out.println(Passenger_Search_table);
//
//
//        } catch (ClassNotFoundException e) {
//        e.printStackTrace();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//
//        return Response.ok().build();
//    }

//    @GET
//    @Path("passengerTickets1")
//    public Response givePassengerSearchTable() {
//        Gson gson = new Gson();
//        return Response.ok(gson.toJson(Passenger_Search_table)).build();
//    }

    @GET
    public Response getList() {

        Gson gson = new Gson();

        return Response.ok(gson.toJson(Search_table)).build();
    }




}
