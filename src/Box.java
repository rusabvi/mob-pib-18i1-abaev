public class Box {
    private int money;
    private int plates;

    public Box(int startCash, int startPlates){
        money = startCash;
        plates = startPlates;
    }

    public boolean Buying (int price, int amount) {
        if (this.money < price * amount) {
            System.out.println("Недостаточно средств для совершения операции");
            return false;
        }
        else {
            this.money -= price * amount;
            this.plates += amount;
            System.out.println("Операция успешно выполнена");
            return true;
        }
    }

    public boolean Selling (int price, int amount){
        if (this.plates < amount)
        {
            System.out.println("Недостаточно горшков для совершения операции");
            return false;
        }
        else{
            this.money += price * amount;
            this.plates -= amount;
            System.out.println("Операция успешно выполнена");
            return true;
        }
    }

    public int ShowMoney() {
        return money;

    }
    public int ShowPlates(){
        return plates;
    }
}