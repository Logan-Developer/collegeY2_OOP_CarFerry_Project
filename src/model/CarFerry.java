package model;

import java.util.ArrayDeque;
import java.util.TreeSet;

/**
 * Represents a car ferry.
 */
public class CarFerry {
	private float length;
	private float maxWeight;
	private RowCarFerry rowRight;
	private RowCarFerry rowLeft;
	private TreeSet<Ticket> listing;
	private int noLeft;
	private int noRight;

	public CarFerry(float length, float maxWeight){
		this.length = length;
		this.maxWeight = maxWeight;
		this.rowRight = new RowCarFerry();
		this.rowLeft = new RowCarFerry();
		this.listing = new TreeSet<>();
		this.noLeft = 0;
		this.noRight =0;
	}

	public float getLength(){
		return this.length;
	}

	public float getMaxWeight(){
		return this.maxWeight;
	}

	/**
	 * Calculates the total weigh of the car ferry. (corresponds to the sum of the weights of the vehicles in the two rows)
	 * @return the total weight of the car ferry
	 */
	public float getTotalWeight(){
		return this.rowRight.getTotalWeight() + this.rowLeft.getTotalWeight();
	}

	public float getTotalLengthLeft(){
		return this.rowLeft.getTotalLength();
	}

	public float getTotalLengthRight(){
		return this.rowRight.getTotalLength();
	}

	public float getTotalWeightLeft(){
		return this.rowLeft.getTotalWeight();
	}

	public float getTotalWeightRight(){
		return this.rowRight.getTotalWeight();
	}

	public Vehicle getFirstVehicleLeft(){
		return this.rowLeft.getFirstVehicle();
	}

	public Vehicle getFirstVehicleRight(){
		return this.rowRight.getFirstVehicle();
	}

	public ArrayDeque<Vehicle> getVehiclesLeft(){
		return this.rowLeft.getVehicles();
	}

	public ArrayDeque<Vehicle> getVehiclesRight(){
		return this.rowRight.getVehicles();
	}

	/**
	 * Adds a vehicle to the left row.
	 * @param vehicle the vehicle to be added
	 */
	public void embarkVehicleLeft(Vehicle vehicle){
		this.rowLeft.addVehicle(vehicle);
		this.noLeft++;
		this.listing.add(new Ticket(vehicle.getFullNameDriver(), 'G', this.noLeft, vehicle));
	}

	/**
	 * Adds a vehicle to the right row.
	 * @param vehicle the vehicle to be added
	 */
	public void embarkVehicleRight(Vehicle vehicle){
		this.rowRight.addVehicle(vehicle);
		this.noRight++;
		this.listing.add(new Ticket(vehicle.getFullNameDriver(), 'D', this.noRight, vehicle));
	}

	/**
	 * Removes a vehicle from the left row. (first in the row)
	 */
	public Vehicle disembarkVehicleLeft(){
		this.noLeft--;
		Vehicle vehicleToDisembark = this.rowLeft.removeVehicle();
		this.listing.remove(getTicketFromVehicle(vehicleToDisembark));
		return vehicleToDisembark;
	}

	/**
	 * Removes a vehicle from the right row. (first in the row)
	 */
	public Vehicle disembarkVehicleRight(){
		this.noRight--;
		Vehicle vehicleToDisembark = this.rowRight.removeVehicle();
		this.listing.remove(getTicketFromVehicle(vehicleToDisembark));
		return vehicleToDisembark;
	}

	/**
	 * Returns the ticket corresponding to the vehicle.
	 * @param vehicle the vehicle
	 * @return the ticket corresponding to the vehicle
	 */
	public Ticket getTicketFromVehicle(Vehicle vehicle){
		for (Ticket t : this.listing){
			if (t.getVehicle().equals(vehicle)){
				return t;
			}
		}
		return null;
	}

	/**
	 * Check if a vehicle is in the car ferry.
	 * @param vehicle the vehicle
	 * @return true if the vehicle is in the car ferry, false otherwise
	 */
	public boolean isInCarFerry(Vehicle vehicle){
		Ticket ticket = getTicketFromVehicle(vehicle);
		return ticket != null;
	}

	/**
	 * Checks if the car ferry is full.
	 * @return true if the car ferry is full, false otherwise
	 */
	public boolean isEmpty() {
		return this.rowLeft.isEmpty() && this.rowRight.isEmpty();
	}

	@Override
	public String toString() {
		StringBuilder string= new StringBuilder("Content of the car ferry: \n");
		string.append("Row_left : ").append(this.rowLeft).append("\n");
		string.append("Row_right : ").append(this.rowRight).append("\n");
		string.append("Listing : \n");
		for(Ticket t : listing){
			string.append(t.toString()).append("\n");
		}
		return string.toString();
	}
}
