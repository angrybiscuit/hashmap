package help;

public class Returner {
    public boolean success;
    public int operationCount;

    public Returner() {
        success = false;
    }

    @Override
    public String toString() {
        return "{" +
                "success=" + success +
                ", operationCount=" + operationCount +
                '}';
    }
}
