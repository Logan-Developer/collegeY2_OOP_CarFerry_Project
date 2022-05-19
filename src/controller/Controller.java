package controller;

import model.Car;
import model.CarFerry;
import model.Truck;
import model.Vehicle;
import utils.ProjectUtils;
import view.EmbarkWindow;
import view.HoldWindow;
import view.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Common controller for all views
public class Controller implements ActionListener {

    private MainWindow mainWindow;
    private EmbarkWindow embarkWindow;
    private HoldWindow holdWindow;
    private CarFerry carFerry;

    public Controller(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.carFerry = new CarFerry(25, 7.5f);
    }

    public void setEmbarkWindow(EmbarkWindow embarkWindow) {
        this.embarkWindow = embarkWindow;
    }

    public void setHoldWindow(HoldWindow holdWindow) {
        this.holdWindow = holdWindow;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        // Main window
        if (this.mainWindow.getMenuItem().equals(source)) {
            this.mainWindow.setHoldWindowVisibility(true);
        }
        else if (this.mainWindow.getEmbarkBtn().equals(source)) {
            this.mainWindow.setEmbarkWindowVisibility(true);
        }

        // Embark window
        else if (this.embarkWindow.getVehicleTypeCarRadioButton().equals(source)) {
            this.embarkWindow.getWeightCargoTruckTextField().setEnabled(false);
            this.embarkWindow.getNbPassengersTextField().setEnabled(true);
        }
        else if (this.embarkWindow.getVehicleTypeTruckRadioButton().equals(source)) {
            this.embarkWindow.getWeightCargoTruckTextField().setEnabled(true);
            this.embarkWindow.getNbPassengersTextField().setEnabled(false);
        }
        else if (this.embarkWindow.getSubmitButton().equals(source)) {
            try {
                this.embarkVehicle();
            } catch (EmbarkException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this.embarkWindow, e.getMessage(), "Embarquement", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Verify the form fields for embarking a vehicle
     */
    public void verifyFieldsEmbarkVehicle() throws EmbarkException {
        String textField = this.embarkWindow.getRegistrationTextField().getText();

        if (textField.isEmpty())
            throw new EmbarkException("Le numéro d'immatriculation est obligatoire");
        if (textField.length() > 20)
            throw new EmbarkException("Le numéro d'immatriculation est trop long");

        textField = this.embarkWindow.getWeightVehicleTextField().getText();

        if (textField.isEmpty())
            throw new EmbarkException("Le poids du véhicule est obligatoire");
        if (!ProjectUtils.isFloat(textField) || Float.parseFloat(textField) < 0) {
            throw new EmbarkException("Le poids du véhicule doit être un décimal >= 0");
        }

        textField = this.embarkWindow.getLengthVehicleTextField().getText();
        if (textField.isEmpty())
            throw new EmbarkException("La longueur du véhicule est obligatoire");
        if (!ProjectUtils.isFloat(textField) || Float.parseFloat(textField) < 0) {
            throw new EmbarkException("La longueur du véhicule doit être un décimal >= 0");
        }

        textField = this.embarkWindow.getDriverLastNameTextField().getText();
        if (textField.isEmpty())
            throw new EmbarkException("Le nom du conducteur est obligatoire");

        textField = this.embarkWindow.getDriverFirstNameTextField().getText();
        if (textField.isEmpty())
            throw new EmbarkException("Le prénom du conducteur est obligatoire");

        textField = this.embarkWindow.getDriverLicenseTextField().getText();
        if (textField.isEmpty())
            throw new EmbarkException("Le numéro de permis de conduire est obligatoire");

        // verify specific fields
        if (this.embarkWindow.getVehicleTypeCarRadioButton().isSelected()) {
            textField = this.embarkWindow.getNbPassengersTextField().getText();
            if (textField.isEmpty())
                throw new EmbarkException("Le nombre de passagers est obligatoire");
            if (!ProjectUtils.isInt(textField) || Integer.parseInt(textField) < 0) {
                throw new EmbarkException("Le nombre de passagers doit être un entier >= 0");
            }
        }
        else if (this.embarkWindow.getVehicleTypeTruckRadioButton().isSelected()) {
            textField = this.embarkWindow.getWeightCargoTruckTextField().getText();
            if (textField.isEmpty())
                throw new EmbarkException("Le poids du camion est obligatoire");
            if (!ProjectUtils.isFloat(textField) || Float.parseFloat(textField) < 0) {
                throw new EmbarkException("Le poids du camion doit être un décimal >= 0");
            }
        }
    }

    /**
     * Adds a vehicle to the car ferry if it is possible.
     * The row is chosen according to the weight of the vehicle, for keeping the balance.
     * @throws EmbarkException If the vehicle can't be added.
     */
    public void embarkVehicle() throws EmbarkException{
        // Verify submitted data
        this.verifyFieldsEmbarkVehicle();

        Vehicle vehicle;
        if (this.embarkWindow.getVehicleTypeCarRadioButton().isSelected()) {
            vehicle = new Car(
                    this.embarkWindow.getRegistrationTextField().getText(),
                    Integer.parseInt(this.embarkWindow.getWeightVehicleTextField().getText()),
                    Integer.parseInt(this.embarkWindow.getLengthVehicleTextField().getText()),
                    Integer.parseInt(this.embarkWindow.getNbPassengersTextField().getText()),
                    this.embarkWindow.getDriverLastNameTextField().getText(),
                    this.embarkWindow.getDriverFirstNameTextField().getText(),
                    this.embarkWindow.getDriverLicenseTextField().getText()
            );
        }
        else {
            vehicle = new Truck(
                    this.embarkWindow.getRegistrationTextField().getText(),
                    Integer.parseInt(this.embarkWindow.getWeightVehicleTextField().getText()),
                    Integer.parseInt(this.embarkWindow.getLengthVehicleTextField().getText()),
                    Integer.parseInt(this.embarkWindow.getWeightCargoTruckTextField().getText()),
                    this.embarkWindow.getDriverLastNameTextField().getText(),
                    this.embarkWindow.getDriverFirstNameTextField().getText(),
                    this.embarkWindow.getDriverLicenseTextField().getText()
            );
        }

        // verify that the vehicle is not already in the car ferry
        if (this.carFerry.isInCarFerry(vehicle)){
            throw new EmbarkException("The vehicle is already in the car ferry.");
        }

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

        // update the HoldWindow
        this.holdWindow.updateLeftRow(this.carFerry.getVehiclesLeft().toArray());
    }
}
