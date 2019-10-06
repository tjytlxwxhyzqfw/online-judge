/**
 * 731 My Calendar II 46.2% Medium 429/66
 * Performance: speed=21%, memory=25%
 */

/*
list all [start, end) to the axis: a, b, c, d, e, f, g, ...
we traverse from left to right,
when we encounter a start point, our degree(# of overlapped intvs) increases by 1
when we encounter a end point, our degree decreases by 1

above is my idea but i have difficuties to impl it because i have trounbles when start of end locates at same point.

IMOS-METHOD(累积和法)

you get start ? delta[start]++
you get end ? delta[end]++
degree ? sum from start to end.

my god i was so close to IMOS
*/

import java.util.*;

public class MyCalendarTwo {
	TreeMap<Integer, Integer> map;

	public MyCalendarTwo() { map = new TreeMap<>(); }
	public boolean book(int start, int end) {
		Integer numStart = map.get(start);
		map.put(start, 1 + (numStart == null ? (numStart = 0) : numStart));
		Integer numEnd = map.get(end);
		map.put(end, -1 + (numEnd == null ? (numEnd = 0) : numEnd));

		int sum = 0;
		for (Integer i : map.values()) {
			if ((sum += i) >= 3) {
				map.put(start, numStart);
				map.put(end, numEnd);
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {};

}
