package entity;

import java.time.LocalDate;

public class Producer implements Runnable {
    private LocalDate date;
    private Hotel hotel;

    public Producer(LocalDate date, Hotel hotel) {
        this.date = date;
        this.hotel = hotel;
    }

    @Override
    public void run() {
        int orderNumber;
        while ((orderNumber = hotel.getOrderNumber()) < Hotel.LIMIT_REQUESTS) {
            String request = "request#" + orderNumber + " от " + date;
            hotel.put(request);
            System.out.println(Thread.currentThread().getName() +
                    ": отпралено " + request);
        }
    }
}