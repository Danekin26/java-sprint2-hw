import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        Checker checker = new Checker();
        String command;

        System.out.println("Версия приложения: V.1.0");
        System.out.println("Добро пожаловать в приложение ведения бухгалтерии парка развлечений.");

        while(true){
            printMenu();

                command = scan.nextLine();
                if(command.equals("1")){
                    printNameFile(monthlyReport);
                }else if(command.equals("2")){
                    yearlyReport.loadFile("resources/y.2021.csv", monthlyReport);
                }else if(command.equals("3")){
                    checker.calculateSumProfitAndLoss(monthlyReport, yearlyReport);
                }else if(command.equals("4")){
                    monthlyReport.getInfo();
                }else if(command.equals("5")){
                    yearlyReport.getInfo();
                }else if(command.equals("exit")){
                    break;
                }else{
                    System.out.println("Увы :с");
                    System.out.println("Такой команды нету.");
                }
        }
    }

    static void printMenu(){        // Вывод меню
        System.out.println();
        System.out.println("Выберите пункт из списка");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("exit - Выход");
    }
    public static void printNameFile(MonthlyReport monthlyReport){      // Считывание всех месяцев

        monthlyReport.monthToReport.clear(); // Отчистка мапы и счётчика перед повторным считыванием
        monthlyReport.countdownOfValidMonths.clear();
                                                                            // Для считывания всех месяцев
        for(int i = 1;i<=3;i++) {                                           // следует изменить условие цикла (i<=12).
            if(i<=10){                                                      // Здесь это не сделано что бы не засорять консоль
            monthlyReport.loadFile("resources/" + "m.20210"+i+".csv"); // т.к. в методе считывания файлов после каждой неудачной
            }else if(i<=12){                                                // попытки в консоль выводится ошибка чтения файла
            monthlyReport.loadFile("resources/" + "m.2021"+i+".csv");
            }else{
                return;
            }
        }
    }
}