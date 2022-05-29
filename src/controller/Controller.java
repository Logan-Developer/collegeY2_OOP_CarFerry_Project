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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Common controller for all views
public class Controller implements ActionListener, ListSelectionListener {

    private MainWindow mainWindow;

    private HoldWindow holdWindow;

    private EmbarkWindow embarkWindow;
    private CarFerry carFerry;

    public Controller(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.carFerry = new CarFerry(25, 7.5f);
    }

    /**
     * Listener used for click on components
     * @param actionEvent The event triggered
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        // Main window
        if (this.mainWindow.getMenuItem().equals(source)) {
            if (this.holdWindow != null) {
                this.holdWindow.setVisible(false);
                this.holdWindow.dispose();
            }
            this.holdWindow = new HoldWindow(this, this.carFerry);
        } else if (this.mainWindow.getEmbarkBtn().equals(source)) {
            if (this.holdWindow != null) {
                this.holdWindow.setVisible(false);
                this.holdWindow.dispose();
            }
            if (this.embarkWindow != null) {
                this.embarkWindow.setVisible(false);
                this.embarkWindow.dispose();
            }
            this.embarkWindow = new EmbarkWindow(this);
        } else if (this.mainWindow.getDisembarkBtn().equals(source)) {
            if (this.holdWindow != null) {
                this.holdWindow.setVisible(false);
                this.holdWindow.dispose();
            }
            Vehicle disembarkedVehicle = disembarkVehicle();
            if (disembarkedVehicle == null) {
                displayDialog(this.mainWindow, "Embaquement", "La cale est vide", JOptionPane.WARNING_MESSAGE);
            }else{
                displayDialog(this.mainWindow, "Embaquement", "Débarquement "+disembarkedVehicle.getRegistration(), JOptionPane.INFORMATION_MESSAGE);
            }
        }

        // Embark window
        else if (this.embarkWindow.getVehicleTypeCarRadioButton().equals(source)) {
            this.embarkWindow.getWeightCargoTruckTextField().setEnabled(false);
            this.embarkWindow.getNbPassengersTextField().setEnabled(true);
        } else if (this.embarkWindow.getVehicleTypeTruckRadioButton().equals(source)) {
            this.embarkWindow.getWeightCargoTruckTextField().setEnabled(true);
            this.embarkWindow.getNbPassengersTextField().setEnabled(false);
        } else if (this.embarkWindow.getSubmitButton().equals(source)) {
            try {
                this.embarkVehicle();
            } catch (EmbarkException | NumberFormatException e) {
                displayDialog(this.embarkWindow, "Embarquement", e.getMessage(), JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Listener used for selecting item in a JList
     * @param listSelectionEvent The event triggered
     */
    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (!listSelectionEvent.getValueIsAdjusting()) {//This line prevents double events
            Object source = listSelectionEvent.getSource();
            if (this.holdWindow.getLeftRowList().equals(source) || this.holdWindow.getRightRowList().equals(source)) {
                if (source instanceof JList) {
                    JList<Vehicle> jList = (JList<Vehicle>) source;
                    if(!jList.isSelectionEmpty()) {
                        System.out.println(this.carFerry.getTicketFromVehicle(jList.getSelectedValue()));
                        displayDialog(this.holdWindow, "Ticket", this.carFerry.getTicketFromVehicle(jList.getSelectedValue()).toString(), JOptionPane.INFORMATION_MESSAGE);
                        jList.clearSelection();
                    }
                }
            }
        }
    }

    /**
     * Open a dialog on top of a JFrame
     * @param view          The JFrame in which the dialog is displayed
     * @param title         The title of the dialog
     * @param message       The message of the dialog
     * @param dialogType    The type of the dialog (ex: INFORMATION_MESSAGE, WARNING_MESSAGE)
     */
    public void displayDialog(JFrame view, String title, String message, int dialogType) {
        JOptionPane.showMessageDialog(view, message, title, dialogType);
    }

    /**
     * Verify the form fields for embarking a vehicle
     * @throws EmbarkException If the vehicle can't be embarked
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
        } else if (this.embarkWindow.getVehicleTypeTruckRadioButton().isSelected()) {
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
     * @param vehicle The vehicle to be added.
     * @throws EmbarkException If the vehicle can't be embarked.
     */
    public void embarkVehicle(Vehicle vehicle) throws EmbarkException{
        // verify that the vehicle is not already in the car ferry
        if (this.carFerry.isInCarFerry(vehicle)) {
            throw new EmbarkException("The vehicle is already in the car ferry.");
        }

        if (this.carFerry.getTotalWeight() + vehicle.getTotalWeight() > this.carFerry.getMaxWeight()) {
            throw new EmbarkException("The weight of the vehicle is too large !");
        }
        if (this.carFerry.getTotalLengthLeft() + vehicle.getLength() >= this.carFerry.getLength() && this.carFerry.getTotalLengthRight() + vehicle.getLength() >= this.carFerry.getLength()) {
            throw new EmbarkException("Not enough space in the carFerry !");
        }

        // test if place in only one row
        if (this.carFerry.getTotalLengthLeft() + vehicle.getLength() >= this.carFerry.getLength()) {
            this.carFerry.embarkVehicleRight(vehicle);
        } else if (this.carFerry.getTotalLengthRight() + vehicle.getLength() >= this.carFerry.getLength()) {
            this.carFerry.embarkVehicleLeft(vehicle);
        }
        // else, decide which row to put the vehicle (the weight must be equilibrated)
        else if (this.carFerry.getTotalWeightLeft() <= this.carFerry.getTotalWeightRight()) {
            this.carFerry.embarkVehicleLeft(vehicle);
        } else {
            this.carFerry.embarkVehicleRight(vehicle);
        }
        if (this.embarkWindow != null) {
            displayDialog(this.embarkWindow, "Embarquement", "Embarquement réussi", JOptionPane.INFORMATION_MESSAGE);
            this.embarkWindow.setVisible(false);
            this.embarkWindow.dispose();
        }
    }

    /**
     * Adds a vehicle to the car ferry if it is possible.
     * The vehicle is created from the values set on the fields of the embarkation form
     * The row is chosen according to the weight of the vehicle, for keeping the balance.
     *
     * @throws EmbarkException If the vehicle can't be embarked.
     */
    public void embarkVehicle() throws EmbarkException {
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
        } else {
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
        embarkVehicle(vehicle);
    }

    /**
     * Removes a vehicle from the car ferry.
     * It's the first vehicle in the row.
     * The row is chosen according to the weight of the vehicle, for keeping the balance.
     *
     * @return The vehicle that has been removed.
     */
    public Vehicle disembarkVehicle() {
        if (this.carFerry.isEmpty()) {
            return null;
        }
        if (this.carFerry.getFirstVehicleLeft() == null) {
            return this.carFerry.disembarkVehicleRight();
        } else if (this.carFerry.getFirstVehicleRight() == null) {
            return this.carFerry.disembarkVehicleLeft();
        }
        float rightWeight = this.carFerry.getTotalWeightRight() - this.carFerry.getFirstVehicleRight().getTotalWeight();
        float leftWeight = this.carFerry.getTotalWeightLeft() - this.carFerry.getFirstVehicleLeft().getTotalWeight();

        if (Math.abs(this.carFerry.getTotalWeightRight() - leftWeight) <= Math.abs(this.carFerry.getTotalWeightLeft() - rightWeight)) {
            return this.carFerry.disembarkVehicleLeft();
        }
        return this.carFerry.disembarkVehicleRight();
    }
}

