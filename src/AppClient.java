import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;


class AppClient{
	
	public static void readAppFile(Map<Integer, ApplianceContainer> locations,String file){ // method to read the comma separated appliance file.
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
				Appliance newApp = new Appliance(Integer.parseInt(attributes[0]),attributes[1],Integer.parseInt(attributes[2]),Double.parseDouble(attributes[3]),Boolean.parseBoolean(attributes[4]),Double.parseDouble(attributes[5]));
				addAppliance(locations,newApp);
				appCount++;
			}
			scan.close();
			System.out.println("Successfully added " + appCount + " new appliances.\n");
		}catch(IOException ioe){ 
			System.out.println("The file can not be read");
		}
	}

	public static void startSimulation(Map<Integer, ApplianceContainer> locations, int allowedWattage, int steps) {
		double currentWattageUsage =0; 

		for (ApplianceContainer applianceContainer: locations.values())
		{
			applianceContainer.startOfStep();
			currentWattageUsage += applianceContainer.getCurrentWattageUsage();	 
		}
		//grab how much energy is using right now and roll randomizers
		
		
		
		//sort the locations by size and wattage
		Integer[] locationsSortedBySize = new Integer[locations.size()]; //low to high
		Integer[] locationsSortedByWattage = new Integer[locations.size()]; //high to low
		int index = 0;
	    for (Integer locationId : locations.keySet()) {
	        locationsSortedBySize[index] = locationId;
	        locationsSortedByWattage[index] = locationId;
	        index++;
	    }
	    Arrays.sort(locationsSortedBySize, Comparator.comparingInt(locationId -> locations.get(locationId).getNumberOfAppliances()));
	   
	    Arrays.sort(locationsSortedByWattage, Comparator.comparingDouble(locationId -> locations.get(locationId).getCurrentWattageUsage()).reversed());
 
		
		//start of main simulation
		int locationCount = 0; 
		int brownOutCount = 0; 
		while (currentWattageUsage > allowedWattage) {
			if (locationCount < locationsSortedByWattage.length)
			{
				ApplianceContainer temp = locations.get(locationsSortedByWattage[locationCount]);
				if (temp.containsAnySmartAppliancesON())
				{
					temp.setSmartToLow();
				}
				else
				{
					locationCount++;
				}
			}
			//no more smart appliance to turn to low, time to brown out
			else 
				{
				locations.get(locationsSortedBySize[brownOutCount++]).brownOut();			
				}
			
			currentWattageUsage = currentWattageUsage-ApplianceContainer.energySaved; 
		}
	}
	public static void addAppliance(Map<Integer, ApplianceContainer> locations, Appliance myApp) {
		int locationID = myApp.getLocation();
		if (locations.containsKey(locationID)) {
			locations.get(locationID).addAppliance(myApp);
		}
		else 
		{
			locations.put(locationID, new ApplianceContainer());
			locations.get(locationID).addAppliance(myApp);
		}
	
	}
	
	public static void main(String []args){
		
		Map<Integer, ApplianceContainer> locations = new HashMap<Integer, ApplianceContainer>();
		
		
		System.out.println("Current Working Directory: " + System.getProperty("user.dir") + "\n");
		

		boolean runApplication = true; 
		//User interactive part
		String option1, option2, option3 = new String();
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
				System.out.println("Please enter the appliance you want to add in this format: \nlocationID,name of app,onPower,probability of staying on, smart or not,Smart appliances (if \"on\") power reduction percent when changed to \"low\" status(floating point, i.e..33=33%).\nMake sure you separate your inputs with \",\" and that there are no spaces anywhere except for the description of the appliance.");
				option2 = scan.nextLine();
				String [] tmpArr = option2.split(",");
				Appliance newApp = new Appliance(Integer.parseInt(tmpArr[0]),tmpArr[1],Integer.parseInt(tmpArr[2]),Double.parseDouble(tmpArr[3]),Boolean.parseBoolean(tmpArr[4]),Double.parseDouble(tmpArr[5]));
				addAppliance(locations, newApp);
				System.out.println("Succesfully added: " + newApp.getDescription() + '\n');
				break;
			case "D":
				System.out.println("TODO: implement deleting an appliance menu option\n");
				break;
			case "L":
				if (locations.isEmpty()) 
				{
					System.out.println("Error: no objects have been added.\n");
				}else {
					locations.forEach((key, value) -> System.out.println("Appliances in location " + key + ":\n" + value.toString()));
				}
				break;
			case "F":
				System.out.println("Which file do you want to use: Type \"A\" for app.txt, type \"B\" for output.txt, or type \"C\" for a custom file.");
				option2 = scan.nextLine();
				switch (option2) {
				case "A":
					readAppFile(locations,"app.txt");
					break;
				case "B":
					readAppFile(locations,"output.txt");
					break;
				case "C":
					System.out.println("Enter the name of the custom text file. Make sure the file is in the correct directory and is properly formated.");
					option3 = scan.nextLine();
					readAppFile(locations,option3);
					break;
				default: //invalid inputs
					System.out.println("Error: Invalid input.\n");
			        break; 
				}
				break;
			case "S":
				if (locations.isEmpty()) 
				{
					System.out.println("Error: no objects have been added.\n");
				}else {
					System.out.println("What is the allowed wattage\n");
					int wattageAllowed = scan.nextInt(); 
					System.out.println("Enter numbers of steps\n");
					int steps = scan.nextInt();
					startSimulation(locations, wattageAllowed, steps);
				}
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