import java.util.Vector;

public class ApplianceContainer {

	private Vector<Appliance> appliances = new Vector<Appliance>();  //change it into vector
	public static double energySaved =0; 
	public static int numSetToLow=0; 
	public static int numBrownOut=0; 
	
	public int getNumberOfAppliances()
	{
		return appliances.size();
	}
	public void addAppliance(Appliance appliance) 
	{
		appliances.add(appliance);
	}
	
	public void deleteAppliance(String appliance) 
	{
		Appliance applianceToRemove=appliances.get(0); 
		for (Appliance tempApp: appliances)
		{
			if (tempApp.getDescription().equals(appliance))
			{
				applianceToRemove = tempApp; 
				break;
			}
		}
		appliances.remove(applianceToRemove);
	}
	
	public void brownOut()
	{
		energySaved += this.getCurrentWattageUsage();
		for (Appliance appliance : appliances )
		{
			appliance.setCurrentMode("off"); 
		}
		numBrownOut++; 
		System.out.println("Location #" +appliances.get(0).getLocation() + " has been browned out");
	}
	
	public int getLocation() {
		return appliances.get(0).getLocation();
	}
	
	public double getCurrentWattageUsage() {
		double total = 0; 
		for (Appliance appliance : appliances )
		{
			total += appliance.getCurrentWattageUsage(); 
		}
		return total; 
	}
	
	public boolean containsAnySmartAppliancesON() {
		for (Appliance appliance : appliances) {
			if (appliance.isSmart())
			{
				if (appliance.getCurrentMode()=="on")
					return true; 
			}
		}
			return false; 
	}
	
	//NEEDS FIXING
	public void setSmartToLow() {  
		Appliance highestAppliance = new Appliance(-1, "empty", 0, 0, true, 0);
		for (Appliance appliance : appliances )
		{
			if (appliance.isSmart()) 
			{
				if (appliance.getCurrentMode()=="on")
				{
					if (appliance.getCurrentWattageUsage() > highestAppliance.getCurrentWattageUsage())
					{
						highestAppliance = appliance; 
					}
				}
			}
		}
		highestAppliance.setCurrentMode("low");
		numSetToLow++; 
		energySaved += highestAppliance.getPowerReduction()*highestAppliance.getOnWattage();
		System.out.println(highestAppliance.getDescription() + " turned to low \n It was using " + highestAppliance.getOnWattage() + " now it's using " + highestAppliance.getCurrentWattageUsage()+ " wattages");
		
	}
	

	public String toString() {
		String retString = new String();
		for (Appliance appliance : appliances) 
		{
			retString += appliance.toString();
		}
		retString += "\n";
		return retString;
	}
	
	public void startOfStep() {
		for (Appliance appliance : appliances) 
		{
			appliance.RandomizeCurrentMode();
		}
	}
}
