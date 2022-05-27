package model;

public class Ticket implements Comparable<Ticket> {
    private String driverFullName;
    private char row;
    private int positionInRow;
    private float price;
    private Vehicle vehicle;

    public Ticket(String driverFullName, char row, int positionInRow, Vehicle vehicle) {
        this.driverFullName = driverFullName;
        this.row = row;
        this.positionInRow = positionInRow;
        this.vehicle = vehicle;
        if(vehicle instanceof Car) {
            this.price= 35 + 3*((Car) vehicle).getNbPassengers();
        }else{
            this.price = 45 + 0.1f * 1000 * ((Truck) vehicle).getCargoWeight();
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public int compareTo(Ticket ticket) {
        return this.driverFullName.compareTo(ticket.driverFullName);
    }

    @Override
    public String toString() {
        return "["+this.row+this.positionInRow+" "+this.driverFullName+" : "+ this.vehicle.getRegistration()+" : "+ this.price+"euros ]";
    }
}
