package model;

/**
 * Represents the driver of a vehicle.
 */
public class Driver {
	private final String lastName;
	private final String firstName;
	private final String driverLicense;
	
	public Driver(String lastName, String firstName, String driverLicense){
		this.lastName = lastName;
		this.firstName = firstName;
		this.driverLicense = driverLicense;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getDriverLicense(){
		return this.driverLicense;
	}
	
	public String toString(){
		return this.lastName + " " + this.firstName;
	}

}
