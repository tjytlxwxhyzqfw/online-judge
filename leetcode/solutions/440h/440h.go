// 440h.go
// 描述: 把所有小于等于n的自然数按照字典序排成一排, 找到第k个数.
// 思路: 十叉树. 10(初始为9, 无0)颗数排成一排, 假设n的自高位是x, 位数是y
//       那么这10颗树里面, x左边的数有y层, x右边的数有y-1层, 如果答案出现在这两种数里, 直接findInTree()即可.
//       否则, 在恰好为h的这棵树上, 我们重新解这个题: findKthNumber(n', k-delta, start=0).
//       n'是n去掉最高位之后剩下的那个数.
// 卧槽, 绕死我了. 中间一度以为自己做错了, 改了半天!!!
// 比如findKthNumber(100, 4)=11, 我一开始以为=4, 然后程序返回的是11, 我以为我写错了, 然后就开始犯晕, 晕了一晚上.
package main

import (
	"log"
	"strconv"
)

const Ones string = "1111111111"

func findKthNumber(n int, k int) int {
	result, _:= find(strconv.Itoa(n), k, 1)
	number, _ := strconv.Atoi(result)
	return number
}

// 意义: 在10棵或9棵树组成的森林里, 找到数值不超过s的字典序第k个数字.
// 参数: s - 不超过s的数(意义有待进一步明确)
//       k - 第k个数
//       start - 从0号树开始还是从1号树开始. 初始从1, 后续都是0
// 返回值: string - 结果, 如果无结果, 返回""
//        int - 这棵树上消费了数字之后的结余. 比如k=5, 但是树上仅3个数字, 那么返回2
func find(s string, k int, start int) (string, int) {
	// log.Printf("find: (%s, %d, %d)", s, k, start)
	result := ""
	for i := start; i < 10; i++ {
		if int(s[0])-48 == i {
			if k == 1 {
				return string(s[0]), 0
			}
			k--
			if len(s) == 1 {
				return "", k
			}
			result, k = find(s[1:], k, 0)
			if len(result) > 0 {
				return string(s[0]) + result, 0
			}
			continue
		}
		depth := len(s)
		if i > int(s[0])-48 {
			depth--
		}
		size, _ := strconv.Atoi(Ones[:depth])
		if k > size {
			k -= size
		} else {
			return findInTree(string(i+48), depth, k), 0
		}
	}
	return "", k
}

func findInTree(root string, depth int, k int) string {
	if k == 1 {
		return string(root)
	}
	k--
	size, _ := strconv.Atoi(Ones[:depth-1])
	return root + findInTree(string(48 + (k-1) / size), depth-1, (k-1)%size + 1)
}

func main() {
	assert(findKthNumber(9, 1) == 1)
	assert(findKthNumber(9, 5) == 5)
	assert(findKthNumber(9, 9) == 9)

	assert(findKthNumber(10, 2) == 10)
	assert(findKthNumber(10, 10) == 9)

	assert(findKthNumber(1, 1) == 1)
	assert(findKthNumber(197, 1) == 1)

	assert(findKthNumber(10, 9) == 8)

	assert(findKthNumber(100, 1) == 1)
	assert(findKthNumber(100, 2) == 10)
	assert(findKthNumber(100, 3) == 100)
	assert(findKthNumber(100, 4) == 11)
	assert(findKthNumber(100, 5) == 12)
	assert(findKthNumber(100, 6) == 13)
	assert(findKthNumber(100, 13) == 2)
	assert(findKthNumber(100, 14) == 20)
	assert(findKthNumber(100, 100) == 99)

	assert(findKthNumber(102, 4) == 101)
	assert(findKthNumber(102, 5) == 102)
	assert(findKthNumber(102, 6) == 11)
	assert(findKthNumber(102, 102) == 99)

	assert(findKthNumber(111, 3) == 100)
	assert(findKthNumber(111, 4) == 101)
	assert(findKthNumber(111, 5) == 102)
	assert(findKthNumber(111, 10) == 107)
	assert(findKthNumber(111, 11) == 108)
	assert(findKthNumber(111, 12) == 109)
	assert(findKthNumber(111, 15) == 111)
	assert(findKthNumber(111, 16) == 12)
	assert(findKthNumber(111, 111) == 99)

	assert(findKthNumber(31415926, 271828) == 10244640)
}

func assert(b bool) {
	log.Printf("\n---\n")
	if !b {
		panic("assertion error")
	}
}
