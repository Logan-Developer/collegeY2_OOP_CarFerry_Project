package controller;

import model.CarFerry;
import model.Vehicle;

/**
 * The controller class for the CarFerry.
 */
public class Controller {
    private CarFerry carFerry;

    public Controller(CarFerry carFerry){
        this.carFerry=carFerry;
    }

    /**
     * Adds a vehicle to the car ferry if it is possible.
     * The row is chosen according to the weight of the vehicle, for keeping the balance.
     * @param vehicle The vehicle to be added.
     */
    public void embarkVehicle(Vehicle vehicle) throws EmbarkException{
        if(this.carFerry.getTotalWeight() + vehicle.getTotalWeight() > this.carFerry.getMaxWeight()) {
            throw new EmbarkException("The weight of the vehicle is too large !");
        }
        if(this.carFerry.getTotalLengthLeft() + vehicle.getLength() >= this.carFerry.getLength() && this.carFerry.getTotalLengthRight() + vehicle.getLength() >= this.carFerry.getLength()){
            throw new EmbarkException("Not enough space in the carFerry !");
        }

        // test if place in only one row
        if(this.carFerry.getTotalLengthLeft() + vehicle.getLength() >= this.carFerry.getLength()){
            this.carFerry.embarkVehicleRight(vehicle);
            return;
        }
       if (this.carFerry.getTotalLengthRight() + vehicle.getLength() >= this.carFerry.getLength()){
            this.carFerry.embarkVehicleLeft(vehicle);
            return;
        }

        // else, decide which row to put the vehicle (the weight must be equilibrated)
        if(this.carFerry.getTotalWeightLeft() <= this.carFerry.getTotalWeightRight()){
            this.carFerry.embarkVehicleLeft(vehicle);
            return;
        }
        this.carFerry.embarkVehicleRight(vehicle);
    }

    /**
     * Removes a vehicle from the car ferry.
     * It's the first vehicle in the row.
     * The row is chosen according to the weight of the vehicle, for keeping the balance.
     */
    public Vehicle debark(){
        if (this.carFerry.isEmpty()){
            return null;
        }
        if(this.carFerry.getFirstVehicleLeft() == null){
            return this.carFerry.disembarkVehicleRight();
        }else if (this.carFerry.getFirstVehicleRight() == null){
            return this.carFerry.disembarkVehicleLeft();
        }
        float rightWeight = this.carFerry.getTotalWeightRight() - this.carFerry.getFirstVehicleRight().getTotalWeight();
        float leftWeight = this.carFerry.getTotalWeightLeft() - this.carFerry.getFirstVehicleLeft().getTotalWeight();

        if(Math.abs(this.carFerry.getTotalWeightRight() - leftWeight) <= Math.abs(this.carFerry.getTotalWeightLeft() - rightWeight)){
            return this.carFerry.disembarkVehicleLeft();
        }
        return this.carFerry.disembarkVehicleRight();
    }
}
