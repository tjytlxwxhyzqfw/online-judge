package main

// 题目: Given an array nums, we call (i, j) an important reverse pair if i < j and
// nums[i] > 2*nums[j].
// You need to return the number of important reverse pairs in the given array.
// Note:
// The length of the given array will not exceed 50,000.
// All the numbers in the input array are in the range of 32-bit integer.

// id=493 pass=26 s=0 m=57 ud=1092/129
func reversePairs(a []int) int {
	// log.Printf("raw: %v", a)
	ans := mergeSort(a)
	// log.Printf("sorted: %v => %d", a, ans)
	return ans
}

func mergeSort(a []int) int {
	if len(a) <= 1 {
		return 0
	}

	// 1 2 | 3 4 5
	m := len(a) / 2
	left := mergeSort(a[:m])
	rght := mergeSort(a[m:])

	var n int
	for i := 0; i < m; i++ {
		j := m
		for ; j < len(a); j++ {
			// 不要用除法: (a[i]-1)/2 < a[j] => 在{0, 0}这个case上会wa
			if int64(a[j])*2 >= int64(a[i]) {
				break
			}
		}
		n += j - m
	}

	b, p := make([]int, len(a)), 0
	i, j := 0, m
	for ; i < m && j < len(a); {
		if a[i] < a[j] {
			b[p], p, i = a[i], p+1, i+1
		} else {
			b[p], p, j = a[j], p+1, j+1
		}
	}
	for i < m {
		b[p], p, i = a[i], p+1, i+1
	}
	for j < len(a) {
		b[p], p, j = a[j], p+1, j+1
	}
	for i := 0; i < len(a); i++ {
		a[i] = b[i]
	}

	return left + rght + n
}

