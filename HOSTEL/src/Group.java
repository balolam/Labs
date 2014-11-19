/**
 * Created by gurov on 17.11.14.
 */
public class Group {
    private String name;
    private int residenceTime;
    private RoomDescription roomDescription;

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getResidenceTime() {
        return residenceTime;
    }

    public void setResidenceTime(int residenceTime) {
        this.residenceTime = residenceTime;
    }

    public RoomDescription getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(RoomDescription roomDescription) {
        this.roomDescription = roomDescription;
    }

    public void printDescription() {
        System.out.println("фамилия: " + name);
        System.out.println("время пребывания: " + residenceTime);
        System.out.println("предпочтение: " + roomDescription.getStatus());
    }
}