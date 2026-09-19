public class Metrics {
    public long comparisons = 0;
    public int maxDepth = 0;
    private int currentDepth = 0;

    public void enterRecursion(){
        currentDepth++;
        if(currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public boolean compareEqual(int a, int b) {
        comparisons++;
        return a==b;
    }

    public void reset() {
        comparisons =0;
        maxDepth =0;
        currentDepth =0;
    }
}