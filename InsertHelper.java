//SET PACKAGE
package Testing_DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertHelper {
     public InsertHelper(){    }

    protected static PreparedStatement Reg(Connection con, String Fname, String Lname, String Username, String password, String email) throws SQLException {
        String query ="insert into user (Fname, Lname, Username, password, email) values (?, ?, ?, ?, ?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1,Fname);
        stm.setString(2,Lname);
        stm.setString(3, Username);
        stm.setString(4, password);
        stm.setString(5,email);
        return stm;
    }
}
