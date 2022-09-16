import java.util.ArrayList;

public class Invoice {
    private String customer;
    private ArrayList<Performance> performances;

    public Invoice(String customer) {
        this.customer = customer;
        this.performances = new ArrayList<Performance>();
    }

    public String getCustomer() {
        return customer;
    }

    public ArrayList<Performance> getPerformances() {
        return this.performances;
    }

    public void addPerfomance(Performance performance) {
        this.performances.add(performance);
    }
    
}
