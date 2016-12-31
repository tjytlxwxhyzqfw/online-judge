import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n = int(read())

	total = 0
	for i in xrange(n):
		dst, drc = read().split()
		dst = int(dst)

		if drc == "East" or drc == "West":
			if total == 0 or total == 20000:
				print "NO"
				return

		if drc == "South":
			total += dst
			if total > 20000:
				print "NO"
				return

		if drc == "North":
			total -= dst
			if total < 0:
				print "NO"
				return
	if total != 0:
		print "NO"
		return

	print "YES"

if __name__ == "__main__":
	solve()
