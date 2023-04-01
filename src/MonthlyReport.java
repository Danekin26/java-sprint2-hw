import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    public HashMap<String, ArrayList<ReportToMonth>> monthToReport = new HashMap<>();
    public ArrayList<Integer> countdownOfValidMonths = new ArrayList<>(); // <-- Счётчик месяцев, отчет которых есть в папке.
    public ArrayList<ReportToMonth> reports;                            // Это позволяет по индексу списка точно опредлить
                                                                        // за какой месяц предоставлен отчет, а за какой нет
    public void loadFile (String path){
        String content = readFileContentsOrNull(path);

        if(content !=null){
            reports = new ArrayList<>();
            countdownOfValidMonths.add(1);    // 1 записывается если файл отчета представлен в папке

            String [] lines = content.split("\r?\n");

            for (int i = 1; i<lines.length;i++){
            String line = lines [i];
            String [] parts = line.split(",");

            String title = parts[0];
            boolean expense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts [2]);
            int price = Integer.parseInt(parts [3]);

            ReportToMonth report = new ReportToMonth(title, expense,quantity,price);
            reports.add(report);
        }
        }else{
        countdownOfValidMonths.add(0);  // 0 записывается если отчета за месяц нету
    }
        writeToHashMap();    // Запись в HashMap
    }

    public String readFileContentsOrNull(String path){   // Считывание данных с файлов
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e){
            System.out.println("Невозможно прочитать файл с отчётом. Запрашиваемые данные отсутствуют в директории.");
            return null;
        }
    }

    public void writeToHashMap(){  // Запись отчета в мапу
        String [] nameAllMonth = {
                "Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"};
        int i = countdownOfValidMonths.size()-1;
            if(countdownOfValidMonths.get(i) == 1) {
                monthToReport.put(nameAllMonth[i], reports);
            }
    }

    public HashMap getProfitLossProduct(String month, String profitOrLoss){ // Поиск прибыльного товара и поиск максимальной траты
        String finalNameProduct;                                            // в формате (товар-цена)
        HashMap<String, Integer> nameProduct = new HashMap<>();
        HashMap<String, Integer> finalInfo = new HashMap<>();
        Integer maxSum = 0;

        for(int i = 0;i<monthToReport.get(month).size();i++) {

            String name = monthToReport.get(month).get(i).title;
            Integer sumPrice = monthToReport.get(month).get(i).price * monthToReport.get(month).get(i).quantity;

            if((!monthToReport.get(month).get(i).expense) && profitOrLoss.equals("profit")){   // Запись в мапу список
                nameProduct.put(name, sumPrice);
            }else if ((monthToReport.get(month).get(i).expense) && profitOrLoss.equals("loss")){
                 nameProduct.put(name, sumPrice);
            }

            for(String namesProd : nameProduct.keySet()){   // Присваивание названия максимальной выгоды или траты за месяц
                Integer localMax = nameProduct.get(namesProd);

                if(localMax>maxSum){
                    finalInfo.clear();
                    maxSum = localMax;
                    finalNameProduct = name;
                    finalInfo.put(finalNameProduct,maxSum);
                }
               }
            }
        return finalInfo;
    }

    public void getInfo(){  // Вывод информации
    if(monthToReport.size() != 0){
       for(String month :monthToReport.keySet()){
           System.out.println(month);
           System.out.println("Самый прибыльный товар в этом месяце: "+getProfitLossProduct(month, "profit"));
           System.out.println("Самая большая трата в этом месяце: "+getProfitLossProduct(month, "loss"));
           System.out.println();
       }
    }else{
        System.out.println("При выводе информации произошла ошибка.");
        System.out.println("Файлы не загружены. Пожалуйста считайте файлы и повторите попытку.");
    }
    }

    public Integer getProfitLossValue(String month, String switcher){
        int sumProfit = 0;

        for(int i = 0;i<monthToReport.get(month).size();i++){
            int sumPrice = monthToReport.get(month).get(i).price * monthToReport.get(month).get(i).quantity;

            if(!monthToReport.get(month).get(i).expense && switcher.equals("profit")){
                sumProfit += sumPrice;

            } else if(monthToReport.get(month).get(i).expense && switcher.equals("loss")) {
                sumProfit += sumPrice;

            }
        }
        return sumProfit;
    }
}
