                                                                                              # Assignment 1: Sorting Algorithms Analysis

## Executive Summary
This report analyzes three fundamental algorithms: **MergeSort**, **QuickSort** (3-way partitioning with random pivot), and **QuickSelect**.

## Key Findings & Benchmark Analysis

| Algorithm | Input Type | Time Complexity | Depth Behavior | Key Observation |
| :--- | :--- | :--- | :--- | :--- |
| **MergeSort** | All Inputs | $O(n \log n)$ | Strict $O(\log n)$ | Consistent performance, robust against duplicates due to insertion cutoff. |
| **QuickSort** | Random / Sorted | $O(n \log n)$ | $O(\log n)$ | 3-way partitioning and random pivot eliminate $O(n^2)$ degraded states on sorted arrays. |
| **QuickSort** | Duplicates | $O(n)$ | Very Low | Equal elements are grouped in center, drastically reducing recursive calls. |
| **QuickSelect**| Average Case | $O(n)$ | Minimal | Successfully finds the $k$-th element linearly without full sorting. |

## Conclusion
* **MergeSort** guarantees stable $O(n \log n)$ efficiency regardless of input distribution.
* **3-way QuickSort** handles arrays with heavy duplicates seamlessly while retaining fast in-place performance.
* **QuickSelect** outperforms full sorting methods when searching for median/order statistics.