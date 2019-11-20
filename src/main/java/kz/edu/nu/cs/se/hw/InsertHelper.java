package kz.edu.nu.cs.se.hw;
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
    protected static PreparedStatement BuyTicket(Connection con, String username, int place, int vagon, String date, String departure, String arrival, String pass_name) throws SQLException {
        String routeQ = " from leg_of_route L, Station S1, Station S2, route R where S1.Location = ? and S2.Location = ? and ((L.Station_dep=S1.StationID and L.Station_arr=S2.StationID) or (R.First_Station = S1.StationID and R.Last_Station=S2.StationID and R.RouteID=L.RouteID ";
        String query="Insert into Ticket (Pass_Name,PassID,seat_number,train_ID, Vagon_num,date, Leg_Serial_number, RouteID, Vagon_type)  values ( " +
                " ? , " +
                " (Select UserID from User where username = ? ), " +
                " ? , " +
                "(Select distinct L.train_id" + routeQ + "))), "+
                " ? , " +
                " ? , " +
                "(Select distinct L.Serial_number_in_route" + routeQ + "and L.Station_arr = S2.StationID))), "+
                "(select distinct L.RouteID" + routeQ + "))), "+
                "(select distinct Vagon_type from vagon where vagon.Vagon_num= ? ));"+
                "Insert into Ticket (Pass_Name,PassID,seat_number,train_ID, Vagon_num,date, Leg_Serial_number, RouteID, Vagon_type)  values ( " +
                " ? , " +
                " (Select UserID from User where username = ?), " +
                "?, " +
                "(Select distinct L.train_id" + routeQ + "))),"+
                " ? , " +
                " ? , " +
                "(Select distinct L.Serial_number_in_route" + routeQ + "and L.Station_dep = S1.StationID))),"+
                "(select distinct L.RouteID" + routeQ + "))),"+
                "(select distinct Vagon_type from vagon where vagon.Vagon_num= ? ))";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1,pass_name);
        stm.setString(2,username);
        stm.setInt(3,place);
        stm.setString(4,departure);
        stm.setString(5,arrival);
        stm.setInt(6,vagon);
        stm.setString(7,date);
        stm.setString(8,departure);
        stm.setString(9,arrival);
        stm.setString(10,departure);
        stm.setString(11,arrival);
        stm.setInt(12,vagon);
        stm.setString(13,pass_name);
        stm.setString(14,username);
        stm.setInt(15,place);
        stm.setString(16,departure);
        stm.setString(17,arrival);
        stm.setInt(18,vagon);
        stm.setString(19,date);
        stm.setString(20,departure);
        stm.setString(21,arrival);
        stm.setString(22,departure);
        stm.setString(23,arrival);
        stm.setInt(24,vagon);

        return stm;
    }
}

