# Codeforces Round 388

呃...历史最差战绩...

## A
**D=1**

2 -> 2
3 -> 3

偶数: 2  
奇数: 3+偶数

## B
**计算几何**

1. 把图画出来,利用平行关系,可以求第四个顶点.
2. 注意分母为0的情况.

平心静气,平心静气,平心静气  
就算别人5分钟做出来,我用了30分钟,那也好过紧张兮兮,做不出来呵  
就算我做到一半, 发现了新的解法, 那不是更好, 连30分钟都不用  
一定要静下心来,细心推导啊!!!


## C
**模拟, 断点续传, counter trick**

1. 每个人都应该禁止下一个投票的敌人.
2. 投完一轮后,每个人一定都禁止了一个,所以还剩下一半敌人.
3. 压缩数组为原来的一半,重复上述过程.
4. 当数组中只有一个党派时,该党派获胜.

###### 断点续传
这里用到了一个经常用的技巧,这次一定要记住: **记忆起点,并且遍历起点为max(i, j)的形式**  
不准再在这种问题上出错.

###### 使用计数器

这题是可以这样做到遍历的:

1. 设D和R的计数器分别为x, y
2. 开始遍历序列
3. 若当前元素的计数器是0, 那么就增加另一个元素的计数器
4. 若当前元素的计数器是个正直, 那么就删掉当前元素

**很棒的一个trick!!!!!!!!!!!!!!!!!!!!!!!!!**

## D
## E

##后记

###### 2017-02-18
关于C题的计数器trick: 其实不管是面试, 还是日常编程, 程序员的智商就体现于这些层出不穷的小trick, 真的.
