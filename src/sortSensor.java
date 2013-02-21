import java.util.Comparator;

public class sortSensor implements Comparator<Object>{
	
	//compare the cost of each sensors and sort it
	public int compare(Object o1, Object o2){
		
		Sensor s1 = (Sensor)o1;
		Sensor s2 = (Sensor)o2;
		
		if(s1.getCost()<s2.getCost())
			return -1;
		else if(s1.getCost()>s2.getCost())
			return 1;
		else
			return 0;
	}

}
