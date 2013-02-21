import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class loadFile {
	
	private static BufferedReader in;
	
	//Store the txt file in a arraylist temporarily
	ArrayList<Double> sensor = new ArrayList<Double>();
	ArrayList<Double> target = new ArrayList<Double>();

	//load file
	public void LoadFile(){
		//system prompt
		System.out.println("Please load the file of targets and sensors:");
		
		InputStream is = System.in;
		Scanner scan = new Scanner(is);
		
		//initialize arraylist
		sensor.clear();
		target.clear();
		//read file
		try{
			//FileReader file = new FileReader("testfile.txt");
			FileReader file = new FileReader(scan.nextLine());
			in = new BufferedReader(file);
			String line;
			int rownum = 1;
			//the line is not empty
			while((line=in.readLine())!=null){
				if(rownum%2 == 1){
					//every element in the txt file is separate by blank or comma
					for(int i = 0; i < line.split("[\\s\\,]").length; i++)
						sensor.add(Double.parseDouble(line.split("[\\s\\,]")[i]));
				}
				else{
					//every element in the txt file is separate by blank or comma
					for(int i = 0; i < line.split("[\\s\\,]").length; i++)
						target.add(Double.parseDouble(line.split("[\\s\\,]")[i]));
				}
				rownum++;
			}
		} catch(FileNotFoundException e){
			System.out.println("There is no such file.");
		} catch(IOException e){
			System.out.println("Invalid input.");
		}
	}

}
