package main

import (
	"log"
)

// id=406 pass= ud=3202/261 pass=66 s=13 m=?
// description: given a array of persion x=(height, numberGE).
//   numberGE means count of people in front of x who are no shorter than x.
//   the original array is disordered and you are supposed to re-order it.
//
// today(20200803): i have strong confidence to the following solution:
//   1. find all people=(height, 0)
//   2. among those people put the shortest one into the queue
//   3. update the rest of the people by decrease their `numberGE`
//   4. goto step 1
// but i made some mistakes today:
//   1. i introduced a heap which is found not necessary
//   2. i wrongly updated all people. the correct update is to update the ones who are shorter
//      than the one just put into the queue
//   3. i wrongly just sort all people with numberGE=0 by height and put them all into the queue.
//      the correct way is to put them one by one. this exposed a very important experience: when
//      you are going to process a batch elements from a set, don't do that and try process them
//      ONE BY ONE.
//
//   discuss:
//      (1) 先拍最高的, 对于最高的那些人, 按照numberGE排序就行了
//      (2) 再排第二高的, 第三高的...
//      (3) 对于第n高的哪些人, 比如numberGE=x, 那就插入到排好的数组中的x这个位置即可 => 这样不会导致失序
//
//  discuss:
//     可以用mergeSort以O(nlogn)的复杂度解决这个问题 => 具体的没有读, 但是好像巨难?
//
//  关于上一个解法:
//     很牛批, 但是显然我今天想到的想法更加简单且容易实现. 说明我在进步哦~
//
func reconstructQueue(rawq [][]int) [][]int {
	count := make([]int, len(rawq))
	for i := 0; i < len(rawq); i++ {
		count[i] = rawq[i][1]
	}
	result := make([][]int, 0)
	for len(result) < len(rawq) {
		var target []int
		for i := 0; i < len(count); i++ {
			if count[i] == 0 && (target == nil || rawq[i][0] < target[0]) {
				target = []int{rawq[i][0], rawq[i][1], i}
			}
		}
		count[target[2]]--
		// log.Printf("target: %v", target)
		result = append(result, target[:2])
		for i := 0; i < len(count); i++ {
			if count[i] != -1 && rawq[i][0] <= target[0] {
				count[i]--
			}
		}
	}
	return result
}

func main() {
	rawqs := [][][]int{
		{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}},
		{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}, {4, 1}},
		{{7, 0}},
		{{1, 6}, {2, 5}, {3, 4}, {4, 3}, {5, 2}, {6, 1}, {7, 0}},
		{},
	}
	for _, rawq := range rawqs {
		log.Printf("%v", reconstructQueue(rawq))
		log.Printf("\n---")
	}
}

func assert(b bool) {
	log.Printf("\n---\n")
	if !b {
		panic("assertion error")
	}
}
