import java.util.Comparator;

/**
 * Created by gurov on 17.11.14.
 */
public class RoomDescription {
    public static final String ROOM_STATUS_ECONOMY = "economy";
    public static final String ROOM_STATUS_STANDART = "standart";
    public static final String ROOM_STATUS_LUXURY = "luxury";

    private int numberOfSeats;
    private String status;

    public RoomDescription(int numberOfSeats, String status) {
        this.numberOfSeats = numberOfSeats;
        this.status = status;
    }

    public RoomDescription(RoomDescription roomDescription) {
        numberOfSeats = roomDescription.getNumberOfSeats();
        status = roomDescription.getStatus();
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int includes(RoomDescription roomDescription) {
        if (roomDescription.getStatus() == status) {
            if (roomDescription.getNumberOfSeats() == numberOfSeats)
                return 0;

            if (roomDescription.getNumberOfSeats() < numberOfSeats)
                return 1;

            return -1;
        }

        return -1;
    }
}
