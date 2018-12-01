public class Booker implements Runnable {
    private Hotel hotel;


    public Booker(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void run() {
        String request;
        while (hotel.quantityOfOrders() < 15 && (request = hotel.get()) != null)
            try {
                System.out.println(Thread.currentThread().getName() + ": получено " + request);
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + ": обработано " + request);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
    }
}
