package kz.edu.nu.cs.se.hw;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected static Connection con=null;
    public RegServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
            con=DatabaseConnection.initializeDatabase();
            String Lname, Fname, Username, password, email;
            Lname= request.getParameter("Lname");
            Fname= request.getParameter("Fname");
            Username= request.getParameter("Username");
            password= request.getParameter("password");
            email =request.getParameter("email");
            InsertHelper register = new InsertHelper();
            String regQuery = register.Reg(Fname, Lname,Username,password,email);
            Statement regSt  = con.createStatement();
            regSt.executeUpdate(regQuery);
            System.out.println(regQuery);
            regSt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
