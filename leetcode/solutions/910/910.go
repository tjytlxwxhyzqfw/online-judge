// 910
// we sort the original array. then there will be a pivot element in the array:
// array: min ... x ... pivot ... y ... max, and in the final result we have:
// 1. [min, pivot] need to add by k
// 2. (pivot, max] need to add by -k
// and the max delta must be in one of `ds` (see bellow)
//
// above solution is a O(nlgn) solution and find another O(nlgn) solution in disscuss.
// https://leetcode.com/problems/smallest-range-ii/discuss/173377/C%2B%2BJavaPython-Add-0-or-2-*-K
func smallestRangeII(a []int, k int) int {
	sort.Ints(a)
	min, max := a[0], a[len(a)-1]
	delta := max - min
	for i := 0; i < len(a)-1; i++ {
		ds := []int{a[i]-min, max-a[i+1], (max-k)-(min+k), (a[i]+k)-(a[i+1]-k)}
		for i := range ds {
			if ds[i] < 0 {
				ds[i] = -ds[i]
			}
		}
		sort.Ints(ds)
		if ds[3] < delta {
			delta = ds[3]
		}
	}
	return delta
}

func assertEqual(ans, expected int) {
	if ans != expected {
		panic(fmt.Sprintf("ans=%d, expected=%d", ans, expected))
	}
}

func main() {
	assertEqual(smallestRange([]int{0}, 0), 0)
	assertEqual(smallestRange([]int{1, 3}, 10), 2)
	assertEqual(smallestRange([]int{1, 3, 8}, 10), 7)
	assertEqual(smallestRange([]int{1, 3, 8}, 7), 7)
	assertEqual(smallestRange([]int{1, 3, 8}, 6), 7)
	assertEqual(smallestRange([]int{1, 3, 8}, 5), 5)
	assertEqual(smallestRange([]int{1, 3, 8}, 4), 3)
	assertEqual(smallestRange([]int{1, 10}, 4), 1)

	assertEqual(smallestRange([]int{0, 10}, 2), 6)
	assertEqual(smallestRange([]int{1, 3, 6}, 3), 3)

	wa := []int{8038,9111,5458,8483,5052,9161,8368,2094,8366,9164,53,7222,9284,5059,4375,2667,2243,5329,3111,5678,5958,815,6841,1377,2752,8569,1483,9191,4675,6230,1169,9833,5366,502,1591,5113,2706,8515,3710,7272,1596,5114,3620,2911,8378,8012,4586,9610,8361,1646,2025,1323,5176,1832,7321,1900,1926,5518,8801,679,3368,2086,7486,575,9221,2993,421,1202,1845,9767,4533,1505,820,967,2811,5603,574,6920,5493,9490,9303,4648,281,2947,4117,2848,7395,930,1023,1439,8045,5161,2315,5705,7596,5854,1835,6591,2553,8628,}
	assertEqual(smallestRange(wa, 4643), 8870)
}

