package kz.edu.nu.cs.se.hw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateHelper {
    public UpdateHelper(){}

    protected static PreparedStatement ChangePassenger(Connection con, String fname, String lname, String email, int place, int route, int leg, int vag, String date) throws SQLException {
        String query = "Update Ticket set (Pass_name = ?, PassID = (select userID from user where fname=? and lname = ? and email = ?)) " +
                "WHERE Seat_Number= ? and RouteID = ? and Leg_Serial_number = ? and Vagon_num = ? and Date= ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, fname);
        pstmt.setString(2, lname);
        pstmt.setString(3, email);
        pstmt.setInt(4, place);
        pstmt.setInt(5, route);
        pstmt.setInt(6, leg);
        pstmt.setInt(7, vag);
        pstmt.setString(8, date);
        return pstmt;
    }


}
