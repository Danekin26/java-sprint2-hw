public class Checker {

    public void calculateSumProfitAndLoss(MonthlyReport monthRep, YearlyReport yearlyReport){
        Integer sumProfit;
        Integer sumLoss;
        boolean errorCorrelationCheck = true;
        boolean fileCheck = (monthRep.monthToReport.size() != 0) && (yearlyReport.nameMonthToLoss.size() != 0)
                && (yearlyReport.nameMonthToProfit.size() != 0);   // Проверка на считывание файлов

        if(fileCheck) {
            for (String month : monthRep.monthToReport.keySet()) {
                sumProfit = monthRep.getProfitLossValue(month, "profit"); // Доход за месяц
                sumLoss = monthRep.getProfitLossValue(month, "loss"); // Расход за месяц

                if (!yearlyReport.nameMonthToProfit.get(month).equals(sumProfit)) {  // Поиск значения дохода в мапе
                    System.out.println(month);
                    System.out.println("В этом месяце обнаружено несоответствие при подсчете доходов.");
                    errorCorrelationCheck = false;
                }

                if (!yearlyReport.nameMonthToLoss.get(month).equals(sumLoss)) {  // Поиск значения расхода в мапе
                    System.out.println(month);
                    System.out.println("В этом месяце обнаружено несоответствие при подсчете расходов");
                    errorCorrelationCheck = false;
                }

            }
        }else{
            System.out.println("Файлы отчетов не загружены. Пожалуйста считайте отчёты и повторите попытку.");
        }
        if(errorCorrelationCheck && fileCheck){
            System.out.println("В представленных отчетах расходы и доходы сошлись :)");
        }

    }
}
