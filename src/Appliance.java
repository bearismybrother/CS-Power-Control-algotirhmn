
public class Appliance {
	private String type;
	private int onWattage;
	private double probability;
	private int location;
	private int ID;
	
	public Appliance(String type, int onWattage, double probability, int location, int iD) {
		super();
		this.type = type;
		this.onWattage = onWattage;
		this.probability = probability;
		this.location = location;
		ID = iD;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getOnWattage() {
		return onWattage;
	}
	public void setOnWattage(int onWattage) {
		this.onWattage = onWattage;
	}
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	

}
