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
 * Servlet implementation class Reserve
 */
public class Reserve extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reserve() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String json;
		List<History> list = new ArrayList<History>();


		response.setContentType("application/json;charset=UTF-8");
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			ResultSet r;
			String username;
			username = request.getParameter("User");
			System.out.println(username);
			PreparedStatement mySt = SelectHelper.SearchHistory(con, username);
			System.out.println(mySt.toString());
			r = mySt.executeQuery();
			while (r.next()) {
				History x = new History(r.getString(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5),r.getString(6),r.getString(7));
				list.add(x);
			}
			json = gson.toJson(list);
			System.out.println(json);
			System.out.println("Success");
			response.getWriter().write(json);
			mySt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String json;
		response.setContentType("application/json;charset=UTF-8");
//        try {
//            Connection con = DatabaseConnection.initializeDatabase();
//            int r, train, vagon;
//            String query;
//            String seat, route, fname, lname, email, date, departure, arrival, pass_name;
//            seat = request.getParameter("Place");
//            route = request.getParameter("Route");
//            fname = request.getParameter("Fname");
//            lname = request.getParameter("Lname");
//            email = request.getParameter("Email");
//            vagon = request.getParameter("Vagon");
//            date = request.getParameter("Train");
//            departure = request.getParameter("Fname");
//            arrival = request.getParameter("Lname");
//            pass_name = request.getParameter("Email");
//            System.out.println(seat);
//            System.out.println(route);
//            System.out.println(train);
//            PreparedStatement h = UpdateHelper.SetPassenger(con,fname,lname,email,seat,vagon,date,departure,arrival,pass_name);
//            r = h.executeUpdate();
//            json = gson.toJson(r);
//            System.out.println(json);
//            System.out.println("Success");
//            response.getWriter().write(json);
//            con.close();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }

	}

}
