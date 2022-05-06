package view;

import javax.swing.*;
import java.awt.*;

public class EmbarkWindow extends JFrame {
    private JPanel vehicleTypePanel, formPanel;
    private JLabel vehicleTypeLabel, registrationLabel, nbPassengersLabel, weightVehicleLabel, lengthVehicleLabel,
            weightCargoTruckLabel, driverLastNameLabel, driverFirstNameLabel, driverLicenseLabel;
    private JTextField registrationTextField, nbPassengersTextField, weightVehicleTextField, lengthVehicleTextField,
                weightCargoTruckTextField, driverLastNameTextField, driverFirstNameTextField, driverLicenseTextField;
    private JRadioButton vehicleTypeCarRadioButton, vehicleTypeTruckRadioButton;

    private ButtonGroup vehicleTypeButtonGroup;
    private JButton submitButton;

    public EmbarkWindow() {
        this.setTitle("CAR FERRY - Embarquement");
        this.setSize(540, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Vehicle type panel
        this.vehicleTypePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.vehicleTypePanel.setBackground(Color.RED);
        this.vehicleTypeLabel = new JLabel("Quel est le type du véhicule ?");

        this.vehicleTypeButtonGroup = new ButtonGroup();
        this.vehicleTypeCarRadioButton = new JRadioButton("Voiture");
        this.vehicleTypeCarRadioButton.setSelected(true);
        this.vehicleTypeTruckRadioButton = new JRadioButton("Camion");
        this.vehicleTypeButtonGroup.add(this.vehicleTypeCarRadioButton);
        this.vehicleTypeButtonGroup.add(this.vehicleTypeTruckRadioButton);

        this.vehicleTypePanel.add(this.vehicleTypeLabel);
        this.vehicleTypePanel.add(this.vehicleTypeCarRadioButton);
        this.vehicleTypePanel.add(this.vehicleTypeTruckRadioButton);

        // Create a form (each field is a label and a text field, and all fields are in column, with a gap between each field)
        // Also, each textField has a different size
        // Show label and text field on same line
        this.formPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.formPanel.setBackground(Color.GREEN);

        this.registrationLabel = new JLabel("Entrez immatriculation du véhicule (20 car maxi) ");
        this.nbPassengersLabel = new JLabel("Entrez le nombre de passagers ");
        this.weightVehicleLabel = new JLabel("                                                                          Entrez le poids du véhicule (en tonnes) ");
        this.lengthVehicleLabel = new JLabel("Entrez la longueur du véhicule (en mètres) ");
        this.weightCargoTruckLabel = new JLabel("Entrez le poids de la cargaison du camion (en tonnes) ");
        this.driverLastNameLabel = new JLabel("Entrez le nom du conducteur ");
        this.driverFirstNameLabel = new JLabel("Entrez le prénom du conducteur ");
        this.driverLicenseLabel = new JLabel("Entrez le numéro de permis de conduire ");

        this.registrationTextField = new JTextField(20);
        this.nbPassengersTextField = new JTextField(2);
        this.weightVehicleTextField = new JTextField(4);
        this.lengthVehicleTextField = new JTextField(4);
        this.weightCargoTruckTextField = new JTextField(4);
        this.driverLastNameTextField = new JTextField(20);
        this.driverFirstNameTextField = new JTextField(20);
        this.driverLicenseTextField = new JTextField(20);

        this.formPanel.add(this.registrationLabel);
        this.formPanel.add(this.registrationTextField);
        this.formPanel.add(Box.createVerticalStrut(30));
        this.formPanel.add(this.nbPassengersLabel);
        this.formPanel.add(this.nbPassengersTextField);
        this.formPanel.add(Box.createVerticalStrut(30));
        this.formPanel.add(this.weightVehicleLabel);
        this.formPanel.add(this.weightVehicleTextField);
        this.formPanel.add(Box.createVerticalStrut(30));
        this.formPanel.add(this.lengthVehicleLabel);
        this.formPanel.add(this.lengthVehicleTextField);
        this.formPanel.add(Box.createVerticalStrut(30));
        this.formPanel.add(this.weightCargoTruckLabel);
        this.formPanel.add(this.weightCargoTruckTextField);
        this.formPanel.add(Box.createVerticalStrut(30));
        this.formPanel.add(this.driverLastNameLabel);
        this.formPanel.add(this.driverLastNameTextField);
        this.formPanel.add(Box.createVerticalStrut(30));
        this.formPanel.add(this.driverFirstNameLabel);
        this.formPanel.add(this.driverFirstNameTextField);
        this.formPanel.add(Box.createVerticalStrut(30));
        this.formPanel.add(this.driverLicenseLabel);
        this.formPanel.add(this.driverLicenseTextField);
        this.formPanel.add(Box.createVerticalStrut(30));

        // Create a button to submit the form
        this.submitButton = new JButton("Valider");

        // Add panels to frame
        this.add(this.vehicleTypePanel, BorderLayout.NORTH);
        this.add(this.formPanel, BorderLayout.CENTER);
        this.add(this.submitButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
