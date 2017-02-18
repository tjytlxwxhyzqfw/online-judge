import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n = int(read())
	a = read(int)
	a = [x-1 for x in a]

	nsingles = 0
	ndoubles = 0
	for i, x in enumerate(a):
		if a[x] == i and a[i] == x:
			if i == x:
				nsingles += 1
			else:
				ndoubles += 1
	print nsingles+ndoubles/2

def solve2():
	n = int(read())
	a = read(int)
	a = [x-1 for x in a]

	nsingles = 0
	for i, x in enumerate(a):
		if x == i:
			nsingles += 1

	#print "nsingles: %d"%nsingles
	ndoubles = len(set(a)) - nsingles
	print nsingles+ndoubles/2

if __name__ == "__main__":
	solve2()
