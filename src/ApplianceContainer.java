import java.util.Vector;

public class ApplianceContainer {

	private Vector<Appliance> appliances = new Vector<Appliance>();  //change it into vector
	public static double energySaved =0; 
	
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
		energySaved += this.getCurrentWattageUsage();
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
