package kz.edu.nu.cs.se.hw;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
//		out.println(" <head>" +"  <meta charset=\"UTF-8\">" +"</head> ");
//		out1.println("<html><body><table><tr><th>Date</th><th>Departure</th><th>Arrival</th>");
			try {
				
				String dbDriver = "com.mysql.jdbc.Driver"; 
				Class.forName(dbDriver); 
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "maslman111");
	            Statement mySt  = con.createStatement(); 
	            ResultSet r; 
	            String query;
	            String query1;
	            String username;
	           
	            username = request.getParameter("User");
	            System.out.println(username);
	            
	            UpdateHelper h = new UpdateHelper();
                query1 = h.SearchPassTicket(username);
	           	System.out.println(query1);
	            r = mySt.executeQuery(query1);
	            
	            
	            
	            
	            while(r.next()) {
          	History x = new History(r.getString(1), r.getString(2), r.getString(3),r.getString(4),r.getString(5));
	            	
	            	
            	list.add(x);
	            }
//	            History x = new History("1", "Nur-Sultan","Semey","13:40:12", "158");
//	            list.add(x);
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
//		out.println(" <head>" +"  <meta charset=\"UTF-8\">" +"</head> ");
//		out1.println("<html><body><table><tr><th>Date</th><th>Departure</th><th>Arrival</th>");
			try {
				
				String dbDriver = "com.mysql.jdbc.Driver"; 
				Class.forName(dbDriver); 
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "maslman111");
	            Statement mySt  = con.createStatement(); 
	            int r; 
	            String query;
	            String seat, route, train, fname, lname, email;
	           
	            seat = request.getParameter("Seat");
	            route = request.getParameter("Route");
	            train = request.getParameter("Train");
	            fname = request.getParameter("Fname");
	            lname = request.getParameter("Lname");
	            email = request.getParameter("Email");
	            
	            
	            
	            System.out.println(seat);
	            System.out.println(route);
	            System.out.println(train);
	            UpdateHelper h = new UpdateHelper();
	            query = h.ChangePassenger(fname, lname, email, seat,train, route);
	            System.out.println(query);
	            r = mySt.executeUpdate(query);
	        
	         
	            
	           
	            
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
