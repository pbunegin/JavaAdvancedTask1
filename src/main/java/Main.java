import entity.Booker;
import entity.Hotel;
import entity.Producer;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        for (int i = 0; i < 3; i++) {
            new Thread(new Producer(generateDate(),hotel), "Producer#" + i).start();
        }
        for (int i = 0; i < 6; i++) {
            new Thread(new Booker(hotel), "Booker#" + i).start();
        }
    }

    private static LocalDate generateDate() {
        int year = (int) (Math.random()*2+2017);
        int month = (int) (Math.random()*12 + 1);
        int day = (int) (Math.random()*28 + 1);
        return LocalDate.of(year,month,day);
    }
}
