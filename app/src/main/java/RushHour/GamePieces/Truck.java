package RushHour.GamePieces;

import javax.swing.ImageIcon;

public class Truck extends Vehicle {
    public Truck(int x, int y, boolean vertical) {
        super(x, y, vertical);
        this.size = 3;
        if (vertical) {
            setIcon(new ImageIcon(getPicResource("truck-gray-64v.png")));
        }
        else {
            setIcon(new ImageIcon(getPicResource("truck-gray-64h.png")));
        }
    }
}
