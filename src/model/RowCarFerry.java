package model;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Represents a row of a ferry.
 * The first vehicle added to the row is the first to be removed.
 */
public class RowCarFerry {
	private Deque<Vehicle> vehicles;

	public RowCarFerry(){
		this.vehicles = new LinkedList<Vehicle>();
	}

	/**
	 * get the first vehicle in the row
	 * @return the first vehicle in the row
	 */
	public Vehicle getFirstVehicle(){
		return this.vehicles.peekLast();
	}

	/**
	 * Adds a vehicle to the row.
	 * @param vehicle the vehicle to be added
	 */
	public void addVehicle(Vehicle vehicle){
		this.vehicles.addFirst(vehicle);
	}

	/**
	 * Removes the first vehicle in the row.
	 * @return the removed vehicle
	 */
	public Vehicle removeVehicle(){
		return this.vehicles.removeLast();
	}

	/**
	 * Calculates the total weight of the row.
	 * It is the sum of the weights of all vehicles in the row.
	 * @return the total weight of the row
	 */
	public float getTotalWeight() {
		float totalWeight = 0;
		for (Vehicle vehicle : this.vehicles){
			totalWeight += vehicle.getTotalWeight();
		}
		return totalWeight;
	}

	/**
	 * Calculates the total volume of the row.
	 * It is the sum of the volumes of all vehicles in the row, with a space of 0.5m between each vehicle.
	 * @return the total length of the row
	 */
	public float getTotalLength() {
		float totalLength = 0;
		for (Vehicle vehicle : this.vehicles){
			totalLength += vehicle.getLength() + 0.5f; // 0.5f is the distance between two vehicles
		}
		return totalLength;
	}

	/**
	 * Checks if the row is empty.
	 * @return true if the row is empty, false otherwise
	 */
	public boolean isEmpty(){
		return this.vehicles.isEmpty();
	}

	@Override
	public String toString() {
		return this.vehicles.toString();
	}
}
