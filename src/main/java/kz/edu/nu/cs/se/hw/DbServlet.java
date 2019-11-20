package kz.edu.nu.cs.se.hw;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;



/**
 * Servlet implementation class DbServlet
 */
public class DbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DbServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I'm in get method");

		Gson gson = new Gson();
		String json;
		List<Ticket> tickets = new ArrayList<>();

		response.setContentType("application/json;charset=UTF-8");
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			ResultSet r;
			String date, departure, arrival;
			date = request.getParameter("Date");
			departure = request.getParameter("Departure");
			arrival = request.getParameter("Arrival");
			System.out.println(date);
			System.out.println(departure);
			System.out.println(arrival);


			PreparedStatement mySt = SelectHelper.SearchTicket(con, date, departure, arrival);
			System.out.println(mySt.toString());
			r = mySt.executeQuery();
			int s; int v; String dep; String arr; String dat; int t; String n; int id;String type; int leg; int route;
			while (r.next()) {
				s = r.getInt(1);
				v = r.getInt(2);
				dep = r.getString(3);
				arr = r.getString(4);
				dat = r.getString(5);
				t = r.getInt(6);
				leg= r.getInt(7);
				n = r.getString(8);
				id = r.getInt(9);
				type = r.getString(10);
				route= r.getInt(11);
				Ticket x = new Ticket(s, v, dep, arr, dat, t, n, id,type,leg, route);
				tickets.add(x);
			}
			System.out.println(tickets);
			json = gson.toJson(tickets);
			System.out.println(json);
			System.out.println("Success");
			response.getWriter().write(json);
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String json;

		response.setContentType("application/json;charset=UTF-8");

		try {

			Connection con = DatabaseConnection.initializeDatabase();
			Statement mySt  = con.createStatement();
			int r;
			String date; int seat, leg, vagon,route;

			seat = Integer.parseInt(request.getParameter("Seat"));
			route = Integer.parseInt(request.getParameter("Route"));
			date = request.getParameter("Date");
			vagon = Integer.parseInt(request.getParameter("Vagon"));
			leg = Integer.parseInt(request.getParameter("Leg"));


			PreparedStatement h = DeleteHelper.Cancel(con,seat,route,leg,vagon,date );
			System.out.println(h.toString());
			r = h.executeUpdate();

			json = gson.toJson(r);
			System.out.println(json);
			System.out.println("Success");
			response.getWriter().write(json);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


