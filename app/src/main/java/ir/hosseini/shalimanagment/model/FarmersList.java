package ir.hosseini.shalimanagment.model;


public class FarmersList {
    private int count;
    private String next;
    private String previous;
    private Farmer[] results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public Farmer[] getResults() {
        return results;
    }

    public void setResults(Farmer[] results) {
        this.results = results;
    }
}
