import java.time.LocalDate;
import java.util.PriorityQueue;
import java.util.Queue;

public class Hotel {
    private int countRequest;
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
            new Thread(producers[i], String.valueOf(i));
        }
        for (int i = 0; i < bookers.length; i++) {
            new Thread(bookers[i], String.valueOf(i));
        }
    }

    public synchronized void put(String request, String name){
        while (countRequest >= 5){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        requests.offer(request);
        countRequest++;
        System.out.println("Producer #" + name +
                ": отпралено" + request);
        notify();
    }

    public synchronized String get(String name){
        while (countRequest < 1){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        countRequest--;
        String request = requests.peek();
        System.out.println("booker #" + name + ": получено " + request);
        try {
            Thread.currentThread().wait(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("booker #" + name + ": обработано " + request);
        notify();
        return request;
    }
}