func main() {
	// a[i] > 2 * a[j]
	// ... dp[j] dp[j+1] => x
	// s[i] a[i+1]
	// [...] [...] => merge sort
	assert(reversePairs([]int{0, 0}) == 0)
	a := []int{12, 2, 1, 17, 19, 10, 5, 23, 7, 20, 10, 17, 22, 15, 9, 18, 12, 12, 16, 16, 17, 8, 11, 19, 2, 21, 5, 19, 22, 9, 17, 24, 8, 8, 16, 5, 2, 25, 1, 0, 3, 24, 25, 0, 11, 7, 19, 0, 5, 16, 17, 4, 19, 20, 20, 0, 14, 4, 16, 15, 11, 15, 20, 11, 17, 13, 3, 18, 12, 6, 10, 25, 12, 6, 18, 6, 19, 19, 18, 13, 21, 9, 17, 1, 1, 2, 10, 15, 24, 24, 22, 7, 10, 23, 15, 9, 1, 23, 22, 15, 3, 16, 23, 25, 8, 18, 0, 5, 1, 12, 9, 0, 25, 0, 13, 11, 22, 5, 3, 13, 10, 17, 14, 24, 23, 1, 8, 1, 21, 18, 2, 16, 21, 21, 5, 3, 19, 8, 23, 6, 6, 3, 2, 4, 13, 2, 4, 14, 9, 17, 23, 18, 4, 23, 5, 13, 25, 10, 9, 14, 3, 9, 11, 5, 14, 18, 0, 10, 13, 5, 19, 17, 24, 25, 4, 8, 16, 14, 3, 24, 18, 2, 17, 22, 4, 11, 18, 9, 9, 7, 10, 4, 24, 0, 7, 0, 6, 15, 18, 13, 14, 20, 22, 17, 22, 15, 17, 9, 10, 17, 13, 0, 22, 22, 23, 2, 21, 18, 6, 10, 10, 15, 14, 4, 4, 18, 21, 15, 0, 18, 14, 0, 2, 24, 6, 10, 1, 8, 25, 20, 13, 20, 13, 20, 5, 21, 21, 9, 19, 8, 9, 9, 5, 17, 18, 18, 20, 5, 17, 18, 3, 7, 21, 6, 0, 8, 3, 3, 1, 11, 0, 21, 6, 15, 11, 10, 13, 6, 7, 21, 7, 1, 1, 14, 15, 20, 2, 8, 21, 25, 19, 12, 18, 16, 0, 4, 10, 19, 14, 23, 6, 17, 2, 15, 19, 4, 13, 8, 14, 4, 15, 21, 4, 23, 20, 3, 18, 0, 12, 14, 14, 19, 0, 21, 18, 21, 17, 13, 9, 20, 17, 25, 17, 21, 16, 22, 4, 1, 13, 20, 15, 9, 7, 18, 18, 7, 22, 8, 18, 1, 13, 0, 24, 8, 12, 16, 1, 3, 6, 23, 16, 24, 5, 0, 1, 25, 3, 16, 9, 4, 24, 1, 11, 24, 9, 16, 11, 0, 2, 20, 16, 0, 1, 6, 19, 22, 12, 3, 23, 21, 4, 20, 1, 0, 18, 24, 10, 0, 12, 21, 17, 23, 0, 13, 1, 25, 9, 19, 0, 13, 21, 23, 6, 24, 25, 16, 9, 8, 16, 2, 22, 23, 3, 7, 16, 25, 11, 18, 19, 4, 11, 1, 25, 22, 9, 11, 14, 9, 3, 16, 8, 5, 11, 12, 15, 15, 19, 15, 15, 7, 17, 24, 18, 9, 8, 20, 23, 18, 17, 7, 8, 19, 23, 9, 13, 4, 17, 23, 21, 19, 11, 22, 22, 9, 3, 19, 23, 11, 2, 23, 8, 8, 21, 15, 1, 25, 7, 6, 14, 6, 7, 11, 3, 2, 11, 14, 10, 24, 3, 8, 10, 1, 18, 4, 6, 16, 12, 18, 12, 6, 5, 25, 24, 25, 7, 12, 17, 19, 15, 8, 23, 7, 6, 11, 6, 16, 14, 15, 13, 18, 5, 9, 21, 24, 8, 17, 25, 21, 22, 19, 24, 9, 9, 25, 21, 6, 25, 24, 3, 15, 20, 19, 13, 7, 13, 3, 0, 11, 2, 3, 23, 4, 14, 13, 7, 14, 3, 2, 18, 6, 1, 24, 19, 11, 6, 22, 9, 20, 3, 15, 23, 14, 18, 11, 11, 0, 2, 14, 21, 1, 12, 8, 8, 22, 10, 25, 20, 15, 22, 15, 21, 4, 19, 23, 5, 20, 4, 10, 17, 9, 7, 8, 11, 7, 10, 2, 18, 5, 24, 4, 16, 22, 13, 0, 11, 6, 19, 8, 21, 23, 24, 14, 19, 6, 3, 1, 17, 25, 22, 9, 14, 12, 15, 2, 24, 23, 17, 3, 3, 3, 6, 11, 20, 11, 0, 12, 17, 0, 3, 12, 24, 5, 13, 11, 19, 5, 2, 5, 12, 20, 19, 23, 2, 14, 23, 19, 4, 6, 15, 12, 2, 24, 17, 18, 9, 18, 4, 12, 20, 17, 19, 21, 16, 15, 13, 0, 17, 10, 23, 22, 10, 8, 20, 6, 4, 13, 11, 0, 3, 1, 5, 19, 17, 23, 17, 10, 10, 7, 4, 1, 20, 21, 23, 21, 21, 25, 2, 1, 8, 22, 4, 10, 16, 9, 15, 12, 12, 7, 3, 10, 14, 11, 9, 0, 7, 1, 1, 18, 23, 16, 6, 4, 20, 17, 18, 20, 17, 22, 8, 19, 6, 8, 14, 23, 14, 14, 15, 3, 24, 19, 16, 18, 14, 3, 6, 10, 8, 22, 12, 6, 8, 5, 3, 20, 10, 15, 19, 17, 8, 10, 7, 22, 0, 5, 19, 18, 16, 22, 24, 6, 18, 19, 19, 21, 1, 22, 14, 0, 24, 1, 20, 21, 7, 2, 11, 13, 10, 9, 7, 13, 15, 22, 2, 17, 4, 1, 4, 22, 22, 7, 18, 3, 12, 12, 7, 6, 20, 15, 25, 8, 13, 7, 5, 1, 25, 12, 1, 25, 16, 3, 23, 25, 9, 22, 4, 11, 16, 21, 20, 15, 17, 16, 13, 14, 20, 5, 23, 9, 0, 6, 3, 21, 2, 7, 2, 22, 7, 5, 8, 17, 14, 17, 8, 18, 21, 22, 14, 8, 15, 2, 10, 24, 0, 10, 23, 11, 16, 22, 5, 5, 19, 20, 14, 2, 19, 3, 25, 5, 10, 14, 22, 3, 5, 10, 20, 22, 16, 17, 22, 15, 23, 10, 0, 21, 17, 20, 3, 15, 0, 13, 17, 2, 10, 20, 8, 24, 5, 6, 19, 9, 4, 25, 11, 19, 10, 3, 24, 0, 10, 10, 9, 21, 16, 25, 6, 20, 11, 7, 17, 20, 10, 9, 22, 19, 21, 7, 0, 4, 11, 1, 9, 18, 18, 3, 1, 25, 5, 1, 20, 13, 2, 7, 19, 10, 13, 25, 3, 23, 13, 5, 10, 15, 11, 15, 22, 9, 10, 8, 18, 0}
	assert(reversePairs(a) == 124430)
	assert(reversePairs([]int{}) == 0)
	assert(reversePairs([]int{1}) == 0)
	assert(reversePairs([]int{1, 1}) == 0)
	assert(reversePairs([]int{2, 1}) == 0)
	assert(reversePairs([]int{3, 1}) == 1)
	assert(reversePairs([]int{1, 3, 1}) == 1)
	assert(reversePairs([]int{2, 3, 1}) == 1)
	assert(reversePairs([]int{3, 3, 1}) == 2)
	assert(reversePairs([]int{6, 3, 1}) == 2)
	assert(reversePairs([]int{7, 3, 1}) == 3)
	assert(reversePairs([]int{1, 3, 2, 3, 1}) == 2)
	assert(reversePairs([]int{2, 4, 3, 5, 1}) == 3)
	assert(reversePairs([]int{3, 1, 4, 1, 5, 9, 2, 6, 5, 3}) == 6)
}

