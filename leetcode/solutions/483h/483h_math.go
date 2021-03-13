package main

import (
	"math"
	"math/big"
	"strconv"
)

// 思路:
// 给定一个大数n, 求一个基B, 要求n表示成B进制数的时候, 全是1
// 如13表示成3进制数就是111(9+3+1)
// 100表示成99进制数就是11(9+1)
// 但是要求, 那个基B是***最小的***
//
// 我的想法仅仅是:
// 1 + x + x^2 + x^3 + ... + x^k = n
// 也就是: (x^(k+1)-1)/(x-1) = n
// 求这个最小的x, 使得有个k满足上式 -> 求个P, 压根没思路
//
// 没思路的时候尝试:
// 1. 动态规划? 没有切入点
// 2. 分治? 不可能
// 3. 回溯? 递归? 也不太行
//
// 不想了, 直接看答案去了
//
// ------ 看答案之后 ------
//
// 这道题目背后的数学知识还挺多.
// 首先: n = x^k + x^(k-1) + .. + x + 1
// 所以我们有以下两个不等式:
// n > x^k ... [1]
// n < (x+1)^k (二项式定理) ... [2]
// 由[1][2]可得, 如果n可以被x和k表出, 那么必有
// n^(1/k)-1 < x < n^(1/k) ... [3]
// 因为x, n, k都是正整数, 结合[3]可以知道, 如果n能被x和k表出, 那么x=下取整{n^(1/k)}
// 有了上述式子, 我们只要遍历所有可能的k, 就能把题目解了.
//
// k是指数, 那么k应该如何遍历呢?
// 注意到, k是n转换成m进制之后的长度.
// n转换成2进制的时候是最长的. 所以, k最大值就是n转换成2进制之后的长度. 即logn
// 题目中给出了n的取值上限是18位数. 所以我们从k=2~64遍历, 足够了.
//
// 此外, 这道题目还涉及到精度问题, 直接用float64去解的话, 会存在精度问题.
// (直接用int64的话, 可能会在中间计算过程中存在溢出问题)
// 可见确实不是什么好的面试题. (可能是一道好的数学题)
//
// (2020-11-24 14:44)


// id=483 pass=36 s= m= ud=174/344
func smallestGoodBase(num string) string {
	n, _ := strconv.ParseInt(num, 0, 64)
	// log.Printf("n=%d", n)
	for k := int(math.Log2(float64(n))); k >= 2; k-- {
		x := int64(math.Floor(math.Pow(float64(n), 1/float64(k))))

		// float64 has some precision problems which is a float insure rather a language insure.
		// so we introduce math/big here to solve this problem
		sum, power, base := big.NewInt(0), big.NewInt(int64(x)), big.NewInt(x)
		for i := 1; i <= k; i++ {
			sum.Add(sum, power)
			power.Mul(power, base)
		}
		sum.Add(sum, big.NewInt(1))

		// log.Printf("k=%d, x=%d, sigma=%s", k, x, sum.String())
		if big.NewInt(n).Cmp(sum) == 0 {
			return strconv.FormatInt(int64(x), 10)
		}
	}
	return strconv.FormatInt(n-1, 10)
}

func main() {
	// wa
	assert(smallestGoodBase("470988884881403701") == "686286299")

	// ------ original ------

	assert(smallestGoodBase("13")  == "3")
	assert(smallestGoodBase("4681")  == "8")

	assert(smallestGoodBase("3") == "2")
	assert(smallestGoodBase("4") == "3")
	assert(smallestGoodBase("5") == "4")
	assert(smallestGoodBase("6") == "5")
	assert(smallestGoodBase("7") == "2")
	assert(smallestGoodBase("8") == "7")
	assert(smallestGoodBase("9") == "8")
	assert(smallestGoodBase("10") == "9")
	assert(smallestGoodBase("1000000000000000000") == "999999999999999999")
}

func assert(x bool) {
	if !x {
		panic("assertion failed")
	}
}

