package RushHour.GamePieces;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Field {
    final public static int gridX = 6;
    final public static int gridY = 6;
    @Setter @Getter private boolean win = false;
    private Set<Vehicle> vehicles = new HashSet<>();

    public boolean isGateLine(int fY) {
        return fY == 2;
    }

    public boolean add(Vehicle newPiece) {
        for (var cell : newPiece.getOccupiedCells()) {
            if (outside(cell.getX(), cell.getY())) return false;
        }
        for (Vehicle existing : vehicles) {
            if (newPiece.conflicts(existing)) return false;
        }
        vehicles.add(newPiece);
        return true;
    }

    public boolean outside(int x, int y) {
        if (x < 0) return true;
        if (y < 0) return true;
        if (x >= gridX) return true;
        if (y >= gridY) return true;
        return false;
    }

    public Vehicle getVehicle(int x, int y) {
        for (var vehicle : vehicles) {
            if (vehicle.occupies(x, y)) return vehicle;
        }
        return null;
    }

    public Vehicle getVehicle(int x, int y, Vehicle except) {
        var someone = getVehicle(x, y);
        if (someone != null && someone != except) return someone;
        else return null;
    }
}
