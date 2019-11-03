//package kz.edu.nu.cs.se.hw;
//
//import java.io.IOException;
//
//import java.io.PrintWriter;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.core.Response;
//
//import com.google.gson.Gson;
//
//
//
//
//
//
//
///**
// * Servlet implementation class DbServlet
// */
//public class DbServletTicket extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public DbServletTicket() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////		String userName = request.getParameter("userName").trim();
////		if(userName == null || "".equals(userName)){
////			userName = "Guest";
////		}
////
////		String greetings = "Hello " + userName;
////
////		response.setContentType("text/plain");
////		response.getWriter().write(greetings);
//		System.out.println("I'm in get method");
//
//		Gson gson = new Gson();
//		String json;
//		List<Ticket> tickets = new ArrayList<>();
//
//		response.setContentType("application/json;charset=UTF-8");
////		out.println(" <head>" +"  <meta charset=\"UTF-8\">" +"</head> ");
////		out1.println("<html><body><table><tr><th>Date</th><th>Departure</th><th>Arrival</th>");
//			try {
//
//				String dbDriver = "com.mysql.jdbc.Driver";
//				Class.forName(dbDriver);
//	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "maslman111");
//	            Statement mySt  = con.createStatement();
//	            ResultSet r;
//	            String query ="select  Ticket.RouteID, User.Fname, User.Lname, Ticket.Seat_Number, Ticket.Status, Ticket.Passenger, Ticket.TrainID " +"from Ticket right join User on User.UserID = Ticket.Passenger " + 	"where Ticket.RouteID = (select Route.RouteID " + "From Route Where  ";
//	            String date, departure, arrival;
//	            date= request.getParameter("Date");
//	            departure = request.getParameter("Departure");
//	            arrival = request.getParameter("Arrival");
//	            System.out.println(date);
//	            System.out.println(departure);
//	            System.out.println(arrival);
//	            boolean added = false;
//	            query += "Route.Departure = "+ '"'+departure+ '"' +" and "+ "Route.Arrival = " + '"'+arrival+ '"' +" and " + "Route.Date = " + '"'+date+ '"'+")";
//
//	            System.out.println("QUERY!!!!:"+query);
//	            r = mySt.executeQuery(query);
//
//	            while(r.next()) {
//	            	int seat = Integer.parseInt(r.getString("Seat_Number"));
//	            	String route = r.getString("RouteId");
//	            	String fname = r.getString("Fname");
//	            	String lname = r.getString("Lname");
//	            	int passenger = Integer.parseInt(r.getString("Passenger"));
//	            	int trainId = Integer.parseInt(r.getString("TrainID"));
//
//	            	boolean status;
//	            	System.out.println(r.getString("Status"));
//	            	if(Integer.parseInt(r.getString("Status"))== 0) {
//	            		status = false;
//	            	}
//	            	else{
//	            		status = true;
//	            	}
//
//
//	            	Ticket x = new Ticket(seat,status, route, fname, lname, passenger, trainId );
//	            	tickets.add(x);
//
//	            }
//
//	            if (!r.next()) {
////            		out.println("<a href = " + "http://localhost:8080/dynamictodolist/index.html" + ">GO BACK</a>");
//            	}
//	            System.out.println(tickets);
//	            json = gson.toJson(tickets);
//	            System.out.println(json);
//	            System.out.println("Success");
//	            response.getWriter().write(json);
//	            con.close();
//
//	        } catch (Exception e) {
//
//	            e.printStackTrace();
//
//	        }
//
////          out.println("</table></body></html>");
//
//
//            // Get a writer pointer
//            // to display the succesful result
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////		PrintWriter out = response.getWriter();
////
//
//
//		Gson gson = new Gson();
//		String json;
//
//
//
//		response.setContentType("application/json;charset=UTF-8");
////		out.println(" <head>" +"  <meta charset=\"UTF-8\">" +"</head> ");
////		out1.println("<html><body><table><tr><th>Date</th><th>Departure</th><th>Arrival</th>");
//			try {
//
//				String dbDriver = "com.mysql.jdbc.Driver";
//				Class.forName(dbDriver);
//	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "maslman111");
//	            Statement mySt  = con.createStatement();
//	            int r;
//	            String query;
//	            String seat, route, train;
//
//	            seat = request.getParameter("Seat");
//
//	            route = request.getParameter("Route");
//	            train = request.getParameter("Train");
//	            System.out.println(seat);
//	            System.out.println(route);
//	            System.out.println(train);
//	            UpdateHelper h = new UpdateHelper();
//	            query = h.Cancel(seat, route, train);
//	            System.out.println(query);
//	            r = mySt.executeUpdate(query);
//
//
//
//
//
//	            json = gson.toJson(r);
//	            System.out.println(json);
//	            System.out.println("Success");
//	            response.getWriter().write(json);
//	            con.close();
//
//	        } catch (Exception e) {
//
//	            e.printStackTrace();
//
//	        }
//
//
////          out.println("</table></body></html>");
//
//
//            // Get a writer pointer
//            // to display the succesful result
//
//
//	}
//
//}
