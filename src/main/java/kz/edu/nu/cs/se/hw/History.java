package kz.edu.nu.cs.se.hw;

public class History {
	//	T.Seat_Number, T.Vagon_num, L1.Location, L2.Location, R.Date_dep, T.Pass_Name,T.Vagon_type
	private String seat;
	private String vagon;
	private String dep;
	private String ar;
	private String date;
	private String name;
	private String type;

	public History (String seat, String vagon, String departure, String arrival,String date, String name, String type) {
		this.seat = seat;
		this.vagon = vagon;
		this.dep = departure;
		this.ar = arrival;
		this.date = date;
		this.name = name;
		this.type = type;
	}



}
