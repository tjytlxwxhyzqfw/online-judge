# Codeforces Round 385

## A
**循环左移**

list加法 + set去重

## B
**两个图拼成一个矩形**

没读懂题,到现在也是.  
有空再说把...

## C
**并查集**

代码写的太慢了.  
因为对并查集的操作不熟悉.

通过这道题,我对并查集的理解至少在一下两个方面有了加深:

1. 首先,并查集中合并两个元素是及其简单的操作(从外部接口来说), 只要调用`set.un(x, y)`就可以了
2. 并查集重要的是元素的根,这是一个元素区别于另一个元素的本质的不同.

其次,还有一些小的理解:  

3. 谁是并查集中的元素,这很重要.
4. 可以用一个数组来存储每个小集合的卫星数据, 这个数组的大小和并查集的大小一样(废话?可以用map吗?)
5. 可以用一些标记来记录那个并查集被访问过了. 这利用了刚才所说的, 元素的根是元素区别于其他元素的本质.

每次用并查集的时候,总有一些数据是不能通过并查集直接存储,更新的.  
这样看来,用一个数组存储卫星数据的做法是很普遍,也是很重要的.

## D

## E

## 后记

### 2016-12-19-02:41

大神们4分钟解完C题是什么鬼....