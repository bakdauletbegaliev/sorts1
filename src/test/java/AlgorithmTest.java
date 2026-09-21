@Test
    void testMetricsCollection() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        Metrics metrics = new Metrics();
        MergeSort.sort(arr, metrics);

        assertTrue(metrics.comparisons > 0);
        assertTrue(metrics.maxDepth > 0);
    }
}