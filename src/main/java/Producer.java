import java.time.LocalDate;

public class Producer implements Runnable {
    private String ad;
    private LocalDate date;
    private Hotel hotel;

    public Producer(LocalDate date, Hotel hotel) {
        this.date = date;
        this.hotel = hotel;
    }

    @Override
    public void run() {
        int orderNumber;
        while ((orderNumber = hotel.getOrderNumber()) < 15) {
            String request = "request#" + orderNumber;
            hotel.put(request);
            System.out.println(Thread.currentThread().getName() +
                    ": отпралено " + request);
        }
    }
}
