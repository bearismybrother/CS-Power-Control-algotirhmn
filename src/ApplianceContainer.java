import java.util.Vector;

public class ApplianceContainer {

	private Vector<Appliance> appliances = new Vector<Appliance>();  //change it into vector
	private static int numSetToLow=0; 
	private static int numBrownOut=0; 
	
	public int getNumberOfAppliances()
	{
		return appliances.size();
	}
	public void addAppliance(Appliance appliance) 
	{
		appliances.add(appliance);
	}
	
	public void deleteappliance(Appliance appliance) 
	{
		appliances.remove(appliance);
	}
	
	public void brownOut()
	{
		for (Appliance appliance : appliances )
		{
			appliance.setCurrentMode("off"); 
		}
		numBrownOut++; 
	}
	
	public double getCurrentWattageUsage() {
		int total = 0; 
		for (Appliance appliance : appliances )
		{
			total += appliance.getCurrentWattageUsage(); 
		}
		return total; 
	}
	
	public void decreaseEnergy(int amount) {  
		for (Appliance appliance : appliances )
		{
			if (appliance.isSmart()) 
			{
				if (appliance.getCurrentMode()=="on")
				{
					appliance.setCurrentMode("low"); 
					numSetToLow++;
					break;   //set only one appliance to low and then break
				}
			}
		}
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
