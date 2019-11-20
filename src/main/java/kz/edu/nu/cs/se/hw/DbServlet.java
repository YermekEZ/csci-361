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
			boolean added = false;
			PreparedStatement mySt = SelectHelper.SearchLeg(con, date, departure, arrival);
			System.out.println(mySt.toString());
			r = mySt.executeQuery();

			while (r.next()) {
				String dat = r.getString(1);
				int train = Integer.parseInt(r.getString(2));
				int leg = Integer.parseInt(r.getString(3));
				int route = Integer.parseInt(r.getString(4));
				String time = r.getString(5);
				String dep = r.getString(6);
				String arr = r.getString(7);

				boolean status;
				System.out.println(r.getString("Status"));
				if (Integer.parseInt(r.getString("Status")) == 0) {

					//Ticket x = new Ticket(dat, train, leg,route,time,dep,arr);
					//tickets.add(x);
				}
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
		List<String> deps = new ArrayList<>();


		response.setContentType("application/json;charset=UTF-8");
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			Statement mySt  = con.createStatement();
			ResultSet r;
			String query ="select Route.Departure from Route where Route.Departure LIKE \"";
			String keyword;
			keyword = request.getParameter("keyword");
			System.out.println('%'+keyword);
			if(!keyword.isEmpty()) {
				query +=keyword+"%"+'"';
			}
			System.out.println("QUERY!!!!:"+query);
			r = mySt.executeQuery(query);

			while(r.next()) {
				System.out.println(r.getString("Departure"));
				deps.add(r.getString("Departure"));
			}

			json = gson.toJson(deps);
			System.out.println(json);
			System.out.println("Success");
			response.getWriter().write(json);
			con.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
