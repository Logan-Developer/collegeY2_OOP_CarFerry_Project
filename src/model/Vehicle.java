package model;

/**
 * Represents a generic vehicle.
 */
public abstract class Vehicle {
	private final String registration;
	private final float unloadedWeight;
	private final float length;
	private Driver driver;
	
	public Vehicle(String registration, float unloadedWeight, float length, String lastNameDriver, String firstNameDriver, String driverLicence){
		this.registration = registration;
		this.unloadedWeight =unloadedWeight;
		this.length = length;
		this.driver = new Driver(lastNameDriver, firstNameDriver, driverLicence);
	}
	
	public abstract float getTotalWeight();

	public float getLength() {
		return length;
	}

	public float getUnloadedWeight() {
		return unloadedWeight;
	}

	public String getRegistration() {
		return registration;
	}
	
	public String getFullNameDriver(){
		return this.driver.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Vehicle)) {
			return false;
		}
		Vehicle v = (Vehicle) o;
		return this.registration.equals(v.registration);
	}

	@Override
	public String toString() {
		return this.driver.getLastName()+" "+this.driver.getFirstName()+" "+this.registration;
	}
}

