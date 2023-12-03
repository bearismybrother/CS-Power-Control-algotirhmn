import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;



class AppClient{
	
	public static void readAppFile(Vector<ApplianceContainer> locations,String file){ // method to read the comma separated appliance file.
		Scanner scan;
		int appCount = 0;
		try {
			File myFile=new File(file);
			scan=new Scanner(myFile);//each line has the format
			//locationID,name of app,onPower,probability of staying on, smart or not,Smart appliances (if "on") power reduction percent when changed to "low" status(floating point, i.e..33=33%).
			//loops per line, makes object per line and is supposed to place it into vector using the resize method
			while(scan.hasNext()) { 
				String str = scan.nextLine();
				String []attributes = str.split(",");
				//TODO: fix the appliance class because it does not contain the necessary attributes at the moment.
				Appliance newApp = new Appliance(Integer.parseInt(attributes[0]),attributes[1],Integer.parseInt(attributes[2]),Double.parseDouble(attributes[3]),Boolean.parseBoolean(attributes[4]),Double.parseDouble(attributes[5]));
				resize(locations,newApp);
				appCount++;
				
				/*int locationID;
				String appName;
				int onPower;
				float probOn;
				boolean appType;
				float lowPower;*/
			}
			/*Complete the method*/
			scan.close();
			System.out.println("Successfully added " + appCount + "new appliances");
		}catch(IOException ioe){ 
			System.out.println("The file can not be read");
		}
	}

	public static void resize(Vector<ApplianceContainer> locations, Appliance myApp) {
		int locationSize = locations.size();
		//so we'll start reading each line			
		//see the read appfile method above, it'll retrieve arguments somehow
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
		
		
		//set up a queue to start step here!!
	}
	
	public static void main(String []args){
		
		System.out.println("Current Working Directory: " + System.getProperty("user.dir") + "\n");
		

		boolean runApplication = true;
		Vector<ApplianceContainer> locations = new Vector<ApplianceContainer>(); 
		//User interactive part
		String option1, option2 = new String();
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

			//menu options system
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
				
				System.out.println("Which file do you want to use: Type A for App.txt or type B for Output.txt");
				switch (option2) {
				case "A":
					readAppFile(locations,"app.txt");
					break;
				case "B":
					readAppFile(locations,"Output.txt");
					break;
				default: //invalid inputs
					System.out.println("Error: Invalid input.\n");
			        break; 
			        
				}
				break;
			case "S":
				System.out.println("TODO: implement starting simulation menu option\n");
				break;
			case "Q":
				System.out.println("Program is now closed");
				runApplication = false;
				break;
			default: //invalid inputs
		        System.out.println("Error: Invalid input.\n");
		        break; 
			}

		} 

		scan.close();

		
	}
}