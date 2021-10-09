import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
            System.out.println("Чтоб выйти, выберите 0\nВыберите пользователя:");
            for (Person p : persons) {
                System.out.println((persons.indexOf(p) + 1) + ") " + p.getName() +
                        ": " + p.getMoney() + " у. е.");
            }
            action = safeInput(List.of("0", "1", "2", "3", "Exit"));
            if (action.equals("0"))
                exit1 = true;
            else {
                int i = Integer.parseInt(action.trim());
                i --;
                for (boolean exit2 = false; !exit2; ) { //Цикл-меню действий
                    System.out.println("Выберите действие:\n0) Сменить пользователя\n1) Купить");
                    action = safeInput(List.of("0", "1"));
                    if (action.equals("1")) {
                        if (formatForDateNow.toString().equalsIgnoreCase("Воскресенье"))
                            sale = 10;
                        else
                            sale = 0;
                        for (boolean exit3 = false; !exit3; ) //Цикл-меню выбора товара
                        {
                            System.out.println("Чтобы выйти, выберите 0\nБаланс у пользователя " +
                                    persons.get(i).getName() + ": " + persons.get(i).getMoney() +
                                    " у. е.. Выберите товар:");
                            for (Ware w : wares) {
                                System.out.println((wares.indexOf(w) + 1) + ") " + w.getName()
                                        + ". Цена: " + w.getPrice(sale, saleForRegular, persons.get(i))
                                        + ". у. е. Количество: " + w.getAmount() + " у. е.");
                            }
                            action = safeInput(List.of("0", "1", "2", "3", "4", "5"));
                            if (action.equals("0"))
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
                                        buying(operations,
                                                persons, i,
                                                wares, k,
                                                amount,
                                                sale, saleForRegular);
                                }
                            }
                        }
                    }
                    else
                        exit2 = true;
                }
            }
        }
        showAllOperations(operations);
        statistics(operations);
    }

    private static void showAllOperations(List<Operation> operations) {
        if (operations.size() == 0)
            System.out.println("Операций не было выполнено");
        else {
            System.out.println("Все операции:");
            for (Operation o : operations)
                System.out.println((operations.indexOf(o) + 1) + ") "
                        + o.getPerson() + " совершил(а) операцию \""
                        + o.getName()
                        + "\" товара \"" + o.getWare() + "\" за "
                        + o.getMoney() + " у.е.. Дата и время: "
                        + o.getDateTime());
        }
    }

    private static void statistics(List<Operation> operations) {
        int statSum = 0;
        int statAmount;
        int statPerson;

        List<String> users = new ArrayList<>(); //уникальные пользователи
        for (Operation o : operations) {
            statSum += o.getMoney();
            if (!users.contains(o.getPerson())) //если имени пользователя нет в users
                users.add(o.getPerson());       //то добавляем его имя туда
        }

        statPerson = users.size();
        statAmount = operations.size();

        System.out.println("\nСтатистика\nОбщая сумма покупок: "
                + statSum + "\nОбщее количество покупок: "
                + statAmount + "\nОбщее количество пользователей, совершивших покупку: "
                + statPerson + "\nСредняя сумма покупок: "
                + statSum / statAmount + "\nСреднее количество покупок: "
                + statAmount / statPerson + "\nСредняя сумма затрат одним пользователем: "
                + statSum / statPerson);
    }

    private static String safeInput(List <String> input) { //в аргументе список принимаемых значений
        String action;
        Scanner scanner = new Scanner(System.in);
        action = scanner.nextLine();
        while (!input.contains(action)) {
            System.out.print("Неверный ввод. Введите ответ: ");
            action = scanner.nextLine();
        }
        return action;
    }

    private static void buying(List<Operation> o,
                               List<Person> p, int i,
                               List<Ware> w, int k,
                               int amount,
                               int sale, int saleForRegular) {
        o.add(new Operation("Покупка",
                p.get(i),
                w.get(k),
                amount,
                sale,
                saleForRegular));
        w.get(k).subtract(amount); //минус кол-во товара
        p.get(i).subtract(w.get(k).getPrice(sale,
                saleForRegular, p.get(i)) * amount); //минус цена со счёта пользователя
    }
}