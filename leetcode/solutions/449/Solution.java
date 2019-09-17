/**
 * 449 Serialize and Deserialize BST
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Codec {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		List<Integer> values = new ArrayList<>(), parents = new ArrayList<>();
		List<Boolean> posotions = new ArrayList<>();
		writeToList(root, -1, values, parents);

		byte[] bytes = new byte[12 * (values.size()+parents.size())];
		for (int i = 0; i < values.size(); ++i) addInt(bytes, i<<2, values.get(i));
		for (int i = 0; i < parents.size(); ++i) addInt(bytes, (values.size()+i)<<2, values.get(i));
		return new String(bytes);
	}

	void addInt(byte[] bytes, int from, int val) {
		bytes[0] = (byte)((val & 0xff000000) >>> 24);
		bytes[1] = (byte)((val & 0x00ff0000) >>> 16);
		bytes[2] = (byte)((val & 0x0000ff00) >>> 8);
		bytes[3] = (byte)((val & 0x000000ff));
	}

	int parseInt(String data, int from) {
		int x = ((int)data.charAt(from)) << 24;
		x |= ((int)data.charAt(from+1)) << 16;
		x |= ((int)data.charAt(from+2)) << 8;
		x |= ((int)data.charAt(from+3));
		return x;
	}

	void writeToList(TreeNode root,
			int parent,
			List<Integer> values,
			List<Integer> parents) {
		if (root == null) return;
		int id = values.size();
		{ values.add(root.val); parents.add(parent); }
		writeToList(root.left, id, values, parents);
		writeToList(root.right, -id, values, parents);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		Map<Integer, TreeNode> map = new HashMap<>();
		int i = 0;
		for (; i * 2 < data.length(); i += 4) map.put(i/4, new TreeNode(parseInt(data, i)));
		for (; i < data.length(); i += 4) {
			int id = (i - data.length() / 2) / 4;
			int p = parseInt(data, i);
			if (p < 0) map.get(Math.abs(p)).right = map.get(id);
			else map.get(Math.abs(p)).left = map.get(id);
		}
		return map.get(0);
	}

	public static void main(String[] args) {
	}
}
