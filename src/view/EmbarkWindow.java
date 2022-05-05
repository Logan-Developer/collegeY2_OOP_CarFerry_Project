package view;

import javax.swing.*;
import java.awt.*;

public class EmbarkWindow extends JFrame {
    private JPanel vehicleTypePanel, formPanel;
    private JLabel vehicleTypeLabel, registrationLabel, nbPassengersLabel, weightVehicleLabel, lengthVehicleLabel,
            weightCargoTruckLabel, driverLastNameLabel, driverFirstNameLabel, driverLicenseLabel;
    private JTextField vehicleTypeTextField, registrationTextField, nbPassengersTextField, weightVehicleTextField, lengthVehicleTextField,
                weightCargoTruckTextField, driverLastNameTextField, driverFirstNameTextField, driverLicenseTextField;
    private JRadioButton vehicleTypeCarRadioButton, vehicleTypeTruckRadioButton;

    private ButtonGroup vehicleTypeButtonGroup;

    public EmbarkWindow() {
        this.setTitle("CAR FERRY - Embarquement");
        this.setSize(600, 800);
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

        // Form panel
        this.formPanel = new JPanel(new GridBagLayout());
        this.formPanel.setBackground(Color.GREEN);

        this.registrationLabel = new JLabel("Entrez immatriculation du véhicule (20 car maxi)");
        this.registrationTextField = new JTextField(20);
        this.nbPassengersLabel = new JLabel("Entrez le nombre de passagers");
        this.nbPassengersTextField = new JTextField(3);
        this.weightVehicleLabel = new JLabel("Entrez le poids du véhicule (en tonnes)");
        this.weightVehicleTextField = new JTextField(5);
        this.lengthVehicleLabel = new JLabel("Entrez la longueur du véhicule (en mètres)");
        this.lengthVehicleTextField = new JTextField(5);
        this.weightCargoTruckLabel = new JLabel("Entrez le poids de la cargaison du camion (en tonnes)");
        this.weightCargoTruckTextField = new JTextField(5);
        this.driverLastNameLabel = new JLabel("Entrez le nom du conducteur )");
        this.driverLastNameTextField = new JTextField(20);
        this.driverFirstNameLabel = new JLabel("Entrez le prénom du conducteur )");
        this.driverFirstNameTextField = new JTextField(20);
        this.driverLicenseLabel = new JLabel("Entrez le numéro de permis de conduire )");
        this.driverLicenseTextField = new JTextField(20);

        this.formPanel.add(this.registrationLabel);
        this.formPanel.add(this.registrationTextField);
        this.formPanel.add(this.nbPassengersLabel);
        this.formPanel.add(this.nbPassengersTextField);
        this.formPanel.add(this.weightVehicleLabel);
        this.formPanel.add(this.weightVehicleTextField);
        this.formPanel.add(this.lengthVehicleLabel);
        this.formPanel.add(this.lengthVehicleTextField);
        this.formPanel.add(this.weightCargoTruckLabel);
        this.formPanel.add(this.weightCargoTruckTextField);
        this.formPanel.add(this.driverLastNameLabel);
        this.formPanel.add(this.driverLastNameTextField);
        this.formPanel.add(this.driverFirstNameLabel);
        this.formPanel.add(this.driverFirstNameTextField);
        this.formPanel.add(this.driverLicenseLabel);
        this.formPanel.add(this.driverLicenseTextField);

        // Add panels to frame
        this.add(this.vehicleTypePanel, BorderLayout.NORTH);
        this.add(this.formPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}