import java.util.*;

/**
 * Created by gurov on 19.11.14.
 */
public class RoomManager {
    private List<RoomDescription> roomStList;
    private List<RoomDescription> roomEcList;
    private List<RoomDescription> roomLuList;

    public RoomManager(Builder builder) {
        roomStList = new ArrayList<RoomDescription>(builder.getNumberOfRoomsSt());
        roomEcList = new ArrayList<RoomDescription>(builder.getNumberOfRoomsEc());
        roomLuList = new ArrayList<RoomDescription>(builder.getNumberOfRoomsLu());

        fillRoomDescription(roomEcList, builder.getNumberOfRoomsEc(), RoomDescription.ROOM_STATUS_ECONOMY);
        fillRoomDescription(roomStList, builder.getNumberOfRoomsSt(), RoomDescription.ROOM_STATUS_STANDART);
        fillRoomDescription(roomLuList, builder.getNumberOfRoomsLu(), RoomDescription.ROOM_STATUS_LUXURY);
    }

    private void fillRoomDescription(List list, int numberOfRooms, String roomStatus) {
        Random random = new Random();

        for (int i = 0 ; i < numberOfRooms; ++i) {
            RoomDescription roomDescription =
                    new RoomDescription(random.nextInt(3) + 1, roomStatus);

            list.add(roomDescription);
        }
    }

    public void printInfo() {
        System.out.println("----------Информация----------");
        System.out.println("Комнаты люкс    : " + roomLuList.size());
        System.out.println("Комнаты стандарт: " + roomStList.size());
        System.out.println("Комнаты эконом  : " + roomEcList.size());
        System.out.println("------------------------------");
    }

    public RoomDescription getSuitableRoom(RoomDescription roomDescription) {
        if (roomDescription.getStatus().equals(RoomDescription.ROOM_STATUS_ECONOMY)) {
            return getter(roomDescription, roomEcList);
        }
        else if (roomDescription.getStatus().equals(RoomDescription.ROOM_STATUS_LUXURY)) {
            return getter(roomDescription, roomLuList);
        }
        else if (roomDescription.getStatus().equals(RoomDescription.ROOM_STATUS_STANDART)) {
            return getter(roomDescription, roomStList);
        }

        return null;
    }

    private RoomDescription getter(RoomDescription roomDescription, List<RoomDescription> list) {
        RoomDescription description = null;

        for (int i = 0; i < list.size(); ++i) {
            if (!list.get(i).getStatus().equals(roomDescription.getStatus()))
                break;

            if (list.get(i).includes(roomDescription) == 0) {
                description = list.get(i);

                break;
            } else if (list.get(i).includes(roomDescription) == 1) {
                description = list.get(i);
            }
        }

        return description;
    }

    public boolean delete(RoomDescription roomDescription) {
        boolean result = false;

        result = ((ArrayList<RoomDescription>) roomEcList).remove(roomDescription);
        if (result == true) return true;
        result = ((ArrayList<RoomDescription>) roomStList).remove(roomDescription);
        if (result == true) return true;
        result = ((ArrayList<RoomDescription>) roomLuList).remove(roomDescription);

        return result;
    }

    public void add(RoomDescription roomDescription) {
        if (roomDescription.getStatus().equals(RoomDescription.ROOM_STATUS_ECONOMY))
            roomEcList.add(roomDescription);
        else if (roomDescription.getStatus().equals(RoomDescription.ROOM_STATUS_LUXURY))
            roomLuList.add(roomDescription);
        else if (roomDescription.getStatus().equals(RoomDescription.ROOM_STATUS_STANDART))
            roomStList.add(roomDescription);
    }

    public static final class Builder {
        private Map<String, List<Integer>> rooms =
                new HashMap<String, List<Integer>>();

        private int numberOfRoomsSt = 0;
        private int numberOfRoomsEc = 0;
        private int numberOfRoomsLu = 0;

        public void putRoomsList(String status, List roomsList) {
            if (status == RoomDescription.ROOM_STATUS_ECONOMY)
                numberOfRoomsEc = roomsList.size();
            else
            if (status == RoomDescription.ROOM_STATUS_STANDART)
                numberOfRoomsSt = roomsList.size();
            else
            if (status == RoomDescription.ROOM_STATUS_LUXURY)
                numberOfRoomsLu = roomsList.size();
            else return;

            rooms.put(status, roomsList);
        }

        public List<Integer> getRoomsList(String roomStatus) {
            return ((HashMap<String, List<Integer>>) rooms).get(roomStatus);
        }

        public int getNumberOfRoomsSt() {
            return numberOfRoomsSt;
        }

        public int getNumberOfRoomsEc() {
            return numberOfRoomsEc;
        }

        public int getNumberOfRoomsLu() {
            return numberOfRoomsLu;
        }
    }
}
