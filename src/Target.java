

public class Target {
	
	private double x;
	private double y;
	//indicate which sensor does this target belonging to
	private Sensor belonging;
	
	Target(double x, double y){
		setX(x);
		setY(y);
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

	public Sensor getBelonging() {
		return belonging;
	}

	public void setBelonging(Sensor belonging) {
		this.belonging = belonging;
	}
}
