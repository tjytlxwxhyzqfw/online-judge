# 044

### 解题之前的想法

**方法1: 根据*划分模式字符串, 然后挨个匹配剩下的部分**

不能用kmp方法优化? 因为kmp方法种不考虑'?'

**方法2. 使用深度优先搜索**

**方法3. 使用动态规划**

(i, j, k):

1. 模式字串的起始匹配位置为i
2. 文本子串的起始匹配位置为j
3. 模式子串p\[i\]强行匹配k个文本字符

跳转到(i+1, j+k, x): x是可能的下一个模式子串的匹配位置

上面的思考在解题种有一种缺陷: 为了填充\[i, j, k\], 你必须遍历所有的\[i+1, j, x\], 才能给出结果.
这是一个有趣的现象, 分解成3维反而解不了, 必须用2维的DP来解, 原因是2维比3维归纳了更多的信息, hdoj1069也是这样的.

我们试着用这样的方式来构造DP:

(i, j): 从p+i与t+j能否匹配成功?

### 一些类似的题目


leetcode-10: 也是关于正则匹配的, 我记得当时这个题目我写了好久, 但是最终也是用的动态规划
hdoj-1069: 这题也是, 分解成2维矩阵反而做不了, 要用1维矩阵来解. (一堆箱子, 把它们罗起来, 要求高度最高)

