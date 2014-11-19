import java.util.*;

/**
 * Created by gurov on 17.11.14.
 */
public class Main {
    private static final int numberGroupOfPeople = 20;

    public static void main(String[] args) {
        final Hotel hotel = new Hotel(CONST.HOTEL_NAME);

        RoomManager.Builder builder = new RoomManager.Builder();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);
        list.add(2);
        builder.putRoomsList(
                RoomDescription.ROOM_STATUS_ECONOMY,
                list);
        builder.putRoomsList(
                RoomDescription.ROOM_STATUS_STANDART,
                list);
        builder.putRoomsList(
                RoomDescription.ROOM_STATUS_LUXURY,
                list);

        hotel.createRoomManager(builder);

        Thread theFlowOfPeople = new Thread(new Runnable() {
            private final Random random = new Random();

            @Override
            public void run() {

                for (int i = 0; i < numberGroupOfPeople; ++i) {
                    Group group = new Group("group_" + Integer.toString(i));

                    group.setResidenceTime(random.nextInt(500));
                    group.setRoomDescription(
                            new RoomDescription((
                                    random.nextInt(3) + 1),
                                    RoomDescription.ROOM_STATUS_ECONOMY));

                    hotel.inWaitingRoom(group);
                    expectation(10);
                }
            }

            private void expectation(long mSeconds) {
                synchronized (this) {
                    try {
                        this.wait(mSeconds);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        theFlowOfPeople.start();
    }
}
