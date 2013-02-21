import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * 
	 */
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		loadFile lf = new loadFile();
		selectSensor ss = new selectSensor();
		
		try{
		lf.LoadFile();
		int sensorsize = lf.sensor.size()/3;
		int targetsize = lf.target.size()/2;
		//generate the object of sensor
		Sensor[] sensor = new Sensor[sensorsize];
		for(int i = 0; i < sensorsize; i++)
			sensor[i] = new Sensor(lf.sensor.get(3*i),lf.sensor.get(3*i+1),lf.sensor.get(3*i+2),(i+1));
		
		//compare the cost of sensors and sort it
		List<Sensor> sensorlist = new ArrayList<Sensor>();
		for(int i = 0; i < sensorsize; i++){
			sensorlist.add(sensor[i]);
		}
		Comparator<Object> sort1 = new sortSensor();
		Collections.sort(sensorlist, sort1);
		for(int i = 0; i < sensorsize; i++){
			//System.out.println(((Sensor)sensorlist.get(i)).getCost()+","+((Sensor)sensorlist.get(i)).getX());
			sensor[i] =(Sensor)sensorlist.get(i);
		}

		//generate the object of target
		Target[] target = new Target[targetsize];
		for(int i = 0; i < targetsize; i++){
			target[i] = new Target(lf.target.get(2*i),lf.target.get(2*i+1));
			//initialize target's belonging
			target[i].setBelonging(null);
		}
		
		//compare the x axis value of target and sort it
		List<Target> targetlist = new ArrayList<Target>();
		for(int i = 0; i < targetsize; i++){
			targetlist.add(target[i]);
		}
		Comparator<Object> sort2 = new sortTarget();
		Collections.sort(targetlist, sort2);
		for(int i = 0; i < targetsize; i++){
			target[i] =(Target)targetlist.get(i);
		}
		//run the DP to get selected Sensor
		ss.DynamicProgramming(target, sensor);
		
		for(int j = 0; j < target.length; j++)
			if(target[j].getBelonging() == null){
				System.out.println("Invalid value.");
				System.exit(0);
			}
		}
		catch(IndexOutOfBoundsException e){
			System.exit(0);
		}
		//output the final table of selected sensor and total cost
		System.out.println("Following sensors are choose(x axis'value, y axis'value):");
		for(int i = 0; i < ss.selectedSensor.size(); i++)
			System.out.println("S"+ ss.selectedSensor.get(i).getID() +" ("+ss.selectedSensor.get(i).getX()+", "+ss.selectedSensor.get(i).getY()+") ");
		System.out.println();
		System.out.println("The total cost is "+ss.addUp(ss.selectedSensor)+".");
			
		System.out.println("Press any key to continue...");
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		int ch = stdin.read();
		if(ch != -1)
			System.exit(0);
	}

}