func assert(x bool) {
	if !x {
		panic("assertion failed")
	}
}

//Description
//Hints
//Submissions
//Discuss
//Solution
//
//General principles behind problems similar to "Reverse Pairs"
//General principles behind problems similar to "Reverse Pairs"
//47.2K
//VIEWS
//936
//Last Edit: October 26, 2018 3:00 PM
//
//fun4LeetCode
//fun4LeetCode
// 15545
//
// It looks like a host of solutions are out there (BST-based, BIT-based, Merge-sort-based).
// Here I'd like to focus on the general principles behind these solutions and its possible
// application to a number of similar problems.
//
// The fundamental idea is very simple: break down the array and solve for the subproblems.
//
// A breakdown of an array naturally reminds us of subarrays. To smoothen our following discussion,
// let's assume the input array is nums, with a total of n elements. Let nums[i, j] denote the
// subarray starting from index i to index j (both inclusive), T(i, j) as the same problem applied
// to this subarray (for example, for Reverse Pairs, T(i, j) will represent the total number of
// important reverse pairs for subarray nums[i, j]).
//
// With the definition above, it's straightforward to identify our original problem as T(0, n - 1).
// Now the key point is how to construct solutions to the original problem from its subproblems.
// This is essentially equivalent to building recurrence relations for T(i, j). Since if we can
// find solutions to T(i, j) from its subproblems, we surely can build solutions to larger subarrays
// until eventually the whole array is spanned.
//
// While there may be many ways for establishing recurrence relations for T(i, j), here I will only
// introduce the following two common ones:
//
// T(i, j) = T(i, j - 1) + C, i.e., elements will be processed sequentially and C denotes the
// subproblem for processing the last element of subarray nums[i, j]. We will call this sequential
// recurrence relation.
//
// T(i, j) = T(i, m) + T(m + 1, j) + C where m = (i+j)/2, i.e., subarray nums[i, j] will be further
// partitioned into two parts and C denotes the subproblem for combining the two parts.
// We will call this partition recurrence relation.
//
// For either case, the nature of the subproblem C will depend on the problem under consideration,
// and it will determine the overall time complexity of the original problem. So usually it's
// crucial to find efficient algorithm for solving this subproblem in order to have better time
// performance. Also pay attention to possibilities of overlapping subproblems,
// in which case a dynamic programming (DP) approach would be preferred.
//
// Next, I will apply these two recurrence relations to this problem "Reverse Pairs" and list some
// solutions for your reference.
//
// I -- Sequential recurrence relation
//
// Again we assume the input array is nums with n elements and T(i, j) denotes the total number of
// important reverse pairs for subarray nums[i, j]. For sequential recurrence relation,
// we can set i = 0, i.e., the subarray always starts from the beginning. Therefore we end up with:
//
// T(0, j) = T(0, j - 1) + C
//
// where the subproblem C now becomes "find the number of important reverse pairs with the first
// element of the pair coming from subarray nums[0, j - 1] while the second element of the pair
// being nums[j]".
//
// Note that for a pair (p, q) to be an important reverse pair, it has to satisfy the following two
// conditions:
//
// p < q: the first element must come before the second element;
// nums[p] > 2 * nums[q]: the first element has to be greater than twice of the second element.
//
// For subproblem C, the first condition is met automatically; so we only need to consider the
// second condition, which is equivalent to searching for all elements within subarray
// nums[0, j - 1] that are greater than twice of nums[j].
//
// The straightforward way of searching would be a linear scan of the subarray, which runs at the
// order of O(j). From the sequential recurrence relation, this leads to the naive O(n^2) solution.
//
// To improve the searching efficiency, a key observation is that the order of elements in the
// subarray does not matter, since we are only interested in the total number of important
// reverse pairs. This suggests we may sort those elements and do a binary search instead of a plain
// linear scan.
//
// If the searching space (formed by elements over which the search will be done) is "static"
// (it does not vary from run to run), placing the elements into an array would be perfect for us to
// do the binary search. However, this is not the case here. After the j-th element is processed,
// we need to add it to the searching space so that it becomes searchable for later elements,
// which renders the searching space expanding as more and more elements are processed.
//
// Therefore we'd like to strike a balance between searching and insertion operations. This is where
// data structures like binary search tree (BST) or binary indexed tree (BIT) prevail, which offers
// relatively fast performance for both operations.
//
// 1. BST-based solution
//
// we will define the tree node as follows, where val is the node value and cnt is the total number
// of elements in the subtree rooted at current node that are greater than or equal to val:
//
// class Node {
//    int val, cnt;
//    Node left, right;
//
//    Node(int val) {
//        this.val = val;
//        this.cnt = 1;
//    }
// }
//
// The searching and insertion operations can be done as follows:
//
// private int search(Node root, long val) {
//    if (root == null) {
//    	return 0;
//    } else if (val == root.val) {
//    	return root.cnt;
//    } else if (val < root.val) {
//    	return root.cnt + search(root.left, val);
//    } else {
//    	return search(root.right, val);
//    }
// }
//
// private Node insert(Node root, int val) {
//    if (root == null) {
//        root = new Node(val);
//    } else if (val == root.val) {
//        root.cnt++;
//    } else if (val < root.val) {
//        root.left = insert(root.left, val);
//    } else {
//        root.cnt++;
//        root.right = insert(root.right, val);
//    }
//
//    return root;
// }
//
// And finally the main program, in which we will search for all elements no less than twice of
// current element plus 1 (converted to long type to avoid overflow) while insert the element itself
// into the BST.
//
// Note: this homemade BST is not self-balanced and the time complexity can go as bad as O(n^2)
// (in fact you will get TLE if you copy and paste the solution here). To guarantee O(nlogn)
// performance, use one of the self-balanced BST's (e.g. Red-black tree, AVL tree, etc.).
//
// public int reversePairs(int[] nums) {
//    int res = 0;
//    Node root = null;
//
//    for (int ele : nums) {
//        res += search(root, 2L * ele + 1);
//        root = insert(root, ele);
//    }
//
//    return res;
// }
//
// 2. BIT-based solution
//
// For BIT, the searching and insertion operations are:
//
// private int search(int[] bit, int i) {
//    int sum = 0;
//
//    while (i < bit.length) {
//        sum += bit[i];
//        i += i & -i;
//    }
//
//    return sum;
// }
//
// private void insert(int[] bit, int i) {
//    while (i > 0) {
//        bit[i] += 1;
//        i -= i & -i;
//    }
// }
//
// And the main program, where again we will search for all elements greater than twice of current
// element while insert the element itself into the BIT. For each element, the "index" function will
// return its index in the BIT. Unlike the BST-based solution, this is guaranteed to run at
// O(nlogn).
//
// public int reversePairs(int[] nums) {
//    int res = 0;
//    int[] copy = Arrays.copyOf(nums, nums.length);
//    int[] bit = new int[copy.length + 1];
//
//    Arrays.sort(copy);
//
//    for (int ele : nums) {
//        res += search(bit, index(copy, 2L * ele + 1));
//        insert(bit, index(copy, ele));
//    }
//
//    return res;
// }
//
// private int index(int[] arr, long val) {
//    int l = 0, r = arr.length - 1, m = 0;
//
//    while (l <= r) {
//    	m = l + ((r - l) >> 1);
//
//    	if (arr[m] >= val) {
//    	    r = m - 1;
//    	} else {
//    	    l = m + 1;
//    	}
//    }
//
//    return l + 1;
// }
//
// More explanation for the BIT-based solution:
//
// We want the elements to be sorted so there is a sorted version of the input array which is copy.
//
// The bit is built upon this sorted array. Its length is one greater than that of the copy array to
// account for the root.
//
// Initially the bit is empty and we start doing a sequential scan of the input array. For each
// element being scanned, we first search the bit to find all elements greater than twice of it and
// add the result to res. We then insert the element itself into the bit for future search.
//
// Note that conventionally searching of the bit involves traversing towards the root from some
// index of the bit, which will yield a predefined running total of the copy array up to the
// corresponding index. For insertion, the traversing direction will be opposite and go from some
// index towards the end of the bit array.
//
// For each scanned element of the input array, its searching index will be given by the index of
// the first element in the copy array that is greater than twice of it (shifted up by 1 to account
// for the root), while its insertion index will be the index of the first element in the copy array
// that is no less than itself (again shifted up by 1). This is what the index function is for.
//
// For our case, the running total is simply the number of elements encountered during the traversal
// process. If we stick to the convention above, the running total will be the number of elements
// smaller than the one at the given index, since the copy array is sorted in ascending order.
// However, we'd actually like to find the number of elements greater than some value (i.e., twice
// of the element being scanned), therefore we need to flip the convention. This is what you see
// inside the search and insert functions: the former traversing towards the end of the bit while
// the latter towards the root.
//
// II -- Partition recurrence relation
//
// For partition recurrence relation, setting i = 0, j = n - 1, m = (n-1)/2, we have:
//
// T(0, n - 1) = T(0, m) + T(m + 1, n - 1) + C
//
// where the subproblem C now reads "find the number of important reverse pairs with the first
// element of the pair coming from the left subarray nums[0, m] while the second element of the pair
// coming from the right subarray nums[m + 1, n - 1]".
//
// Again for this subproblem, the first of the two aforementioned conditions is met automatically.
// As for the second condition, we have as usual this plain linear scan algorithm, applied for each
// element in the left (or right) subarray. This, to no surprise,
// leads to the O(n^2) naive solution.
//
// Fortunately the observation holds true here that the order of elements in the left or right
// subarray does not matter, which prompts sorting of elements in both subarrays. With both
// subarrays sorted, the number of important reverse pairs can be found in linear time by employing
// the so-called two-pointer technique: one pointing to elements in the left subarray while the
// other to those in the right subarray and both pointers will go only in one direction due to the
// ordering of the elements.
//
// The last question is which algorithm is best here to sort the subarrays. Since we need to
// partition the array into halves anyway, it is most natural to adapt it into a Merge-sort.
// Another point in favor of Merge-sort is that the searching process above can be embedded
// seamlessly into its merging stage.
//
// So here is the Merge-sort-based solution, where the function "reversePairsSub" will return the
// total number of important reverse pairs within subarray nums[l, r]. The two-pointer searching
// process is represented by the nested while loop involving variable p, while the rest is the
// standard merging algorithm.
//
// public int reversePairs(int[] nums) {
//    return reversePairsSub(nums, 0, nums.length - 1);
// }
//
// private int reversePairsSub(int[] nums, int l, int r) {
//    if (l >= r) return 0;
//
//    int m = l + ((r - l) >> 1);
//    int res = reversePairsSub(nums, l, m) + reversePairsSub(nums, m + 1, r);
//
//    int i = l, j = m + 1, k = 0, p = m + 1;
//    int[] merge = new int[r - l + 1];
//
//    while (i <= m) {
//        while (p <= r && nums[i] > 2 L * nums[p]) p++;
//        res += p - (m + 1);
//
//        while (j <= r && nums[i] >= nums[j]) merge[k++] = nums[j++];
//        merge[k++] = nums[i++];
//    }
//
//    while (j <= r) merge[k++] = nums[j++];
//
//    System.arraycopy(merge, 0, nums, l, merge.length);
//
//    return res;
// }
// III -- Summary
//
// Many problems involving arrays can be solved by breaking down the problem into subproblems
// applied on subarrays and then link the solution to the original problem with those of the
// subproblems, to which we have sequential recurrence relation and partition recurrence relation.
// For either case, it's crucial to identify the subproblem C and find efficient algorithm for
// approaching it.
//
// If the subproblem C involves searching on "dynamic searching space", try to consider data
// structures that support relatively fast operations on both searching and updating
// (such as self-balanced BST, BIT, Segment tree, ...).
//
// If the subproblem C of partition recurrence relation involves sorting, Merge-sort would be a nice
// sorting algorithm to use. Also, the code could be made more elegant if the solution to the
// subproblem can be embedded into the merging process.
//
// If there are overlapping among the subproblems T(i, j), it's preferable to cache the intermediate
// results for future lookup.
//
// Lastly let me name a few leetcode problems that fall into the patterns described above and thus
// can be solved with similar ideas.
//
// 315. Count of Smaller Numbers After Self
// 327. Count of Range Sum
//
// For leetcode 315, applying the sequential recurrence relation (with j fixed),
// the subproblem C reads: find the number of elements out of visited ones that are smaller than
// current element, which involves searching on "dynamic searching space"; applying the partition
// recurrence relation, we have a subproblem C: for each element in the left half, find the number
// of elements in the right half that are smaller than it, which can be embedded into the merging
// process by noting that these elements are exactly those swapped to its left during the merging
// process.
//
// For leetcode 327, applying the sequential recurrence relation (with j fixed) on the pre-sum
// array, the subproblem C reads: find the number of elements out of visited ones that are within
// the given range, which again involves searching on "dynamic searching space"; applying the
// partition recurrence relation, we have a subproblem C: for each element in the left half,
// find the number of elements in the right half that are within the given range, which can be
// embedded into the merging process using the two-pointer technique.
//
// Anyway, hope these ideas can sharpen your skills for solving array-related problems.
//
//
//Comments: 60
//
//Type comment here... (Markdown is supported)
//mr_freeze 151  August 15, 2018 12:27 PM
//If you're in the bay area, let me buy you a beer
//
//90ShareReply
//z_pomegranate 44  February 21, 2017 11:56 PM
//Great solution with BIT!
//
//Let me add more explanations for why the BIT approach is
//"i += i&(-i)" for search, and
//"i -= i &(-i)" for insert.
//which is contrary to the "commonly" used way for BIT, where
//"i += (-i)" for insert, and
//"i -= i&(-i)" for search
//
//First, the concept of "search(i)" here should be explained as "getSum(i)", which is to get the accumulative frequency from the starting index to i (inclusively), where i is an index in BIT.
//
//In this problem, we want to get how many elements that are greater than 2 * nums[j], (num[j] is the current value that we are visiting). Therefore, instead of "searching down", here we need to "searching up".
//
//Based on the classical BIT format, where:
//"i += (-i)" for insert
//"i -= i&(-i)" for search
//
//One possible way is to use the getRange approach,
//so we can use getSum(MaxNumberOfValue) - getSum(index(2*nums[j])) to get the number of elements that are greater than 2 * nums[j].
//
//Another possible way is just like @fun4LeetCode implements,
//we can reverse the direction for insert and search in BIT
//so what we get is always the number greater than query values by using single search method.
//
//Read More
//44ShareReply
//SHOW 4 REPLIES
//merc14 46  September 1, 2018 9:49 AM
//Thank you....You need to create your own paid site and provide insights for all kind of algorithms
//
//16ShareReply
//sylarcp 15  June 21, 2018 10:01 AM
//I would read this every morning.
//
//15ShareReply
//Self_Learner 1820  October 22, 2018 5:57 AM
//Nice summary, I spend a lot of time to understand the BIT solutions, partially because of the
// reverse treatment of searching and updating, and I found by iterate the original array from the
// end, we can avoid such reverse meaning...
//
//class Solution {
//
//    public int reversePairs(int[] nums) {
//        if (nums == null || nums.length <= 1) return 0;
//        int n = nums.length;
//        int[] BIT = new int[n + 1];
//        int[] copy = nums.clone();
//        Arrays.sort(copy);
//
//        int res = 0;
//
//        for (int i = n - 1; i >= 0; i--) {
//            //find the first position in the array, where copy[index] >= num / 2, then any rand before index will contribute to the result
//            int num = nums[i];
//            res += search(BIT, index(copy, 1.0 * num / 2));
//            insert(BIT, index(copy, num));
//        }
//        return res;
//    }
//
//    private int search(int[] BIT, int i) {
//        int cnt = 0;
//        while (i > 0) {
//            cnt += BIT[i];
//            i -= (i & -i);
//        }
//        return cnt;
//    }
//
//    private void insert(int[] BIT, int i){
//        i = i + 1;
//        while (i < BIT.length) {
//            BIT[i]++;
//            i += (i & -i);
//        }
//    }
//
//    //first position that has num >= val
//    private int index(int[] arr, double val) {
//        int lo = 0, hi = arr.length;
//        while (lo < hi) {
//            int mid = lo + (hi - lo) / 2;
//            if (arr[mid] >= val) hi = mid;
//            else lo = mid + 1;
//        }
//        return lo;
//    }
//}
//Read More
//6ShareReply
//SHOW 2 REPLIES
//han_xuan 196  March 19, 2017 10:06 AM
//Share my AVL Tree Implementation:
//This is a somewhat long solution, but a good practice to use an AVL Tree, LOL...
//Ref:
//(1) https://discuss.leetcode.com/topic/83451/share-my-avl-tree-solution-o-nlgn-time
//(2) http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
//
//public class Solution {
//
//public int reversePairs(int[] nums) {
//
//    // Algo thinking: building a BST, go left when node.val <= 2 * root.val, right otherwise
//    // But need to keep it balanced -> AVL Tree or Red-Black Tree
//    // time = O(NlgN), space = O(N)
//
//    if (nums == null || nums.length == 0) return 0;
//
//    int n = nums.length;
//
//    TreeNode root = new TreeNode(nums[0]);
//    int ans = 0;
//    for (int i = 1; i < nums.length; i++) {
//        ans += search(root, (long) nums[i] * 2);
//        root = insert(root, (long) nums[i]);
//        // preOrder(root);
//        // System.out.println();
//    }
//
//    return ans;
//
//}
//
//private int search(TreeNode root, long key) {
//
//    if (root == null) return 0;
//
//    if (key < root.val) {       // key < root.val:  go left
//        return root.rightCount + search(root.left, key);
//    } else {                    // key >= root.val: go right
//        return search(root.right, key);
//    }
//}
//
//private TreeNode insert(TreeNode root, long key) {
//
//    if (root == null) return new TreeNode(key);
//
//    if (key < root.val) {   // key < root.val:  go left
//        root.left = insert(root.left, key);
//    } else if (key == root.val){
//        root.rightCount++;
//        return root;
//    } else {
//        root.rightCount++;
//        root.right = insert(root.right, key);
//    }
//
//    root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
//
//    int balance = getBalance(root);
//
//    // System.out.println(root.val + " balance " + balance);
//
//    // case 1 left left
//    if (balance > 1 && getHeight(root.left.left) > getHeight(root.left.right)) {
//        return rightRotate(root);
//    }
//
//    // case 2 left right
//    if (balance > 1 && getHeight(root.left.left) < getHeight(root.left.right)) {
//        root.left = leftRotate(root.left);
//        return  rightRotate(root);
//    }
//
//    // case 3 right right
//    if (balance < -1 && getHeight(root.right.left) < getHeight(root.right.right)) {
//        return leftRotate(root);
//    }
//
//    // case 4 right left
//    if (balance < -1 && getHeight(root.right.left) > getHeight(root.right.right)) {
//        root.right = rightRotate(root.right);
//        return leftRotate(root);
//    }
//
//    return root;
//}
//
//private TreeNode leftRotate(TreeNode root) {
//
//    // setp 1: take care of nodes
//    TreeNode newRoot = root.right;
//    TreeNode b = newRoot.left;
//
//    newRoot.left = root;
//    root.right = b;
//
//    // step 2: take care of height
//    root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
//    newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;
//
//    // step 3: take care of rightCount
//    root.rightCount -= getRightCount(newRoot);
//
//    return newRoot;
//}
//
//private TreeNode rightRotate(TreeNode root) {
//
//    // setp 1: take care of nodes
//    TreeNode newRoot = root.left;
//    TreeNode b = newRoot.right;
//
//    newRoot.right = root;
//    root.left = b;
//
//    // step 2: take care of height
//    root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
//    newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;
//
//    // step 3: take care of rightCount
//    newRoot.rightCount += getRightCount(root);
//
//    return newRoot;
//}
//
//
//private int getHeight(TreeNode node) {
//    return node == null ? 0 : node.height;
//}
//
//private int getBalance(TreeNode node) {
//    return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
//}
//
//private int getRightCount(TreeNode node) {
//    return node == null ? 0 : node.rightCount;
//}
//
//private void preOrder(TreeNode root) {
//
//    if (root == null) {
//        System.out.print("NIL ");
//        return;
//    }
//
//    System.out.print(root.val + " ");
//    preOrder(root.left);
//    preOrder(root.right);
//}
//
//class TreeNode {
//
//    long val;
//    int rightCount;
//    int height;
//    TreeNode left;
//    TreeNode right;
//    public TreeNode(long val) {
//        this.val = val;
//        height = 1;
//        rightCount = 1;
//    }
//}
//}
//
//Read More
//6ShareReply
//SHOW 2 REPLIES
//dionwang 30  July 27, 2018 1:38 PM
//This is the best article I have ever read from Leetcode!
//
//5ShareReply
//iaming 1342  February 26, 2017 12:18 PM
//great idea of using BIT, but it is sooo confusing
//
//5ShareReply
//SHOW 2 REPLIES
//krishnacs 32  February 16, 2017 12:33 PM
//@fun4LeetCode I really appreciate your effort presenting a good encyclopedia !! This really of great help. Though I solved it using BST, BIT approach is really encouraging
//
//3ShareReply
//jienan2 681  August 23, 2018 9:58 AM
//God of leetcode
//
//2ShareReply
//Enter topic title...
//If you want to include code in your post,
//please surround your code block with 3 backticks ```
//
//For example:
//```
//  def helloWorld():
//  	pass
//  ```
