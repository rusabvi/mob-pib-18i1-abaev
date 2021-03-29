import java.util.Scanner;

public class BuhUch {
    private static Operations[] operations = new Operations[1000];
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int startCash, startPlates, platePrice, action, operationAmount = 0;
        boolean end = false;
        System.out.print("Введите начальную сумму: ");
        startCash = in.nextInt();
        System.out.print("Введите начальное количество горшков: ");
        startPlates = in.nextInt();
        Box box = new Box(startCash, startPlates);
        System.out.print("Введите цену за 1 горшок: ");
        platePrice = in.nextInt();
        while (!end)
        {
            System.out.println("Выберите действие:\n1) Продать горшки\n2) Купить горшки\n" +
                    "3) Показать все операции\n4) Показать баланс\n5) Выход");
            action = in.nextInt();
            if (action == 1){
                int amount;
                System.out.println("Сколько?");
                amount = in.nextInt();
                if (box.Selling(platePrice, amount)) {
                    operations[operationAmount] = new Operations(60, "Продажа горшков", true, platePrice, amount);
                    operationAmount++;
                }
            }
            else if (action == 2){
                int amount;
                System.out.println("Сколько?");
                amount = in.nextInt();
                if (box.Buying(platePrice, amount)) {
                    operations[operationAmount] = new Operations(60, "Покупка горшков", false, platePrice, amount);
                    operationAmount++;
                }
            }
            else if (action == 3){
                if (operationAmount == 0)
                    System.out.println("Не было выполнено ни одной операции");
                else{
                    System.out.println("Счёт\tНазвание\t\t\tЦена за штуку\tКоличество\tТип операции");
                    for (int i = 0; i < operationAmount; i ++){
                        operations[i].Show();
                    }
                }
            }
            else if (action == 4)
                System.out.println("Баланс: " + box.ShowMoney() + "\nГоршков в наличии: " + box.ShowPlates());
            else if (action == 5)
                end = true;
            else
                System.out.println("Неверный ввод");
        }
    }
}