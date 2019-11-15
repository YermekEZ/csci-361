package Testing_DB;

import java.sql.PreparedStatement;

public class InsertHelper {
     public InsertHelper(){    }

    protected static PreparedStatement Reg(String Fname, String Lname, String Username, String password, String email){
        String query ="insert into user (Fname, Lname, Email";
        boolean added1 = false;
        boolean added2 = false;

        if(!password.isEmpty()){
            added1 = true;
            query += ",Password";
        }
        if(!Username.isEmpty()) {
            added2=true;
            query += ",Username)";
        }else {
            query += ")";
        }
        query += " values (" + '"' + Fname+'"'+","+ '"' + Lname+'"'+","+ '"' + email+'"';
        if(added1==true){
            query += "," + '"' + password+'"';
        }
        if(added2==true) {
            query += "," + '"' + Username+'"'+")";
        }else {
            query += ")";
        }
        return query;
    }
    protected String AddEmployee(String Fname, String Lname, String Status){
        String query ="insert into /// (Fname, Lname, Status) values (";
        query +='"' + Fname+'"'+","+ '"' + Lname+'"'+","+ '"' +Status+'"'+")";
        return query;
    }

}
