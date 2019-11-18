package kz.edu.nu.cs.se.hw;

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
    protected String ChangePassenger(String fname, String lname, String email, String place, String train, String route){
        String query="Update Ticket Set Status = 1, Passenger = ";
        query += "(Select UserID from User where Fname =" + '"'+fname+'"';
        query += " and Lname = " + '"' + lname+'"';
        query += " and Email = " + '"' + email+'"'+")";
        query += " Where Seat_number = "  + place;
        query += " and TrainID = "  + train;
        query += " and RouteID = " + '"' + route+'"';
        return query;
    }
    protected String SearchPassTicket(String Username) {
        String query ="select Ticket.Seat_Number, Route.Departure, Route.Arrival, Route.Time, Ticket.TrainID from Ticket, Route where";
            query +=" Ticket.Passenger = (select UserID from User where";
            query += " Username =" + '"'+Username+'"'+")";
            query += " and Route.RouteID = Ticket.RouteID";
        return query;
    }
}
