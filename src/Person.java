public class Person {
    private String name;
    private int money;
    private int count;
    private boolean regular;

    public Person(String name, int money) {
        this.count = 0;
        this.regular = false;
        this.name = name;
        this.money = money;
    }

    public String getName() { return name; }

    public void subtract(int money) {
        this.money -= money;
        this.count++;
        if (this.count > 4)
            this.regular = true;
    }

    public int getMoney() { return money; }

    public boolean isRegular() { return regular; }

    public int getCount() { return count; }
}