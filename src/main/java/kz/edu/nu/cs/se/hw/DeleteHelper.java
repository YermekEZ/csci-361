package kz.edu.nu.cs.se.hw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteHelper {
    public DeleteHelper(){}
        protected static PreparedStatement Cancel(Connection con, int place, int route, int leg, int vag, String date) throws SQLException {
            String query = "Delete from Ticket " +
                    "WHERE Seat_Number= ? and RouteID = ? and Leg_Serial_number = ? and Vagon_num = ? and Date= ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, place);
            pstmt.setInt(2, route);
            pstmt.setInt(3, leg);
            pstmt.setInt(4, vag);
            pstmt.setString(5, date);
            return pstmt;
        }

}
