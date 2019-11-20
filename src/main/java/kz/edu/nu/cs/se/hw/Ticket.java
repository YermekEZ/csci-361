package kz.edu.nu.cs.se.hw;

public class Ticket {
	private String dat;
	private int train;
	private int leg;
	private int route;
	private String time;
	private String dep;
	private String arr;


	public Ticket(String d, int t, int l, int r, String time, String dep, String arr) {
		this.dat = d;
		this.train = t;
		this.leg = l;
		this.route = r;
		this.time = time;
		this.dep = dep;
		this.arr = arr;
	}
}
