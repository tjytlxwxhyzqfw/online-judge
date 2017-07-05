/**
 * 134 - Gas Station
 *
 * 不应该想这么久的!!!
 * 刚开始的想法是, 职能一个一个试试.
 *
 * 其实不是这样的.
 * 我这样跑:
 * 1. 从加油站(i)开始向前跑;
 * 2. 设加油站(j)是第一个为我减油的加油站, (gas[j]小于cost[j])
 * 3. 如果我从(i)跑到(k)就跑不下去了, 那么说明从(i)到(k-1)之间, 任何一个加油站都泡不到(k)吗?
 * 4. 是的!!! 任取 (i 小于 x 小于 k)
 *	4.1 直接从x开始跑: 初始状态是gas[x]升油
 * 	4.2 从i开始跑, 跑到x, 然后再从x跑, 状态是: 从开始, 初始状态有gas[x]+剩余油量(比为正)
 *	已知4.2都跑不完, 那么(4.1)肯定跑不完!!
 * 5. 于是, 我们从k开始跑, 重复上述过程
 * 6. 什么时候停止呢?
 * 	6.1 如果我们发现跑完一圈了, OK
 *	6.1 我们从0开始跑, 把每个不可能开始的位置都记录下来(很好记)
 *	如果我们发现,所有位置都可能开始, 那就GG
 */

public class Solution {
	private int gas[];
	private int cost[];
	private int length;

	public int canCompleteCircuit(int gas[], int cost[]) {
		if (gas==null || cost==null)
			return -1;

		this.gas = gas;
		this.cost = cost;
		this.length = gas.length;

		int begin = 0;
		while (begin < length) {
			int stop = runForrestRun(begin);
			if (stop == -1)
				return begin;
			if (stop < begin)
				return -1;
			begin = stop+1;
		}

		return -1;
	}

	private int runForrestRun(int begin) {
		int i = (begin+1)%length;
		int rest = gas[begin]-cost[begin];
		if (rest < 0)
			return begin;

		for (; i != begin; i=(i+1)%length) {
			rest += gas[i]-cost[i];
			if (rest < 0)
				break;
		}

		return (rest < 0 ? i : -1);
	}

	public static void main(String args[]) {
		int gas[] = {10, 10};
		int cost[] = {15, 6};
		int ans = new Solution().canCompleteCircuit(gas, cost);
		System.out.printf("ans=%d\n", ans);
	}
}
