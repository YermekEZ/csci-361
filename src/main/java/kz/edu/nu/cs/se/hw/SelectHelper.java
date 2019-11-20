package kz.edu.nu.cs.se.hw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectHelper {
    public SelectHelper(){    }
    protected static PreparedStatement SearchRoute(Connection con, String date, String departure, String arrival) throws SQLException {
        PreparedStatement stm;
        String query ="select DATE(R1.Date_dep), TIME(R1.Date_dep), L1.Location, L2.Location from leg_of_route R1,leg_of_route R2, station L1, station L2" +
                " where DATE(R1.Date_dep) = ? and DATE(R2.Date_Dep)=DATE(R1.Date_arr) and L1.Location = ? and L2.Location = ? " +
                "and L1.StationId=R1.Station_dep and L2.StationID = R2.Station_arr and R1.RouteID=R2.RouteID";
        stm = con.prepareStatement(query);
        stm.setString(1, date);
        stm.setString(2, departure);
        stm.setString(3, arrival);
        return stm;
    }
    protected static PreparedStatement SearchLeg(Connection con, String date, String departure, String arrival) throws SQLException {
        PreparedStatement stm;
        String query ="select distinct DATE(R1.Date_dep), R1.Train_ID,R1.serial_number_in_route,R1.routeID, TIME(R1.Date_dep), L1.Location, L2.Location from leg_of_route R1,leg_of_route R2, station L1, station L2" +
                "where DATE(R1.Date_dep) = ? and DATE(R2.Date_Dep)=DATE(R1.Date_arr) and L1.Location = ? and L2.Location = ? " +
                "and L1.StationId=R1.Station_dep and L2.StationID = R1.Station_arr";
        stm = con.prepareStatement(query);
        stm.setString(1, date);
        stm.setString(2, departure);
        stm.setString(3, arrival);
        return stm;
    }
    protected static PreparedStatement SearchRoutePassenger(Connection con, String date, String departure, String arrival) throws SQLException {
        PreparedStatement stm;
        String query ="select distinct DATE(R1.Date_dep), R1.Train_ID,R1.serial_number_in_route,R1.routeID, TIME(R1.Date_dep), L1.Location, L2.Location from leg_of_route R1,leg_of_route R2, station L1, station L2" +
                "where DATE(R1.Date_dep) = ? and DATE(R2.Date_Dep)=DATE(R1.Date_arr) and L1.Location = ? and L2.Location = ? " +
                "and L1.StationId=R1.Station_dep and L2.StationID = R1.Station_arr";
        stm = con.prepareStatement(query);
        stm.setString(1, date);
        stm.setString(2, departure);
        stm.setString(3, arrival);
        return stm;
    }

    protected static PreparedStatement SearchHistory(Connection con, String Username) throws SQLException {
        String query = "select distinct T.Seat_Number, T.Vagon_num, L1.Location, L2.Location, T.Date, T.Pass_Name,T.Vagon_type" +
                " from Ticket T, Leg_of_route R, Station L1, Station L2 where T.PassID = " +
                "(select UserID from user where Username = ?) " +
                "and L1.StationId=R.Station_dep and L2.StationID = R.Station_arr and R.RouteID=T.RouteID and R.Serial_number_in_route = T.Leg_Serial_number";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, Username);
        return stm;
    }

    protected static PreparedStatement findUser(Connection con, String username) throws SQLException {
        String query = "SELECT * from user where username = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1,username);
        return stm;
    }
    protected static PreparedStatement loginCheck(Connection con, String username, String password) throws SQLException {
        String query = "SELECT * from user where username = ? and password =?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1,username);
        stm.setString(2,password);
        return stm;
    }
}