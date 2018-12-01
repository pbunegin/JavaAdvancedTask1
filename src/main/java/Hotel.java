import java.util.PriorityQueue;
import java.util.Queue;

public class Hotel {
    private static int orderNumber = 0;
    private Queue<String> requests = new PriorityQueue<>();

    public synchronized void put(String request){
        while (requests.size() >= 5){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        requests.offer(request);
        notifyAll();
    }

    public synchronized String get(){
        while (requests.size() < 1){
            if (orderNumber >= 15)
                return null;
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        String request = requests.poll();
        notifyAll();
        return request;
    }

    public synchronized int getOrderNumber() {
        return orderNumber++;
    }
}
