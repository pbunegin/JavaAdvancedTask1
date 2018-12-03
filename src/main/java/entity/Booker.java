package entity;

public class Booker implements Runnable {
    private Hotel hotel;


    public Booker(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void run() {
        String request;
        while (!hotel.isStopBooker()) {
            if ((request = hotel.get()) == null)
                continue;
            String nameBooker = Thread.currentThread().getName();
            System.out.println(nameBooker + ": получено " + request);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(nameBooker + ": обработано " + request);
        }
    }
}
