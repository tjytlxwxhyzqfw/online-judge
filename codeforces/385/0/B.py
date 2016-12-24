import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n, m = read(int)

	nxs = []
	for i in range(n):
		ln = read()
		ln = [1 if x == 'X' else 0 for x in ln]
		nxs.append(sum(ln))
	#print nxs

	nxs = sum(nxs)
	if nxs == 0:
		print "NO"
		return

	for i in range(1, 501):
		if nxs % i != 0:
			continue
		j = nxs / i
		#print "i: %3d, j: %3d"%(i, j)
		if j <= n and i <= m:
			print "YES"
			return

	print "NO"
	
if __name__ == "__main__":
	solve()
