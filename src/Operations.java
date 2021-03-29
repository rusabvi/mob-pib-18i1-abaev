public class Operations {
    private int account;
    private String name;
    private boolean debet;
    private int price;
    private int amount;

    public Operations(int account, String name, boolean debet, int price, int amount) {
        this.account = account;
        this.name = name;
        this.debet = debet;
        this.price = price;
        this.amount = amount;
    }

    public void Show(){
        System.out.println(account + "\t\t" + name + "\t\t"
                + price + "\t\t\t" + amount + "\t\t\t" + debOrCred());
    }
    private String debOrCred (){
        if (debet)
            return "Дебет";
        else
            return  "Кредит";
    }
}