package RushHour;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import RushHour.GamePieces.Car;
import RushHour.GamePieces.Field;
import RushHour.GamePieces.Truck;
import RushHour.GamePieces.Vehicle;

public class RushHourGame {
    final public static int pagePadding = 80;

    static Field displayField = new Field();
    public static Field getField() {return displayField;}

    Vehicle[] generateGamePieces() {
        return new Vehicle[] {
            new Car(0, 0, true),
            new Car(1, 2, false),
            new Truck(5, 1, true),
            new Truck(2, 3, false) };
    }

    RushHourGame(JPanel panel) {
        for (var b : generateGamePieces()) {
            if (displayField.add(b)) {
                b.addMouseListener(b);
                b.updatePosition();
                panel.add(b);
            }
        }
    }

    boolean play(JPanel panel) {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (displayField.getVehicle(5, 1).moveForward(displayField, 2)) {
            System.out.println("Game Piece has moved successfully");
        }
        var redCar = displayField.getVehicle(1,2);
        redCar.moveForward(displayField, 3);
        redCar.moveForward(displayField, 1);
        System.out.println("Now move pieces with your mouse");
        while (!displayField.isWin()) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        panel.setBackground(Color.green);
        System.out.println("You won!!!");
        return true;
    }

    public static void main(String args[]) {
        var panel = new JPanel();
        panel.setBounds(
            pagePadding, pagePadding,
            Vehicle.boxPixels*Field.gridX,
            Vehicle.boxPixels*Field.gridY);
        panel.setLayout(null);
        panel.setBackground(Color.black);
        panel.setVisible(true);

        var game = new RushHourGame(panel);

        var frame = new JFrame("Rush Hour game");
        frame.setBackground(Color.black);
        frame.setLayout(null);
        frame.setSize(
            2*pagePadding + Vehicle.boxPixels*Field.gridX,
            2*pagePadding + Vehicle.boxPixels*Field.gridY);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        game.play(panel);
    }
}