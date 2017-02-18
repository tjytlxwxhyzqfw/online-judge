# Codeforces Round 389

## A
**找规律, D=1**

1. 教室从左到右分为分为若干组.
2. 每组的同学个数是: 排数*2
3. 找规律可以发现,每组第一个同学的编号是: (组号-1)*组大小+1
4. 找规律可以发现,每个同学的组号是: (编号-1)/组大小+1
5. 找规律可以发现,坐在左边的同学是奇数,右边的同学是偶数

找规律不失为一种上佳策略.

## B
**配对, D=2**

如果键盘布局是合理的,那么以下条件为真:

1. 如果a映射到b,那么b一定映射为a
2. 每个键最多映射一个键

综合上述两点,结合python的set可解.

## C
**二分(划掉), 简单标记(状态指针), D=3**

愚蠢的二分解法:

1. 可以利用前缀和数组在O(1)的时间内实现相反方向的查询.
2. 可以利用(1)来构建二分算法: 起点属于\[i,k,j\].若k到终点不重复,则j->k-1,否则i->k+1

聪明的解法:

1. 从路径的右端点开始向左端点遍历.
2. 记录踩过的格子.
3. 如果踩到了相反方向的格子,那么一个新的终点被发现了.

###### 2017-01-02

今天用"聪明的方法"重解了C题,发现这种方法确实比我的二分要简单不少.  
但是这里依然有个很微妙的地方,就是什么时候应该给统计终点个数的变量加1.  
这里的关键是在循环中找到变量的意义.我采用的方式是,每当找到一个终点的时候,  
就给统计变量ans的值+1.终点应该满足以下两个条件:

1. 倒数第一个点是终点
2. 如果一个点的相反点已经出现过,那么这个点是终点.

**我感觉这道题也可以使用从前向后的遍历方式.**

###### 2017-02-18

状态指针, 带状态的循环变量  
这不但是一类题型, 貌似还可以用这种方式去理解一个循环.

## D
## E

## 后记

做了这么多题目了,对codeforces的第三题应该有所了解了吧.

1. 并查集
2. 二分搜索
3. 小规模数学演算
4. OGP
5. 找规律,递推
6. 简单的数论
7. 分类讨论
8. 贪心
9. 前缀树
10. 平均值类的问题
11. 稍显复杂的模拟题

重点是,以上所有题目都是可以在30分钟内解决战斗的.  
真的不难.