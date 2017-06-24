import re
import sys
import time

import math

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n, m = read(int)
	if n >= m:
		delta = 1 + 8*(n-m)
		top = 1.0 + math.sqrt(delta)
		k = math.ceil(top/2.0)
		ans = int(k) + m - 1
		print ans

		#print "---> delta: %d"%delta
		#print "---> math.sqrt(delta): %f"%math.sqrt(delta)
		#print "---> top: %f"%top
		#print "---> k: %d"%k
		#print "---> int(k+m): %d"%(int(k+m))
		#print "---> int(k)+m: %d"%(int(k)+m)

	if n < m:
		print n
		

if __name__ == "__main__":
	solve()
