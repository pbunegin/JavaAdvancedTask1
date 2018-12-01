import java.time.LocalDate;

public class Producer implements Runnable{
    private String ad;
    private LocalDate date;
    private Hotel hotel;

    public Producer(LocalDate date, Hotel hotel) {
        this.date = date;
        this.hotel = hotel;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            hotel.put("request#" + i,Thread.currentThread().getName());
        }
    }


}
