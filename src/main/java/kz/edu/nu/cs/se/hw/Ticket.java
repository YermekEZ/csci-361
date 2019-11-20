package kz.edu.nu.cs.se.hw;

public class Ticket {
	private int seat;
	private int vagon;
	private String dep;
	private String arr;
	private String date;
	private int train;
	private String name;
	private int id;
	private String type;
	private int leg;
	private int route;


	public Ticket(int s, int v, String dep, String arr, String date, int t, String n, int id, String type, int leg,int route) {
		this.seat = s;
		this.vagon = v;
		this.date = date;
		this.name = n;
		this.train = t;
		this.dep = dep;
		this.arr = arr;
		this.id = id;
		this.type = type;
		this.leg = leg;
		this.route = route;
	}
}
