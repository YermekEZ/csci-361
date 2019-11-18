package kz.edu.nu.cs.se.hw;

public class Ticket {
	private int TrainId;
	private int Passenger;
	private int seat;
	private boolean status;
	private String routeId;
	private String firstName;
	private String lastName;


	public Ticket(int seatNumber, boolean status, String routeId, String f, String l, int Passenger, int TrainId ) {
		this.Passenger = Passenger;
		this.TrainId = TrainId;
		this.seat = seatNumber;
		this.status = status;
		this.routeId = routeId;
		this.firstName =f;
		this.lastName = l;
	}

	protected String Cancel(String place, String route, String passenger){
		String query="Update ticket Set Status = "+'0'+"Passenger = "+'0'+" Where";
		query += " Seat_number =" + '"'+place+'"';
		query += " and RouteID = " + '"' + route+'"';
		query += " and Passenger = " + '"' + passenger+'"';
		return query;
	}



	public void setFirstname(String f) {
		this.firstName =f;
	}
	public void setLastName(String l) {
		this.lastName = l;
	}

	public String toString() {
		return Integer.toString(seat) + Boolean.toString(status)+ routeId+firstName+lastName;

	}
}
