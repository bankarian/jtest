## p1

Given 2021/09/12 is Sunday, please provide an algorithm to calculate every date's weekday.

> Solution

To avoid paying to much attention on handling leap year's February case, we can use year's 1th Jan as pivot. To do this, extra days should be recorded, since we only know current day as pivot (or any other day given by the problem).

## p2

Given a number n, please print all available sequence in order.
e.g. n = 4
1
2
3
4
1 2
1 3
1 4
2 3
2 4
3 4
1 2 3
...
1 2 3 4

> Solution

Just recurse, the tranferring state is (currentSeq, leftSize)

## P3

Given ascending arrays A and B, length of m and n respectively. Please provide an algorithm to find the k-th number in these two arrays in O(lg(m+n)) time.

> Solution

The easiest way is to merge the two arrays until meets the kth item, which is O(k). To accelerate, we should notice that these 2 arrays are ascending, which means we can exclude a series of items at a time.

So, we can try to exclude k/2 items in one of the array:

- If A[k/2] < B[k/2], then we can exclude k/2 item in A, vice versa.
- Let's say we have A[k/2] < B[k/2], after previous step, we run findKth(A[k/2:], B[:], k-k/2)
