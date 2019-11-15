package Testing_DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectHelper {
    public SelectHelper(){    }
    protected static PreparedStatement SearchRoute(Connection con, String date, String departure, String arrival) throws SQLException {
        PreparedStatement stm;
        if(!date.isEmpty()){
            String query ="select DATE(R1.Date_dep), TIME(R1.Date_dep), L1.Location, L2.Location from leg_of_route R1,leg_of_route R2, station L1, station L2" +
                    " where DATE(R1.Date_dep) = ? and DATE(R2.Date_Dep)=DATE(R1.Date_arr) and L1.Location = ? and L2.Location = ? " +
                    "and L1.StationId=R1.Station_dep and L2.StationID = R2.Station_arr and R1.RouteID=R2.RouteID";
            stm = con.prepareStatement(query);
            stm.setString(1, date);
            stm.setString(2, departure);
            stm.setString(3, arrival);
        }else{
            String query ="select DATE(R1.Date_dep), TIME(R1.Date_dep), L1.Location, L2.Location from leg_of_route R1,leg_of_route R2, station L1, station L2" +
                    " where DATE(R2.Date_Dep)=DATE(R1.Date_arr) and L1.Location = ? and L2.Location = ? " +
                    "and L1.StationId=R1.Station_dep and L2.StationID = R2.Station_arr and R1.RouteID=R2.RouteID";
            stm = con.prepareStatement(query);
            stm.setString(1, departure);
            stm.setString(2, arrival);
        }
        return stm;
    }
    protected static PreparedStatement SearchPassTicket(Connection con, String Username) throws SQLException {
        String query = "select T.Seat_Number, T.Vagon_num, L1.Location, L2.Location, R.Date_dep, T.Train_ID, T.Pass_Name,T.PassID" +
                " from Ticket T, Leg_of_route R, Station L1, Station L2 where T.PassID = " +
                "(select UserID from user where Username = ?) " +
                "and L1.StationId=R.Station_dep and L2.StationID = R.Station_arr and R.RouteID=T.RouteID and R.Serial_number_in_route = T.Leg_Serial_number";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, Username);
        return stm;
    }
    protected static PreparedStatement SearchRouteTicket(Connection con, String date, String departure, String arrival) throws SQLException {
        String query = "select T.Seat_Number,T.Vagon_num, L1.Location, L2.Location, R.Date_dep, T.Train_ID, T.Pass_Name, T.PassID" +
                " from Ticket T, Leg_of_route R, Station L1, Station L2 " +
                "where DATE(R.Date_dep) = ? and L1.Location = ? and L2.Location = ? " +
                "and L1.StationId=R.Station_dep and L2.StationID = R.Station_arr and R.RouteID=T.RouteID and R.Serial_number_in_route = T.Leg_Serial_number";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, date);
        stm.setString(2, departure);
        stm.setString(3, arrival);
        return stm;
    }
}