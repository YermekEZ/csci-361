package kz.edu.nu.cs.se.hw;

public class SelectHelper {
    public SelectHelper(){    }
    protected String Search(String date, String departure, String arrival) {
        String query ="select * from Route where ";
                query += " Date =" + '"'+date+'"';
                query += " and Departure = " + '"' + departure + '"';
                query += " and Arrival = " + '"' + arrival+'"';
//        query += " Order by Date, Time asc";
        return query;
    }
    protected String SearchPassTicket(String Username) {
        String query ="select T.Seat_Number, R.Departure, R.Arrival, R.Time, T.TrainID from Ticket T, Route R where";
            query +=" T.Passenger = (select UserID from User where";
            query += " Username =" + '"'+Username+'"'+")";
            query += " and R.RouteID = T.RouteID";
        return query;
    }
    protected String SearchRouteTicket(String date, String departure, String arrival) {
        String query ="select T.Seat_Number, R.Departure, R.Arrival, R.Time, T.TrainID from Ticket T, Route R where";
        query +=" T.RouteID = (select RouteID from Route where";
        query += " Date =" + '"'+date+'"';
        query += " and Departure = " + '"' + departure+'"';
        query += " and Arrival = " + '"' + arrival+'"'+")";
        query += " and R.RouteID = T.RouteID";
        return query;
    }
}