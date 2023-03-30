public class ReportToMonth { // Объкт, описывающий месячный отчет
    public String title;
    public boolean expense;
    public int quantity;
    public int price;

    public ReportToMonth(String title, boolean expense, int quantity, int price) {
        this.title = title;
        this.expense = expense;
        this.quantity = quantity;
        this.price = price;
    }


}
