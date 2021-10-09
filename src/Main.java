import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //int [][] massive = {{200, 10}, {120, 20}, {30, 200}, {25, 250}, {90, 25}};//массив
        String path = "C://Files//Massive.txt";
        File file = new File(path);
        if(!file.exists())
            NewFile.main(args); //Создание массива

        //Чтение массива из файла
        int[][] massive = new int[5][2];
        try (BufferedReader br = new BufferedReader(new FileReader("C://Files//Massive.txt"))) {
            for (int i = 0; i < massive.length; i++) {
                String[] strArr = br.readLine().trim().split(" ");
                for (int j = 0; j < massive[i].length; j++) {
                    massive[i][j] = Integer.parseInt(strArr[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E"); //День недели
        int sale; //Скидка в зависимости от дня недели
        int saleForRegular = 5; //Скидка для постоянных покупателей
        System.out.println("Каждое воскресенье скидки 10 %! Для постоянных (более 5 покупок) скидка "
                + saleForRegular + " %!");
        Scanner scanner = new Scanner(System.in);
        String action; //Чтение ответов-действий
        List<Operation> operations = new ArrayList<>();

        List<Ware> wares = new ArrayList<>(); //Товары
        for (int i = 0; i < massive.length; i ++)
            wares.add(new Ware(i, massive[i][0], massive[i][1]));

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Екатерина", 10_000));
        persons.add(new Person("Максим", 200));
        persons.add(new Person("Артём", 15_000));

        for (boolean exit1 = false; !exit1; ) { //Цикл-меню выбора пользователя
            System.out.println("Чтоб выйти, введите \"Exit\"\nВыберите пользователя:");
            for (Person p : persons) {
                System.out.println((persons.indexOf(p) + 1) + ") " + p.getName() +
                        ": " + p.getMoney() + " у. е.");
            }
            action = scanner.nextLine();

            //Проверка ввода, чтоб перевести в int без ошибок
            while (!(action.equals("1") || action.equals("2") || action.equals("3")
                    || action.equalsIgnoreCase("Exit"))) {
                System.out.print("Неверный ввод. Введите ответ: ");
                action = scanner.nextLine();
            }
            if (action.equalsIgnoreCase("Exit"))
                exit1 = true;
            else {
                int i = Integer.parseInt(action.trim());
                i --;
                for (boolean exit2 = false; !exit2; ) { //Цикл-меню действий
                    System.out.println("Выберите действие:\n1) Купить\n2) Сменить пользователя");
                    action = scanner.nextLine();
                    if (action.equals("1")) {
                        if (formatForDateNow.toString().equalsIgnoreCase("Воскресенье"))
                            sale = 10;
                        else
                            sale = 0;
                        for (boolean exit3 = false; !exit3; ) //Цикл-меню выбора товара
                        {
                            System.out.println("Чтобы выйти, введите \"Exit\"\nБаланс у пользователя " +
                                    persons.get(i).getName() + ": " + persons.get(i).getMoney() +
                                    " у. е.. Выберите товар:");
                            for (Ware w : wares) {
                                System.out.println((wares.indexOf(w) + 1) + ") " + w.getName() + ". Цена: " +
                                        w.getPrice(sale, saleForRegular, persons.get(i)) +
                                        ". у. е. Количество: " + w.getAmount() + " у. е.");
                            }
                            action = scanner.nextLine();

                            //Проверка ввода, чтоб перевести в int без ошибок
                            while (!(action.equals("1") || action.equals("2")
                                    || action.equals("3") || action.equals("4") ||
                                    action.equals("5") || action.equalsIgnoreCase("Exit"))) {
                                System.out.println("Неверный ввод. Введите ответ: ");
                                action = scanner.nextLine();
                            }
                            if (action.equalsIgnoreCase("Exit"))
                                exit3 = true;
                            else {
                                int k = Integer.parseInt(action.trim());
                                k --;
                                int amount = 1;//Количество - пока что по штуке
                                if (wares.get(k).getAmount() <= 0)
                                    System.out.println("Товара нет в наличии");
                                else {
                                    if (wares.get(k).getPrice(sale, saleForRegular, persons.get(i))
                                            > persons.get(i).getMoney())
                                        System.out.println("У пользователя " + persons.get(i).getName() +
                                                " недостаточно средств");
                                    else //Покупка
                                        operations.add(new Operation("Покупка",
                                            persons.get(i),
                                            wares.get(k),
                                            amount,
                                            sale,
                                            saleForRegular));
                                }
                            }
                        }
                    }
                    else if (action.equals("2"))
                        exit2 = true;
                    else
                        System.out.println("Неверный ввод");
                }
            }
        }

        //Вывод операций
        if (operations.size() == 0)
            System.out.println("Операций не было выполнено");
        else {
            System.out.println("Все операции:");
            for (Operation o : operations)
                System.out.println((operations.indexOf(o) + 1) + ") " + o.getPerson() + " совершил(а) операцию \"" + o.getName()
                        + "\" товара \"" + o.getWare() + "\" за " + o.getMoney() + " у.е.. Дата и время: " + o.getDateTime());


            int statSum = 0;
            int statAmount = 0;
            int statPerson = 0;
            for (Person p : persons)
                if (p.getCount() > 0)
                    statPerson++;
            for (Operation o : operations) {
                statAmount++;
                statSum += o.getMoney();
            }
            System.out.println("\nСтатистика\nОбщая сумма покупок: "
                    + statSum + "\nОбщее количество покупок: "
                    + statAmount + "\nОбщее количество пользователей, совершивших покупку: "
                    + statPerson + "\nСредняя сумма покупок: "
                    + statSum / statAmount + "\nСреднее количество покупок: "
                    + statAmount / statPerson + "\nСредняя сумма затрат одним пользователем: "
                    + statSum / statPerson);
        }
    }
}

//магазин - должен хранить инфу о покупателях, товарах и расспродажах.
// Система должна давать скидку постоянным клиентам
// Обработка 2мерного массива. После сделать из него статистику какую-нибудь

// 1 вар - массив на товары (1 мерность - номер товара,     2 мерность - цена и количество)
// 2 вар - массив на людей  (1 мерность - номер человека,   2 мерность - баланс)
// 3 вар - массив на товары (1 мерность - номер операции,   2 мерность - человек, тип операции, товар, сумма, день недели, месяц, число, время, ДжиЭмТи и год)