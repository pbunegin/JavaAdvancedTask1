import java.time.LocalDate;
import java.util.PriorityQueue;
import java.util.Queue;

public class Hotel {
    private static int orderNumber = 0;
    private Queue<String> requests = new PriorityQueue<>();

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Producer[] producers = new Producer[]{
                new Producer(LocalDate.of(2018, 11, 20), hotel),
                new Producer(LocalDate.of(2018, 10, 16),hotel),
                new Producer(LocalDate.of(2018, 6, 12),hotel)
        };

        Booker[] bookers = new Booker[]{
                new Booker(hotel),new Booker(hotel),new Booker(hotel),
                new Booker(hotel),new Booker(hotel),new Booker(hotel)
        };

        for (int i = 0; i < producers.length; i++) {
            new Thread(producers[i], "Producer#" + i).start();
        }
        for (int i = 0; i < bookers.length; i++) {
            new Thread(bookers[i], "Booker#" + i).start();
        }
    }

    public synchronized int getOrderNumber() {
        return orderNumber++;
    }

    public synchronized int quantityOfOrders() {
        return orderNumber;
    }

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
}
