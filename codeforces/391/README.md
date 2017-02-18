# Codeforces Round 391

## A
**计数**
没读明白题意,导致走了弯路,不应该!  
从报纸上一个字母一个字母的剪下来,然后自己拼出单词"Bulbasaur",问最多能拼出几个.  
就是分别给字母"Bulbar"技术,然后问这么多个"源材料"能组合出几个目标单词.  
跟386A是同一个题.

## B
**数论,质数,约数**
这题其实赶上平时的第三题了.

你要注意到这样两个规律:

1. 素数测试的时间复杂度只有sqrt(n),这是很低的复杂度,要利用好
2. 整数的约数是成对出现的,若d是n的约数,则n/d是n的另一个约数.(这是解题的时候发现的)

利用这两条性质,你就可以在sqrt(10^5)*10^5的时间内解决这个问题:

1. 维护一个从约数计数数组,其索引是约数,范围是0-100000; 其值是输入序列中此约数倍数的个数
2. 找到一个数的所有约数(理想情况下,应该是素因子,但是约数也可以),并增加每个约数的计数
3. 计数最大的那个(或很多个)约数(一定是素数)是最大集合的公因子,其计数的值就是答案

## C
**OGP**

输入的每一行,代表一个训练营.

我们以"怪兽"代表怪兽的种类,而不是具体的怪兽.

你能证明出这样的命题:
在每个训练营中,可以互相进化的怪兽对该训练营的怪兽集合构成一个划分
这个划分是根据每种怪兽的数目完成的.

这样,每个训练营都可以拆解成一个一个的划分.  
每个划分S为总答案提供一个因子: |S|!.

这个题的难点是不同的训练营之间划分的关系:

第一个训练营: 1, 2, 3, 5, 5, 6, 6  
第二个训练营: 2, 3, 4  
第三个训练营: 2, 3, 4

那么我们可以得到这样的划分:

1#: {1, 2, 3}, {5, 6}
2#: {2, 3, 4}
3#: {2, 3, 5}

你可以通过这样的划分形式,找到它们跟最终答案的各种规律.  
这里就不展开细说了, 只要你肯动脑,可以找到很多规律.

这里有用的规律是这样的:

我们以怪兽为索引, 每个怪兽都有自己的训练营, 不妨称之为每个怪兽的"训练营特征":

1: 1
2: 1, 2, 3
3: 1, 2, 3
4: 2
5: 1, 1, 3
6: 1, 1

这里你会看到:

1. 2号和3号怪兽由于其训练营一模一样,因此它们是可以互相进化的.
2. 6号和1号不一样, 与因为6号在1中出现了两次, 而1只有一次

这样,这个题的答案就变为以"训练营特征"为样本的频数统计问题, 答案变成所有样本频数的乘积.

**然而说了这么多并没有什么卵用, 还不是超时了 ^_^**

### 重点

这个时候我至少可以总结出以下两个经验:

第一个是对一类问题的定义: 将一组元素以某种规律组织成一个字典, 元素本身为索引, 其出现的次数是其值.  
我将这类问题命名为**样本的频数统计问题**, 这里, **将给定的这组元素视为一组样本**.  
这个问题经常遇到, 尤其是最近做数据分析的时候.

第二个是个更加重要的规律: 解题的时候, 按照"键值互换"的方式重新组织数据, 往往题目就会迎刃而解.  
我将它视为算法界的傅立叶变换...




## D

## E

## F

## G