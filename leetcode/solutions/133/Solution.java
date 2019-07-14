/**
 * 133 - Clone Graph
 *
 * 广度优先搜索.
 *
 * 对于每个原始节点, 关注两个状态
 * 1. 没有排过队
 * 2. 正在排队或者已经拍完了
 * 
 * BFS的过程中, 每次循环都会从队列中删除一个元素,
 * 但是只有(1)状态的元素会被放入队列, 这就保证了每个元素只会入队/出队一次.
 *
 * 当元素从嘟列中删除的时候了, 我们复制这个元素.
 * 方法是这样的:
 * 1. 把已经复制好的元素存放在map中, 并按照label索引;
 * 2. 复制元素时, 对照着刚刚出队的元素, 如果有个邻居在map中, 直接从map中引用之, 否则, 重新分配, 并放入map
 *
 * 基于以下两个事实, 我们保证, 每个元素只会被构造一次:
 * 1. 每个元素被构造后, 都会被立即放入map中
 * 2. 每个元素构造前, 都会检查map, 如果map中有此元素, 则不构造
 *
 * 注意对BFS的理解:
 * 有一个不断缩小的队列, 为每个队列中的元素提供一个服务,
 * 并且, 通常只有从来没有入队过的元素才能入队.
 * 
 * 在本题中, "服务"就是"clone这个节点.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class Solution {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null)
			return null;

		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		Map<Integer, UndirectedGraphNode> map = new TreeMap<>();
		queue.add(node);
		UndirectedGraphNode handle = new UndirectedGraphNode(node.label);
		map.put(node.label, handle);
		
		while (!queue.isEmpty()) {
			UndirectedGraphNode current = queue.remove();
			UndirectedGraphNode clone = map.get(current.label);
			for (UndirectedGraphNode neighbor : current.neighbors) {
				Integer label = neighbor.label;
				if (map.containsKey(label)) {
					clone.neighbors.add(map.get(label));
				} else {
					UndirectedGraphNode voidneighbor = new UndirectedGraphNode(label);
					map.put(label, voidneighbor);
					clone.neighbors.add(voidneighbor);
					queue.add(neighbor);
				}
			}
		}

		return handle;
	}

	public static void main(String args[]) {
	}
}

class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;

	public UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<>();
	}
}
