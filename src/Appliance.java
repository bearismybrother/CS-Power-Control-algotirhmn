import java.util.Random; 

public class Appliance {
	private int location;
	private String description;
	private int onWattage;
	private double probability;
	private boolean isSmart;
	private double powerReduction;
	private String currentMode = "off"; //can be off, on or low
	


	public Appliance(int location, String description, int onWattage, double probability, boolean isSmart, double powerReduction) {
	    this.location = location;
	    this.description = description;
	    this.onWattage = onWattage;
	    this.probability = probability;
	    this.isSmart = isSmart;
	    this.powerReduction = powerReduction;
	    
	    RandomizeCurrentMode(); 
	}
	
	public double getCurrentWattageUsage()
	{
		if (currentMode == "off")
			return 0;
		else  //on
		{
			if (currentMode == "low")
				return onWattage * (1-powerReduction);
			else 
				return (double)onWattage;  //regular
		}
	}
	
	public void RandomizeCurrentMode() {
	    if (rollGenerator() <= probability)
	    {
	    	currentMode = "on";
	    }
	}
	
	 private double rollGenerator() {
	        Random random = new Random();
	        return random.nextDouble();
	 }
	
    // Getter methods
    public int getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public int getOnWattage() {
        return onWattage;
    }

    public double getProbability() {
        return probability;
    }

    public boolean isSmart() {
        return isSmart;
    }

    public double getPowerReduction() {
        return powerReduction;
    }

    // Setter methods
    public void setLocation(int location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOnWattage(int onWattage) {
        this.onWattage = onWattage;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public void setSmart(boolean isSmart) {
        this.isSmart = isSmart;
    }

    public void setPowerReduction(double powerReduction) {
        this.powerReduction = powerReduction;
    }
	
	public String getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(String currentMode) {
		this.currentMode = currentMode;
	}

	@Override
	public String toString() {
		return "[" + description + ":" + " onWattage = " + onWattage
				+ ", probability = " + probability + ", isSmart = " + isSmart + ", powerReduction = " + powerReduction
				+ ", currentMode = " + currentMode + "]\n";
	}
}
