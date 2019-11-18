package kz.edu.nu.cs.se.hw;

public class History {
//	seat, route, train, fname, lname, email;
	private String TrainId;
	private String seat;
	private String dep;
	private String ar;
	private String time;
	
	public History (String s, String departure, String arrival, String time, String t) {
		this.TrainId =t;
		this.seat = s;
		this.dep = departure;
		this.ar =arrival;
		this.time=time;
	}
	
	
	
}
