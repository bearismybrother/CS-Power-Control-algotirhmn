
public class ApplianceContainer {

	private Appliance[] appliances;  //change it into vector
	
	public ApplianceContainer()
	{
		
	}
	
	public void BrownOut()
	{
		for (Appliance appliance : appliances )
		{
			appliance.setCurrentMode("off"); 
		}
	}
	
	public double getTotalEnergy() {
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
				appliance.setCurrentMode("low"); //should I return here?
		}
	}
}
