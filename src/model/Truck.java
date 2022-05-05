package model;

/**
 * Vehicle that can transport cargo.
 */
public class Truck extends Vehicle {
	private float cargoWeight;
	
	public Truck(String registration, float unloadedWeight, float length, float cargoWeight, String lastNameDriver, String firstNameDriver, String driverLicence){
		super(registration, unloadedWeight, length, lastNameDriver, firstNameDriver, driverLicence);
		this.cargoWeight = cargoWeight;
	}
	
	public float getCargoWeight(){
		return this.cargoWeight;
	}

	/**
	 * Calculates the weight of the cargo. (corresponds to the unloaded weight of the truck + the weight of the cargo)
	 * @return the weight of the truck
	 */
	public float getTotalWeight(){
		return this.getUnloadedWeight() + this.cargoWeight;
	}
}
