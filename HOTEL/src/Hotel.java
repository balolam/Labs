/**
 * Created by gurov on 17.11.14.
 */
public class Hotel {
    private String name;
    private HotelQueue hotelQueue;
    private RoomManager roomManager;

    public void inWaitingRoom(Group group) {
        System.out.println("В зал ожидания пришла группа: " + group.getName());
        hotelQueue.add(group);
    }

    public Hotel(String name) {
        this.name = name;

        hotelQueue = new HotelQueue(new HotelQueueListener() {
            @Override
            public boolean onSettle(Order order) {
                return settle(order);
            }

            @Override
            public boolean onMoveOut(Order order) {
                moveOut(order);
                return false;
            }
        });
    }

    private synchronized boolean settle(Order order) {
        RoomDescription roomDescription =
                roomManager.getSuitableRoom(order.getRoomDescription());
        System.out.println("Ппопытка заселиться у " + order.getGroup().getName());
        if (roomDescription == null)
            return false;

        roomManager.delete(roomDescription);
        System.out.println("В отель поселилась: " + order.getGroup().getName());
        System.out.println("Врем пребывания: " + order.getGroup().getResidenceTime() + " миллисекунд.");
        roomManager.printInfo();
        return true;
    }

    private synchronized void moveOut(Order order) {
        roomManager.add(order.getRoomDescription());
        System.out.println("Выселение: " + order.getGroup().getName());

        roomManager.printInfo();
    }

    public void createRoomManager(RoomManager.Builder builder) {
        roomManager = new RoomManager(builder);
        roomManager.printInfo();
    }
}
