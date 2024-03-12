public class Checker {

    public void calculateSumProfitAndLoss(MonthlyReport monthRep, YearlyReport yearlyReport) {
        Integer sumProfitMonth;
        Integer sumLossMonth;
        boolean errorCorrelationCheck = true;
        boolean fileCheck = (!monthRep.monthToReport.isEmpty()) && (!yearlyReport.nameMonthToLoss.isEmpty())
                && (!yearlyReport.nameMonthToProfit.isEmpty());

        if (fileCheck) {
            for (String month : monthRep.monthToReport.keySet()) {
                sumProfitMonth = monthRep.getProfitLossValue(month, "profit");
                sumLossMonth = monthRep.getProfitLossValue(month, "loss");
                if (!yearlyReport.nameMonthToProfit.get(month).equals(sumProfitMonth)) {
                    System.out.println(month + "\n" + "В этом месяце обнаружено несоответствие при подсчете доходов.");
                    errorCorrelationCheck = false;
                }

                if (!yearlyReport.nameMonthToLoss.get(month).equals(sumLossMonth)) {
                    System.out.println(month + "\n" + "В этом месяце обнаружено несоответствие при подсчете расходов");
                    errorCorrelationCheck = false;
                }
            }
        } else {
            System.out.println("Файлы отчетов не загружены. Пожалуйста считайте отчёты и повторите попытку.");
        }
        if (errorCorrelationCheck && fileCheck) {
            System.out.println("В представленных отчетах расходы и доходы сошлись :)");
        }
    }
}
