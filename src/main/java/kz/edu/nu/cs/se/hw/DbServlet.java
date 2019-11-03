package kz.edu.nu.cs.se.hw;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

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

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter out = response.getWriter(); 
//		
		
		
		Gson gson = new Gson();
		String json;
		List<String> deps = new ArrayList<>();
		
		
		response.setContentType("application/json;charset=UTF-8");
//		out.println(" <head>" +"  <meta charset=\"UTF-8\">" +"</head> ");
//		out1.println("<html><body><table><tr><th>Date</th><th>Departure</th><th>Arrival</th>");
			try {
				
				String dbDriver = "com.mysql.jdbc.Driver"; 
				Class.forName(dbDriver); 
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "maslman111");
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
//	            	out.println("<tr><td>"+ r.getString(2)+"</td><td>"+ r.getString(3)+"</td><td>"+ r.getString(4)+"</td></tr>");
	            	System.out.println(r.getString("Departure"));
	            	deps.add(r.getString("Departure"));
	            }
	            
	            if (!r.next()) {
//            		out.println("<a href = " + "http://localhost:8080/dynamictodolist/index.html" + ">GO BACK</a>");
            	}
	            
	            json = gson.toJson(deps);
	            System.out.println(json);
	            System.out.println("Success");
	            response.getWriter().write(json);
	            con.close(); 

	        } catch (Exception e) {

	            e.printStackTrace();

	        }
			
  
//          out.println("</table></body></html>");
           
  
            // Get a writer pointer  
            // to display the succesful result 
            
           
	}

}
