import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Operation {
    final private String name;
    final private int money;
    final private String ware;
    final private String person;
    final private String dateTime;

    public Operation(String name,
                     Person person,
                     Ware ware,
                     int amount,
                     int sale,
                     int saleForRegular) {
        LocalDateTime date = LocalDateTime.now();
        this.name = name;
        this.money = ware.getPrice(sale, saleForRegular, person);
        this.ware = ware.getName();
        this.person = person.getName();
        this.dateTime = date.toString();
    }

    public String getName() { return name; }

    public int getMoney() { return money; }

    public String getWare() { return ware; }

    public String getPerson() { return person; }

    public String getDateTime() { return dateTime; }
}