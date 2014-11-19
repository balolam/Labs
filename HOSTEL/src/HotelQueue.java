import java.util.ArrayList;
import java.util.List;

/**
 * Created by gurov on 18.11.14.
 */
public class HotelQueue {
    private List<Order> orders = new ArrayList<Order>();
    private HotelQueueListener hotelQueueListener;

    public HotelQueue(HotelQueueListener hotelQueueListener) {
        this.hotelQueueListener = hotelQueueListener;
    }

    public void add(Group group) {
        final Order order = new Order(group);

        order.setOnMoveOutListener(new MoveOutListener() {
            @Override
            public boolean onMoveOut(Order order) {
                return hotelQueueListener.onMoveOut(order);
            }
        });

        order.setOnSettleListener(new OnSettleListener() {
            @Override
            public boolean onSettle(Order order) {
                return hotelQueueListener.onSettle(order);
            }
        });

        orders.add(order);
        order.start();
    }

}
