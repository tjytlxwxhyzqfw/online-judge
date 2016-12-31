import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n, k = read(int)

	left = 240 - k
	#print "left: %d"%left

	s, t = 0, 5
	while s <= left:
		s += t
		t += 5
		#print "s: %d, t: %s"%(s, t)
	
	if t <= 5:
		t = 10

	print min(n, t/5 - 2)

if __name__ == "__main__":
	solve()
