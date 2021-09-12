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