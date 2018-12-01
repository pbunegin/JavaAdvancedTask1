public class Booker implements Runnable {
    private Hotel hotel;


    public Booker(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void run() {
            hotel.get(Thread.currentThread().getName());
    }
}
