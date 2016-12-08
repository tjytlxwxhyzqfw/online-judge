import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def swap(tp, lsm, rsm):
	left, rght = tp
	lsm = lsm - left + rght
	rsm = rsm - rght + left
	return abs(lsm-rsm)

def solve():
	n = int(read())
	a = []
	for i in xrange(n):
		left, rght = read(int)
		a.append((left, rght))

	leftsum = sum([x[0] for x in a])
	rghtsum = sum([x[1] for x in a])

	ans = abs(leftsum - rghtsum)
	#print "ans: %d"%ans
	ansi = -1

	for i in xrange(n):
		tmp = swap(a[i], leftsum, rghtsum)
		#print "tmp: %d, i: %d"%(tmp, i)
		if tmp > ans:
			ans = tmp
			ansi = i

	print ansi+1

if __name__ == "__main__":
	solve()
