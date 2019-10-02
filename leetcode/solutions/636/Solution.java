/**
 * 636 Exclusive Time of Functions 49.5% Medium 509/836
 * Performance: speed=14%, memory=100%
 */

/*
description of this problem is not clear enough:
(1) the function calls is always match a stack case (which is not classified)
(2) logs are (or might have been) sorted by time
*/

import java.util.*;

public class Solution {
	public int[] exclusiveTime(int n, List<String> list) {
		if (n == 0 && list.size() == 0) return new int[n];

		Log[] logs = new Log[list.size()];
		for (int i = 0; i < logs.length; ++i) {
			String[] tuple = list.get(i).split(":");
			logs[i] = new Log(Byte.parseByte(tuple[0]), tuple[1].equals("start"), Integer.parseInt(tuple[2]));
		}

		// according some discuss solution, the sort is unnecessary ?
		// https://leetcode.com/problems/exclusive-time-of-functions/discuss/105062/Java-Stack-Solution-O(n)-Time-O(n)-Space
		Arrays.sort(logs); 
		
		
		Stack<Byte> stack = new Stack<>();

		int[] a = new int[n];
		int startTime = 0;
		for (int i = 0; i < logs.length; ++i) {
			if (logs[i].start) {
				if (!stack.isEmpty()) a[stack.peek()] += logs[i].time - startTime;
				{ startTime = logs[i].time; stack.push(logs[i].id); }
			} else {
				{ a[stack.peek()] += logs[i].time - startTime; ++a[logs[i].id]; stack.pop(); }
				startTime = logs[i].time+1;
			}
		}

		return a;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		// s.exclusiveTime(2, Arrays.asList("0:start:0","1:start:2","1:end:5","0:end:6"));
		// s.exclusiveTime(2, Arrays.asList("0:start:0","1:start:2","1:end:5","0:end:6", "0:start:3", "0:end:4"));
		// s.exclusiveTime(8, Arrays.asList("6:start:14","7:start:15","1:start:24","1:end:29","7:end:34","6:end:37"));
		s.exclusiveTime(8, Arrays.asList("0:start:0","1:start:5","2:start:6","3:start:9","4:start:11","5:start:12","6:start:14","7:start:15","1:start:24","1:end:29","7:end:34","6:end:37","5:end:39","4:end:40","3:end:45","0:start:49","0:end:54","5:start:55","5:end:59","4:start:63","4:end:66","2:start:69","2:end:70","2:start:74","6:start:78","0:start:79","0:end:80","6:end:85","1:start:89","1:end:93","2:end:96","2:end:100","1:end:102","2:start:105","2:end:109","0:end:114"));
                System.out.println("done");
	}
}

class Log implements Comparable<Log> {
	byte id;
	boolean start;
	int time;

	Log(byte id, boolean start, int time) {
		this.id = id;
		this.start = start;
		this.time = time;
	}

	@Override
	public int compareTo(Log that) {
		return this.time - that.time;
	}
}

