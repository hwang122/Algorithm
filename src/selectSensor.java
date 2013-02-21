import java.util.ArrayList;
import java.util.List;


public class selectSensor {
	
	private static final int maxlength = 1;
	List<Sensor> selectedSensor = new ArrayList<Sensor>();
	List<Integer> otherPossibleSensor = new ArrayList<Integer>();
	
	//function of add up all the cost of selected sensors
	public int addUp(List<Sensor> s){
		
		int totalcost=0;
		for(int i = 0; i< s.size(); i++)
			totalcost += (s.get(i)).getCost();
		
		return totalcost;
			
	}
	
	public void DynamicProgramming(Target[] t, Sensor[] s){
		//initialize selectedSensor
		selectedSensor.clear();
		int targetsize = t.length;
		int sensorsize = s.length;
		target: for(int i = 0; i < targetsize; i++){
			//if the target has been covered, the algorithm will skip it
			for(int k = 0; k < selectedSensor.size(); k++){
				if(Math.pow(t[i].getX()-selectedSensor.get(k).getX(), 2)+Math.pow(t[i].getY()-selectedSensor.get(k).getY(), 2) <= maxlength){
					t[i].setBelonging(selectedSensor.get(k));
					selectedSensor.get(k).AddCoveredtarget(i);
					continue target;
				}
			}
			//add the first sensor, base situation
			if(i == 0){
				for(int j = 0; j < sensorsize; j++){
					//find a sensor can cover the target
					if(Math.pow(t[i].getX()-s[j].getX(), 2)+Math.pow(t[i].getY()-s[j].getY(), 2) <= maxlength){
						t[i].setBelonging(s[j]);
						s[j].AddCoveredtarget(i);
						selectedSensor.add(s[j]);
						continue target;
					}
				}
				//could not find a sensor to cover this target
				t[i].setBelonging(null);
				break;
			}
			//not the base situation
			else{
				//to get another sensor may cover the previous targets covered by the previous sensor
				otherPossibleSensor.clear();
				outer: for(int m = 0; m < sensorsize; m++){
						for(int n = 0; n < t[i-1].getBelonging().getCoveredtarget().size(); n++){
							if(Math.pow(t[t[i-1].getBelonging().getCoveredtarget().get(n)].getX()-s[m].getX(), 2)+
									Math.pow(t[t[i-1].getBelonging().getCoveredtarget().get(n)].getY()-s[m].getY(), 2) > maxlength){
								continue outer;
							}
						}
					if(s[m]!=t[i-1].getBelonging()
						&&(Math.pow(t[i].getX()-s[m].getX(), 2)+Math.pow(t[i].getY()-s[m].getY(), 2) <= maxlength))
						otherPossibleSensor.add(m);
				}
				//there exists such a sensor searched above
				if(otherPossibleSensor.size() == 0){
					for(int j = 0; j < sensorsize; j++)
						if(Math.pow(t[i].getX()-s[j].getX(), 2)+Math.pow(t[i].getY()-s[j].getY(), 2) <= maxlength){
							t[i].setBelonging(s[j]);
							s[j].AddCoveredtarget(i);
							selectedSensor.add(s[j]);
							continue target;
						}
					t[i].setBelonging(null);
					break;
				}
				//there does not exist a sensor searched above
				else{
					for(int j = 0; j < sensorsize; j++){
						//find a minimum cost sensor covered target i
						if(Math.pow(t[i].getX()-s[j].getX(), 2)+Math.pow(t[i].getY()-s[j].getY(), 2) <= maxlength){
							if(s[j].getCost() + t[i-1].getBelonging().getCost() > s[otherPossibleSensor.get(0)].getCost()){
								//System.out.println(s[j].getCost()+" plus "+t[i-1].getBelonging().getCost()+" is bigger than "+s[otherPossibleSensor.get(0)].getCost());
								//set previous target, they belong to the more optimal sensor
								for(int n = 0; n < selectedSensor.get(selectedSensor.size()-1).getCoveredtarget().size();n++){
									t[selectedSensor.get(selectedSensor.size()-1).getCoveredtarget().get(n)].setBelonging(s[otherPossibleSensor.get(0)]);
									s[otherPossibleSensor.get(0)].AddCoveredtarget(selectedSensor.get(selectedSensor.size()-1).getCoveredtarget().get(n));
								}
								selectedSensor.remove(selectedSensor.size()-1);
								s[otherPossibleSensor.get(0)].AddCoveredtarget(i);
								selectedSensor.add(s[otherPossibleSensor.get(0)]);
								t[i].setBelonging(s[otherPossibleSensor.get(0)]);
								continue target;
							}
							//the sensor searched above has a larger cost
							else{
								t[i].setBelonging(s[j]);
								s[j].AddCoveredtarget(i);
								selectedSensor.add(s[j]);
								continue target;
							}
						}
						else continue;
					}
					t[i].setBelonging(null);
					break;
				}
			}
		}
	}
}
