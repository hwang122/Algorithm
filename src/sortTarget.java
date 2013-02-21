import java.util.Comparator;

public class sortTarget implements Comparator<Object>{
	
	//compare the x axis value of target and sort it
	public int compare(Object o1, Object o2){
		
		Target t1 = (Target)o1;
		Target t2 = (Target)o2;
		
		if(t1.getX()<t2.getX())
			return -1;
		else if(t1.getX()>t2.getX())
			return 1;
		else
			return 0;
	}

}
