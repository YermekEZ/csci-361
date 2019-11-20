package kz.edu.nu.cs.se.hw;
//DATE(R1.Date_dep), R1.Train_ID,R1.serial_number_in_route,R1.routeID, TIME(R1.Date_dep), L1.Location, L2.Location
public class Leg {
    private String Date;
    private int train;
    private int leg;
    private int route;
    private String time;
    private String dep;
    private String arr;
    private boolean status;

    public Leg(String d, int t, int l, int r, String tim, String dep, String arr) {
        this.Date = d;
        this.train = t;
        this.leg = l;
        this.route = r;
        this.time = tim;
        this.dep = dep;
        this.arr = arr;
    }
}
