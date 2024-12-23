package RushHour.GamePieces;

import javax.swing.ImageIcon;

public class Car extends Vehicle {
    public Car(int x, int y, boolean vertical) {
        super(x, y, vertical);
        this.size = 2;
        if (vertical) {
            setIcon(new ImageIcon(getPicResource("car-gray-64v.png")));
        }
        else {
            setIcon(new ImageIcon(getPicResource("car-gray-64h.png")));
        }
    }
}
