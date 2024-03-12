import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {

    public ArrayList<ReportToYear> reports = new ArrayList<>();
    public HashMap<String, Integer> nameMonthToLoss = new HashMap<>();
    public HashMap<String, Integer> nameMonthToProfit = new HashMap<>();

    public void loadFile(String path, ReadDataFromFiles readDataFromFiles) {
        String content = readDataFromFiles.readFileContentsOrNull(path);

        if (content != null) {
            String[] lines = content.split("\r?\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] parts = line.split(",");

                Integer month = Integer.parseInt(parts[0]);
                Integer amount = Integer.parseInt(parts[1]);
                Boolean expense = Boolean.parseBoolean(parts[2]);

                String nameMonth = numMonthToNameMonth(month);
                ReportToYear reportToYear = new ReportToYear(nameMonth, amount, expense);
                reports.add(reportToYear);
            }

            for (ReportToYear report : reports) {
                if (!report.expense) {
                    nameMonthToProfit.put(report.month, report.amount);
                } else {
                    nameMonthToLoss.put(report.month, report.amount);
                }
            }
            reports.clear();
        }
    }

    public String numMonthToNameMonth(Integer num) {
        String[] nameAllMonth = {
                "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return nameAllMonth[num - 1];
    }

    public void getInfo() {
        if ((!nameMonthToLoss.isEmpty()) && (!nameMonthToProfit.isEmpty())) {
            profit();
            averageLoss();
            averageProfit();

        } else {
            System.out.println("При выводе информации произошла ошибка.");
            System.out.println("Файлы не загружены. Пожалуйста считайте файлы и повторите попытку.");
        }
    }

    public void profit() {
        int prof;
        for (String nameMonth : nameMonthToLoss.keySet()) {
            System.out.println();
            System.out.println(nameMonth);
            prof = nameMonthToProfit.get(nameMonth) - nameMonthToLoss.get(nameMonth);

            if (prof < 0) {
                System.out.println("В этом месяце не было прибыли.");
                System.out.println("Убыток составляет: " + Math.abs(prof));

            } else {
                System.out.println("Прибыль в этом месяце составляет: " + prof);
            }
        }
    }

    public void averageLoss() {
        Integer avgLoss = 0;
        for (String nameMonth : nameMonthToLoss.keySet()) {
            avgLoss += nameMonthToLoss.get(nameMonth);
        }
        avgLoss /= nameMonthToLoss.size();
        System.out.println();
        System.out.println("Средний расход за все месяцы в году составляет: " + avgLoss);
    }

    public void averageProfit() {
        Integer avgProf = 0;
        for (String nameMonth : nameMonthToProfit.keySet()) {
            avgProf += nameMonthToProfit.get(nameMonth);
        }
        avgProf /= nameMonthToProfit.size();
        System.out.println();
        System.out.println("Средний доход за все месяцы в году составляет: " + avgProf);
    }
}