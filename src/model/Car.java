package model;

/**
 * Vehicle that can carry passengers.
 */
public class Car extends Vehicle {
	private int nbPassengers;
	
	public Car(String registration, float unloadedWeight, float length, int nbPassengers, String lastNameDriver, String firstNameDriver, String driverLicence){
		super(registration, unloadedWeight, length, lastNameDriver, firstNameDriver, driverLicence);
		this.nbPassengers=nbPassengers;
	}

	public int getNbPassengers() {
		return nbPassengers;
	}

	/**
	 * Calculate the weight of the car. (Corresponds to the unloaded weight of the car)
	 * @return the weight of the car
	 */
	public float getTotalWeight(){
		return this.getUnloadedWeight();
	}
	
}
