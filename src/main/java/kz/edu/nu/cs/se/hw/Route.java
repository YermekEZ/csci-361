package kz.edu.nu.cs.se.hw;

public class Route {
	private String Date;
	private String Departure;
	private String Arrival;
	
	public Route(String d, String dep, String ar) {
		this.Date = d;
		this.Departure = dep;
		this.Arrival = ar;
	}
	public String getDate() {
		return this.Date; 
	}
	public String getDep() {
		return this.Departure;
	}
}
