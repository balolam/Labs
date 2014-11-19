/**
 * Created by gurov on 17.11.14.
 */
public class Order extends Thread{
    private Group group;

    private OnSettleListener onSettleListener;
    private MoveOutListener moveOutListener;

    public Order(Group group) {
        this.group = group;
    }

    @Override
    public void run() {
        super.run();

        for (; ;){
            boolean result = onSettleListener.onSettle(Order.this);

            if (!result) {
                synchronized (this) {
                    try {
                        wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else break;
            System.out.println("ожидание:" + group.getName());
        }

        synchronized (this) {
            try {
                wait(group.getResidenceTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //expectation(group.getResidenceTime());
        moveOutListener.onMoveOut(Order.this);
    }

    public Group getGroup() {
        return group;
    }

    public RoomDescription getRoomDescription() {
        return group.getRoomDescription();
    }

    public void setOnSettleListener(OnSettleListener onSettleListener) {
        this.onSettleListener = onSettleListener;
    }

    public void setOnMoveOutListener(MoveOutListener moveOutListener) {
        this.moveOutListener = moveOutListener;
    }
}
