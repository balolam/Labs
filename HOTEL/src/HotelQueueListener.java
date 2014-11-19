/**
 * Created by gurov on 18.11.14.
 */
public interface HotelQueueListener {
    boolean onSettle(Order order);
    boolean onMoveOut(Order order);
}
