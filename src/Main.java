import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        ReadDataFromFiles readDataFromFiles = new ReadDataFromFiles();
        Checker checker = new Checker();
        String command;

        System.out.println("Версия приложения: V.1.0");
        System.out.println("Добро пожаловать в приложение ведения бухгалтерии парка развлечений.");

        while (true) {
            printMenu();
            command = scan.nextLine();
            switch (command) {
                case "1":
                    printNameFile(monthlyReport, readDataFromFiles);
                    break;
                case "2":
                    yearlyReport.loadFile("resources/y.2021.csv", readDataFromFiles);
                    break;
                case "3":
                    checker.calculateSumProfitAndLoss(monthlyReport, yearlyReport);
                    break;
                case "4":
                    monthlyReport.getInfo();
                    break;
                case "5":
                    yearlyReport.getInfo();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Такой команды нету." + "\n" + "Попробуйте еще раз.");
            }
        }
    }

    static void printMenu() {
        System.out.println();
        System.out.println("Выберите пункт из списка");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("exit - Выход");
    }

    public static void printNameFile(MonthlyReport monthlyReport, ReadDataFromFiles readDataFromFiles) {
        monthlyReport.monthToReport.clear();
        monthlyReport.countdownOfValidMonths.clear();
        for (int i = 1; i <= 3; i++) {
            if (i <= 10) {
                monthlyReport.loadFile("resources/" + "m.20210" + i + ".csv", readDataFromFiles);
            } else if (i <= 12) {
                monthlyReport.loadFile("resources/" + "m.2021" + i + ".csv", readDataFromFiles);
            } else {
                return;
            }
        }
    }
}