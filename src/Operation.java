import java.util.Date;

public class Operation {
    private String name;
    private int money;
    private String ware;
    private String person;
    private String dateTime;

    public Operation(String name,
                     Person person,
                     Ware ware,
                     int amount,
                     int sale,
                     int saleForRegular) {
        Date date = new Date();
        ware.subtract(amount);
        person.subtract(ware.getPrice(sale, saleForRegular, person) * amount);
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