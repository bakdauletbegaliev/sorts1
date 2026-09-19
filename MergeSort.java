import java.util.Random;

public class QuickSelect {
    private static final Random random = new Random();

    public static int select(int[] a, int k, Metrics metrics){
        if (a == null || a.length == 0 || k<0 || k>=a.length) {
            throw new IllegalArgumentException("Invalid index k or empty array");
        }

        int low = 0;
        int high = a.length -1;

        while (low<=high) {
            int pivotIndex = low + random.nextInt(high - low +1);
            swap(a, low, pivotIndex);
            int pivot = a[low];

            int lt = low;
            int gt = high;
            int i = low + 1;

            while (i <= gt) {
                if(metrics.compareLessThan(a[i], pivot)) {
                    swap(a, lt++, i++);
                } else if (metrics.compareLessThan(pivot, a[i])) {
                    swap(a, i, gt--);
                }else {
                    i++;
                }
            }
            if(k<lt) {
                high = lt -1;
            } else if (k >gt) {
                low = gt +1;
            }else {
                return a[k];
            }
        }
        return a[k];
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i]=a[j];
        a[j]= temp;
    }
}