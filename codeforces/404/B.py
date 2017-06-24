import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

top = 2000000000

def solve():
	n = int(read())
	maxbgn1, minend1 = -1, top
	for i in xrange(n):
		bgn, end = read(int)
		maxbgn1 = max(bgn, maxbgn1) 
		minend1 = min(end, minend1)

	m = int(read())
	maxbgn2, minend2 = -1, top
	for i in xrange(m):
		bgn, end = read(int)
		maxbgn2 = max(bgn, maxbgn2)
		minend2 = min(end, minend2)

	x = max(0, maxbgn2-minend1)
	y = max(0, maxbgn1-minend2)

	print max(x, y)
		

if __name__ == "__main__":
	solve()
