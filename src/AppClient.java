import java.io.File;
import java.util.Vector;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;


class AppClient{
	
	public void readAppFile(String file){ // method to read the comma seperated appliance file.
		Scanner scan;
		try {
			File myFile=new File(file);
			scan=new Scanner(myFile);//each line has the format
			//locationID,name of app,onPower,probability of staying on, smart or not,Smart appliances (if "on") power reduction percent when changed to "low" status(floating point, i.e..33=33%).
			String str;
			int locationID;
			String appName;
			int onPower;
			float probOn;
			String appType;
			int lowPower;
			Appliance aAppl;
			
			/*Complete the method*/
			scan.close();
		}catch(IOException ioe){ 
			System.out.println("The file can not be read");
		}
	}
	
	public void startSimulation() {
		Vector<ApplianceContainer> locations = new Vector<ApplianceContainer>(); 
		int locationSize = locations.size();
		/*so we'll start reading each line
		Appliance appliance = new Appliance(location, description, onWattage, probability, isSmart, powerReduction);
		see the read appfile method above, it'll retrieve arguments somehow
		final int startingLocation = location; 
		int locationInArray = location - startingLocation; 
		if (locationInArray > locationSize-1)
		{
			locations.add(new ApplianceContainer());
			locationSize++; 
		}
		else 
		{
			locations[locationInArray].addAppliance(appliance); 
		}
		*/
		
		//set up a queue to start step here!!
	}
	
	public static void main(String []args){
		
		System.out.println("Current Working Directory: " + System.getProperty("user.dir") + "\n");
		
		AppClient app = new AppClient();
		boolean runApplication = true;
		//User interactive part
		String option1, option2;
		Scanner scan = new Scanner(System.in);
		while(runApplication){// Application menu to be displayed to the user.
			System.out.println("Select an option:");
			System.out.println("Type \"A\" Add an appliance");
			System.out.println("Type \"D\" Delete an appliance");	
			System.out.println("Type \"L\" List the appliances");
			System.out.println("Type \"F\" Read Appliances from a file");
			System.out.println("Type \"S\" To Start the simulation");
			System.out.println("Type \"Q\" Quit the program");
			option1=scan.nextLine();
			/* Complete the skeleton code below */
		
			switch (option1) {
			case "A":
				System.out.println("TODO: implement adding an appliance menu option\n");
				break;
			case "D":
				System.out.println("TODO: implement deleting an appliance menu option\n");
				break;
			case "L":
				System.out.println("TODO: implement listing the appliances menu option\n");
				break;
			case "F":
				//thing
				break;
			case "S":
				System.out.println("TODO: implement starting simulation menu option\n");
				break;
			case "Q":
				System.out.println("Program is now closed");
				runApplication = false;
				break;
			default: //invalid inputs
		         System.out.println("Error: Invalid input\n");
		         break; 
			}
		} 
		
	}
}