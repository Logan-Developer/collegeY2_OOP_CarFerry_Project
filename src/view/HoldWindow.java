package view;

import controller.Controller;
import model.Car;
import model.CarFerry;
import model.Vehicle;

import javax.swing.*;
import java.awt.*;

public class HoldWindow extends JFrame {

    private JPanel leftRow, rightRow;
    private JList<Object> leftRowList, rightRowList;

    public HoldWindow(Controller controller, CarFerry carFerry) {
        this.setTitle("Cale du ferry");
        this.setSize(430, 240);
        this.setResizable(false);
        this.setLayout(new GridLayout(1, 2));

        // left row
        this.leftRow = new JPanel(new FlowLayout());
        this.leftRow.setBorder(BorderFactory.createTitledBorder("Rangée gauche"));
        this.leftRow.setBackground(Color.GREEN);
        this.leftRowList = new JList<>(carFerry.getVehiclesLeft().toArray());
        this.leftRowList.setFixedCellWidth(180);
        this.leftRowList.setFixedCellHeight(20);
        this.leftRow.add(new JScrollPane(this.leftRowList));

        // right row
        this.rightRow = new JPanel(new FlowLayout());
        this.rightRow.setBorder(BorderFactory.createTitledBorder("Rangée droite"));
        this.rightRow.setBackground(Color.GREEN);
        this.rightRowList = new JList<>(carFerry.getVehiclesRight().toArray());
        this.rightRowList.setFixedCellWidth(180);
        this.rightRowList.setFixedCellHeight(20);
        this.rightRow.add(new JScrollPane(this.rightRowList));

        // Add panels to frame
        this.add(this.leftRow);
        this.add(this.rightRow);

        // Add action listeners
        this.leftRowList.addListSelectionListener(controller);
        this.rightRowList.addListSelectionListener(controller);

        this.setVisible(true);
    }

    public JList<Object> getLeftRowList() {
        return leftRowList;
    }

    public JList<Object> getRightRowList() {
        return rightRowList;
    }
}
