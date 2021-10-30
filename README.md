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

- If `A[k/2] < B[k/2]`, then we can exclude k/2 item in A, vice versa.
- Let's say we have `A[k/2] < B[k/2]`, after previous step, we run `findKth(A[k/2:], B[:], k-k/2)`


## P4
Given N, which means you have numbers from 0~N-1. Given an mutex array indicates which two number should not be in the same group. Try to divide all the numbers into fewest groups.

eg. N=6, M=3, mutex=[[3, 5], [4, 3], [1, 2]]
Divided into fewest groups: [0, 1, 3] [2, 4, 5], so the output is 2 groups.

> Solution

Construct a graph from mutex array, then color it.

## P5
Implement an IEEE float triming machanim.

eg:
1.0100, reserve 1 decimal => 1.0
1.1100, reserve 1 decimal => 10.0


## P6
Given a senario that there are lots of events, each event has 2 properties: 
1. time, indicating when the event happened
2. type, indicating the type

Please provide a fucntion that finds out the top-K existing event types in a time window [a, b].

## P7
Implement the word counter example in MapReduce thesis.