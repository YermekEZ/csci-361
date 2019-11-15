package Testing_DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateHelper {
	public UpdateHelper(){}
    protected static PreparedStatement Cancel(Connection con, int place, int vagon, String departure, String arrival, String date, int train, int passID) throws SQLException {
        String query="Update Ticket T, Leg_of_route R, Station L1, Station L2 Set T.PassID = NULL, T.Pass_Name=NULL Where " +
                "T.Seat_Number = ? and T.Vagon_num = ? and T.Date =? and T.Train_ID = ? and T.PassID = ? and " +
                "L1.StationId=R.Station_dep and L2.StationID = R.Station_arr and R.RouteID=T.RouteID and R.Serial_number_in_route = T.Leg_Serial_number " +
                "and L1.Location = ? and L2.Location = ?";
        PreparedStatement stm = con.prepareStatement(query);;
        stm.setInt(1,place);
        stm.setInt(2,vagon);
        stm.setString(3,date);
        stm.setInt(4,train);
        stm.setInt(5,passID);
        stm.setString(6,departure);
        stm.setString(7,arrival);
        return stm;
    }
    protected static PreparedStatement ChangePassenger(Connection con, String fname, String lname, String email, int place, int train, int vagon, String date,String departure, String arrival) throws SQLException {
        String query="Update Ticket T, Station L1, Station L2, leg_of_route R Set T.Pass_Name = ?, " +
        "T.PassID = (Select UserID from User where Fname = ? and Lname = ? and Email = ?) " +
                "where T.seat_number = ? and T.train_ID = ? and T.Vagon_num = ? and T.date = ?" +
                "and L1.StationId=R.Station_dep and L2.StationID = R.Station_arr and R.RouteID=T.RouteID and R.Serial_number_in_route = T.Leg_Serial_number " +
                "and L1.Location = ? and L2.Location = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1,fname);
        stm.setString(2,fname);
        stm.setString(3,lname);
        stm.setString(4,email);
        stm.setInt(5,place);
        stm.setInt(6,train);
        stm.setInt(7,vagon);
        stm.setString(8,date);
        stm.setString(9,departure);
        stm.setString(10,arrival);
        return stm;
    }
}
