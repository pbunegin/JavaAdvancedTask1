import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        List<Producer> producers = new ArrayList<>();
        List<Booker> bookers = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            producers.add(new Producer(generateDate(), hotel));
        }
        for (int i = 0; i < 6; i++) {
            bookers.add(new Booker(hotel));
        }

        for (int i = 0; i < producers.size(); i++) {
            new Thread(producers.get(i), "Producer#" + i).start();
        }
        for (int i = 0; i < bookers.size(); i++) {
            new Thread(bookers.get(i), "Booker#" + i).start();
        }
    }

    private static LocalDate generateDate() {
        int year = (int) (Math.random()*2+2017);
        int month = (int) (Math.random()*12 + 1);
        int day = (int) (Math.random()*28 + 1);
        return LocalDate.of(year,month,day);
    }
}
