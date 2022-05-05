package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private JPanel titlePanel, buttonsPanel;
    private JLabel titleLabel;
    private JButton embarkBtn, disembarkBtn;

    public MainWindow() {
        this.setTitle("CAR FERRY");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Add the menu bar
        this.menuBar = new JMenuBar();
        this.menu = new JMenu("Cale du ferry");
        this.menuItem = new JMenuItem("Afficher la cale");
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        this.titlePanel = new JPanel(new FlowLayout());
        this.buttonsPanel = new JPanel(new FlowLayout());

        // Title panel
        this.titleLabel = new JLabel("Nouvelle croisière");
        this.titlePanel.setBackground(Color.GREEN);
        this.titlePanel.add(this.titleLabel);

        // Buttons Panel
        this.embarkBtn = new JButton("Embarquer");
        this.disembarkBtn = new JButton("Débarquer");
        this.buttonsPanel.setBackground(Color.GREEN);
        this.buttonsPanel.add(this.embarkBtn);
        this.buttonsPanel.add(this.disembarkBtn);

        // Add panels to frame
       this.add(this.titlePanel, BorderLayout.NORTH);
       this.add(this.buttonsPanel);

        // Add listeners
        this.setVisible(true);
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
