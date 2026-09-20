public class Metrics {
    public long comparisons = 0;
    public int maxDepth = 0;
    private int currentDepth = 0;

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        if (currentDepth > 0) {
            currentDepth--;
        }
    }

    public boolean compareLessThan(int a, int b) {
        comparisons++;
        return a < b;
    }
}