import controller.Controller;
import controller.EmbarkException;
import model.Car;
import model.Truck;
import model.Vehicle;
import view.MainWindow;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        Controller controller = mainWindow.getController();

        // List of the vehicles to embark on the ferry
        List<Vehicle> toEmbark = new ArrayList<>();
        toEmbark.add(new Truck("AZ 678 DF", 4, 12, 15, "Grant", "Philip", "20FF"));
        toEmbark.add(new Car("RM 1054 FF", 1.2f, 4.2f, 2, "Martin", "Jeanne", "22FF"));
        toEmbark.add(new Car("PO 377 A4", 1.4f, 4.5f, 1, "Dupont", "Vincent", "A55"));
        toEmbark.add(new Truck("QS 543 HJ", 5.2f, 13.5f, 22.5f, "Scott", "Simon", "B55JG"));
        toEmbark.add(new Car("WX 456 RT", 1.2f, 5.3f, 0, "Durand", "Marie", "B34"));
        toEmbark.add(new Truck("BN 321 XC", 4.5f, 15, 18, "Lambert", "Alain", "C44Djk"));
        for(Vehicle v : toEmbark){
            try{
                controller.embarkVehicle(v);
            }catch (EmbarkException e) {
                System.out.printf(e.getMessage());
            }
        }

    }
}
