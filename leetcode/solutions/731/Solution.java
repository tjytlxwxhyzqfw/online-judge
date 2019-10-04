/**
 * 
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class MyCalendarTwo {
}

class Intv {
	int begin, end;

	Intv(int begin ,int end) {
		this.begin = begin;
		this.end = end;
	}

	boolean overlap(int begin, int end) {
		return !(end <= this.begin || begin >= this.end);
	}
}
