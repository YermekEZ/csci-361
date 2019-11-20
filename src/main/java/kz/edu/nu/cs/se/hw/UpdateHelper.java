package kz.edu.nu.cs.se.hw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateHelper {
	public UpdateHelper(){}
    protected String Cancel(String place, String route, String train){
        String query="Update Ticket Set Ticket.Status = "+"0 "+", Ticket.Passenger = "+'0'+" Where";
        query += " Seat_Number =" +place;
        query += " and RouteID = " + '"' + route+'"';
        query += " and TrainID = " +  train ;
        return query;
    }
    protected String UpdateConditions(String route, String weather){
        String query="Update Route Set Weather = "+ '"' + weather+'"'+" Where";
        query += " RouteID = " + '"' + route+'"';
        return query;
    }
    protected static PreparedStatement SetPassenger(Connection con, String fname, String lname, String email, int place, int train, int vagon, String date, String departure, String arrival, String pass_name) throws SQLException {
        String query="Insert into Ticket (Pass_Name,PassID,seat_number,train_ID, Vagon_num,date, Leg_Serial_number, RouteID, Vagon_type)  values ( ?, " +
                " (Select UserID from User where Fname = ? and Lname = ? and Email = ?), " +
                "?, ?, ?, ?," +
                "(Select distinct R.Serial_number_in_route from Station L1, Station L2, Leg_of_route R where L1.StationId=R.Station_dep and L2.StationID = R.Station_arr and L1.Location=? and L2.Location = ?), " +
                "(select distinct R1.RouteID from leg_of_route R1, Station S1, Station S2 where S1.StationId=R1.Station_arr and S2.StationID = R1.Station_dep and S1.Location=? and S2.Location = ?),"+
                "(select distinct Vagon_type from vagon where vagon.vagon_num=?))";
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
        stm.setInt(13,vagon);
        return stm;
    }

//    protected String SearchPassTicket(String Username) {
//        String query ="select Ticket.Seat_Number, Route.Departure, Route.Arrival, Route.Time, Ticket.TrainID from Ticket, Route where";
//            query +=" Ticket.Passenger = (select UserID from User where";
//            query += " Username =" + '"'+Username+'"'+")";
//            query += " and Route.RouteID = Ticket.RouteID";
//        return query;
//    }
}
