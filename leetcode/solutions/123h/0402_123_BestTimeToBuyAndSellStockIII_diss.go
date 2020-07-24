// 123.go.diss

package main

import (
	"fmt"
)

// id=123 pass=36 ud=1722/69 s=96 m=?
//
// idea: you deal with the first transaction carefully
// then you deal with the second transaction carefully too
//
// you may fill some confusion when you found that:
// 1. you can not finish the 2nd buy-tx at day 1 or day 2
// 2. you can not finish the 2nd sell-tx at day <= 3
func maxProfit(prices []int) int {
	if len(prices) == 0 {
		return 0
	}

	// fmt.Printf("%v\n", prices)

	sell, buy := make([][2]int, len(prices)), make([][2]int, len(prices))
	var profit int

	// the first transaction
	buy[0][0] = -prices[0]
	for i := 1; i < len(prices); i++ {
		sell[i][0] = sell[i-1][0]
		if sell[i][0] < buy[i-1][0] + prices[i] {
			sell[i][0] = buy[i-1][0] + prices[i]
		}
		buy[i][0] = -prices[i]
		if buy[i][0] < buy[i-1][0] {
			buy[i][0] = buy[i-1][0]
		}
		// fmt.Printf("first transaction: day=%d: sell=%d buy=%d\n", i+1, sell[i][0], buy[i][0])
	}
	profit = sell[len(prices)-1][0]

	if len(prices) < 3 {
		return profit
	}

	// the second transaction
	buy[2][1] = prices[1] - prices[0] - prices[2]
	for i := 3; i < len(prices); i++ {
		buy[i][1] = sell[i-1][0] - prices[i]
		if buy[i][1] < buy[i-1][1] {
			buy[i][1] = buy[i-1][1]
		}
		sell[i][1] = buy[i-1][1] + prices[i]
		if sell[i][1] < sell[i-1][1] {
			sell[i][1] = sell[i-1][1]
		}
		// fmt.Printf("second transaction: day=%d: sell=%d buy=%d\n", i+1, sell[i][1], buy[i][1])
	}
	if profit < sell[len(prices)-1][1] {
		profit = sell[len(prices)-1][1]
	}

	return profit
}

func main() {
	assert(maxProfit([]int{}), 0)
	assert(maxProfit([]int{1}), 0)
	assert(maxProfit([]int{1, 2}), 1)
	assert(maxProfit([]int{1, 2, 1}), 1)
	assert(maxProfit([]int{1, 2, 1, 2}), 2)
	assert(maxProfit([]int{1, 1, 1, 1}), 0)
	assert(maxProfit([]int{3, 3, 5, 0, 0, 3, 1, 4}), 6)
	assert(maxProfit([]int{1, 2, 3, 4, 5}), 4)
	assert(maxProfit([]int{7, 6, 4, 3, 1}), 0)
	assert(maxProfit([]int{4, 5, 8, 5, 4, 9, 0, 3, 0}), 9)
}

func assert(real, expected int) {
	if expected != real {
		panic(fmt.Sprintf("expected=%d, real=%d", expected, real))
	}
	fmt.Printf("ok, expected=real=%d\n", real)
}
