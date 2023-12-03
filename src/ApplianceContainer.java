import java.util.Vector;

public class ApplianceContainer {

	private Vector<Appliance> appliances = new Vector<Appliance>();  //change it into vector
	
	
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
			{
				if (appliance.getCurrentMode()=="on")
				{
					appliance.setCurrentMode("low"); 
					break;   //set only one appliance to low and then break
				}
			}
		}
	}
}
