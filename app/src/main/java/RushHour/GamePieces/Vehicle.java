package RushHour.GamePieces;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import RushHour.RushHourGame;

public class Vehicle extends JButton implements MouseListener {
    final public static int boxPixels = 64;
    boolean vertical;
    int size;
    public int fX, fY; // f stands for "field". JButton already has x and y

    public Vehicle(int x, int y, boolean vertical) {
        this.vertical = vertical;
        this.fX = x;
        this.fY = y;
    }

    protected String getPicResource(String name) {
        var picURI = this.getClass().getClassLoader().getResource("pic");
        return picURI.getPath().concat("/").concat(name);
    }

    public boolean moveForward(Field field, int steps) {
        if (vertical) {
            if (field.outside(fX, fY + size + steps - 1)) return false;
            for (int i = 0; i < steps; i++) {
                var someone = field.getVehicle(fX, fY + size + i);
                if (someone != null) return false;
            }
            fY += steps;
        }
        else {
            if (field.outside(fX + size + steps - 1, fY)) {
                if (field.isGateLine(fY)) {
                    field.setWin(true);
                }
                return false;
            }
            for (int i = 0; i < steps; i++) {
                var someone = field.getVehicle(fX + size + i, fY);
                if (someone != null) return false;
            }
            fX += steps;
        }
        updatePosition();
        return true;
    }

    public boolean moveBackward(Field field, int steps) {
        if (vertical) {
            if (field.outside(fX, fY - steps)) return false;
            for (int i = 1; i <= steps; i++) {
                var someone = field.getVehicle(fX, fY - i);
                if (someone != null) return false;
            }
            fY -= steps;
        }
        else {
            if (field.outside(fX - steps, fY)) return false;
            for (int i = 1; i <= steps; i++) {
                var someone = field.getVehicle(fX - i, fY);
                if (someone != null) return false;
            }
            fX -= steps;
        }
        updatePosition();
        return true;
    }

    boolean conflicts(Vehicle other) {
        for (var myCell : getOccupiedCells()) {
            for (var otherCell : other.getOccupiedCells()) {
                if (myCell.equals(otherCell)) return true;
            }
        }
        return false;
    }

    boolean occupies(int col, int row) {
        if (vertical) {
            return col == fX && row >= fY && fY + size > row;
        }
        else {
            return row == fY && col >= fX && fX + size > col;
        }
    }

    public Set<Cell> getOccupiedCells() {
        Set<Cell> cells = new HashSet<Cell>();
        for (int i=0; i<size; i++) {
            if (vertical) cells.add(new Cell(fX, fY+i));
            else cells.add(new Cell(fX+i, fY));
        }
        return cells;
    }

    public void updatePosition() {
        if (vertical) {
            setBounds(
                fX * 64, //Const.boxPixels,
                fY * boxPixels,
                boxPixels,
                size * boxPixels);
        }
        else {
            setBounds(
                fX * boxPixels,
                fY * boxPixels,
                size * boxPixels,
                boxPixels);
        }
    }

    /**
     * Move the gamepiece one step toward the mouse click
     * @param e MouseEvent
     */
    public void mouseClicked(MouseEvent e) {
        if ((vertical ? e.getY() : e.getX()) > size * boxPixels / 2) {
            moveForward(RushHourGame.getField(), 1);
        }
        else {
            moveBackward(RushHourGame.getField(), 1);
        }
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
