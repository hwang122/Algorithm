import java.util.ArrayList;


public class Sensor {
	
	private double x;
	private double y;
	private double cost;
	private int ID;
	//indicate the targets covered by this sensor
	private ArrayList<Integer> coveredtarget= new ArrayList<Integer>();
	
	Sensor(double x, double y, double c, int id){
		setX(x);
		setY(y);
		setCost(c);		
		setID(id);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public ArrayList<Integer> getCoveredtarget() {
		return coveredtarget;
	}

	public void AddCoveredtarget(int coveredtarget) {
		this.coveredtarget.add(coveredtarget);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}


}
