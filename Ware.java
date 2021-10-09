public class Ware {

    final private String name;
    final private int price;
    private int amount;

    public Ware(int index, int price, int amount) {
        this.name = newName(index);
        this.price = price;
        this.amount = amount;
    }

    public String getName() { return name; }

    public int getPrice(int sale, int saleForRegular, Person person) {
        if (person.isRegular())
            return (100 - sale - saleForRegular) * price / 100;
        else
            return (100 - sale) * price / 100;
    }

    public int getAmount() { return amount; }

    public void subtract(int amount) { this.amount -= amount; }

    private String newName(int index) { //с enum заморочки больше
        if (index == 0)
            return "Посуда";
        else if (index == 1)
            return "Шоколад";
        else if (index == 2)
            return "Фрукт";
        else if (index == 3)
            return "Овощ";
        else
            return "Напиток";
    }
}
