import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def getn(x, n):
	return "".join([x for i in range(n)])

def solve():
	n, k, a, b = read(int)

	if a > b:
		S = 'B'
		B = 'G'
	else:
		S = 'G'
		B = 'B'

	small = min(a, b)
	big = max(a, b)

	delta = big - small
	nheaps = small+1

	avg = big / nheaps
	avg_max = avg
	overflow = 0

	if big % nheaps != 0:
		overflow = big - avg * nheaps
		avg_max += 1

	#print "avg: %d"%avg
	#print "avg_max: %d"%avg_max
	#print "overflow: %d"%overflow

	if avg_max <= k:
		#print "YES"
		ans = ""
		for i in range(overflow):
			ans += getn(B, avg_max)
			ans += S
		for i in range(overflow, small):
			ans += getn(B, avg)
			ans += S
		ans += getn(B, avg)
		print ans
	else:
		print "NO"

if __name__ == "__main__":
	solve()
