/**
 * 399 Evaluate Division
 * Performance: speed=89%, memory=76%
 *
 * todo: floyed, dfs, bfs, union-find
 */

import java.util.*;

public class Solution {
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		Map<String, List<Node>> g = new HashMap<>();

		int i = 0;
		for (List<String> uv : equations) {
			String u = uv.get(0), v = uv.get(1);
			if (g.get(u) == null) g.put(u, new ArrayList<>());
			if (g.get(v) == null) g.put(v, new ArrayList<>());
			g.get(u).add(new Node(v, values[i]));
			if (values[i] != 0) g.get(v).add(new Node(u, 1 / values[i]));
			++i;
		}

		
		double[] anses = new double[queries.size()];
		i = 0;
		for (List<String> uv : queries) {
			String u = uv.get(0), v = uv.get(1);
			if (g.containsKey(u) && g.containsKey(v)) {
				Double ans = search(u, v, new HashSet<String>(), 1., g);
				anses[i++] = (ans == null ? -1 : ans);
			} else anses[i++] = -1.;
		}

		return anses;
	}

	Double search(String from, String to, Set<String> visited, Double product, Map<String, List<Node>> g) {
		System.out.printf("from=%s, to=%s, p=%g\n", from, to, product);
		if (from.equals(to)) return product;
		visited.add(from);
		List<Node> neighbors = g.get(from);
		if (neighbors == null) return null;
		for (Node n : neighbors) {
			if (visited.contains(n.v)) continue;
			Double ans = search(n.v, to, visited, product * n.w, g);
			if (ans != null) return ans;
		}
		return null;
	}

	public static void main(String args[]) {
	}
}

class Node {
	String v;
	Double w;
	Node(String v, Double w) {
		this.v = v;
		this.w = w;
	}
}
