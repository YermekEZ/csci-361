package Testing_DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateHelper {
	public UpdateHelper(){}
    protected static PreparedStatement Cancel(Connection con, int place, int vagon, String departure, String arrival, String date, int train, int passID) throws SQLException {
        String query="Delete from Ticket Where " +
                "Seat_Number = ? and Vagon_num = ? and Date =? and Train_ID = ? and PassID = ? and " +
                "Leg_Serial_number = (Select distinct R.Serial_number_in_route from Station L1, Station L2, Leg_of_route R where L1.StationId=R.Station_dep and L2.StationID = R.Station_arr and L1.Location=? and L2.Location = ?)" +
                "and RouteID = (select distinct R1.RouteID from Leg_of_route R1, Station S1, Station S2 where S1.StationId=R1.Station_dep and S2.StationID = R1.Station_arr and S1.Location=? and S2.Location = ?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1,place);
        stm.setInt(2,vagon);
        stm.setString(3,date);
        stm.setInt(4,train);
        stm.setInt(5,passID);
        stm.setString(6,departure);
        stm.setString(7,arrival);
        stm.setString(8,departure);
        stm.setString(9,arrival);
        return stm;
    }
      protected static PreparedStatement SetPassenger(Connection con, String fname, String lname, String email, int place, int train, int vagon, String date,String departure, String arrival,String pass_name) throws SQLException {
        String query="Insert into Ticket (Pass_Name,PassID,seat_number,train_ID, Vagon_num,date, Leg_Serial_number, RouteID, Vagon_type)  values ( ?, " +
        " (Select UserID from User where Fname = ? and Lname = ? and Email = ?), " +
                "?, ?, ?, ?," +
                "(Select distinct R.Serial_number_in_route from Station L1, Station L2, Leg_of_route R where L1.StationId=R.Station_dep and L2.StationID = R.Station_arr and L1.Location=? and L2.Location = ?), " +
                "(select distinct R1.RouteID from Leg_of_route R1, Station S1, Station S2 where S1.StationId=R1.Station_dep and S2.StationID = R1.Station_arr and S1.Location=? and S2.Location = ?),"+
                "(select Vagon_type from vagon where vagon.train_Id=? and vagon.Vagon_num=?))";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1,pass_name);
        stm.setString(2,fname);
        stm.setString(3,lname);
        stm.setString(4,email);
        stm.setInt(5,place);
        stm.setInt(6,train);
        stm.setInt(7,vagon);
        stm.setString(8,date);
        stm.setString(9,departure);
        stm.setString(10,arrival);
        stm.setString(11,departure);
        stm.setString(12,arrival);
        stm.setInt(13,train);
        stm.setInt(14,vagon);

        return stm;
    }
}
